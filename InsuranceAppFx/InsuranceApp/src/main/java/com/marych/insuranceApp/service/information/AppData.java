package com.marych.insuranceApp.service.information;

import java.util.HashMap;
import java.util.Map;

public class AppData {
    private static AppData appData;
    private Map<String,String> args;

    private AppData(){
        args = new HashMap<>();
    }

    public static AppData getInstance() {
        if (appData == null) {
            appData = new AppData();
        }
        return appData;
    }

    public Map<String,String>  getArgs() {
        return args;
    }


    public AppData clear(){
        args.clear();
        return  this;
    }
    public AppData clear(String key){
        args.remove(key);
        return  this;
    }
    public AppData put(String key,String value){
        args.put(key,value);
        return this;
    }
    public String get(String key){
        return args.get(key);
    }
}
