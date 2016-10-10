package panhui.cn.bwf.home.bean;

/**
 * Created by Administrator on 2016/6/20.
 */
public class ReceiveBean {
//用户id
    private UserBean user;
//是否默认
    private int isDefault;
//   接收地址
    private String receiveAddress;
//    区域id
    private int receiveAreaId;
    //    接收人电话
    private String receiveTel;
    //    接收人姓名
    private String receiver;



    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getReceiveAreaId() {
        return receiveAreaId;
    }

    public void setReceiveAreaId(int receiveAreaId) {
        this.receiveAreaId = receiveAreaId;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
