package com.jun.plugin.utils;

/**
 * 系统常量
 * 
 * @author hermit
 * @date 2017 -06-10 14:10:50
 */
public class Constants {
    
    /********************************* 字典常量 ***********************************/
    /** 管理员登录id **/
    public static final String ADMIN_LOGIN_ID = "admin";
    
    /** 管理员id **/
    public static final String ADMIN_ID = "user0000000000000000000000000000";

    /** 第三方登录用户密码 **/
    public static final String SOCIAL_PWD = "administrator_init";

    /********************************* 访问路径常量 ***********************************/
    /** 登录页面 **/
    public static final String URL_LOGIN = "/login.jsp";

    /** 首页 **/
    public static final String URL_INDEX = "/index.jsp";

    /** 欢迎页面 **/
    public static final String URL_WELCOME = "/layout/welcome.jsp";

    /** 默认访问路径：济南 **/
    public static final String DEFAULT_URL = "https://jn.fangshenghuo.net/";

    /********************************* 操作信息常量 ***********************************/
    /** 成功提示 **/
    public static final String MSG_SUCCESS = "信息操作成功!";

    /** 保存成功提示 **/
    public static final String MSG_SUCCESS_SAVE = "信息添加成功!";

    /** 修改成功提示 **/
    public static final String MSG_SUCCESS_UPDATE = "信息修改成功!";

    /** 删除成功提示 **/
    public static final String MSG_SUCCESS_DELETE = "信息删除成功!";

    /** 操作失败提示 **/
    public static final String MSG_ERROR = "服务器异常，操作失败!";

    /** 删除失败提示 **/
    public static final String MSG_ERROR_DELETE = "该节点含有子节点";

    /********************************* 缓存常量 ***********************************/
    /** 权限缓存常量 **/
    public static final String CACHE_ROLE_LIST = "roleList";

    /********************************* 其他常量 ***********************************/
    /** 下划线 */
    public static final String UNDERLINE = "_";

    /** 斜杠 */
    private static String SLASHLINE = "/";

    /** 业务类型--二手房地图 */
    public static final String BUSINESS_TYPE_SELL_HOUSE_MAP = "sellhousemap";
    /** 业务类型--房源出租地图 */
    public static final String BUSINESS_TYPE_RENT_HOUSE_MAP = "renthousemap";
    /** 业务类型--厂房出售地图 */
    public static final String BUSINESS_TYPE_SELL_FACTORY_MAP = "sellfactorymap";

    /** 业务类型--厂房出租地图 */
    public static final String BUSINESS_TYPE_RENT_FACTORY_MAP = "rentfactorymap";

    /** 业务类型--写字间出售地图 */
    public static final String BUSINESS_TYPE_SELL_OFFICE_MAP = "sellofficemap";

    /** 业务类型--写字间出租地图 */
    public static final String BUSINESS_TYPE_RENT_OFFICE_MAP = "rentofficemap";

    /** 业务类型--商铺出售地图 */
    public static final String BUSINESS_TYPE_SELL_SHOP_MAP = "sellshopmap";

    /** 业务类型--商铺出租地图 */
    public static final String BUSINESS_TYPE_RENT_SHOP_MAP = "rentshopmap";

    /** 业务类型--别墅出售地图 */
    public static final String BUSINESS_TYPE_SELL_VILLA_MAP = "sellvillamap";

    /** 业务类型--别墅出租地图 */
    public static final String BUSINESS_TYPE_RENT_VILLA_MAP = "rentvillamap";

    /** 业务类型--厂房出售 */
    public static final String BUSINESS_TYPE_SELL_FACTORY = "sellfactory";
    /** 业务类型--厂房出售_微信端 */
    public static final String WX_BUSINESS_TYPE_SELL_FACTORY = "wxsellfactory";
    /** 业务类型--厂房出租 */
    public static final String BUSINESS_TYPE_RENT_FACTORY = "rentfactory";
    /** 业务类型--厂房出租_微信端  */
    public static final String WX_BUSINESS_TYPE_RENT_FACTORY = "wxrentfactory";
    /** 业务类型--写字间出售 */
    public static final String BUSINESS_TYPE_SELL_OFFICE = "selloffice";
    /** 业务类型--写字间出售_微信端  */
    public static final String WX_BUSINESS_TYPE_SELL_OFFICE = "wxselloffice";
    /** 业务类型--写字间出租 */
    public static final String BUSINESS_TYPE_RENT_OFFICE= "rentoffice";
    /** 业务类型--写字间出租_微信端  */
    public static final String WX_BUSINESS_TYPE_RENT_OFFICE= "wxrentoffice";
    /** 业务类型--商铺出售 */
    public static final String BUSINESS_TYPE_SELL_SHOP = "sellshop";
    /** 业务类型--商铺出售_微信端  */
    public static final String WX_BUSINESS_TYPE_SELL_SHOP = "wxsellshop";
    /** 业务类型--商铺出租 */
    public static final String BUSINESS_TYPE_RENT_SHOP = "rentshop";
    /** 业务类型--商铺出租_微信端  */
    public static final String WX_BUSINESS_TYPE_RENT_SHOP = "wxrentshop";
    /** 业务类型--别墅出售 */
    public static final String BUSINESS_TYPE_SELL_VILLA = "sellvilla";
    /** 业务类型--别墅出售_微信端  */
    public static final String WX_BUSINESS_TYPE_SELL_VILLA = "wxsellvilla";
    /** 业务类型--别墅出租 */
    public static final String BUSINESS_TYPE_RENT_VILLA = "rentvilla";
    /** 业务类型--别墅出租_微信端  */
    public static final String WX_BUSINESS_TYPE_RENT_VILLA = "wxrentvilla";
    /** 业务类型--二手房 */
    public static final String BUSINESS_TYPE_SELL_HOUSE = "sellhouse";

    /** 业务类型--二手房 */
    public static final String WX_BUSINESS_TYPE_SELL_HOUSE = "wxsellhouse";

    /** 业务类型--租房 */
    public static final String BUSINESS_TYPE_RENT_HOUSE = "renthouse";

    /** 业务类型--微信端租房 */
    public static final String WX_BUSINESS_TYPE_RENT_HOUSE = "wxrenthouse";

    /** 业务类型--小区找房 */
    public static final String BUSINESS_TYPE_SEARCH_ESTATE = "searchestate";

    /** 业务类型--经纪人住宅出售 */
    public static final String BUSINESS_TYPE_AGENT_SELL_HOUSE = "agentsellhouse";

    /** 业务类型--经纪人商铺出售 */
    public static final String BUSINESS_TYPE_AGENT_SELL_SHOP = "agentsellshop";

    /** 业务类型--经纪人厂房出售 */
    public static final String BUSINESS_TYPE_AGENT_SELL_FACTORY = "agentsellfactory";

    /** 业务类型--经纪人写字间出售 */
    public static final String BUSINESS_TYPE_AGENT_SELL_OFFICE = "agentselloffice";

    /** 业务类型--经纪人住宅出租 */
    public static final String BUSINESS_TYPE_AGENT_RENT_HOUSE = "agentrenthouse";

    /** 业务类型--经纪人商铺出租 */
    public static final String BUSINESS_TYPE_AGENT_RENT_SHOP = "agentrentshop";

    /** 业务类型--经纪人厂房出租 */
    public static final String BUSINESS_TYPE_AGENT_RENT_FACTORY = "agentrentfactory";

    /** 业务类型--经纪人写字间出租 */
    public static final String BUSINESS_TYPE_AGENT_RENT_OFFICE = "agentrentoffice";

    /** 业务类型--经纪人后台出售 */
    public static final String BUSINESS_TYPE_BACK_SELL_HOUSE = "backsellhouse";

    /** 业务类型--经纪人后台出租 */
    public static final String BUSINESS_TYPE_BACK_RENT_HOUSE = "backrenthouse";

    /** 业务类型--新房 */
    public static final String BUSINESS_TYPE_NEW_ESTATE = "newestate";


    /** 输入类型--单数据输入 */
    public static final String INPUT_TYPE_SINGLE = "single";

    /** 输入类型--范围数据输入 */
    public static final String INPUT_TYPE_REGION = "region";

    /** 已售 */
    public static final String FLAG_SOLD = "已售";

    /** 已租 */
    public static final String FLAG_RENTED = "已租";

    /** 已租 */
    public static final String FLAG_INVALID = "无效";
    /** 我租 */
    public static final String FLAG_IRENT = "我租";
    /** 我售 */
    public static final String FLAG_ISELL = "我售";
    /** 暂缓 */
    public static final String FLAG_DEFER = "暂缓";
    /** 暂缓 */
    public static final String FLAG_VALID = "有效";



    /** 图片类型--封面图 */
    public static final String PIC_TYPE_COVER = "1";

    /** 图片类型--户型图 */
    public static final String PIC_TYPE_LAYOUR = "2";

    /** 图片类型--勘察图 */
    public static final String PIC_TYPE_SURVEY = "3";

    /** 图片类型--园景图 */
    public static final String PIC_TYPE_ESTATE = "4";

    /** 图片类型--公司logo */
    public static final String PIC_TYPE_LOGO = "5";

    /** 图片类型--公司资质*/
    public static final String PIC_TYPE_CERT = "6";

    /** 图片类型--经纪人头像（方） */
    public static final String PIC_TYPE_AGENT = "7";

    /** 图片类型--经纪人头像（圆） */
    public static final String PIC_TYPE_AGENT_CIRCLE = "8";

    /** 图片类型--委托书 */
    public static final String PIC_TYPE_PROXY = "9";


    /**
     * 图片路径和格式分隔符
     */
    public static final String SPLIT_FORMAT="\\|";


    public static String getAdminLoginId() {
        return ADMIN_LOGIN_ID;
    }

    public static String getAdminId() {
        return ADMIN_ID;
    }

    /********************************* 发送短信 ***********************************/
    public final static String SMS_PLAT_URL = "http://101.200.29.88:8082/SendMT/SendMessage";// 短信接口

    public final static String SMS_CORP_ID = "huizhi";// 短信接口账户

    public final static String SMS_PWD = "hzly1q2w3e";// 短信接口密码

    /**
     * MOBILE_CODE_SEND_SUCCESS:手机验证码发送成功
     */
    public static final String MOBILE_CODE_SEND_SUCCESS = "0";

    /**
     * MOBILE_CODE_SEND_ERROR:发送短信验证码失败
     */
    public static final String MOBILE_CODE_SEND_ERROR = "1";

    /**
     * MOBILE_CODE_SEND_TIMES:发送次数太多
     */
    public static final String MOBILE_CODE_SEND_TIMES = "2";

    /**
     * PHONECODE_RIGHT_CODE:手机验证码正确
     */
    public static final String PHONECODE_RIGHT_CODE = "0";

    /**
     * PHONECODE_ERROR_CODE:手机验证码错误
     */
    public static final String PHONECODE_ERROR_CODE = "1";

    /**
     * PHONECODE_VALID_CODE:手机验证码已失效
     */
    public static final String PHONECODE_VALID_CODE = "2";

    /********************************* 角色 ***********************************/

    /**
     * FSH_OPERATOR_ROLE_ID:房生活管理员
     */
    public static final String FSH_ADMIN_ROLE_ID = "1";

    /**
     * FSH_OPERATOR_ROLE_ID:房生活运维
     */
    public static final String FSH_OPERATOR_ROLE_ID = "2";

    /**
     * COMPANY_ADMIN_ROLE_ID:经纪公司管理员
     */
    public static final String COMPANY_ADMIN_ROLE_ID = "3";

    /**
     * COMPANY_OPERATOR_ROLE_ID:经纪公司运维角色
     */
    public static final String COMPANY_OPERATOR_ROLE_ID = "4";

    /**
     * COMPANY_COMMON_ROLE_ID:经纪公司经纪人角色
     */
    public static final String COMPANY_COMMON_ROLE_ID = "5";

    /**
     * COMPANY_ADMIN_ROLE_ID:普通经纪公司管理员
     */
    public static final String COMMON_COMPANY_ADMIN_ROLE_ID = "6";

    /**
     * COMPANY_OPERATOR_ROLE_ID:普通经纪公司运维角色
     */
    public static final String COMMON_COMPANY_OPERATOR_ROLE_ID = "7";

    /**
     * COMPANY_COMMON_ROLE_ID:普通经纪公司经纪人角色
     */
    public static final String COMMON_COMPANY_COMMON_ROLE_ID = "8";


    /**
     * 电梯标志--一梯两户
     */
    public static final String LIFT_FLAG_1_2 = "1";
    /**
     * 电梯标志--一梯三户
     */
    public static final String LIFT_FLAG_1_3 = "2";
    /**
     * 电梯标志--两梯三户
     */
    public static final String LIFT_FLAG_2_3 = "3";
    /**
     * 电梯标志--两梯四户
     */
    public static final String LIFT_FLAG_2_4 = "4";
    /**
     * 电梯标志--其他
     */
    public static final String LIFT_FLAG_OTHER = "9";

    /**
     * cookie路径：/www.fangshenghuo.net
     */
    public static final String COOKIE_PATH = "/";

    /**
     * cookie id
     */
    public static final String COOKIE_ID = "FSHID";

    /**
     * cookie过期时间
     */
    public static final int COOKIE_MAXAGE = 10 * 365 * 24 * 60 * 60;

    /**
     * 用户状态--无效
     */
    public static final String USER_STATUS_UNABLE="0";
    /**
     * 用户状态--有效
     */
    public static final String USER_STATUS_ENABLE="1";
    /**
     * 用户状态--锁定
     */
    public static final String USER_STATUS_LOCK="2";
    /**
     * 用户状态--需激活
     */
    public static final String USER_STATUS_ACT_NEEDED="3";

    /**
     * 员工状态--在职
     */
    public static final String EMP_STATUS_ONJOB="在职";
    /**
     * 员工状态--请假
     */
    public static final String EMP_STATUS_LEAVE="请假";
    /**
     * 员工状态--离职
     */
    public static final String EMP_STATUS_DIMISSION="离职";

    /**
     * XSH---新房，出售，住宅
     */
    public static final String HOUSE_TYPE_XSH = "XSH";


    /**
     * ESH---二手房，出售，住宅
     */
    public static final String HOUSE_TYPE_ESH = "ESH";

    /**
     * ERH--二手房，出租，住宅
     */
    public static final String HOUSE_TYPE_ERH="ERH";
    /**
     * ESS---二手房，出售，商铺
     */
    public static final String HOUSE_TYPE_ESS="ESS";
    /**
     * ERS--二手房，出租，商铺
     */
    public static final String HOUSE_TYPE_ERS="ERS";
    /**
     * ESF---二手房，出售，厂房
     */
    public static final String HOUSE_TYPE_ESF="ESF";

    /**
     * ERF--二手房，出租，厂房
     */
    public static final String HOUSE_TYPE_ERF="ERF";
    /**
     * ESO--二手房，出售，写字间
     */
    public static final String HOUSE_TYPE_ESO="ESO";
    /**
     * ERO--二手房，出租，写字间
     */
    public static final String HOUSE_TYPE_ERO="ERO";

    /**
     * ESV---二手房，出售，别墅
     */
    public static final String HOUSE_TYPE_ESV="ESV";
    /**
     * ERV--二手房，出租，别墅
     */
    public static final String HOUSE_TYPE_ERV="ERV";


    /**
     * 房产网站登录信息Session名称
     */
    public static final String SESSION_USER = "frontUserInfo";

    /**
     *房产网站登录信息SESSION_VERIFY_CODE名称
     */
    public static final String SESSION_VERIFY_CODE = "frontVerifyCode";

    /**
     *房产网站第三方登录信息SESSION_SOCIAL_CODE名称
     */
    public static final String SESSION_SOCIAL_CODE = "frontSocialCode";

    /**
     *房产网站第三方登录信息城市站地址
     */
    public static final String SESSION_SOCIAL_URL = "frontCityUrl";
    /**
     * 房产网站页面地址--首页
     */
    public static final String PAGE_INDEX = "/common/index";

    /**
     * 房产网站页面地址--登录
     */
    public static final String PAGE_LOGIN = "/common/login";

    /**
     * 房产网站页面地址--权限不足
     */
    public static final String PAGE_UAUTH = "/common/unauth";

    /**
     * 房产网站页面地址--错误
     */
    public static final String PAGE_ERROR = "/common/error";

    /**
     * 房产网站第三方完善资料地址
     */
    public static final String PAGE_OPENID = "/common/openid";

    /**
     *后台登录信息Session名称
     */
    public static final String ADMIN_SESSION_USER = "adminUserInfo";

    /**
     *后台登录信息SESSION_VERIFY_CODE名称
     */
    public static final String  ADMIN_SESSION_VERIFY_CODE = "adminVerifyCode";

    /**
     *后台登录信息cityid
     */
    public static final String ADMIN_SESSION_CITYID = "cityid";

    /**
     *后台登录信息deptid
     */
    public static final String ADMIN_SESSION_DEPTID = "deptid";

    /**
     * 后台页面地址--登录
     */
    public static final String ADMIN_PAGE_LOGIN = "/common/login";

    /**
     * 后台页面地址--首页
     */
    public static final String ADMIN_PAGE_INDEX = "/common/index";

    /**
     * 后台页面地址--权限不足
     */
    public static final String  ADMIN_PAGE_UAUTH = "/common/unauth";

    /**
     * 后台页面地址--错误
     */
    public static final String  ADMIN_PAGE_ERROR = "/common/error";


    /**
     * 消息记录mongodb collection名称
     */
    public static final String MSG_COLLECTION="moSynchroMsgLog";

    /**
     * 消息记录mongodb collection名称
     */
    public static final String MSG_TMP_COLLECTION="moSynchroMsgLogTmp";
    /**
     * 消息处理失败mongodb collection名称
     */
    public static final String MSG_ERROR_COLLECTION="moSynchroMsgLogError";

    /**
     * 单位--租价--每月
     */
    public static final String UNIT_RENT_MONTH="元/月";
    /**
     * 单位--租价--每年
     */
    public static final String UNIT_RENT_YEAR="元/年";

    /**
     * 单位--租价--元/平/天
     */
    public static final String UNIT_RENT_SQUARE="元/平/天";

    /**
     * 物业用途--住宅
     */
    public static final String PROPERTYUSAGE_HOUSE="住宅";
    /**
     * 物业用途--商住
     */
    public static final String PROPERTYUSAGE_BUSIHOUSE="商住";
    /**
     * 物业用途--公寓
     */
    public static final String PROPERTYUSAGE_APARTMENT="公寓";
    /**
     * 物业用途--地下室
     */
    public static final String PROPERTYUSAGE_BASEMENT="地下室";
    /**
     * 物业用途--车位
     */
    public static final String PROPERTYUSAGE_PARK="车位";
    /**
     * 物业用途--写字楼
     */
    public static final String PROPERTYUSAGE_OFFICE="写字楼";
    /**
     * 物业用途--商铺
     */
    public static final String PROPERTYUSAGE_SHOP="商铺";
    /**
     * 物业用途--厂房
     */
    public static final String PROPERTYUSAGE_FACTORY="厂房";
    /**
     * 物业用途--别墅
     */
    public static final String PROPERTYUSAGE_VILLA="别墅";



    /**
     * 用户默认密码
     */
    public static final String USER_DEFAULT_PASSWORD="123456";

//    /**
//     * 网站登录地址
//     */
//    public static final String LOGIN_URL="www.fangshenghuo.net";

    /**
     * 网站登录地址
     */
    public static final String LOGIN_URL="http://www.fangshenghuo.net/views/index/login.html";


    /**
     * 普通经济公司账号管理(批量导入账号模板文件)
     */
    public static final String BATCH_IMPORT_USER_TEMPLATE_FILENAME="user_account_template.xlsx";


    /**
     * 1核心推送 2手动录入，默认1
     */
    public static final Integer SOURCE_FLAG_A = 1;
    public static final Integer SOURCE_FLAG_B = 2;

    /**
     * 发布状态：1已发布 2未发布 3下架
     */
    public static final Integer RELEASESTATE_FLAG_A = 1;
    public static final Integer RELEASESTATE_FLAG_B = 2;
    public static final Integer RELEASESTATE_FLAG_C = 3;


    /**
     1001：楼盘
     1002：房源
     1003：企业Logo
     1004：企业视频
     1005：经纪人头像

     2001：房源
     2002：企业Logo
     2003：企业信息营业执照
     2004：普通会员头像
     */
    public static final String PIC_SOURCE_SUBTYPE_A_1 = "1001";
    public static final String PIC_SOURCE_SUBTYPE_A_2 = "1002";
    public static final String PIC_SOURCE_SUBTYPE_A_3 = "1003";
    public static final String PIC_SOURCE_SUBTYPE_A_4 = "1004";
    public static final String PIC_SOURCE_SUBTYPE_A_5 = "1005";

    public static final String PIC_SOURCE_SUBTYPE_B_1 = "2001";
    public static final String PIC_SOURCE_SUBTYPE_B_2 = "2002";
    public static final String PIC_SOURCE_SUBTYPE_B_3 = "2003";
    public static final String PIC_SOURCE_SUBTYPE_B_4 = "2004";

    /**
     *鉴黄：限制时间，默认1秒 1000毫秒
     */
    public static final int GREEN_IMG_LIMIT_TIME = 1000;
    /**
     *鉴黄：限制1秒内访问次数，默认10次
     */
    public static final int GREEN_IMG_COUNT = 10;
    /**
     *鉴黄：限制访问时间间隔，默认3分钟 180000毫秒
     */
    public static final int GREEN_IMG_SPACE_TIME = 180000;


    /**
     *详情页前缀，wap端
     */
    public static final String DETAIL_PATH_PRE_WAP = "W";
    /**
     *详情页前缀，手机端
     */
    public static final String DETAIL_PATH_PRE_APP = "A";



    /******************** 测试用 暂时写死 start *******************************/

    //济南的城市ID
    public static final String CITY_ID_JN = "30f5d677-f015-11e5-94fa-687309d306a5";


    /******************** 测试用 暂时写死 end *******************************/

    /**
     * 片区批量导入(批量导入账号模板文件)
     */
    public static final String BATCH_IMPORT_PIC_TEMPLATE_FILENAME="pic_account_template.xlsx";


}
