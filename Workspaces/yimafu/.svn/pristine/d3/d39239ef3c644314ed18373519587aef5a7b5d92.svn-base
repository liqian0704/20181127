### 易码付统一配置 生产
YMF_OpenIdUrl                           https://api.weixin.qq.com/sns/oauth2/access_token?appid=AppID&secret=SECRET&grant_type=authorization_code&code=CODE
YMF_BaseAuth2Url                        https://open.weixin.qq.com/connect/oauth2/authorize?appid=AppID&redirect_uri=Auth2CallUrl&response_type=code&scope=snsapi_base&state=customerNumber_SIGN#wechat_redirect
YMF_PAY_HOST                            https://yimafu.yeepay.com/ymf-pay
YMF_AppID                               wx9e13bd68a8f1921e
YMF_WECHATSECRET                        09e3f360c6b25033e738a01c721b9a4c
YMF_NC_API_ACCESS_CODE                  17
YMF_NC_API_HMAC                         1oC3L9516894J0jX2k5X7Uh505G9ER
YMF_COD_HESSIAN_URL                     http://172.31.250.3/cod/codPOSPIndInvoker.remote
YMF_NC_API_MODE                         PRODUCT
YMF_DOWNLOAD_PAGE_SIZE                  1000
YMF_HEART_BEAT_IP                       10.149.254.225

### dubbo降级配置
com.yeepay.g3.facade.ymf.facade         http://ymf.boss.3g:8080/ymf-hessian/hessian/



### 易码付物理环境配置
易码付运营
内侧物理IP：10.149.154.113:8080(内测)  F5VS：10.149.200.170:8080(内测：ymf.boss.test)  网络IP 106.120.186.92:80
生产物理IP：10.149.154.114~115:8080 F5VS：10.149.200.171:8080 (生产网络内部域名：ymf.boss.3g) 网络IP ： 106.120.186.94:80

易码付交易
内侧物理IP 10.149.154.116:8080(内测) F5VS：10.149.200.172:8080(内测)  网络IP ：106.120.186.94:28080
生产物理IP 10.149.154.117~118：8080 F5VS：10.149.200.173:8080 网络 IP 106.120.186.81:80

易码付项目网络IP
1、ymfboss内侧 106.120.186.94:8080 对应内部 VSF5 : 10.149.200.170:8080  允许办公网访问
2、ymfboss生产 106.120.186.94:80 对应内部 VSF5 : 10.149.200.171:8080    允许办公网访问
3、ymfpay内侧 106.120.186.94:28080 对应内部 VSF5 : 10.149.200.172:8080 允许办公网访问
4、ymfpay生产 106.120.186.81:80   对应内部 VSF5 : 10.149.200.173:8080  允许公网访问

易码付项目新增日志中心配置
日志中心新增日志组：ymfboss-test 对应的物理IP： 10.149.154.113:8080
日志中心新增日志组：ymfboss 10.149.154.114~115:8080  三代标准tomcat
日志中心新增日志组：ymfpay-test 10.149.154.116:8080(内测)
日志中心新增日志组：ymfpay 10.149.154.117~118:8080

办公网络域名解析：
1、yimafu.yeepay.com  对应公网IP: 106.120.186.81:80
2、ymfboss.yeepay.com 对应办公网络IP: 106.120.186.94:80 办公网络配置DNS解析


运营服务器
内侧物理IP：10.149.154.113:8080(内测)  F5VS：10.149.200.170:8080/443  网络IP 106.120.186.92:8080 支持https访问  办公网络
生产物理IP：10.149.154.114~115:8080 F5VS：10.149.200.171:8080/443 (生产网络内部域名：ymf.boss.3g) 网络IP :106.120.186.94:80 支持https访问 办公网络

易码付交易
内侧物理IP 10.149.154.116:8080(内测) F5VS：10.149.200.172:8080/443(内测)  网络IP ：106.120.186.93:28080  支持https访问 办公网络
生产物理IP 10.149.154.117~118：8080 F5VS：10.149.200.173:8080/443 网络 IP 106.120.186.81:80 支持https访问 公网


https://106.120.186.92/ymf-hessian/g2Servlet
https://106.120.186.94/ymf-hessian/g2Servlet
https://106.120.186.93/ymf-pay/index
https://106.120.186.81/ymf-pay/index




10.149.200.170:443==106.120.186.92:443
10.149.200.171:443==106.120.186.94:443
10.149.200.172:443==106.120.186.93:443
10.149.200.173:443==106.120.186.81:443

### 运营内测 修改本机hosts
106.120.186.92 ymfboss.yeepay.com
106.120.186.94:28080/ymf-pay/index