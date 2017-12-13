package com.github.qywx.core.constant;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaijun on 2017/12/10.
 * 微信接口错误状态码
 */
public class WxRCode {

    private static Map<Integer,String> statusMap = new HashMap<Integer,String>();

    static {
        statusMap.put(-1,"系统繁忙");
        statusMap.put(0,"请求成功");
        statusMap.put(40001,"不合法的secret参数");
        statusMap.put(40003,"无效的UserID");
        statusMap.put(40004,"不合法的媒体文件类型");
        statusMap.put(40005,"不合法的type参数");
        statusMap.put(40006,"不合法的文件大小");
        statusMap.put(40007,"不合法的media_id参数");
        statusMap.put(40008,"不合法的msgtype参数");
        statusMap.put(40009,"上传图片大小不是有效值");
        statusMap.put(40011,"上传视频大小不是有效值");
        statusMap.put(40013,"不合法的CorpID");
        statusMap.put(40014,"不合法的access_token");
        statusMap.put(40016,"不合法的按钮个数");
        statusMap.put(40017,"不合法的按钮类型");
        statusMap.put(40018,"不合法的按钮名字长度");
        statusMap.put(40019,"不合法的按钮KEY长度");
        statusMap.put(40020,"不合法的按钮URL长度");
        statusMap.put(40022,"不合法的子菜单级数");
        statusMap.put(40023,"不合法的子菜单按钮个数");
        statusMap.put(40024,"不合法的子菜单按钮类型");
        statusMap.put(40025,"不合法的子菜单按钮名字长度");
        statusMap.put(40026,"不合法的子菜单按钮KEY长度");
        statusMap.put(40027,"不合法的子菜单按钮URL长度");
        statusMap.put(40029,"不合法的oauth_code");
        statusMap.put(40031,"不合法的UserID列表");
        statusMap.put(40032,"不合法的UserID列表长度");
        statusMap.put(40033,"不合法的请求字符");
        statusMap.put(40035,"不合法的参数");
        statusMap.put(40050,"chatid不存在");
        statusMap.put(40054,"不合法的子菜单url域名");
        statusMap.put(40055,"不合法的菜单url域名");
        statusMap.put(40056,"不合法的agentid");
        statusMap.put(40057,"不合法的callbackurl或者callbackurl验证失败");
        statusMap.put(40058,"不合法的参数");
        statusMap.put(40059,"不合法的上报地理位置标志位");
        statusMap.put(40063,"参数为空");
        statusMap.put(40066,"不合法的部门列表");
        statusMap.put(40068,"不合法的标签ID");
        statusMap.put(40070,"指定的标签范围结点全部无效");
        statusMap.put(40071,"不合法的标签名字");
        statusMap.put(40072,"不合法的标签名字长度");
        statusMap.put(40073,"不合法的openid");
        statusMap.put(40074,"news消息不支持保密消息类型");
        statusMap.put(40077,"不合法的pre_auth_code参数");
        statusMap.put(40078,"不合法的auth_code参数");
        statusMap.put(40080,"不合法的suite_secret");
        statusMap.put(40082,"不合法的suite_token");
        statusMap.put(40083,"不合法的suite_id");
        statusMap.put(40084,"不合法的permanent_code参数");
        statusMap.put(40085,"不合法的的suite_ticket参数");
        statusMap.put(40086,"不合法的第三方应用appid");
        statusMap.put(40088,"jobid不存在");
        statusMap.put(40089,"批量任务的结果已清理");
        statusMap.put(40092,"导入文件存在不合法的内容");
        statusMap.put(40093,"不合法的jsapi_ticket参数");
        statusMap.put(40094,"不合法的URL");
        statusMap.put(41001,"缺少access_token参数");
        statusMap.put(41002,"缺少corpid参数");
        statusMap.put(41004,"缺少secret参数");
        statusMap.put(41006,"缺少media_id参数");
        statusMap.put(41008,"缺少auth code参数");
        statusMap.put(41009,"缺少userid参数");
        statusMap.put(41010,"缺少url参数");
        statusMap.put(41011,"缺少agentid参数");
        statusMap.put(41033,"缺少 description 参数");
        statusMap.put(41016,"缺少title参数");
        statusMap.put(41019,"缺少 department 参数");
        statusMap.put(41017,"缺少tagid参数");
        statusMap.put(41021,"缺少suite_id参数");
        statusMap.put(41025,"缺少permanent_code参数");
        statusMap.put(42001,"access_token已过期");
        statusMap.put(42007,"pre_auth_code已过期");
        statusMap.put(42009,"suite_access_token已过期");
        statusMap.put(43004,"指定的userid未绑定微信或未关注微信插件");
        statusMap.put(44001,"多媒体文件为空");
        statusMap.put(44004,"文本消息content参数为空");
        statusMap.put(45001,"多媒体文件大小超过限制");
        statusMap.put(45002,"消息内容大小超过限制");
        statusMap.put(45004,"应用description参数长度不符合系统限制");
        statusMap.put(45007,"语音播放时间超过限制");
        statusMap.put(45008,"图文消息的文章数量不符合系统限制");
        statusMap.put(45009,"接口调用超过限制");
        statusMap.put(45022,"应用name参数长度不符合系统限制");
        statusMap.put(45024,"帐号数量超过上限");
        statusMap.put(45026,"触发删除用户数的保护");
        statusMap.put(45032,"图文消息author参数长度超过限制");
        statusMap.put(46003,"菜单未设置");
        statusMap.put(46004,"指定的用户不存在");
        statusMap.put(48002,"API接口无权限调用");
        statusMap.put(48003,"不合法的suite_id");
        statusMap.put(48004,"授权关系无效");
        statusMap.put(48005,"API接口已废弃");
        statusMap.put(50001,"redirect_url未登记可信域名");
        statusMap.put(50002,"成员不在权限范围");
        statusMap.put(50003,"应用已禁用");
        statusMap.put(60001,"部门长度不符合限制");
        statusMap.put(60003,"部门ID不存在");
        statusMap.put(60004,"父部门不存在");
        statusMap.put(60005,"部门下存在成员");
        statusMap.put(60006,"部门下存在子部门");
        statusMap.put(60007,"不允许删除根部门");
        statusMap.put(60008,"部门已存在");
        statusMap.put(60009,"部门名称含有非法字符");
        statusMap.put(60010,"部门存在循环关系");
        statusMap.put(60011,"指定的成员/部门/标签参数无权限");
        statusMap.put(60012,"不允许删除默认应用");
        statusMap.put(60020,"访问ip不在白名单之中");
        statusMap.put(60028,"不允许修改第三方应用的主页");
        statusMap.put(60102,"UserID已存在");
        statusMap.put(60103,"手机号码不合法");
        statusMap.put(60104,"手机号码已存在");
        statusMap.put(60105,"邮箱不合法");
        statusMap.put(60106,"邮箱已存在");
        statusMap.put(60107,"微信号不合法");
        statusMap.put(60110,"用户所属部门数量超过限制");
        statusMap.put(60111,"UserID不存在");
        statusMap.put(60112,"成员name参数不合法");
        statusMap.put(60123,"无效的部门id");
        statusMap.put(60124,"无效的父部门id");
        statusMap.put(60125,"非法部门名字");
        statusMap.put(60127,"缺少department参数");
        statusMap.put(60129,"成员手机和邮箱都为空");
        statusMap.put(72023,"发票已被其他公众号锁定");
        statusMap.put(72024,"发票状态错误");
        statusMap.put(72037,"存在发票不属于该用户");
        statusMap.put(80001,"可信域名不正确");
        statusMap.put(81001,"部门下的结点数超过限制（3W）");
        statusMap.put(81002,"部门最多15层");
        statusMap.put(81011,"无权限操作标签");
        statusMap.put(82001,"指定的成员/部门/标签全部无效");
        statusMap.put(82002,"不合法的PartyID列表长度");
        statusMap.put(82003,"不合法的TagID列表长度");
        statusMap.put(84014,"成员票据过期");
        statusMap.put(84015,"成员票据无效");
        statusMap.put(84019,"确认user_ticket参数来源是否正确");
        statusMap.put(84020,"templateid不存在");
        statusMap.put(84021,"缺少register_code参数");
        statusMap.put(84022,"无效的register_code参数");
        statusMap.put(84023,"不允许调用设置通讯录同步完成接口");
        statusMap.put(84024,"无注册信息");
        statusMap.put(85002,"包含不合法的词语");
        statusMap.put(85004,"每企业每个月设置的可信域名不可超过20个");
        statusMap.put(85005,"可信域名未通过所有权校验");
        statusMap.put(86001,"参数 chatid 不合法");
        statusMap.put(86003,"参数 chatid 不存在");
        statusMap.put(86216,"存在非法会话成员ID");
        statusMap.put(86217,"会话发送者不在会话成员列表中");
        statusMap.put(86220,"指定的会话参数不合法");
        statusMap.put(90002,"缺少摇一摇周边ticket参数");
        statusMap.put(90003,"摇一摇周边ticket参数不合法");
        statusMap.put(91040,"获取ticket的类型无效");
        statusMap.put(301002,"无权限操作指定的应用");
        statusMap.put(301005,"不允许删除创建者");
        statusMap.put(301012,"参数 position 不合法");
        statusMap.put(301013,"参数 telephone 不合法");
        statusMap.put(301014,"参数 english_name 不合法");
        statusMap.put(301015,"参数 mediaid 不合法");
        statusMap.put(301016,"上传语音文件不符合系统要求");
        statusMap.put(301017,"上传语音文件仅支持AMR格式");
        statusMap.put(301021,"参数 userid 无效,至少有一个userid不存在于通讯录中");
        statusMap.put(301022,"获取打卡数据失败");
        statusMap.put(301023,"useridlist非法或超过限额");
        statusMap.put(301024,"获取打卡记录时间间隔超限");
        statusMap.put(301036,"不允许更新该用户的userid");
        statusMap.put(302004,"组织架构不合法（1不是一棵树，2 多个一样的partyid，3 partyid空，4 partyid name 空，5 同一个父节点下有两个子节点 部门名字一样 可能是以上情况，请一一排查）");
        statusMap.put(2000002,"CorpId参数无效");

    }

    public static String getErrorMsg(int code){
        String msg = "";
        if(statusMap!=null){
                msg = statusMap.get(code);
            if(msg==null||msg==""){
                msg = "未知错误";
            }
        }
        return msg;
    }

}
