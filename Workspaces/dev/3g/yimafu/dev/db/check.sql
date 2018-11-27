--二维码查询
select 	row_number() over(order by 	q.id) as rowid,
										q.id as id,
										c.CUSTOMER_NUMBER as customernumber,
										c.CUSTOMER_NAME as customername,
										d.NAME as apptype,
										q.CREATE_TIME as createtime,
										q.UPDATE_TIME as updatetime,
										q.QR_ID as qrid,
										case when q.status is null then 'NOTEFFECT' else q.status end as status
								from YMF_CUSTOMER c
								left join YMF_QRCODE q on q.CUSTOMER_NUMBER = c.CUSTOMER_NUMBER and q.STATUS!='DELETE'
								left join YMF_DICTIONARY d on d.code = c.APP_TYPE and d.type='AppType'
								where 1=1 and c.STATUS='ACTIVE'
                and c.CUSTOMER_NUMBER  = '10000682968' --CUSTOMER_NUMBER和QR_ID必填一项
								and  q.CREATE_TIME >= '2016-09-09-00:00:00.000000'
							  and  q.CREATE_TIME <= '2016-09-09-23:59:59.999999'
								and  c.CUSTOMER_NAME  like '%%'
								and   nvl(q.status,'NOTEFFECT') = ''
								and  c.APP_TYPE= ''
								and  q.QR_ID= ''
								with ur
--商户查询
select
								row_number() over(order by c.id) as rowid,
									c.id as id,
									c.CUSTOMER_NUMBER as customernumber,
									c.CUSTOMER_NAME as customername,
									case when d.name is null then c.INDUSTRY_TYPE
										 else d.name end as industrytype,
									case when c.customer_level='A' then '甲级'
										 when c.customer_level='B' then '乙级'
										 when c.customer_level='C' then '丙级' end as customerlevel,
									case when  d1.name is null then c.customer_type
										 else d1.name end as customertype,
									c.create_time as createtime,
									c.update_time as updatetime,
									case when c.status='ACTIVE' then '开通'
										 when c.status='INACTIVE' then '关闭' end as status,
									c.PAY_TYPE_INFO as paytypeinfo,
									b.BIZ_NAME  as bizcode
								from YMF_CUSTOMER c
								left join YMF_DICTIONARY d on d.code=c.industry_type and d.type='IndustryType'
								left join YMF_DICTIONARY d1 on d1.code=c.CUSTOMER_TYPE and d1.type='CustomerType'
								left join YMF_BUSINESS b on b.id = c.business_id
								where 1=1
                 and c.CUSTOMER_NUMBER = '10000682968' --CUSTOMER_NUMBER和CUSTOMER_NAME必填一项
							   and  c.CUSTOMER_NAME like '%%'
								 and  c.create_time >='2016-09-09-00:00:00.000000'
							   and  c.create_time <='2016-09-09-23:59:59.999999'
								 and c.INDUSTRY_TYPE =''
								 and c.customer_level =''
								 and c.customer_type=''
								 and c.pay_type_info like '%%'
								 and b.biz_code =''
								 and c.status=''
								with ur
--业务方查询
select
										row_number() over(order by b.id) as rowid,
										b.id as id,
										b.BIZ_CODE as bizcode,
										b.BIZ_NAME as bizname,
										b.CREATE_TIME as createtime,
										b.UPDATE_TIME as updatetime,
										t.customercount as customercount,
										case when b.status='ACTIVE' then '开通'
											when b.status='INACTIVE' then '关闭' end as status
								from YMF_BUSINESS b
								left join (select b1.BIZ_CODE as bizcode,count(c1.id) as customercount
											from YMF_BUSINESS b1
											left join YMF_CUSTOMER c1 on b1.id=c1.business_id and c1.status='ACTIVE'
											group by b1.BIZ_CODE) t on t.bizcode =  b.BIZ_CODE
								where 1=1
								and b.BIZ_NAME like '%%'
								and  b.CREATE_TIME >= '2016-09-09-00:00:00.000000'~/
							  and  b.CREATE_TIME <= '2016-09-09-23:59:59.999999'~/
								and  b.BIZ_CODE= ''
								and  b.status= ''
								with ur
--业务方不会超过100个,所以查询条件不做限制

--程序中的SQL
  select * from YMF_BUSINESS where status='ACTIVE'--查询可用的业务方,数据不超过100
  select * from YMF_CUSTOMERFUN where customer_number='10000682968' and status='ACTIVE' --查询商户支付方式,商编已建索引,而且数据量不会超过10
  select * from YMF_CUSTOMER where customer_number='10000682968' and status='ACTIVE'--查询商户
  select * from YMF_QRCODE where customer_number='10000682968' and status='ACTIVE'--二维码查询
  select * from YMF_QRCODE where qr_id='47ENVP6F' --二维码查询