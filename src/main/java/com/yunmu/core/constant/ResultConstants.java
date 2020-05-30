package com.yunmu.core.constant;

/**
 * Created by yangbin on 2017/11/21
 */
public class ResultConstants {
	public static final String RESULT_CODE_SUCCESS = "0";
	public static final String RESULT_DESC_SUCCESS = "成功";
	public static final String RESULT_CODE_FAILED = "1";
	public static final String RESULT_CDESC_FAILED = "请求失败";

	public static final String Result_Error_Code_500 = "500";
	public static final String TOKEN_INVALID = "TOKEN_INVALID";//未登录
	/** 接口返回结果ErrorDesc */
	public static final String Result_Error_Desc_500 = "SYS_ERROR";

	/**
	 * 1002 订单失败
	 */
	public static final String ORDER_STATUS_TICKET_PRINT_FIALURE = "1002"; //
	/**
	 * 订单成功
	 */
	public static final String ORDER_STATUS_SUCCESS = "1001";
	public static final int ORDER_STATUS_REFUND_APPLYING = 1304; // 退款申请中
	public static final int ORDER_STATUS_REFUNDED = 1302; // 已退款
	public static final int ORDER_STATUS_DISCARDED = 1501; // 已作废
	public static final int ORDER_STATUS_REFUNDED_FAILED = 1305; // 退票失败
	public static final int ORDER_STATUS_REFUNDED_SUCCESS = 1311; // 自动退款

	/**性别******/
	public static final String SEX_TAG="sex";
	/**年龄******/
	public static final String AGE_TAG="age";
	/**是否持卡******/
	public static final String CARD_TAG="iscard";
	/**看过的电影******/
	public static final String MOVIE_TAG="movie";
	/**影片类型******/
	public static final String MOVIE_TYPE_TAG="movietype";
	/**观影日期偏好******/
	public static final String VIEW_TYPE_TAG="viewtype";
	/**观影时段偏好******/
	public static final String VIEW_TIME_TAG="viewtime";
	/**卖品品类偏好******/
	public static final String SELL_TYPE_TAG="selltype";
	/**支付方式偏好******/
	public static final String PAY_TYPE_TAG="paytype";
	/**导演******/
	public static final String DIRECTOR_TAG="director";
	/**演员******/
	public static final String ACTOR_TAG="actor";
	/**编剧******/
	public static final String SCREENWRITER_TAG="screenwriter";
	/**卖品单次消费金额******/
	public static final String SELLSINGLENUM_TAG="sellSingleNum";
	/**卖品消费频次******/
	public static final String SELL_NUM_TAG="sellNum";
	/**会员卡类型******/
	public static final String CARD_TYPE_TAG="cardtype";
	/**观影频次******/
	public static final String VIEW_NUM_TAG="viewNum";
	/**会员活跃度******/
	public static final String MEMBERACTIVE_TAG="member";

}
