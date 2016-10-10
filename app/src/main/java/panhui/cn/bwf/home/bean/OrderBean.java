package panhui.cn.bwf.home.bean;

/**添加訂單的接口：
 * 需要用户的id以及本身的信息
 * Created by Administrator on 2016/6/17.
 */
public class OrderBean {
//    订单id
    private int bookId;
//    用户对象类
    private UserBean user;
//    订单状态
    private int bookState;
//    订单时间
    private String bookTime;
    //	地址信息
    private String receiveAddress;
    //	地区id
    private int receiveAreaId;
    //	接收人电话
    private String receiveTel;
    //	接收人姓名
    private String receiver;
    //  提交訂單的时间
    private String sendTime;
    //接收的时间
    private String receiveTime;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getBookState() {
        return bookState;
    }

    public void setBookState(int bookState) {
        this.bookState = bookState;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public int getReceiveAreaId() {
        return receiveAreaId;
    }

    public void setReceiveAreaId(int receiveAreaId) {
        this.receiveAreaId = receiveAreaId;
    }

    public String getReceiveTel() {
        return receiveTel;
    }

    public void setReceiveTel(String receiveTel) {
        this.receiveTel = receiveTel;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }
}
