package com.aak1247;

import com.aak1247.jsons.JsonUtils;
import com.aak1247.maps.MapUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;

public class Test {

    public static void main(String args[]) {
        Yaml yaml = new Yaml();
        URL url = Test.class.getClassLoader().getResource("test.yml");
        MapUtils mapUtils = new MapUtils();
        JsonUtils jsonUtils = new JsonUtils();
        if (url != null){
            try {
                Map map = (Map)yaml.load(new FileInputStream(url.getFile()));
                System.out.println( "name: " + mapUtils.getStringFromMap(map, "html.body.div.form.input.name"));
                Object temp = mapUtils.getObjFromMap(map,"html.body.div.form.input.data-list");
                System.out.println( "data-list: " + temp.toString());
                System.out.println("the first one: " + mapUtils.getStringFromMap(map, "html.body.div.form.input.data-list[0]"));
                System.out.println("the second one: " + mapUtils.getStringFromMap(map, "html.body.div.form.input.data-list[1]"));
                System.out.println(jsonUtils.getHumanifiedStringFromJsonMap(map));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
