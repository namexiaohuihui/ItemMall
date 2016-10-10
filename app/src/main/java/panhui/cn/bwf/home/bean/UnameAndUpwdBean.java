package panhui.cn.bwf.home.bean;

/**
 * 用户个人类
 * Created by Administrator on 2016/6/19.
 */
public class UnameAndUpwdBean {
//  用户id
    private int userId;
    //  用户昵称
    private String nickName;
    //  用户名
    private String uname;
    //  用户密码
    private String upwd;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }


    @Override
    public String toString() {
        return "UnameAndUpwdBean{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }
}
