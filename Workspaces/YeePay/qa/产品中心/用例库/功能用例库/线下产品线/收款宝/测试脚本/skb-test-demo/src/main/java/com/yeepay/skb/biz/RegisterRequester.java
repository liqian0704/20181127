package com.yeepay.skb.biz;

import com.yeepay.skb.util.AESUtil;
import com.yeepay.skb.util.Conts;
import com.yeepay.skb.util.Digest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 子商户注册
 * @author sailfish
 * @create 2017-08-24-上午10:20
 */
public class RegisterRequester {

    public static void main(String[] args) {
    	for(int i=0;i<1;i++){
	        String filePath = "/Users/yp-tc-m-2851/Documents/workspace/skb-test-demo/src/main/resources/resource/2.png";
	        String pdfPath = "/Users/yp-tc-m-2851/Documents/workspace/skb-test-demo/src/main/resources/resource/register.pdf";
	        File file = new File(filePath);
	        File elecAgreement = new File(pdfPath);
	
	        PostMethod postMethod = new PostMethod(Conts.baseRequestUrl + "/register.action");
	
	        HttpClient client = new HttpClient();
	
	        try {
	            String MainCustomerNumber = Conts.maincustomerNumber; // 代理商编码
	            String key = Conts.hmacKey; // 商户秘钥
	            String MailStr = "";// 商户邮箱
	            String LoginPassword = "";// 登陆密码，加密后签名
	            String LoginPasswordConfirm = "";// 登陆密码确认
	            String TradePassword = "";// 交易密码，加密后签名
	            String TradePasswordConfirm = "";// 交易密码确认
	            String requestid = UUID.randomUUID().toString().substring(0, 15);  //注册请求号，每次请求唯一
	            //String requestid = "1eab1126-9916-4";
	            String customertype = "PERSON";// 企业-ENTERPRISE,个体工商户-INDIVIDUAL
	            // 个人-PERSON
	            String businesslicence = "";
	            String bindmobile = "13522666106";
	            String signedname ="谢烽滨";
	            String linkman = "谢烽滨";
	            String idcard = "441522199210293577";//身份证
	            String legalperson = "谢烽滨";// 法人
	            String minsettleamount = "1";
	            String riskreserveday = "0";
	            String bankaccountnumber = "6217992900040937976";//银行卡号
	            String bankname = "邮储银行";
	            String bankaccounttype = "";// 非必填，不参与签名
	            String accountname = "谢烽滨";
	            String areaCode = "";
	            String splitter = "";// splitter，分润方商编，以英文逗号“,"分隔
	            String splitterprofitfee = "";// splitterprofitfee，每个分润比例的值域都是(0,1]；所有比例之和是(0,1]
	            String whiteList = "1";
	            String freezeDays = "19";
	            String certFee = "";
	            String manualSettle = "Y";
	            String loginPasswordEncrypted = AESUtil.encrypt(LoginPassword, key);
	            String tradPasswordEncrypted = AESUtil.encrypt(TradePassword, key);
	            String auditStatus = "success";
	            //String reason = "";
	
	            StringBuffer signature = new StringBuffer();
	            signature
	                    .append(MainCustomerNumber == null ? ""
	                            : MainCustomerNumber)
	                    .append(loginPasswordEncrypted == null ? ""
	                            : loginPasswordEncrypted)
	                    .append(tradPasswordEncrypted == null ? ""
	                            : tradPasswordEncrypted)
	                    .append(requestid == null ? "" : requestid)
	                    .append(customertype == null ? "" : customertype)
	                    .append(businesslicence == null ? "" : businesslicence)
	                    .append(bindmobile == null ? "" : bindmobile)
	                    .append(signedname == null ? "" : signedname)
	                    .append(linkman == null ? "" : linkman)
	                    .append(idcard == null ? "" : idcard)
	                    .append(legalperson == null ? "" : legalperson)
	                    .append(minsettleamount == null ? "" : minsettleamount)
	                    .append(riskreserveday == null ? "" : riskreserveday)
	                    .append(bankaccountnumber == null ? "" : bankaccountnumber)
	                    .append(bankname == null ? "" : bankname)
	                    .append(accountname == null ? "" : accountname)
	                    .append(areaCode == null ? "" : areaCode)
	                    .append(splitter == null ? "" : splitter)
	                    .append(splitterprofitfee == null ? "" : splitterprofitfee)
	                    .append(whiteList == null ? "" : whiteList)
	                    .append(freezeDays == null ? "" : freezeDays)
	                    .append(certFee == null ? "" : certFee)
	                    .append(manualSettle == null ? "" : manualSettle);
	
	            System.out.println("source===" + signature.toString());
	            String hmac = Digest.hmacSign(signature.toString(), key);
	            System.out.println("hmac====" + hmac);
	
	            Part[] parts = new RegisterPartsBuilder()
	                    .setMainCustomerNumber(MainCustomerNumber)
	                    .setMailStr(MailStr)
	                    .setLoginPassword(loginPasswordEncrypted)
	                    .setLoginPasswordConfirm(loginPasswordEncrypted)
	                    .setTradePassword(tradPasswordEncrypted)
	                    .setTradePasswordConfirm(tradPasswordEncrypted)
	                    .setRequestId(requestid).setCustomerType(customertype)
	                    .setBusinessLicence(businesslicence)
	                    .setBindMobile(bindmobile).setSignedName(signedname)
	                    .setLinkMan(linkman).setIdCard(idcard)
	                    .setLegalPerson(legalperson)
	                    .setMinSettleAmount(minsettleamount)
	                    .setRiskReserveDay(riskreserveday)
	                    .setBankAccountNumber(bankaccountnumber)
	                    .setBankName(bankname).setBankaccounttype(bankaccounttype)
	                    .setAccountName(accountname).setAreaCode(areaCode)
	                    .setBankCardPhoto(file).setBusinessLicensePhoto(file)
	                    .setIdCardBackPhoto(file).setIdCardPhoto(file)
	                    .setPersonPhoto(file)
	                    .setAuditStatus(auditStatus)
	                    .setElectronicAgreement(elecAgreement)
	                    .setHmac(hmac).setSplitter(splitter)
	                    .setSplitterprofitfee(splitterprofitfee)
	                    .setWhiteList(whiteList).setFreezeDays(freezeDays)
	                    .setCertFee(certFee).setManualSettle(manualSettle)
	                    .generateParams();
	
	            postMethod.setRequestEntity(new MultipartRequestEntity(parts,
	                    postMethod.getParams()));
	            System.out.println(postMethod.toString());

	            int status = client.executeMethod(postMethod);
	            if (status == HttpStatus.SC_OK) {
	                System.out.println(postMethod.getResponseBodyAsString());
	            } else if (status == HttpStatus.SC_MOVED_PERMANENTLY
	                    || status == HttpStatus.SC_MOVED_TEMPORARILY) {
	                // 从头中取出转向的地址
	                Header locationHeader = postMethod
	                        .getResponseHeader("location");
	                String location = null;
	                if (locationHeader != null) {
	                    location = locationHeader.getValue();
	                    System.out
	                            .println("The page was redirected to:" + location);
	                } else {
	                    System.err.println("Location field value is null.");
	                }
	            } else {
	                System.out.println("fail======" + status);
	            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放连接
            postMethod.releaseConnection();
        }
    	}
    }
}

class RegisterPartsBuilder {

    private List<Part> parts = new ArrayList<Part>(26);

    public Part[] generateParams() {
        return parts.toArray(new Part[parts.size()]);
    }

    /**
     * @param mainCustomerNumber
     *            the mainCustomerNumber to set
     */
    public RegisterPartsBuilder setMainCustomerNumber(String mainCustomerNumber) {
        this.parts.add(new StringPart("mainCustomerNumber",
                mainCustomerNumber == null ? "" : mainCustomerNumber, "UTF-8"));
        return this;
    }

    /**
     * @param bindMobile
     *            the bindMobile to set
     */
    public RegisterPartsBuilder setBindMobile(String bindMobile) {
        this.parts.add(new StringPart("bindMobile", bindMobile == null ? ""
                : bindMobile, "UTF-8"));
        return this;
    }

    /**
     * @param signedName
     *            the signedName to set
     */
    public RegisterPartsBuilder setSignedName(String signedName) {
        this.parts.add(new StringPart("signedName", signedName == null ? ""
                : signedName, "UTF-8"));
        return this;
    }

    /**
     * @param linkMan
     *            the linkMan to set
     */
    public RegisterPartsBuilder setLinkMan(String linkMan) {
        this.parts.add(new StringPart("linkMan",
                linkMan == null ? "" : linkMan, "UTF-8"));
        return this;
    }

    /**
     * @param idCard
     *            the idCard to set
     */
    public RegisterPartsBuilder setIdCard(String idCard) {
        this.parts.add(new StringPart("idCard", idCard == null ? "" : idCard,
                "UTF-8"));
        return this;
    }

    /**
     * @param legalPerson
     *            the legalPerson to set
     */
    public RegisterPartsBuilder setLegalPerson(String legalPerson) {
        this.parts.add(new StringPart("legalPerson", legalPerson == null ? ""
                : legalPerson, "UTF-8"));
        return this;
    }

    /**
     * @param minSettleAmount
     *            the minSettleAmount to set
     */
    public RegisterPartsBuilder setMinSettleAmount(String minSettleAmount) {
        this.parts.add(new StringPart("minSettleAmount",
                minSettleAmount == null ? "" : minSettleAmount, "UTF-8"));
        return this;
    }

    /**
     * @param riskReserveDay
     *            the riskReserveDay to set
     */
    public RegisterPartsBuilder setRiskReserveDay(String riskReserveDay) {
        this.parts.add(new StringPart("riskReserveDay",
                riskReserveDay == null ? "" : riskReserveDay, "UTF-8"));
        return this;
    }

    /**
     * @param bankAccountNumber
     *            the bankAccountNumber to set
     */
    public RegisterPartsBuilder setBankAccountNumber(String bankAccountNumber) {
        this.parts.add(new StringPart("bankAccountNumber",
                bankAccountNumber == null ? "" : bankAccountNumber, "UTF-8"));
        return this;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public RegisterPartsBuilder setBankName(String bankName) {

        this.parts.add(new StringPart("bankName", bankName == null ? ""
                : bankName, "UTF-8"));
        return this;
    }

    /**
     * @param accountName
     *            the cacountName to set
     */
    public RegisterPartsBuilder setAccountName(String accountName) {
        this.parts.add(new StringPart("accountName", accountName == null ? ""
                : accountName, "UTF-8"));
        return this;
    }

    /**
     * @param requestId
     *            the requestId to set
     */
    public RegisterPartsBuilder setRequestId(String requestId) {
        this.parts.add(new StringPart("requestId", requestId == null ? ""
                : requestId, "UTF-8"));
        return this;
    }

    /**
     * @param customerType
     *            the customerType to set
     */
    public RegisterPartsBuilder setCustomerType(String customerType) {
        this.parts.add(new StringPart("customerType", customerType == null ? ""
                : customerType, "UTF-8"));
        return this;
    }

    /**
     * @param hmac
     *            the hmac to set
     */
    public RegisterPartsBuilder setHmac(String hmac) {
        this.parts
                .add(new StringPart("hmac", hmac == null ? "" : hmac, "UTF-8"));
        return this;
    }

    /**
     * @param areaCode
     *            the areaCode to set
     */
    public RegisterPartsBuilder setAreaCode(String areaCode) {
        this.parts.add(new StringPart("areaCode", areaCode == null ? ""
                : areaCode, "UTF-8"));
        return this;
    }

    /**
     * setter
     *
     * @param mailstr
     */
    public RegisterPartsBuilder setMailStr(String mailstr) {
        this.parts.add(new StringPart("mailstr",
                mailstr == null ? "" : mailstr, "UTF-8"));
        return this;
    }

    /**
     * setter
     *
     * @param loginpassword
     */
    public RegisterPartsBuilder setLoginPassword(String loginpassword) {
        this.parts.add(new StringPart("loginPassword",
                loginpassword == null ? "" : loginpassword, "UTF-8"));
        return this;
    }

    /**
     * setter
     *
     * @param tradepassword
     */
    public RegisterPartsBuilder setTradePassword(String tradepassword) {
        this.parts.add(new StringPart("tradePassword",
                tradepassword == null ? "" : tradepassword, "UTF-8"));
        return this;
    }

    public RegisterPartsBuilder setBankaccounttype(String bankaccounttype) {
        this.parts.add(new StringPart("bankAccountType",
                bankaccounttype == null ? "" : bankaccounttype, "UTF-8"));
        return this;
    }

    /**
     * @param businessLicence
     *            the businessLicence to set
     */
    public RegisterPartsBuilder setBusinessLicence(String businessLicence) {
        this.parts.add(new StringPart("businessLicence",
                businessLicence == null ? "" : businessLicence, "UTF-8"));
        return this;
    }

    /**
     * @param loginPasswordConfirm
     *            the loginPasswordConfirm to set
     */
    public RegisterPartsBuilder setLoginPasswordConfirm(
            String loginPasswordConfirm) {
        this.parts.add(new StringPart("loginPasswordConfirm",
                loginPasswordConfirm == null ? "" : loginPasswordConfirm,
                "UTF-8"));
        return this;
    }

    /**
     * @param tradePasswordConfirm
     *            the tradePasswordConfirm to set
     */
    public RegisterPartsBuilder setTradePasswordConfirm(
            String tradePasswordConfirm) {
        this.parts.add(new StringPart("tradePasswordConfirm",
                tradePasswordConfirm == null ? "" : tradePasswordConfirm,
                "UTF-8"));
        return this;
    }

    /**
     * 分润方
     *
     * @param splitter
     *            the splitter to set
     */
    public RegisterPartsBuilder setSplitter(String splitter) {
        this.parts.add(new StringPart("splitter", splitter == null ? ""
                : splitter, "UTF-8"));
        return this;
    }

    /** 白名单 */
    public RegisterPartsBuilder setWhiteList(String whiteList) {
        this.parts.add(new StringPart("whiteList", whiteList == null ? ""
                : whiteList, "UTF-8"));
        return this;
    }

    /***/
    public RegisterPartsBuilder setFreezeDays(String freezeDays) {
        this.parts.add(new StringPart("freezeDays", freezeDays == null ? ""
                : freezeDays, "UTF-8"));
        return this;
    }

    /**
     * 分润比率
     *
     * @param splitterprofitfee
     *            the splitterprofitfee to set
     */
    public RegisterPartsBuilder setSplitterprofitfee(String splitterprofitfee) {
        this.parts.add(new StringPart("splitterprofitfee",
                splitterprofitfee == null ? "" : splitterprofitfee, "UTF-8"));
        return this;
    }

    // [end] jun.lin 2015-03-30 这里是普通入参

    // [start] jun.lin 2015-03-20 这里是文件入参

    private void configFilePart(File f, FilePart fp) {
        String fileName = f.getName();
        fp.setContentType("image/"
                + fileName.substring(fileName.lastIndexOf('.') + 1));
        fp.setCharSet("UTF-8");

        System.out.println(fp.getContentType());
    }

    private void configPdfFilePart(File f, FilePart fp) {
        String fileName = f.getName();
        fp.setContentType("application/"
                + fileName.substring(fileName.lastIndexOf('.') + 1));
        fp.setCharSet("UTF-8");

        System.out.println(fp.getContentType());
    }

    /**
     * @param idCardPhoto
     *            the idCardPhoto to set
     * @throws FileNotFoundException
     */
    public RegisterPartsBuilder setIdCardPhoto(File idCardPhoto)
            throws FileNotFoundException {
        FilePart fp = new FilePart("idCardPhoto", idCardPhoto);
        configFilePart(idCardPhoto, fp);
        this.parts.add(fp);

        return this;
    }

    // 目前非必传
    public RegisterPartsBuilder setIdCardBackPhoto(File idCardBackPhoto)
            throws FileNotFoundException {
        FilePart fp = new FilePart("idCardBackPhoto", idCardBackPhoto);
        configFilePart(idCardBackPhoto, fp);
        this.parts.add(fp);

        return this;
    }

    /**
     * @param bankCardPhoto
     *            the bankCardPhoto to set
     * @throws FileNotFoundException
     */
    public RegisterPartsBuilder setBankCardPhoto(File bankCardPhoto)
            throws FileNotFoundException {
        FilePart fp = new FilePart("bankCardPhoto", bankCardPhoto);
        configFilePart(bankCardPhoto, fp);
        this.parts.add(fp);
        return this;
    }

    /**
     * @param personPhoto
     *            the personPhoto to set
     * @throws FileNotFoundException
     */
    public RegisterPartsBuilder setPersonPhoto(File personPhoto)
            throws FileNotFoundException {
        FilePart fp = new FilePart("personPhoto", personPhoto);
        configFilePart(personPhoto, fp);
        this.parts.add(fp);
        return this;
    }

    /**
     * @param businessLicensePhoto
     *            the businessLicensePhoto to set
     * @throws FileNotFoundException
     */
    public RegisterPartsBuilder setBusinessLicensePhoto(
            File businessLicensePhoto) throws FileNotFoundException {
        FilePart fp = new FilePart("businessLicensePhoto", businessLicensePhoto);
        configFilePart(businessLicensePhoto, fp);
        this.parts.add(fp);
        return this;
    }

    public RegisterPartsBuilder setCertFee(String certFee) {
        this.parts.add(new StringPart("certFee",
                certFee == null ? "" : certFee, "UTF-8"));
        return this;
    }

    public RegisterPartsBuilder setManualSettle(String manualSettle) {
        this.parts.add(new StringPart("manualSettle", manualSettle == null ? ""
                : manualSettle, "UTF-8"));
        return this;
    }

    public RegisterPartsBuilder setElectronicAgreement(File electronicAgreement)
            throws FileNotFoundException {
        FilePart fp = new FilePart("electronicAgreement", electronicAgreement);
        configPdfFilePart(electronicAgreement, fp);
        this.parts.add(fp);
        return this;
    }
    
    public RegisterPartsBuilder setAuditStatus(String auditStatus) {
        this.parts.add(new StringPart("auditStatus", auditStatus == null ? ""
                : auditStatus, "UTF-8"));
        return this;
    }
    
    public RegisterPartsBuilder setReason(String reason) {
        this.parts.add(new StringPart("reason", reason == null ? ""
                : reason, "UTF-8"));
        return this;
    }
}
