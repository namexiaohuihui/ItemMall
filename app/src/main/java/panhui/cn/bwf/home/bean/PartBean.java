package panhui.cn.bwf.home.bean;

import java.io.Serializable;

public class PartBean implements Serializable {

	private int partId;
	private String partName;
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	
}
