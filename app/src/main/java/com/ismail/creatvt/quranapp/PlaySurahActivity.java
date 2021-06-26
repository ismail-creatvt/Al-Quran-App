package com.ismail.creatvt.quranapp;

import android.animation.LayoutTransition;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ismail.creatvt.quranapp.specificsurah.SpecificSurahResponse;
import com.ismail.creatvt.quranapp.specificsurah.Tafsir;
import com.ismail.creatvt.quranapp.specificsurah.TafsirSerializer;
import com.ismail.creatvt.quranapp.specificsurah.VersesItem;

import java.util.List;

import static com.ismail.creatvt.quranapp.Constant.SURAH_NUMBER;

public class PlaySurahActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private boolean isPlaying = false;
    private boolean isPaused = false;
    private TextView nameTitle, arabicTitle, revelationSubtitle, versesSubtitle, ayahArabic, ayahTranslation;
    private FloatingActionButton playPauseButton;
    private SeekBar ayahSeekbar;
    private TextView verseNumberText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_surah);

        int number = getIntent().getIntExtra(SURAH_NUMBER, 1);

        RequestQueue queue = Volley.newRequestQueue(this);

        initViews();

        Response.Listener<String> responseListener = response -> {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Tafsir.class, new TafsirSerializer())
                    .create();
            TypeToken<SpecificSurahResponse> typeToken = new TypeToken<SpecificSurahResponse>() {
            };
            SpecificSurahResponse surahData = gson.fromJson(response, typeToken.getType());
            setUpUI(surahData);
        };

        Response.ErrorListener errorListener = error -> Log.d("PlaySurahActivity", "Error while fetching surah info: " + error.getLocalizedMessage());

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://api.quran.sutanlab.id/surah/" + number,
                responseListener,
                errorListener
        );

        queue.add(request);
    }

    private void initViews() {
        nameTitle = findViewById(R.id.surah_name_title);
        arabicTitle = findViewById(R.id.surah_arabic_title);
        revelationSubtitle = findViewById(R.id.surah_revelation_subtitle);
        versesSubtitle = findViewById(R.id.surah_verses_subtitle);

        ayahArabic = findViewById(R.id.ayah_arabic);
        ayahTranslation = findViewById(R.id.ayah_translation);

        playPauseButton = findViewById(R.id.play_pause_button);

        ayahSeekbar = findViewById(R.id.ayah_seekbar);
        verseNumberText = findViewById(R.id.verse_text);

        ayahSeekbar.setVisibility(View.GONE);
        ayahSeekbar.setProgress(0);
        verseNumberText.setVisibility(View.GONE);
    }

    private int currentVerse = 0;
    private MediaPlayer versePlayer;
    private List<VersesItem> verses;

    private void setUpUI(SpecificSurahResponse surahData) {
        versesSubtitle.setText(getResources().getString(R.string.verses_count, surahData.data.numberOfVerses));
        revelationSubtitle.setText(surahData.data.revelation.en);
        arabicTitle.setText(surahData.data.name.jsonMemberLong);
        nameTitle.setText(surahData.data.name.transliteration.en);

        verses = surahData.data.verses;

        verseNumberText.setVisibility(View.VISIBLE);
        verseNumberText.setText(getString(R.string.verses_count, 1));
        ayahSeekbar.setMax(verses.size() - 1);

        ayahSeekbar.setOnSeekBarChangeListener(this);

        ayahArabic.setText(verses.get(currentVerse).text.arab);
        ayahTranslation.setText(verses.get(currentVerse).translation.en);

        playPauseButton.setOnClickListener((v) -> {
            if (isPlaying) {
                playPauseButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                isPlaying = false;
                isPaused = true;
                ayahSeekbar.setVisibility(View.GONE);
                versePlayer.pause();
            } else if (isPaused) {
                playPauseButton.setImageResource(R.drawable.ic_baseline_pause_24);
                isPaused = false;
                isPlaying = true;
                ayahSeekbar.setVisibility(View.VISIBLE);
                versePlayer.start();
            } else {
                playPauseButton.setImageResource(R.drawable.ic_baseline_pause_24);
                isPlaying = true;
                ayahSeekbar.setVisibility(View.VISIBLE);
                ayahSeekbar.setProgress(currentVerse);
                playCurrentVerse();
            }
        });
    }

    private void playCurrentVerse() {
        stopPlaying();
        try {
            verseNumberText.setText(getString(R.string.verses_count, currentVerse + 1));
            animateAyah(new Pair<>(
                    verses.get(currentVerse).text.arab,
                    verses.get(currentVerse).translation.en
            ));
            isPlaying = true;
            isPaused = false;
            versePlayer = new MediaPlayer();
            versePlayer.setOnCompletionListener(this);
            versePlayer.setDataSource(getCurrentVerseUrl());
            versePlayer.prepare();
            versePlayer.start();
        } catch (Exception e) {
            isPlaying = false;
            isPaused = false;
            Log.d("PlaySurahActivity", "Exception while playing: " + e.getMessage());
        }
    }

    private void animateAyah(Pair<String, String> verseText) {
        ayahArabic.animate().alpha(0.5f)
                .withEndAction(() -> {
                    ayahArabic.setText(verseText.first);
                    ayahArabic.animate().alpha(1f).start();
                }).start();
        ayahTranslation.animate().alpha(0.5f)
                .withEndAction(() -> {
                    ayahTranslation.setText(verseText.second);
                    ayahTranslation.animate().alpha(1f).start();
                }).start();
    }

    private void stopPlaying() {
        if (versePlayer != null) {
            try {
                versePlayer.release();
                versePlayer = null;
            } catch (Exception ignored) {
            }
        }
    }

    private String getCurrentVerseUrl() {
        return verses.get(currentVerse).audio.secondary.get(0);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        isPlaying = false;
        isPaused = false;
        if (currentVerse < verses.size() - 1) {
            currentVerse++;
            ayahSeekbar.setProgress(currentVerse);
            playCurrentVerse();
        } else {
            currentVerse = 0;
            stopPlaying();
            ayahSeekbar.setVisibility(View.GONE);
            ayahSeekbar.setProgress(0);
            playPauseButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            animateAyah(new Pair<>(
                    "",
                    ""
            ));
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            currentVerse = progress;
            playCurrentVerse();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}