package com.github.qywx.core.constant;

/**
 * Created by kaijun on 2017/12/10.
 * @author tangkaijun
 * @email 571856518@qq.com
 * @description:微信API
 */
public class WxAPI {


    /***********----------------------------第一部分企业微信核心API获取AccessToken----------------------------------*****/
    public final static String TOKEN_GET_URL  = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRECT";


    /*************--------------------------第二部分通讯录管理-------------------------------------**********************/
    /**成员管理*/
    //创建成员 （post）
    public final  static String USER_CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=ACCESS_TOKEN";
    //读取成员 （get）
    public final static  String USER_GET_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";
    //更新成员 （post）
    public final static  String USER_UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=ACCESS_TOKEN";
    //删除成员 （get）
    public final static  String USER_DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";
    //批量删除成员（post）
    public final static  String USER_BATCH_DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=ACCESS_TOKEN";
    //获取部门成员 （get）
    public final static String USER__SIMPLELIST_URl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
    //获取部门成员详情 (get)
    public final static String USER_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD";
    //userid与openid互换(post)
    public final static String USER_TO_OPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=ACCESS_TOKEN";
    //openid转userid
    public final static String OPENID_TO_USER = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid?access_token=ACCESS_TOKEN";
    //二次验证
    public final static String USER_AUTH_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=ACCESS_TOKEN&userid=USERID";

    /**部门管理*/
    //创建部门 (post)
    public final static String DEPARTMENT_CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=ACCESS_TOKEN";
    //更新部门 （post）
    public final static String DEPARTMENT_UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=ACCESS_TOKEN";
    //删除部门 （get）
    public final static String DEPARTMENT_DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=ACCESS_TOKEN&id=ID";
    //获取部门列表（get）
    public final static String DEPARTMENT_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=ACCESS_TOKEN&id=ID";

    /**标签管理*/
    //创建标签(post)
    public final static String TAG_CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN";
    //更新标签（post）
    public final static String TAG_UPDATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token=ACCESS_TOKEN";
    //删除标签(get)
    public final static String TAG_DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token=ACCESS_TOKEN&tagid=TAGID";
    //获取标签成员（get）
    public final static String TAG_GETUSER_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?access_token=ACCESS_TOKEN&tagid=TAGID";
    //新增标签成员（post）
    public final static String TAG_ADDUSER_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers?access_token=ACCESS_TOKEN";
    //删除标签成员（get）
    public final static String TAG_DELUSER_URL = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers?access_token=ACCESS_TOKEN";
    //获取标签列表（get）
    public final static String TAG_LIST_URl = "https://qyapi.weixin.qq.com/cgi-bin/tag/list?access_token=ACCESS_TOKEN";

    /**异步任务**/
    //增量更新成员(post)
    public final static String BATCH_SYNADDUSER_URL = "https://qyapi.weixin.qq.com/cgi-bin/batch/syncuser?access_token=ACCESS_TOKEN";
    //全量覆盖成员(post)
    public final static String BATCH_REPLACEUSER_URL = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceuser?access_token=ACCESS_TOKEN";
    //全量覆盖部门 (post)
    public final static String BATCH_REPLACEPARTY_URL = "https://qyapi.weixin.qq.com/cgi-bin/batch/replaceparty?access_token=ACCESS_TOKEN";
    //获取异步任务结果(get)
    public final static String BATCH_GETRESULT_URL = "https://qyapi.weixin.qq.com/cgi-bin/batch/getresult?access_token=ACCESS_TOKEN&jobid=JOBID";

    /*************************------------------第三部门应用管理-----------------------------------************************************/
    /**获取应用(get)*/
    public final static String AGENT_GET_URL = "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token=ACCESS_TOKEN&agentid=AGENTID";
    /**设置应用（post）*/
    public final static String AGENT_SET_URL = "https://qyapi.weixin.qq.com/cgi-bin/agent/set?access_token=ACCESS_TOKEN";
    /**获取应用列表（get）*/
    public final static String AGNET_LIST_URL = "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token=ACCESS_TOKEN";
    /**自定义菜单*/
    //创建菜单（post）
    public final static String MENU_CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID";
    //获取菜单（get）
    public final static String MENU_GET_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN&agentid=AGENTID";
    //删除菜单（get）
    public final static String MENU_DELETE_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN&agentid=AGENTID";

    /**********--------------------------------第四部分消息推送---------------------------***********************************/
    /**发送消息（post）*/
    public final static String MESSAGE_SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    /********---------------------------------第五部分素材管理----------------------------***********************************/
    //上传临时素材(post)
    public final static String MEDIA_UPLOAD_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    //获取临时素材（get）
    public final static String MEDIA_GET_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    /********---------------------------------第六部分身份验证----------------------------***********************************/
    //获取code(get)
    public final static String USER_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&agentid=AGENTID&state=STATE#wechat_redirect";
    //更加code获取成员信息(get)
    public final static String USER_INFO_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
    //使用user_ticket获取成员详情(post)
    public final static String USER_LIST_TICKET_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN";

    /********---------------------------------第七部分OA数据接口----------------------------******************************/
    //获取打卡规则（post）
    public final static String CHECKIN_GET_URL= "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckinoption?access_token=ACCESS_TOKEN";
    //获取打卡数据(post)
    public final static String CHECKIN_GETDATA_URL = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token=ACCESS_TOKEN";
    //审批打卡数据(post)
    public final static String CHECKIN_APPROVAL_DATA_URL = "https://qyapi.weixin.qq.com/cgi-bin/corp/getapprovaldata?access_token=ACCESS_TOKEN";

}
