package com.ismail.creatvt.quranapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ismail.creatvt.quranapp.surahlist.SurahListResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView surahList = findViewById(R.id.surah_list);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.quran.sutanlab.id/surah";

        Response.Listener<String> responseListener = response -> {
            Gson gson = new Gson();
            TypeToken<SurahListResponse> token = new TypeToken<SurahListResponse>() {};
            SurahListResponse surahs = gson.fromJson(response, token.getType());
            surahList.setAdapter(new SurahListAdapter(surahs.data));
            surahList.setLayoutManager(new LinearLayoutManager(this));
            Log.d("MainActivity", "Reponse parsed success");
        };

        Response.ErrorListener errorListener = error -> {
            Log.d("MainActivity", "Error While requesting : " + error);
        };

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}