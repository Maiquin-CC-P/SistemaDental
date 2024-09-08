/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dentolife.utilitarios;


import com.Dentolife.dtos.Agenda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;


public class JsonConverter {
    
   //  private final Gson gson;

//    public JsonConverter() {
//
//        gson = new GsonBuilder().create();
//    }
    
      public String convertToJson(List<Agenda> citas) {

//        JsonArray jarray = gson.toJsonTree(citas).getAsJsonArray();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.add("x", jarray);
//       // jsonObject.add(property, jarray);

        
        String json = new Gson().toJson(citas);
        
        return json.toString();
    }
}
