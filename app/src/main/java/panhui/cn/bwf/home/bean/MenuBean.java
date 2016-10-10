package panhui.cn.bwf.home.bean;

/**
 * Created by Administrator on 2016/6/13.
 */
public class MenuBean {

    private String name;
    private int state = 0;

    public MenuBean() {
    }

    public MenuBean(String name) {
        //
        this.name = name;//
    }

    public String getName() {
        //
        return name;//
    }

    public void setName(String name) {
        //
        this.name = name;//
    }

    public int getState() {//
        return state;//
    }

    public void setState(int state) {
        //
        this.state = state;//
    }

}
