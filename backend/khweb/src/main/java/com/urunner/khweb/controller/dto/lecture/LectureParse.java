package com.urunner.khweb.controller.dto.lecture;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;

import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
public class LectureParse {

    public static org.json.JSONObject parsingInfo(LinkedHashMap test) {

        String jsonTest = new JSONObject(test).toString();

        org.json.JSONObject jsonObject = new org.json.JSONObject(jsonTest);

        return jsonObject;
    }

    public static org.json.JSONObject parsingInfoWithString(LinkedHashMap test, String Info) {

        String jsonTest = new JSONObject(test).toString();

        org.json.JSONObject jsonObject = new org.json.JSONObject(jsonTest);

        org.json.JSONObject infoWithString = jsonObject.getJSONObject(Info);

        return infoWithString;
    }




}
