package com.ismail.creatvt.quranapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ismail.creatvt.quranapp.surahlist.Surah;
import com.ismail.creatvt.quranapp.surahlist.SurahListResponse;

import static com.ismail.creatvt.quranapp.Constant.SURAH_NUMBER;

public class MainActivity extends AppCompatActivity implements OnSurahClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);

        RecyclerView surahList = findViewById(R.id.surah_list);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.quran.sutanlab.id/surah";

        Response.Listener<String> responseListener = response -> {
            Gson gson = new Gson();
            TypeToken<SurahListResponse> token = new TypeToken<SurahListResponse>() {};
            SurahListResponse surahs = gson.fromJson(response, token.getType());
            surahList.setAdapter(new SurahListAdapter(surahs.data, this));
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

    @Override
    public void onSurahClick(Surah surah) {
        Intent showDetails = new Intent(this, PlaySurahActivity.class);
        showDetails.putExtra(SURAH_NUMBER, surah.number);
        startActivity(showDetails);
    }
}