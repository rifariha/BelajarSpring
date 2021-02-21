package com.example.springmongo.springmongo.helper;

import com.example.springmongo.springmongo.model.Tutorial;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomResponse {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public ArrayList hasilData(List<Tutorial> tutorials) throws JSONException {
        ArrayList list = new ArrayList();
        list.add (0,"200");
        list.add(1,"Sucess");
        list.add(2, tutorials.toString());
        return (ArrayList) list;
    }
}
