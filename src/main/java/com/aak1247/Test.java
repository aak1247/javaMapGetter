package com.aak1247;

import org.omg.CORBA.OBJ_ADAPTER;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Test {

    public static void main(String args[]) {
        Yaml yaml = new Yaml();
        URL url = Test.class.getClassLoader().getResource("test.yml");
        if (url != null){
            try {
                Map map = (Map)yaml.load(new FileInputStream(url.getFile()));
                Test test = new Test();
                System.out.println( "name: " + test.getStringFromYaml(map, "html.body.div.form.input.name"));
                Object temp = test.getObjFromMap(map,"html.body.div.form.input.data-list");
                System.out.println( "data-list: " + temp.toString());
                System.out.println("the first one: " + test.getStringFromYaml(map, "html.body.div.form.input.data-list[0]"));
                System.out.println("the second one: " + test.getStringFromYaml(map, "html.body.div.form.input.data-list[1]"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getStringFromYaml(Map map, String name) {
        String listPattern = ".*\\[\\d*]";
        String[] nameList = name.split("\\.");
        int count = 0;
        String curKey = nameList[0];
        Object curValue = null;
        while (count < nameList.length){
            if (Pattern.matches(listPattern, curKey)){
                //检验本次是否为数组
                String[] curKeyList = curKey.split("(\\[|])");
                String curKeyName = curKeyList[0];
                int index = Integer.parseInt(curKeyList[1]);
                //取到数组
                curValue = map.get(curKeyName);
                //取到数组内元素, 放入map
                //不一定是map map = (Map) ((List)curValue).get(index);
                curValue = ((List)curValue).get(index);
                if (curValue instanceof String){
                    return (String) curValue;
                }
                curKey = nameList[++count];
                map = (Map)curValue;
            }
            //取下一级元素
            curValue = map.get(curKey);
            if (curValue instanceof Map){
                map = (Map) curValue;
                curKey = nameList[++count];
            }else if (curValue instanceof String){
                return (String)curValue;
            }else return curValue.toString();
        }
        return (String) curValue;
    }

    public Object getObjFromMap(Map map, String name) {
        String listPattern = ".*\\[\\d*]";
        String[] nameList = name.split("\\.");
        int count = 0;
        String curKey = nameList[0];
        Object curValue = null;
        while (count < nameList.length){
            if (Pattern.matches(listPattern, curKey)){
                //检验本次是否为数组
                String[] curKeyList = curKey.split("(\\[|])");
                String curKeyName = curKeyList[0];
                int index = Integer.parseInt(curKeyList[1]);
                //取到数组
                curValue = map.get(curKeyName);
                //取到数组内元素, 放入map
                //不一定是map map = (Map) ((List)curValue).get(index);
                curValue = ((List)curValue).get(index);
                if (curValue instanceof String){
                    return curValue;
                }
                curKey = nameList[++count];
                map = (Map)curValue;
            }
            //取下一级元素
            curValue = map.get(curKey);
            if (curValue instanceof Map){
                map = (Map) curValue;
                curKey = nameList[++count];
            }else if (curValue instanceof String){
                return curValue;
            }else return curValue;
        }
        return  curValue;
    }
}
