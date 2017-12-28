package com.aak1247.maps;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MapUtils {
    public String getStringFromMap(Map map, String name) {
        String listPattern = ".*\\[\\d*]";
        String[] nameList = name.split("\\.");
        int count = 0;
        String curKey = nameList[0];
        Object curValue = null;
        while (count < nameList.length){
            if (Pattern.matches(listPattern, curKey)){
                //check if it a list
                String[] curKeyList = curKey.split("(\\[|])");
                String curKeyName = curKeyList[0];
                int index = Integer.parseInt(curKeyList[1]);
                //get the list
                curValue = map.get(curKeyName);
                //get the list element as map
                curValue = ((List)curValue).get(index);
                if (curValue instanceof String){
                    return (String) curValue;
                }
                curKey = nameList[++count];
                map = (Map)curValue;
            }
            //next level
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
                //check if it a list
                String[] curKeyList = curKey.split("(\\[|])");
                String curKeyName = curKeyList[0];
                int index = Integer.parseInt(curKeyList[1]);
                //get list
                curValue = map.get(curKeyName);
                //get the list element as map
                curValue = ((List)curValue).get(index);
                if (curValue instanceof String){
                    return curValue;
                }
                curKey = nameList[++count];
                map = (Map)curValue;
            }
            //next level
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
