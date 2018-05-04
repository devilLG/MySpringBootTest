package com.zhilai.master.modules.utils;


/**
 * 全局变量
 * 
 */

public class GParameter {
	//公众号信息
	public static final String APPID = "wxecf2a65dc8209b35";
	public static final String SECRET = "964e21f02999cf5def55d5e696a79821";

	//授权信息
	public static final String AUTH_ID = "wechat_id";
	public static final String AUTH_NAME = "wechat_name";
	public static final String BCODE = "02";//
	public static final String IFLAG="1";
	public static final String IEND="1";
	public static final String ISTART="1";
	public static final String SUCCESS_CODE="0000";
	public static final String PLAT_CODE="02";

	//用户同意授权，获取code地址
	public static final String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecf2a65dc8209b35";
	
	/**发布服务器*/
	public static String reqSiteName="发布服务器";
	/**接收服务器*/
	public static String respSiteName="接收服务器";
	/**消息类型00发出 */
	public static String reqMsgType="00";
	/**消息类型01收到 */
	public static String respMsgType="01";
	/**逻辑删除 0不删除*/
	public static String deleteFlgNot="0";
	/**逻辑删除 1删除 */
	public static String deleteFlg="1";
	/**数据处理结果标示 00 未处理*/
	public static String hand_flg="00";
	/**数据处理结果标示 02 不予处理 */
	public static String hand_flgNot="02";
	/**数据处理结果标示 01 成功处理 */
	public static String hand_flgSuccess="01";
	/**主题类型  00*/
	public static String topic_type="00";
	/**
	 * 加密类型 00:none 01:3des 02:aes 03:rsa Encryption type 1: clear text  2:3des  3:aes
	 */
	public static String encryptType="00";
	/**站点ID 0000 */
	public static String site_id="0000";
	/**安装信息主题   */
	public static String adviceTheme="advice";
	/**硬件信息主题   */
	public static String sysinfoTheme="sysinfo";
	/**运行状态主题   */
	public static String stateTheme="state";
	/**流量统计主题   */
	public static String trafficTheme="traffic";
	/**软件更新主题   */
	public static String upgradesTheme="upgrades";
	/**系统反控主题   */
	public static String controlTheme="control";
	/**终端上报主题   */
	public static String terminalReport="shunfeng0755/cn/app/resptopic";
	/**安装信息业务编号  */
	public static String adviceCode="1001";
	/**硬件信息业务编号  */
	public static String sysinfoCode="1002";
	/**运行状态业务编号  */
	public static String stateCode="1003";
	/**流量统计业务编号  */
	public static String trafficCode="1004";
	/**软件更新业务编号  */
	public static String upgradesCode="1005";
	/**系统反控业务编号  */
	public static String controlCode="1006";
	public static Integer randomLength=10;
	
	public static String curnum="1";
	
	public static String totnum="1";
	
	public static String retmsg="";
	
	public static String retcode_success="0000";
//	/*** 00离线    01在线 */
//	public static String refreshState="00";
	/*** 00在线 */
	public static String refreshState_Online="00";
	/*** 01离线 */
	public static String refreshState_offline="01";
	/*** 02未启用*/
	public static String refreshState_NotEnabled="02";
	
	public static String handflgOnline="handflgOnline";
	
	public static String handflgOffline="handflgOffline";
	/**02使用中**/
	public static String hard_stateUse="02";
	/**00 有效**/
	public static String hard_stateValid="00";
	/**01 准备升级**/
	public static String upgradeState_wait="01";
	/**02 正在升级**/
	public static String upgrading="02";
	/**03 升级成功**/
	public static String upgradeState_success="03";
	/**04 升级失败**/
	public static String upgradeState_fail="04";
	/**离线站点信息     01:3天内离线站点**/
	public static final String offlineSite="01";
	/**离线站点信息     02:3到7天离线站点**/
	public static final String offlineSites="02";
	/**默认组别名称**/
	public static final String defaultGroup="defaultGroup";
	/**默认组别编号**/
	public static final String defaultGroupId="10001";
	/**站点组别**/
	public static final String  groupSite="00";
	/**顺丰公司编号**/
	public static final String SF_corp="10001";
	/**智莱公司编号**/
	public static final String ZL_corp="10000";
	/*** 01启用 */
	public static String state_Enabled="01";
	/*** 02未启用*/
	public static String state_NotEnabled="02";
	/*** 00成功*/
	public static String STATE_SUCCESS="00";
	/*** 01失败*/
	public static String STATE_FAIL="01";
	/*** 02待处理*/
	public static String STATE_WAIT="02";
	//'网络类型 01：2G、02：4G、03：WLAN 04有线网络 05 未连接网络',Wi-Fi  有线网络   未连接网络   4G 2G 
	/**网络类型 01：2G **/
	public static String netType_num2g="01";
	/**网络类型 02：4G **/
	public static String netType_num4g="02";
	/**网络类型 03：WLAN **/
	public static String netType_numwlan="03";
	/**网络类型 04：有线网络 **/
	public static String netType_numcable="04";
	/**网络类型 05：未连接网络 **/
	public static String netType_numconnected="05";
	/**网络类型 ：2G **/
	public static String netType_2g="2G";
	/**网络类型 ：4G **/
	public static String netType_4g="4G";
	/**网络类型 ：WLAN **/
	public static String netType_wifi="Wi-Fi";
	/**网络类型 ：有线网络 **/
	public static String netType_cable="有线网络";
	/**网络类型 ：未连接网络 **/
	public static String netType_connected="未连接网络";
	
	public static String platCode_web = "04"; // 平台代码：网站
	public static String platCode_terminal = "03"; // 平台代码：终端
	
	public static String issued_partnerOrder_trade_code = "1223";// 订单下发交易码
	
	public static String favourableState_wait = "0";// 待执行
	public static String favourableState_executing = "1";// 正在执行
	
	public static String favourableType_all = "02";// 全网
	
	public static String inventoryState_normal = "1";// 正常
	public static String inventoryState_lack = "2";// 缺货
	public static String inventoryState_none = "3";// 无货
	
	//是否有过期商品
	public static String isOverdue_yes = "1";// 是
	public static String isOverdue_no = "2";// 否
	
	public static String barterOrderState_wait = "1";// 等待换货
	public static String barterOrderState_finish = "2";// 换货完成
	
	public static String barterOrderType_product = "1";// 按商品换货
	public static String barterOrderType_goods = "2";// 按货道换货
	
	public static String downState_wait = "0";// 待下发
	public static String downState_yes = "1";// 已下发
	public static String downState_confrim = "2";// 已确认
	
	public static String temperature_nomal = "1";// 正常
	public static String temperature_low = "2";// 低
	public static String temperature_hiegh = "3";// 高
	
	public static String siteControlLogState_key = "siteControlLogState";// 心跳通知开始
	public static String siteControlLogState_sheart = "01";// 心跳通知开始
	public static String siteControlLogState_dataNoIuessed = "02";// 数据未开发
	public static String siteControlLogState_dataAlIuessed = "03";// 数据已下发
	public static String siteControlLogState_dataAlAffirm = "04";// 数据已确认
	public static String siteControlLogState_dataAffirmError = "05";// 数据确认错误
	public static String siteControlLogState_dataRepeartAffirm = "06";// 数据重复确认
	public static String siteControlLogState_dataAffirmNoOrder = "07";// 数据重复确认
	public static String siteControlLogState_eheart = "08";// 心跳通知结束

	public static String siteCur_state_key = "siteCur_state";// 地点状态
	public static String siteCur_state_nomarl = "0";// 正常
	public static String siteCur_state_no = "1";// 失效
	
	public static String bind_state_key = "siteCur_state";// 地点状态
	public static String bind_state_nomarl = "0";// 有效
	public static String bind_state_no = "1";// 无效
	
	//控制箱
	public static String boxTypeId_controlBox = "1";
	
	public static String boxState_key = "boxState"; //箱子状态
	public static String boxState_lock = "0";//锁定
	public static String boxState_empty = "1";//空箱
	public static String boxState_using = "2";//使用
	public static String boxState_damage = "3";//损坏
	
	public static String boxSta_key = "boxState"; //货道状态
	public static String boxSta_nomal = "1";//正常
	public static String boxSta_bad = "2";//故障
	public static String boxSta_stop = "3";//停用
	
	public static String siteUseState_key = "siteUseState";//地点状态
	public static String siteUseState_online="0";//在线
	public static String siteUseState_offline="1";//离线
	public static String siteUseState_unavailable="2";//不可用
	
	public static String commtype_key = "commtype";//通讯方式
	public static String commtype_udp="1";//udp
	public static String commtype_http="2";//http
	public static String commtype_soap ="3";//soap
	public static String commtype_demo ="4";//demo
	
	public static String is_replenishment_key = "is_replenishment";//是否补货
	public static String is_replenishment_yes="1";//是
	public static String is_replenishment_no="0";//否
	
	public static String warn_type_key = "warn_type";//警报类型
	public static String warn_type_jixie="02";//机械故障
	public static String warn_type_no="01";//无
	public static String warn_type_box="03";//货道故障
	public static String warn_type_nogoods="04";//缺货
	public static String warn_type_donot="05";//缺货
	
	public static String warn_state_nomal="1";//正常
	public static String warn_state_warn="2";//报警
	
	public static String siteControlConfigState_key = "siteControlConfigState";// 地点配置状态
	public static String siteControlConfigState_nomarl = "01";// 地点下发配置正常
	public static String siteControlConfigLog_on = "01";// 打开地点下发日志
	
	public static String cur_state_key = "siteControlConfigState";// 状态
	public static String cur_state_on = "01";// 启用
	public static String cur_state_close = "02";// 禁用
	
	public static String is_log_on="01";//记录
	public static String is_log_no="02";//不记录
	
	public static String issued_barterOrder_trade_code = "1225";// 换货下发交易码
	public static String issued_barterOrder_trade_desc = "issuedBarterOrder";
	public static String issued_productRecover_trade_code = "1229";// 回收下发交易码
	public static String issued_productRecover_trade_desc = "issuedProductRecover";
	
	public static String chanelType_terminal = "01";// 终端
	public static String chanelType_wechart = "02";// 微信
	public static String chanelType_android = "03";// 安卓
	public static String chanelType_ios = "04";// IOS
	
	public static String issued_sitePerson_trade_code = "1221";// 人员下发交易码
	public static String issued_sitePerson_trade_desc = "issuedSitePerson";
	
	public static String issued_site_trade_code = "1221";// 地点下发交易码
	public static String issued_site_trade_desc = "issuedSitePerson";
	
	public static String permissMethod_corpId = "setCorp_id";
	public static String corpId_zhilai = "1";
	
	public static String orderState_locked = "02";
	
	public static String empType_maintenance = "1";//维修员
	public static String empType_replenishment = "2";//补货员
	
	public static String issued_siteCmd_trade_code = "1227";// 地点控制命令下发交易码
	public static String issued_siteCmd_trade_desc = "issuedSiteCmd";
	
	public static String noticeObj_key = "noticeObj"; // 通知对象
	public static String noticeObj_order = "01"; // 订单
	public static String noticeObj_site = "02"; // 地点
	
	public static String noticeType_key = "noticeType";//通知方式
	public static String noticeType_mobile = "01";//短信
	public static String noticeType_email = "02";//邮件
	public static String noticeType_manager = "03";//管理台
	public static String noticeType_app = "04";//补货APP
	public static String noticeType_wechat = "05";//微信公众号
	public static String noticeType_paySsystem = "06";//支付系统
	
	public static String bindState_key = "bindState";//绑定状态
	public static String bindState_yes = "0";//有效
	public static String bindState_no = "1";//无效
	
	public static String procFlag_key = "procFlag";//通知处理状态
	public static String procFlag_no = "0";//未处理
	public static String procFlag_success = "1";//发送成功
	public static String procFlag_failed = "2";//发送失败
	
	public static String replenishment_key = "replenishment"; // 是否需要补货
	public static String replenishment_yes = "1"; // 是
	public static String replenishment_no = "0"; // 否
	
	public static String productFavourableState_key = "productFavourableState"; //商品优惠状态
	public static String productFavourableState_wait = "0";//待执行
	public static String productFavourableState_doing = "1";//正在执行
	public static String productFavourableState_finish = "2";//执行完成
	
	public static String siteCmdObj_site = "01";//地点
	
	public static String replenishmentOrderDetailState_overdue = "4"; // 已过期
	
	public static String zhilaiPay_authId = "zhilai_web";//支付平台授权编号
	public static String zhilaiPay_authName = "zhilai_web";//支付平台授权名称
	public static String zhilaiPay_corpId = "201710";//支付平台公司编号
	public static String zhilaiPay_corpName = "深圳智莱";//支付平台公司名称
	
	public static String payState_paysuccess = "01"; // 支付成功
	public static String payState_invalid = "02"; // 支付失效
	public static String payState_allrefund = "04"; // 全部退款
	
	public static String pushType_success = "02"; // 推送成功
	public static String pushType_failed = "03"; // 推送失败
	
	public static String abnormalType_setPayOverFailed = "1"; // 设置支付超期失败
	
	
	public static String barterType_product = "1"; // 按商品换货
	public static String barterType_goods = "2"; // 按货道换货
	
	/**
	 * 自动便利店二期开发
	 */
	//通知发送模块配置
	public static String Message_Uid = "13036130218";//sms登录账号
	public static String Message_Key = "4b5f00ea3b08d76b5790";//sms账户密匙
	public static String Message_Pwd = "zlpf2016";//sms测试账户密码，实际可不填
	
	//状态
	public static String curState_key = "Cur_state";
	public static String Cur_state_normal = "0";//正常
	public static String Cur_state_valid = "1";//失效
	
	//计划对象类型
	public static String planType_key = "planType";
	public static String planType_site = "01";//站点
	public static String planType_area = "02";//区域
	
	//周期类型
	public static String cycleType_key = "cycleType";
	public static String cycleType_week = "1"; //每周
	public static String cycleType_mounth = "2"; //每月
	
	//盘点
	//盘点对象
	public static String checkObj_key = "checkObj";
	public static String checkObj_product = "2";//商品
	public static String checkObj_goods = "1"; //货道
	
	//盘点方式
	public static String checkType_key = "checkType";
	public static String checkType_all = "1"; // 全盘
	public static String checkType_random = "2"; // 抽盘
	
	/** 通知模块 */
	//业务类型
	public static String mainType_key = "mainType";
	public static String mainType_saleOrder = "01";// 销售订单
	public static String mainType_siteMonitor = "02";// 站点监控
	public static String mainType_work = "03";// 工单服务
	public static String mainType_siteInventory = "04";// 站点库存
	public static String mainType_siteReplenishment = "05";// 站点补货
	public static String mainType_siteBarter = "06";// 站点换货
	public static String mainType_siteCheck = "07";// 站点盘点
	public static String mainType_userRegister = "08";// 人员注册及更新
	public static String mainType_payChecking = "09";// 对账单
	
	//通知发送级别
	public static int one = 1;//最高级
	public static int two = 2;//较高级
	public static int three = 3;//正常
	public static int four = 4;//较低级
	public static int five = 5;//最低级
	
	//通知发送状态
	public static String noticeSendState_key = "noticeSendState";
	public static String noticeSendState_wait = "1"; //待发送
	public static String noticeSendState_sends = "2"; //已发送
	public static String noticeSendState_success = "3";//发送成功
	public static String noticeSendState_defeat = "4";//发送失败
	public static String noticeSendState_again = "5";//重发
	
	// 对账状态
	public static String accountCheckState_wait = "1";// 等待对账
	public static String accountCheckState_finish = "2";// 完成对账
	public static String accountCheckState_lack = "3";// 缺帐
	public static String accountCheckState_win = "4";// 账赢
	public static String accountCheckState_loss = "5";// 账亏
	
	// 异常处理状态
	public static String procStatus_key = "procStatus";
	public static String procStatus_wait = "1";
	public static String procStatus_finish = "2";
	
	/** 日志 */
	//补货日志状态
	public static String replenishmentAction_key = "replenishmentAction";
	//补货日志状态
	public static String barterChangeAction_key = "barterChangeAction";
	//盘点日志状态
	public static String checkChangeAction_key = "checkChangeAction";
	//通知队列日志状态
	public static String noticeQueueAction_key = "noticeQueueAction";
	
	
	//设备名称
	public static String deviceInfo_key = "device_info"; 
	//设备型号
	public static String deviceModel_key = "device_model";
	//设备品牌
	public static String deviceBard_key = "device_bard";
	//生产厂商
	public static String deviceMfrs_key = "device_mfrs";
	//故障等级
	public static String errorLevelInfo_key = "error_level_info"; 
	//是否触发设备故障状态
	public static String errorLevelEnv_key = "error_level_env"; 
	//设备参数单位
	public static String deviceUnit_key = "device_unit"; 
	
	//设备状态
	public static String siteDeviceState_key = "siteDevice_state";
	//检测状态
	public static String siteDeviceCheckState_key = "siteDeviceCheck_state";
	//操作状态
	public static String operState_key = "oper_state";
	//日志等级
	public static String logLevel_key = "log_level";
	//站点设备操作动作
	public static String operAction_key = "oper_action";
	//媒体类型
	public static String mediaType_key = "media_type";
	//媒体格式
	public static String mimeType_key = "mime_type";
	//媒体状态
	public static String mediaState_key = "media_state";
	//默认播放
	public static String isDefalut_key = "is_defalut";
	//广告状态
	public static String advertConfigState_key = "advertConfig_state";
	// yes or no
	public static String yon_key = "yon";
	public static String yon_yes = "1"; // 是
	public static String yon_no = "2"; // 是
	//播控权限类型
	public static String targetType_key =  "target_type";
	//广告日志动作
	public static String advertOperAction_key = "advertOper_action";
	//工单操作动作
	public static String workOperAction_key = "workOper_action";
	//订单状态
	public static String orderApplyState_key = "order_apply_state";
	//站点状态
	public static String siteState_key = "site_state";
	//系统角色
	public static String roleType_key = "role_type";
	//站点补货状态
	public static String replenishmentCurState_key = "replenishment_cur_state";
	//站点换货状态
	public static String barterState_key = "barter_state";
	//站点盘点
	public static String checkState_key = "check_state";
	//对账单状态
	public static String paymentState_key = "payment_state";
	//配置类型
	public static String configType_key = "config_type";
	//通知渠道
	public static String noticeChannel_key = "notice_channel";
	//通知等级
	public static String noticeLevel_key = "notice_level";
	//发送状态
	public static String sendState_key = "send_state";
	//通知操作状态
	public static String noticeOperAction_key = "noticeOper_action";
	//支付方式
	public static String payChannel_key = "pay_channel";
	//币种
	public static String currency_key = "currency";
	//对账结果信息处理状态
	public static String payResultCurState_key = "payResultCur_state";
	//工单状态
	public static String workCurState_key = "workCur_state";
	//工单类型
	public static String workType_key = "work_type";
	//工单级别
	public static String workLevel_key = "work_level";
	//问题设备状态
	public static String workDeviceState_key = "workDevice_state";
}
