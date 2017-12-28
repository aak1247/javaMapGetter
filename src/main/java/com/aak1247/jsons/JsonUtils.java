package com.aak1247.jsons;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.List;
import java.util.Map;

public class JsonUtils {




    public String getHumanifiedStringFromJsonMap(Map map) {
        return getHumanifiedStringFromJsonMap(map, 0);
    }

    private String getHumanifiedStringFromJsonMap(Object map, int lev) {
        StringBuilder sb = new StringBuilder();
        Object cur = map;
        Object curKey;
        Object curVal;
        if (cur instanceof String) {
            sb.append("\"" + cur + "\"");
            return sb.toString();
        }

        if (cur instanceof Map){
            sb.append("{\n");
            for (Object entry : ((Map) cur).entrySet()) {
                curKey = ((Map.Entry) entry).getKey();
                curVal = ((Map.Entry) entry).getValue();
                sb.append(getPaddiing(lev) + " \"" + curKey + "\": ");
                if (curVal instanceof String){
                    sb.append("\"" + curVal + "\",\n");
                }
                else sb.append(getHumanifiedStringFromJsonMap( curVal, lev + 1));
            }
            sb.append(getPaddiing(lev) + "}\n");
        }

        if (cur instanceof List) {
            sb.append("\n" + sb.append(getPaddiing(lev)) +"[");
            for (Object ele : (List)cur) {
                if (((List) cur).indexOf(ele) > 0) sb.append("\n" + getPaddiing(lev));
                sb.append(getHumanifiedStringFromJsonMap( ele,lev + 1));
                if (((List) cur).indexOf(ele) < ((List)cur).size()-1)
                    sb.append(",");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    public String getPaddiing(int lev){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lev ; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }


}
