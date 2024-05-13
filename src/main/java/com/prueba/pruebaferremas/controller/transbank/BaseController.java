package com.prueba.pruebaferremas.controller.transbank;

import lombok.Getter;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

public abstract class BaseController {
    @Getter private Map<String, Object> model = new HashMap<>();

    protected void addRequest(String key, Object value) {
        Object request = getModel().get("request");
        if (null == request) {
            request = new HashMap<String, Object>();
            getModel().put("request", request);
        }

        if (request instanceof Map) {
            ((Map) request).put(key, value);
        }
    }

    protected void cleanModel() {
        model.clear();
    }

    protected void prepareLoger(Level logLevel) {

    }

    public String toJson(Object obj){
        return (new GsonBuilder().setPrettyPrinting().create()).toJson(obj);
    }

    protected String getRandomNumber(){
        return String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
    }
}
