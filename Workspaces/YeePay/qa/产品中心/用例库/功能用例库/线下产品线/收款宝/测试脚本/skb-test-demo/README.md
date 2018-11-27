
### 测试代理商信息
- 代理商商编：10001674445    
- 代理商秘钥：h8VV8k2337HgrHL9gBn8K2615KXa710vKd4IB27953nqq5PP0gnhVsQ0H900

### 依赖包
- fastjson
```$xslt
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.31</version>
</dependency>
```
- httpclient
```
<dependency>
    <groupId>commons-httpclient</groupId>
    <artifactId>commons-httpclient</artifactId>
    <version>3.1</version>
</dependency>
```

### 工具类
- 加密hmac
```
/**
 * aValue：要加密字符串
 * aKey: 商家的hmacKey
 */
Digest.hmacSign(String aValue, String aKey);
     
```

- 交易接口返回支付URL解密工具
```
/*
 * data:返回的url
 * key：商家的加密hmacKey
 */
AESUtil.decrypt(String data, String key);
```

### 对接注意事项
- 示例Demo中对应商家对接文档v0.18
- 示例中字段未赋值，需要商家在测试过程中按照对接文档填写必填字段
- 默认请求方式为HTTP/1.1 POST


### FAQ
**接口请求需要的hmacKey商户用的是同一个吗？**
- 同一个代理商（对接商家）下的子商户使用的hmacKey是同一个，该Key不应该暴露给商户

**收款接口的支付方式有哪些？**
- 目前只支持B：店主代付

**结算卡支持哪些银行？**
- 结算卡只支持借记卡，具体支持银行文档中有描述

**商户费率如何设置**
- 商户费率包含交易费率和结算费率，结算费率包含了T1自动结算和T0自助结算，T0自助结算分为基本费率和
额外费率，额外费率又分为工作日额外和非工作日额外，详细设置见文档，每次设置只支持一种，如果需要设置
多种费率，需要调用多次

**入网文件上传限制**
- 目前上传文件有两种格式，分别是图片和PDF文件，服务器端作了限制，在上传文件的时候需要制定
Content-Type为以下几种格式的其中一种:
```
application/pdf,
application/pdf; charset=UTF-8,
image/png,
image/jpeg,
image/jpg,
image/png; charset=UTF-8,
image/jpeg; charset=UTF-8,
image/jpg; charset=UTF-8
```

**入网流程是什么？**
- 子商户注册接口
- 子商户审核接口
- 费率设置接口：每次只支持设置一种费率，T0包含基本+额外，需要单独设置
    - 在交易时商家最好做一个最低限额，例如5元或者10元
- 设置完成后最好查询一次保证设置成功再进行交易流程
- 如果商家对商户有限额设置，在设置完成限额后再进行查询确认后才能确定商户是否可以进行后续交易流程

### 补充
- 如果正在对接过程中出现提示`代理商未开通收款宝`说明该代理商目前还没有开通收款宝权限
- 如果属于业务问题请咨询产品对接人或者是业务方
- 在测试阶段测试出款请求出现：商家可用出款余额不足，原因是因为出款的钱和交易的钱一致，没有考虑扣除手续费的问题，
- 请对接商家分清楚交易和结算，易宝支付在交易的时候只关注交易，不会关注商户是D0还是T1结算的，
  商家需要记录商户在交易时选择的结算方式，如果商户选择的是自动结算，商家是不需
  要调用接口结算的，如果商户选择的是D0自助结算并且在入网的时候开通了D0自助结算，商家需要调用
  结算接口进行相应的结算