package panhui.cn.bwf.home.bean;

/**
 * 查询收货地址类
 */
public class AddrBean {
//默认地址识别数，1为默认
	private int isDefault;
//	地址信息
	private String receiveAddress;
//	地区id
	private int receiveAreaId;
//	接收人id
	private int receiveId;
//	接收人电话
	private String receiveTel;
//	接收人姓名
	private String receiver;

	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
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
	public int getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
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
