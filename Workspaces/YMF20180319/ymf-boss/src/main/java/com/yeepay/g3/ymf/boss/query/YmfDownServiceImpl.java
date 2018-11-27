package com.yeepay.g3.ymf.boss.query;

import com.yeepay.g3.core.ymf.constants.Constants;
import com.yeepay.g3.core.ymf.support.bean.DictionaryHolder;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.OgnlUtils;
import com.yeepay.g3.utils.common.StringUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.dal.DALSession;
import com.yeepay.g3.utils.query.Query;
import com.yeepay.g3.utils.query.QueryException;
import com.yeepay.g3.utils.query.QueryParam;
import com.yeepay.g3.utils.query.QueryResult;
import com.yeepay.g3.utils.query.impl.QueryServiceImpl;
import com.yeepay.g3.utils.query.jdbc.IJdbcQueryer;
import com.yeepay.g3.utils.query.jdbc.JdbcParamSetter;
import com.yeepay.g3.utils.query.jdbc.JdbcQueryResult;
import com.yeepay.g3.utils.query.jdbc.JdbcQueryerFactory;
import com.yeepay.g3.utils.query.parser.QueryParser;
import com.yeepay.g3.utils.query.parser.QueryRequest;
import com.yeepay.g3.utils.query.parser.SimpleSQLParser;
import com.yeepay.g3.ymf.boss.utils.*;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @author jiwei.lv
 * @create 2016-08-16/8/22
 */
public class YmfDownServiceImpl extends QueryServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(YmfDownServiceImpl.class);
    private Map<String, Query> querys;
    private Map<String, DataSource> dataSourceMap;
    private DataSource dataSource;
    private DALSession dalSession;
    private DictionaryHolder dictionaryHolder;
    private static final Map<String, List<String>> selectKeyWordMap = new HashMap();
    //查询结果总数
    public int count(DataSource ds,String sql,List sqlParams){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            JdbcParamSetter.setParams(ps, sqlParams);
            rs = ps.executeQuery();
            rs.last();
            int rowCount = rs.getRow();
            if(rowCount>0){
                return rowCount;
            }
        } catch (SQLException e) {
            handelSQLException(e);
        } finally {
            release(conn, ps, rs);
        }
        return 0;
    }
    //处理sql异常
    private void handelSQLException(SQLException e){
        //忽略由于参数长度导致的异常
        if(e.getErrorCode()==-302 && "22001".equals(e.getSQLState())){
            logger.warn("sql param invalid, SQLCODE="+e.getErrorCode()+" SQLSTAT="+e.getSQLState());
            return;
        }
        throw new QueryException("query jdbc exception : "+e.getMessage(), e);
    }
    //关闭资源
    private void release(Connection conn, Statement ps, ResultSet rs) {
        try{
            if (rs != null) {
                rs.close();
            }
        }catch(SQLException e){
        }
        try{
            if (ps != null) {
                ps.close();
            }
        }catch(SQLException e){
        }
        try{
            if (conn != null) {
                conn.close();
            }
        }catch(SQLException e){
        }
    }
    @Override
    public QueryResult query(String queryKey, QueryParam queryParam) {
        Map<String, Object> param = retrieve(queryParam.getParams());
        queryParam.setParams(param);
        String operator = (String) param.get("operator");
        DownloadQuery queryConfig = this._findQuery(queryKey);
        if(queryConfig == null) {
            throw new QueryException("no query defined with key : " + queryKey);
        } else {
            DataSource ds = this.findDataSource(queryConfig.getDataSource());
            QueryRequest queryRequest = this.createQueryRequest(queryConfig, param);
            if(queryRequest == null) {
                return null;
            } else {
                String orderBy = SimpleSQLParser.parseOrderBy(queryParam.getOrderByColumn(), queryParam.getIsAsc() != null?queryParam.getIsAsc().toString():null);
                String sql = null;
                if(!CheckUtils.isEmpty(orderBy)) {
                    sql = queryRequest.getSqlStatement().replaceOrderBy(orderBy).getSQL();
                } else {
                    sql = queryRequest.getSqlStatement().getSQL();
                }
                IJdbcQueryer queryer = JdbcQueryerFactory.createJdbcQueryer(queryParam.isForDal(), ds);
                boolean download = param.get("download") == null ? false : Boolean.valueOf(param.get("download").toString());
                if(download){//下载接口走
                    String downFileName=null;
                    try {
                        downFileName= dataDown(queryConfig, queryParam, ds, sql, queryRequest, operator);
                        logger.info("文件生成成功");
                    }catch (Exception e){
                        logger.error("文件创建失败!",e);
                        downFileName="error";
                    }
                    List<Map<String, String>> result = new ArrayList<Map<String, String>>();
                    Map<String, String> nameMap = new HashMap<String, String>();
                    nameMap.put("fileName",downFileName);
                    result.add(nameMap);
                    return new QueryResult(result, 0L);
                }else{//查询接口走
                    JdbcQueryResult jdbcResult = queryer.query(queryParam.getCurrentPage(), queryParam.getStartIndex(), queryParam.getMaxSize(), sql, queryRequest.getSqlParams(), queryParam.getCounter());
                    QueryResult result = new QueryResult(jdbcResult.getData(), jdbcResult.getTotalCount());
                    result.setQueryKey(queryKey);
                    result.setStartIndex(queryParam.getStartIndex());
                    result.setMaxFetchSize(queryParam.getMaxSize());
                    result.setOrderStr(queryParam.getOrderByColumn());
                    result.setIsAsc(queryParam.getIsAsc());
                    result.setCounter(jdbcResult.getCounter());
                    if(queryParam.getDoSum() != null && queryParam.getDoSum()) {
                        result.setSumData(this.doSum(queryKey, queryConfig, queryRequest, ds, queryParam.getParams(), queryParam.isForDal()));
                    }
                    return result;
                }
            }
        }
    }

    //EXCEL文件写入
    private String dataExcelDown(String filePath,QueryParam queryParam,
                                 IJdbcQueryer queryer,int page, String sql,QueryRequest queryRequest,
                                 String[] filedNames, String[] fileds, List<Format>  formatList,
                                 String operator){
            String msg=null;
            File file = new File(filePath);
            SimpleExcelWriter excel = null;
            String fileName = operator + "-"+Calendar.getInstance().getTimeInMillis()+ ".xlsx";
            excel = new SimpleExcelWriter(file, fileName, filedNames);
            try {
                for(int i=0;i<page;i++){
                    JdbcQueryResult jdbcRe = queryer.query(i, i*1000+1, 1000, sql, queryRequest.getSqlParams(), queryParam.getCounter());
                    List<Map<String,String>> resultlist=jdbcRe.getData();
                    int num=i*1000;
                    downExcel(resultlist, fileds, filedNames, formatList, excel, num);
                }
                msg= fileName;
                logger.info(msg);
                logger.info("EXCEL文件创建成功!");
            } catch (Exception e) {
                logger.error("Excel异常", e);
                msg="error";
            }finally {
                excel.finishWrite();
            }
        return msg;
    }
    //TXT文件写入
    private String dataTxtDown(String filePath,QueryParam queryParam,
                               IJdbcQueryer queryer,int page,String sql,QueryRequest queryRequest,
                               String[] filedNames, String[] fileds, List<Format>  formatList,int totalcount,
                               String operator
                             )throws IOException{
        String fileName = operator + "-"+Calendar.getInstance().getTimeInMillis()+ ".txt";
        String titleSplit="   ";
        String readStr="|";
        Map<String,String> titlemap=new HashMap<String, String>();
        for (int i=0; i<filedNames.length; i++) {
            titlemap.put(fileds[i],filedNames[i]);
        }
        File txtFile = null;
        try {
            txtFile= TxtUtil.writeTxtTitle(titlemap,filePath,"|",titleSplit,fileName);

            for(int i=0;i<page;i++){
                JdbcQueryResult jdbcRe = queryer.query(i, i*1000+1, 1000, sql, queryRequest.getSqlParams(), queryParam.getCounter());
                List<Map<String,String>> resultlist=jdbcRe.getData();
                int num=i*1000;
                TxtUtil.writeTxtFile(txtFile,resultlist,titlemap,formatList,readStr,num,totalcount);
            }
        } catch (Exception e) {
            logger.error("txt文件创建失败!",e);
            fileName="error";
        }
        return fileName;
    }
    //下载接口
    private String dataDown(DownloadQuery queryConfig, QueryParam queryParam,DataSource ds,String sql,QueryRequest queryRequest, String operator) throws Exception{
        IJdbcQueryer queryer = JdbcQueryerFactory.createJdbcQueryer(queryParam.isForDal(), ds);
        String msg=null;
        //获得总条数
        int totalcount=this.count(ds,sql,queryRequest.getSqlParams());
       String filePath = FileManageUtils.getFilePath();
        logger.info(filePath);

        int page = 1;
        if(totalcount>1000){
            if((totalcount%1000)!=0){
                page = (totalcount/1000)+1;
            }else{
                page = (totalcount/1000);
            }
        }

        //其他操作,不需要读取模板文件
        if("other".equals(queryParam.getParams().get("downloadType"))){
            msg= new QrCodeDownload().dataOtherDown(filePath,queryParam,queryer,page,sql,queryRequest, operator);
            if (StringUtils.isBlank(msg)) {
                return "error";
            }
            return msg;
        }
        //文件下载获取样式
        String filename = queryConfig.getTemplateName();
        List<Format> formatList = XmlReader.readFile(filename,getAbsolutePath());
        String[] fileds =  new String[formatList.size()];
        String[] filedNames =  new String[formatList.size()];
        for (int i = 0; i < formatList.size(); i++) {
            Format format  =  formatList.get(i);
            fileds[i] =format.getField();
            filedNames[i] =format.getFieldName();
        }
        //excel操作
        if("excel".equals(queryParam.getParams().get("downloadType"))){
            msg= dataExcelDown(filePath,queryParam,queryer,page,sql,queryRequest,filedNames,fileds,formatList, operator);

        }
        //txt操作
        if("txt".equals(queryParam.getParams().get("downloadType"))){
            msg= dataTxtDown(filePath,queryParam,queryer,page,sql,queryRequest,filedNames,fileds,formatList,totalcount, operator);

        }

        if(null!=msg && !"".equals(msg)){
            return msg;
        }else{
            return "error";
        }

    }
    //EXCEL写入内容
    private void downExcel(List<Map<String,String>> resultlist,String[] fileds,String[] filedNames,List<Format>  formatList,SimpleExcelWriter excel,int i ) throws Exception{
        String[]  s = new String[filedNames.length];
        int rowIndex = i;
            for (int j = 0; j < resultlist.size(); j++) {

                Map<String, String> map = resultlist.get(j);
                for (int k = 0; k < fileds.length; k++) {
                    if (formatList.get(k).getFieldType() == null) {
                        s[k] = map.get(fileds[k]) == null ? "" : String.valueOf(map.get(fileds[k]));
                    } else if (formatList.get(k).getFieldType().equals("date")) {
                        SimpleDateFormat format = new SimpleDateFormat(formatList.get(k).getFormat());
                            String strdate = String.valueOf(map.get(fileds[k]));
                            if (null != strdate && !"NULL".equals(strdate) && !"null".equals(strdate)) {
                                Date date = format.parse(strdate);
                                String resultdate = format.format(date);
                                s[k] = String.valueOf(resultdate);
                            }

                    } else if (formatList.get(k).getFieldType().equals("amount")) {
                        s[k] = AmountUtil.formatAmount(map.get(fileds[k]), formatList.get(k).getFormat());
                    }else if(formatList.get(k).getFieldType().equals("dict")){
                        s[k]= dictionaryHolder.getDictValue(String.valueOf(formatList.get(k).getFormat())+String.valueOf(map.get(fileds[k])));
                    }
                }
                excel.writeRow(rowIndex + 1, s);
                rowIndex++;

            }
    }
    private  String getAbsolutePath(){
        String path=this.getClass().getClassLoader().getResource("/").getPath();
        return path+"/downloadtemp";
    }

    private QueryRequest createQueryRequest(Query queryConfig, Map<String, Object> params) {
        if("false".equals(params.get("_queryable"))) {
            return null;
        } else {
            QueryRequest queryRequest = QueryParser.parse(queryConfig.getSql(), params);
            if(!queryConfig.isQueryWithoutParam() && !queryRequest.hasCondition()) {
                return null;
            } else {
                if(!CheckUtils.isEmpty(queryConfig.getQueryableExp())) {
                    HashMap _params = new HashMap();
                    Iterator queryable = params.entrySet().iterator();

                    while(queryable.hasNext()) {
                        Map.Entry item = (Map.Entry)queryable.next();
                        Object value = item.getValue();
                        if(!CheckUtils.isEmpty(value)) {
                            _params.put(item.getKey(), value);
                        }
                    }

                    Object queryable1 = OgnlUtils.executeExpression(queryConfig.getQueryableExp(), _params);
                    if(!Boolean.TRUE.equals(queryable1)) {
                        return null;
                    }
                }

                return queryRequest;
            }
        }
    }

    private DownloadQuery _findQuery(String key) {
        if(this.querys == null) {
            throw new QueryException("querys is empty !");
        } else {
            DownloadQuery query = (DownloadQuery) this.querys.get(key);
            if(query == null) {
                throw new QueryException("no query found : " + key);
            } else {
                return query;
            }
        }
    }

    private DataSource findDataSource(String dsName) {
        if(dsName == null) {
            if(this.dataSource == null) {
                throw new QueryException("no default dataSource found !");
            } else {
                return this.dataSource;
            }
        } else if(this.dataSourceMap != null && this.dataSourceMap.containsKey(dsName)) {
            return (DataSource)this.dataSourceMap.get(dsName);
        } else {
            throw new QueryException("no dataSource found : " + dsName);
        }
    }
    private Map doSum(String queryKey, Query queryConfig, QueryRequest queryRequest, DataSource ds, Map param, boolean isForDal) {
        if(CheckUtils.isEmpty(queryConfig.getSumSelect())) {
            throw new QueryException("no sum sql found ! queryKey[" + queryKey + "]");
        } else {
            String sumSelect = QueryParser.parseSelectOnly(queryConfig.getSumSelect(), param);
            IJdbcQueryer queryer = JdbcQueryerFactory.createJdbcQueryer(isForDal, ds);
            Map sumData = queryer.queryUniqueRow(queryRequest.getSqlStatement().retriveSumSql(sumSelect).getSQL(), queryRequest.getSqlParams(), true);
            return sumData;
        }
    }

    /**
     * 处理查询参数
     * @param param
     * @return
     */
    private Map<String, Object> retrieve(Map<String, Object> param) {
        String bankNo=String.valueOf(param.get("bankCardNo"));

        if(bankNo!=null && !"".equals(bankNo) && !"NULL".equals(bankNo) && !"null".equals(bankNo)){
            String bankcardNo=AES.encryptToBase64(bankNo,Constants.CARD_NO_AES_KEY);
            if(StringUtils.isNotEmpty(bankcardNo)){
                param.put("bankCardNo",bankcardNo);
            }
        }
        String batchCusOrderNo= String.valueOf(param.get("batchCusOrderNo"));
        if(batchCusOrderNo!=null&&!"".equals(batchCusOrderNo) && !"NULL".equals(batchCusOrderNo) && !"null".equals(batchCusOrderNo)){
            String customerOrderStrList = batchCusOrderNo.trim().replaceAll(" ","").replaceAll("\n",",").replaceAll("\t",",").replaceAll("\r",",").replace("，", ",");
            if("".equals(customerOrderStrList)){
                param.put("batchCusOrderNos", "");
                return param;
            }

            String[] numberArray = customerOrderStrList.split(",");
            if(numberArray.length == 0){
                param.put("batchCusOrderNos", "");
                return param;
            }

            List<String> list = new ArrayList<String>();
            for(int i=0;i<numberArray.length;i++){
                if(numberArray[i]!=null && !"".equals(numberArray[i].trim())){
                    list.add(numberArray[i].trim());
                }
            }
            if(list.size()>500){
                param.put("batchCusOrderNos", "");
                return param;
            }
            StringBuilder customerOrderNo = new StringBuilder();
            for(String str : list){
                customerOrderNo.append("'");
                customerOrderNo.append(str);
                customerOrderNo.append("',");
            }

            String snin = customerOrderNo.toString();
            param.put("batchCusOrderNos", snin.substring(0, snin.length()-1));
        }else{
            param.put("batchCusOrderNos", "");
        }

        return param;
    }

    private String stripOrderByString(String afterFrom) {
        String result = afterFrom;
        List orderKeyWords = (List)selectKeyWordMap.get("2");
        int i = this.getKeyWordIndex(afterFrom, orderKeyWords);
        if(i >= 0) {
            result = afterFrom.substring(0, i);
        }

        return result;
    }



    private int getKeyWordIndex(String sql, List<String> keyWords) {
        int i = -1;

        for(int index = 0; index < keyWords.size(); ++index) {
            String keyWord = (String)keyWords.get(index);
            i = sql.indexOf(keyWord);
            if(i > 0) {
                return i;
            }
        }

        return i;

    }
    public Map<String, Query> getQuerys() {
        return querys;
    }

    @Override
    public void setQuerys(Map<String, Query> querys) {
        this.querys = querys;
    }

    public Map<String, DataSource> getDataSourceMap() {
        return dataSourceMap;
    }

    @Override
    public void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public DALSession getDalSession() {
        return dalSession;
    }

    public void setDalSession(DALSession dalSession) {
        this.dalSession = dalSession;
    }

    public DictionaryHolder getDictionaryHolder() {
        return dictionaryHolder;
    }

    public void setDictionaryHolder(DictionaryHolder dictionaryHolder) {
        this.dictionaryHolder = dictionaryHolder;
    }
}
