package imbmob.test.api;

/**
 * Created by Administrator on 2016/4/25.
 */
public interface Server {

//    public static  String BASE_URL="http://test.ddearn.com/appservice.svc/";
//    public static  int OVER_TIME=15;

    public static String RESPONSE_OK="200";

    public static String BASE_URL = "http://webim.demo.rong.io/";

    //登录（过时）
    public final static String LOGIN_EMAIL = "email_login";

    //登录获得token
    public final static String EMAIL_LOGIN_TOKEN = "email_login_token";

    //注册
    public final static String REQ = "reg";

    //更新个人昵称
    public final static String UPDATE_PROFILE = "update_profile";

    //获得token，需要先登录（过时）
    public final static String TOKEN = "token";

    //加入群组
    public final static String JOIN_GROUP = "join_group";

    //退出群组
    public final static String QUIT_GROUP = "quit_group";

    //获取群组列表
    public final static String GET_ALL_GROUP = "get_all_group";

    //获取我加入的群组列表
    public final static String GET_MY_GROUP = "get_my_group";

    //根据群组id 获得群组信息
    public final static String GET_GROUP = "get_group";

    //按名称查询用户
    public final static String SEARCH_NAME = "seach_name";

    //获取好友列表
    public final static String GET_FRIEND = "get_friend";

    //发送好友邀请
    public final static String REQUEST_FRIEND = "request_friend";

    //删除好友
    public final static String DELETE_FRIEND = "delete_friend";

    //处理好用请求
    public final static String PROCESS_REQUEST_FRIEND = "process_request_friend";

    //根据 userid 获得用户信息
    public final static String PROFILE = "profile";

    //更新群组信息
    public final static String UPDATE_GROUP = "update_group";

    //按邮箱查询用户
    public final static String SEACH_EMAIL = "seach_email";
}
