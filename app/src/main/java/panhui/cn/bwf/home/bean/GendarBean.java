package panhui.cn.bwf.home.bean;

import java.io.Serializable;

public class GendarBean  implements Serializable {

	private int gendarId;
	private String gendarName;
	public int getGendarId() {
		return gendarId;
	}
	public void setGendarId(int gendarId) {
		this.gendarId = gendarId;
	}
	public String getGendarName() {
		return gendarName;
	}
	public void setGendarName(String gendarName) {
		this.gendarName = gendarName;
	}
}
