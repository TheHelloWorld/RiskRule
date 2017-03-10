package com.riskrule.constants;

public class OnLinePayConstants {


	/**
	 * 订单号列表(按日期)
	 * */
	public  static final String PREFIX_ORDER_LIST= "ON_ORDER_";

	/**
	 * 订单历史
	 * */
	public  static final String SUFFIX_ORDER_LISTHIS= "_ORDER_HIS";

	/**
	 * 商户日订单
	 * */
	public  static final String SUFFIX_CUSTOMER_ORDERDATE = "_CUSTOMER_ORDERDATE";



	/**
	 * 流水号列表(按日期)
	 * */
	public  static final String PREFIX_PAYEMNT_LIST= "ON_PAYMENT_"; //流水号列表(按日期)
	/**
	 * 订单流水关系维护(按日期)
	 * */
	public  static final String SUFFIX_ORERDATE_RELATION= "_ORERDATE_RELATION"; //
	/**
	 * 银行卡交易流水关系维护（按日期）
	 * */
	public  static final String SUFFIX_CARDDATE_RELATION= "_CARDDATE_RELATION"; //
	/**
	 * IP交易流水关系维护{按日期}
	 * */
	public  static final String SUFFIX_IPDATE_RELATION= "_IPDATE_RELATION"; //
	/**
	 * 商户订单流水关系（按日期）
	 * @param customerNO  date
	 * */
	public  static final String SUFFIX_CUSTOME_RPAYMENT_RELATION= "_CUSTOME_RPAYMENT_RELATION"; //

	/**
	 *电话订单流水关系（按日期）
	 * */
	public  static final String SUFFIX_PHONEDATE_RELATION = "_PHONEDATE_RELATION"; //

	/**
	 *身份证订单流水关系（按日期）
	 * */
	public  static final String SUFFIX_IDCARDNODATE_RELATION = "_IDCARDNODATE_RELATION"; //

	/**
	 * 卡交易地点关系维护
	 * */
	public  static final String SUFFIX_DES_RELATION= "_DES_RELATION"; //卡交易地点关系维护
	/**
	 * 商户历史交易信息维护
	 * */
	public  static final String SUFFIX_PAYMENT_HIS= "_PAYMENT_HIS"; //
	/**
	 * 卡交易历史维护
	 * */
	public  static final String SUFFIX_CARDTRANS_HIS= "_CARDTRANS_HIS"; //
	/**
	 * IP历史维护
	 * */
	public  static final String SUFFIX_IPTRANS_HIS= "_IPTRANS_HIS"; //

	/**
	 * 身份证交易历史维护
	 * */
	public  static final String SUFFIX_IDCARDNOTRANS_HIS= "_IDCARDNOTRANS_HIS"; //
	/**
	 * 电话历史交易维护
	 * */
	public  static final String SUFFIX_PHONETRANS_HIS= "_PHONETRANS_HIS"; //
	/**
	 *IP订单关系 SET
	 * */
	public  static final String SUFFIX_IPORDER_RELATAION= "_IPORDER_RELATAION"; //
	/**
	 *卡订单关系 SET
	 * */
	public  static final String SUFFIX_CARDORDER_RELATAION= "_CARDORDER_RELATAION"; //

	/**
	 *手机单关系 SET
	 * */
	public  static final String SUFFIX_PNONEORDER_RELATAION= "_PNONEORDER_RELATAION"; //
	/**
	 *身份证单关系 SET
	 * */
	public  static final String SUFFIX_IDCARDNOORDER_RELATAION= "_IDCARDNOORDER_RELATAION"; //


	/**
	 *商户黑名单
	 * */
	public 	static final String CUSTOMER_BLACKLIST = "CUSTOMER_BLACKLIST"; //
	/**
	 *电话黑名单
	 * */
	public 	static final String PHONENO_BLACKLIST = "PHONENO_BLACKLIST"; //
	/**
	 *身份证黑名单
	 * */
	public 	static final String IDCARDNO_BLACKLIST = "IDCARDNO_BLACKLIST"; //
	/**
	 *卡黑名单
	 * */
	public 	static final String PAN_BLACKLIST = "PAN_BLACKLIST"; //

	/**
	 * 交易数
	 * */
	public static final String  _PAYMENTCOUNT = "_PAYMENTCOUNT";

	/**
	 * 交易金额
	 * */
	public static final String  _PAYMENTAMOUNT = "_PAYMENTAMOUNT";


	/**
	 * 成功交易数
	 * */
	public static final String  _PAYMENTCOUNTSUCCESS = "_PAYMENTCOUNTSUCCESS";


	/**
	 * 成功交易金额
	 * */
	public static final String  _PAYMENTAMOUNTSUCCESS = "_PAYMENTAMOUNTSUCCESS";


	/**
	 * 订单笔数
	 * */
	public static final String  _ORDERCOUNTBYTYPE = "_ORDERCOUNTBYTYPE";



	/**
	 * 订单金额
	 * */
	public static final String  _ORDERAMOUNTBYTYPE = "_ORDERAMOUNTBYTYPE";


	/**
	 * 订单笔数
	 * */
	public static final String  _ORDERCOUNT = "_ORDERCOUNT";

	/**
	 * 订单金额
	 * */
	public static final String  _ORDERAMOUNT = "_ORDERAMOUNT";

	/**
	 * 成功订单笔数
	 * */
	public static final String  _ORDERCOUNTSUCCESS = "_ORDERCOUNTSUCCESS";

	/**
	 * 成功订单笔数
	 * */
	public static final String  _ORDERAMOUNTSUCCESS = "_ORDERAMOUNTSUCCESS";


	public static final String RULECODE = "_RULECODE";

	public static final String HANDLECODE = "_HANDLECODE";

	public static final String RULEHANDLERELATION = "_RH";

}
