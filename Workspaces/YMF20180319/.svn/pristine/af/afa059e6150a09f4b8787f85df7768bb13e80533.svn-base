<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/main/taglibs.jsp"%>
<jsp:useBean id="customer" scope="request" type="com.yeepay.g3.core.ymf.entity.customer.Customer"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>网点信息详情</title>
    <script>
        function toShopManager(id){
            window.location="${pageContext.request.contextPath}/shop/query?customerNumber="+id;
        }
    </script>
</head>
<body>
    <div class="search_tit">
          <h2 class="fw">网点信息详情</h2>
     </div>
     <div class="clearer"></div>
    <div style="margin-left: 10px;margin-top: 20px;">
        <table border="1" style="width:98%">
            <tr>
                <td colspan="4">
                    <label class="text_tit" style="font-size: 16px;">基本信息</label>

                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >商户编号</label>
                </td>
                <td width="24%">
                   <label class="text_tit"> ${customer.customerNumber}</label>
                </td>
                <td width="24%">
                    <label class="text_tit" >商户名称</label>
                </td>
                <td width="24%">
                <label class="text_tit"> ${customer.customerName}</label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >网点名称</label>
                </td>
                <td width="24%">
                    <label class="text_tit">
                        ${shop.shopName}
                    </label>
                </td>
                <td width="24%">
                    <label class="text_tit" >所属省份</label>
                </td>
                <td width="24%">
                    <label class="text_tit"> ${shop.provinceName}</label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >所属城市</label>
                </td>
                <td width="24%">
                   <label class="text_tit"> ${shop.cityName}</label>
                </td>
                <td width="24%">
                    <label class="text_tit" >详细地址</label>
                </td>
                <td width="24%">
                    <label class="text_tit">
                        ${shop.address}
                    </label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >联系人姓名</label>
                </td>
                <td width="24%">
                   <label class="text_tit"> ${shop.linkMan}</label>
                </td>
                <td width="24%">
                    <label class="text_tit" >联系人电话</label>
                </td>
                <td width="24%">
                    <label class="text_tit">
                        ${shop.linkPhone}
                    </label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >创建时间</label>
                </td>
                <td width="24%">
                   <label class="text_tit">
                       <fmt:formatDate value="${shop.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                   </label>
                </td>
                <td width="24%">
                    <label class="text_tit" >状态</label>
                </td>
                <td width="24%">
                    <label class="text_tit">
                        <e:write type="com.yeepay.g3.facade.ymf.enumtype.common.ShopStatus"
                                 name="${shop.status}"></e:write>
                    </label>
                </td>
            </tr>
            <tr>
                <td width="24%">
                    <label class="text_tit" >创建人</label>
                </td>
                <td width="24%">
                    <label class="text_tit">
                        ${shop.createUser}
                    </label>
                </td>
            </tr>
        </table>

    </div>
    <br/>
    <input type="button"  class="btn_sure fw" id="btn"
           onclick="toShopManager('${customer.customerNumber}')" value="确定" />
</body>
</html>
