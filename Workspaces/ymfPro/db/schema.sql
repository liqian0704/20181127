-- 用户：LIKER

CREATE TABLE YMF.TBL_USER (
  ID               VARCHAR(50) PRIMARY KEY                                          NOT NULL,
  MEMBER_NO        VARCHAR(50), --易宝会员号
  MERCHANT_NO      VARCHAR(50), --所属商户号
  AGENT_NO         VARCHAR(50), --所属代理商号
  PHONE_NO         VARCHAR(64)                                                      NOT NULL, --手机号
  USER_STATUS      VARCHAR(20)                                                      NOT NULL, --用户状态
  LOCATION         VARCHAR(128), --定位
  ROLE             VARCHAR(20), --角色
  LAST_VERSION_ID  VARCHAR(50), --本次登陆版本
  IMEI             VARCHAR(50), --手机标识
  LAST_LOGIN_TIME  TIMESTAMP, --本次登陆时间
  LAST_LOGOUT_TIME TIMESTAMP, --本次注销时间
  CREATE_TIME      TIMESTAMP DEFAULT CURRENT_TIMESTAMP                              NOT NULL
);
COMMENT ON TABLE YMF.TBL_USER IS '來客用戶表';
CREATE UNIQUE INDEX YMF.USER_PHONE_NO_IDX ON YMF.TBL_USER (PHONE_NO);
CREATE INDEX YMF.USER_MERCHANT_NO_IDX
  ON YMF.TBL_USER (MERCHANT_NO);
CREATE INDEX YMF.USER_CREATE_TIME_IDX ON YMF.TBL_USER (CREATE_TIME);
CREATE SEQUENCE YMF.SEQ_YMF_USER_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_USER TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_USER TO USER QUERY;

CREATE TABLE YMF.TBL_SMS_CODE (
  ID          BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  PHONE_NO    VARCHAR(64)                                                      NOT NULL, --手机号
  SMS_CODE    VARCHAR(16)                                                      NOT NULL, --验证码
  TYPE        VARCHAR(64)                                                      NOT NULL, --使用类型
  AVAILABLE   SMALLINT                                                         NOT NULL, --使用状态
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP                              NOT NULL,
  EFFECT_TIME TIMESTAMP                                                        NOT NULL --失效时间
);
COMMENT ON TABLE YMF.TBL_SMS_CODE IS '验证码表';
CREATE INDEX YMF.SMS_CODE_IDX ON YMF.TBL_USER (CREATE_TIME);
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_SMS_CODE TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_SMS_CODE TO USER QUERY;

CREATE TABLE YMF.TBL_ACCOUNT_OPEN (
  ID                 VARCHAR(50) PRIMARY KEY                                          NOT NULL,
  MEMBER_NO          VARCHAR(50)                                                      NOT NULL, --易宝会员号
  PHONE_NO           VARCHAR(64)                                                      NOT NULL, --手机号
  INVITE_TYPE        VARCHAR(50)                                                      NOT NULL, --邀请方式
  INVITE_CODE        VARCHAR(50)                                                      NOT NULL, --邀请码或者邀请商编
  MERCHANT_NO        VARCHAR(50), --商户号
  AGENT_NO           VARCHAR(50), --代理商号
  MERCHANT_NAME      VARCHAR(100), --商户名称
  OP_REG_NO          VARCHAR(50), --入网单号
  OPEN_STATUS        VARCHAR(50) DEFAULT 'INIT'                                       NOT NULL, --开户状态
  BIZ_NO             VARCHAR(100), --营业执照号
  BIZ_NO_START       VARCHAR(50), --营业执照开始时间
  BIZ_NO_END         VARCHAR(50), --营业执照失效时间
  TAX_NO             VARCHAR(100), --税务登记证号
  ORG_NO             VARCHAR(100), --组织机构代码证
  ACCOUNT_LICENSE    VARCHAR(100), --开户许可证
  ORG_NO_START       VARCHAR(50), --组织机构代码证开始时间
  ORG_NO_END         VARCHAR(50), --组织机构代码证失效时间
  COMPANY_TYPE       VARCHAR(20), --企业类型
  PRODUCTS           VARCHAR(128), --产品表
  LEGAL_NAME         VARCHAR(100), --法人姓名
  LEGAL_ID_CARD      VARCHAR(50), --法人身份证号
  LEGAL_ID_START     VARCHAR(20), --法人身份开始时间
  LEGAL_ID_END       VARCHAR(20), --法人身份失效时间
  MER_LEVEL1         VARCHAR(50), --商户一级分类
  MER_LEVEL2         VARCHAR(50), --商户二级分类
  MER_LEVEL1_NAME    VARCHAR(100), --商户一级分类
  MER_LEVEL2_NAME    VARCHAR(100), --商户二级分类
  PROVINCE           VARCHAR(50), --商户省
  PROVINCE_NAME      VARCHAR(100), --商户省
  CITY               VARCHAR(50), --商户市
  CITY_NAME          VARCHAR(100), --商户市
  DISTRICT           VARCHAR(50), --商户区
  DISTRICT_NAME      VARCHAR(100), --商户区
  ADDRESS            VARCHAR(100), --详细地址
  SETTLE_CARD_NO     VARCHAR(100), --结算银行卡号
  SETTLE_CARD_NAME   VARCHAR(50), --结算银行卡户名
  SETTLE_BANK_CODE   VARCHAR(20), --结算银行编号
  SETTLE_BANK_NAME   VARCHAR(50), --结算银行名
  BRANCH_BANK_CODE   VARCHAR(50),
  BRANCH_BANK_NAME   VARCHAR(100),
  BANK_PROVINCE_CODE VARCHAR(20), --结算银行开户省编码
  BANK_PROVINCE_NAME VARCHAR(20), --结算银行开户省
  BANK_CITY_CODE     VARCHAR(20), --结算银行开户市编码
  BANK_CITY_NAME     VARCHAR(20), --结算银行开户市
  OPEN_LBS           VARCHAR(128), --开户定位
  CREATE_TIME        TIMESTAMP DEFAULT CURRENT_TIMESTAMP                              NOT NULL,
  UPDATE_TIME        TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_ACCOUNT_OPEN IS '来客开户表';
COMMENT ON TABLE YMF.TBL_ACCOUNT_OPEN.OPEN_LBS IS '';
CREATE UNIQUE INDEX YMF.ACCOUNT_OPEN_MEMBER_NO_IDX ON YMF.TBL_ACCOUNT_OPEN (MEMBER_NO);
CREATE INDEX YMF.ACCOUNT_OPEN_CREATE_TIME_IDX ON YMF.TBL_ACCOUNT_OPEN (CREATE_TIME);
CREATE INDEX YMF.ACCOUNT_OPEN_MERCHANT_NO_IDX ON YMF.TBL_ACCOUNT_OPEN (MERCHANT_NO);
CREATE SEQUENCE YMF.SEQ_YMF_ACCOUNT_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_ACCOUNT_OPEN TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_ACCOUNT_OPEN TO USER QUERY;

CREATE TABLE YMF.TBL_ACCOUNT_ATTACHMENT (
  ID              BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  ACCOUNT_ID      VARCHAR(50)                         NOT NULL, --开户id
  BIZ_IMG         VARCHAR(512), --营业执照照片
  CREDIT_CODE_IMG VARCHAR(512), --统一社会信用代码证
  PERMIT_IMG      VARCHAR(512), --许可证
  TAX_IMG         VARCHAR(512), --税务登记证
  IDCARD_IMG1     VARCHAR(512), --身份证证正面
  IDCARD_IMG2     VARCHAR(512), --身份证证反面
  BANKCARD1_IMG   VARCHAR(512), --银行卡照片
  BANKCARD2_IMG   VARCHAR(512), --银行卡照片
  AGREEMENT1_IMG  VARCHAR(512), --协议文件
  AGREEMENT2_IMG  VARCHAR(512), --协议文件
  AGREEMENT3_IMG  VARCHAR(512), --协议文件
  CREATE_TIME     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
COMMENT ON TABLE YMF.TBL_ACCOUNT_ATTACHMENT IS '开户附件表';
CREATE UNIQUE INDEX YMF.ATTACHMENT_ACCOUNT_IDX ON YMF.TBL_ACCOUNT_ATTACHMENT (ACCOUNT_ID);
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_ACCOUNT_ATTACHMENT TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_ACCOUNT_ATTACHMENT TO USER QUERY;

CREATE TABLE YMF.TBL_APP_VERSION (
  ID           VARCHAR(50) PRIMARY KEY                       NOT NULL,
  ROLE_TYPE    VARCHAR(20)                                   NOT NULL, --版本目标用户
  PLATFORM     VARCHAR(20)                                   NOT NULL, --手机平台
  VERSION_CODE VARCHAR(20)                                   NOT NULL, --版本号
  FORCE_UPDATE SMALLINT DEFAULT 0                            NOT NULL, --强制更新标志
  FILE_URL     VARCHAR(512), --下载路径
  DESCRIPTION  VARCHAR(256)                                  NOT NULL, --更新内容
  OPERATOR     VARCHAR(50)                                   NOT NULL, --创建者
  CREATE_TIME  TIMESTAMP DEFAULT CURRENT_TIMESTAMP           NOT NULL,
  UPDATE_TIME  TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_APP_VERSION IS 'APP版本表';
CREATE UNIQUE INDEX YMF.APP_VERSION_TYPE_PLATHFORM_VERSION_CODE_UX ON
  YMF.TBL_APP_VERSION (VERSION_CODE, PLATFORM, ROLE_TYPE);
CREATE SEQUENCE YMF.SEQ_YMF_VERSION_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_APP_VERSION TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_APP_VERSION TO USER QUERY;


CREATE TABLE YMF.TBL_PUSH_MSG (
  ID             BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  MESSAGE_NO     VARCHAR(50)                                   NOT NULL, --消息编号
  JPUSH_ID       VARCHAR(50), --极光编号
  OPERATOR       VARCHAR(50)                                   NOT NULL, --消息发布者
  TYPE           VARCHAR(20)                                   NOT NULL, --消息类型
  TITLE          VARCHAR(100)                                  NOT NULL, --消息标题
  CONTENT        VARCHAR(1500)                                 NOT NULL, --消息内容
  LIFE_START     TIMESTAMP, --业务生效时间
  LIFE_END       TIMESTAMP, --业务失效时间
  URL1           VARCHAR(128), --网页/图片链接1
  URL2           VARCHAR(128), --网页/图片链接2
  PHONE_NUMBERS  VARCHAR(1200), --接收者
  ROLE           VARCHAR(20), --用户类型
  APP_VERSION_ID VARCHAR(50), --应用版本
  PLATFORM       VARCHAR(50), --应用平台
  MANUFACTURER   VARCHAR(50), --终端厂商
  MODEL          VARCHAR(50), --终端型号
  PUSH_STATUS    VARCHAR(50), --推送状态
  PUSH_TYPE      VARCHAR(50), --推送类型
  PUSH_TIME      TIMESTAMP,
  CREATE_TIME    TIMESTAMP DEFAULT CURRENT_TIMESTAMP           NOT NULL,
  UPDATE_TIME    TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_PUSH_MSG IS '消息对象表';
CREATE UNIQUE INDEX YMF.PUSH_MSG_MESSAGE_NO_IDX ON YMF.TBL_PUSH_MSG (MESSAGE_NO);
CREATE INDEX YMF.PUSH_MSG_CREATE_TIME_IDX ON YMF.TBL_PUSH_MSG (CREATE_TIME);
CREATE SEQUENCE YMF.SEQ_YMF_MSG_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_PUSH_MSG TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_PUSH_MSG TO USER QUERY;

CREATE TABLE YMF.TBL_FUNCTION (
  ID             BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  FUNCTION_NAME  VARCHAR(50)                                                      NOT NULL,
  FUNCTION_CODE  VARCHAR(50)                                                      NOT NULL,
  APP_VERSION_ID VARCHAR(50),
  OPERATOR       VARCHAR(50)                                                      NOT NULL,
  CREATE_TIME    TIMESTAMP DEFAULT CURRENT_TIMESTAMP                              NOT NULL,
  UPDATE_TIME    TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_FUNCTION IS '来客功能表';
COMMENT ON TABLE YMF.TBL_FUNCTION.ID IS '功能ID';
COMMENT ON TABLE YMF.TBL_FUNCTION.FUNCTION_NAME IS '功能名称';
COMMENT ON TABLE YMF.TBL_FUNCTION.FUNCTION_CODE IS '功能码(对应接口)';
COMMENT ON TABLE YMF.TBL_FUNCTION.APP_VERSION_ID IS '上线版本';
COMMENT ON TABLE YMF.TBL_FUNCTION.CREATE_TIME IS '创建时间';
COMMENT ON TABLE YMF.TBL_FUNCTION.OPERATOR IS '操作人';
COMMENT ON TABLE YMF.TBL_FUNCTION.UPDATE_TIME IS '上次更新时间';
CREATE INDEX YMF.ROLE_FUNCTION_NAME_IDX ON YMF.TBL_FUNCTION (FUNCTION_NAME);
CREATE UNIQUE INDEX YMF.ROLE_FUNCTION_CODE_IDX ON YMF.TBL_FUNCTION (FUNCTION_CODE);
CREATE SEQUENCE YMF.SEQ_YMF_FUNC_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_FUNCTION TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_FUNCTION TO USER QUERY;

CREATE TABLE YMF.TBL_ROLE_FUNC (
  ID          BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  FUNCTION_ID BIGINT                                             NOT NULL, --功能ID
  ROLE        VARCHAR(20), --作用角色
  AVAILABLE   SMALLINT DEFAULT 0                                 NOT NULL, --功能是否启用
  OPERATOR    VARCHAR(50)                                        NOT NULL, --操作人
  DESCRIPTION VARCHAR(256), --禁用理由
  CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP                NOT NULL,
  UPDATE_TIME TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_ROLE_FUNC IS '角色功能关系表';
CREATE INDEX YMF.ROLE_FUNC_ROLE_IDX ON YMF.TBL_ROLE_FUNC (ROLE);
CREATE SEQUENCE YMF.SEQ_YMF_ROLE_FUNC_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_ROLE_FUNC TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_ROLE_FUNC TO USER QUERY;

CREATE TABLE YMF.TBL_BLACK_LIST (
  ID          BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  FUNCTION_ID BIGINT, --功能ID
  MEMBER_NO   VARCHAR(50)                                        NOT NULL, --易宝会员号
  AVAILABLE   SMALLINT DEFAULT 1                                 NOT NULL, --是否启用
  DESCRIPTION VARCHAR(256), --禁用理由
  OPERATOR    VARCHAR(50)                                        NOT NULL, --操作员
  CREATE_TIME TIMESTAMP                                          NOT NULL,
  UPDATE_TIME TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_BLACK_LIST IS '来客用户功能黑名单';
CREATE INDEX YMF.BLACK_LIST_MEMBER_NO_IDX ON YMF.TBL_BLACK_LIST (MEMBER_NO);
CREATE SEQUENCE YMF.SEQ_YMF_BLACK_LIST_ID AS BIGINT INCREMENT BY 1 START WITH 1 NO MAXVALUE;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_BLACK_LIST TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_BLACK_LIST TO USER QUERY;


CREATE TABLE YMF.TBL_SECURITY
(
  ID                 BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  PHONE_NUMBER       VARCHAR(64)                         NOT NULL,
  CONTROL_TYPE       VARCHAR(30)                         NOT NULL,
  MISTAKE_TIMES      INTEGER            DEFAULT 0,
  FREEZED            SMALLINT DEFAULT 0                  NOT NULL,
  FIRST_MISTAKE_TIME TIMESTAMP,
  LAST_MISTAKE_TIME  TIMESTAMP
);
COMMENT ON TABLE YMF.TBL_SECURITY IS '安全控制表';
CREATE UNIQUE INDEX YMF.SECURITY_INDEX ON YMF.TBL_SECURITY (PHONE_NUMBER, CONTROL_TYPE);
CREATE SEQUENCE YMF.SEQ_SECURITY_ID AS BIGINT INCREMENT BY 1
START WITH 1 NO MAXVALUE NO CYCLE CACHE 20 ORDER;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_SECURITY TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_SECURITY TO USER QUERY;


CREATE TABLE YMF.TBL_APP_ERROR_MSG (
  ID                  BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
  MEMBER_NO           VARCHAR(50), --会员号
  APP_VERSION_ID      VARCHAR(50), --应用版本
  PHONE_MANUFACTURER  VARCHAR(50), --手机厂商
  PHONE_MODEL         VARCHAR(50), --手机型号
  REQ_INTERFACE       VARCHAR(64), --请求接口
  LOG_LEVEL           VARCHAR(50), --日志级别
  LOG_CONTENT         VARCHAR(1500), --日志内容
  SDK                 VARCHAR(50), --手机系统版本
  CURRENT_ACTIVITY    VARCHAR(50), --当前页面
  IMEI                VARCHAR(50), --手机标识
  CREATE_TIME         TIMESTAMP DEFAULT CURRENT_TIMESTAMP                NOT NULL
);
COMMENT ON TABLE YMF.TBL_APP_ERROR_MSG IS 'APP错误信息表';
CREATE INDEX YMF.ERROR_MSG_CREATE_TIME_IDX ON YMF.TBL_APP_ERROR_MSG (CREATE_TIME);
CREATE INDEX YMF.ERROR_MSG_APP_VERSION_IDX ON YMF.TBL_APP_ERROR_MSG (APP_VERSION_ID);
CREATE SEQUENCE YMF.SEQ_APP_ERROR_MSG_ID AS BIGINT INCREMENT BY 1
START WITH 1 NO MAXVALUE NO CYCLE CACHE 20 ORDER;
GRANT INSERT, UPDATE, DELETE, SELECT ON TABLE YMF.TBL_APP_ERROR_MSG TO USER LIKER;
GRANT SELECT ON TABLE YMF.TBL_APP_ERROR_MSG TO USER QUERY;