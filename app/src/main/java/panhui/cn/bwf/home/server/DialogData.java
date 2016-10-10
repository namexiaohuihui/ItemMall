package panhui.cn.bwf.home.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/18.
 */
public class DialogData {

    private String countries[] = new String[]{"中国", "美国"};
    private String information [] = new String[]{"称呼","密码"};
    private String provinces [] = new String[]{"华南","东北",
            "华中","华中","西部"};
    private String fenlei [] = new String[]{"注册","登陆 "};
    /**
     *
     * @param index  为1返回国家   为2返回信息   为3返回省份
     * @return
     */
    public List<Map<String,Object>> getDialog(int index){
        switch (index){
            case 1:
//                为1返回国家
                return getDialog(countries);
            case 2:
//                为2返回信息
                return getDialog(information);
            case 3:
//               为3返回省份
                return getDialog(provinces);
            case 4:
//               为3返回分类
                return getDialog(fenlei);
        }
        return null;
    }

    private List<Map<String,Object>> getDialog(String name[]){
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0 ;i <name.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("string",name[i]);
            list.add(map);
        }
        return list;
    }
}
