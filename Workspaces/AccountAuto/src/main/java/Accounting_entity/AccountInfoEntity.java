package Accounting_entity;

import com.miitang.facade.account.enumtype.AccountTypeEnum;
import org.jfaster.mango.annotation.Getter;
import org.jfaster.mango.annotation.Setter;
import org.jfaster.mango.invoker.function.enums.EnumToStringFunction;
import org.jfaster.mango.invoker.function.enums.StringToEnumFunction;

import java.util.Date;

/**
 * 类名称: AccountInfoEntity <br>
 * 类描述: 账户信息<br>
 *
 * @author: hua.jin
 * @since: 2018/1/8 下午4:18
 * @version: 1.0.0
 */
public class AccountInfoEntity {
    /** 流水号 **/
    private String uniqueAccountNo;

    /** 版本号 **/
    private Integer optimistic;

    /** 业务方号 **/
    private String bizSystemNo;

    /** 商户商编（MT） **/
    private String customerNo;

    /** 商户名称 **/
    private String customerName;

    /** 账户类型 **/
    private AccountTypeEnum accountType;

    /** 支付公司编码 **/
    private String payCompany;

    /** 支付公司商编 **/
    private String payCompanyCustomerNo;

    /** 秘钥 **/
    private String hmac;

    /** 私钥 **/
    private String privateKey;

    private Date createDate;

    private Date updateDate;

    private String errorCode;

    private String errorMessage;

    public String getUniqueAccountNo() {
        return uniqueAccountNo;
    }

    public void setUniqueAccountNo(String uniqueAccountNo) {
        this.uniqueAccountNo = uniqueAccountNo == null ? null : uniqueAccountNo.trim();
    }

    public Integer getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Integer optimistic) {
        this.optimistic = optimistic;
    }

    public String getBizSystemNo() {
        return bizSystemNo;
    }

    public void setBizSystemNo(String bizSystemNo) {
        this.bizSystemNo = bizSystemNo == null ? null : bizSystemNo.trim();
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Getter(EnumToStringFunction.class)
    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    @Setter(StringToEnumFunction.class)
    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany == null ? null : payCompany.trim();
    }

    public String getPayCompanyCustomerNo() {
        return payCompanyCustomerNo;
    }

    public void setPayCompanyCustomerNo(String payCompanyCustomerNo) {
        this.payCompanyCustomerNo = payCompanyCustomerNo == null ? null : payCompanyCustomerNo.trim();
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac == null ? null : hmac.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}