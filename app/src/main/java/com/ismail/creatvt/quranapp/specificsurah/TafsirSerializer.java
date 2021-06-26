package com.ismail.creatvt.quranapp.specificsurah;

import android.text.TextUtils;

import com.google.gson.JsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TafsirSerializer implements JsonDeserializer<Tafsir> {

    @Override
    public Tafsir deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Tafsir tafsir = new Tafsir();
        JsonObject jsonObject = json.getAsJsonObject();

        if (jsonObject.has("id")) {
            JsonElement elem = jsonObject.get("id");
            if (elem != null && !elem.isJsonNull()) {
                if(elem.isJsonObject()){
                    tafsir.id = gson.fromJson(elem, Id.class);
                } else if(elem.isJsonPrimitive()){
                    Id id = new Id();
                    id.jsonMemberLong = elem.getAsString();
                    id.jsonMemberShort = "";
                    tafsir.id = id;
                }
            }
        }
        return tafsir;
    }
}