package com.ismail.creatvt.quranapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ismail.creatvt.quranapp.surahlist.Surah;

import java.util.List;

public class SurahListAdapter extends RecyclerView.Adapter<SurahListAdapter.SurahViewHolder> {

    private final List<Surah> data;
    private final OnSurahClickListener surahClickListener;

    public SurahListAdapter(List<Surah> data, OnSurahClickListener surahClickListener) {
        this.data = data;
        this.surahClickListener = surahClickListener;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.surah_item_layout, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        Surah surah = data.get(position);

        holder.surahName.setText(surah.name.transliteration.en);
        holder.surahRevelation.setText(surah.revelation.en);
        holder.surahNumber.setText(String.valueOf(surah.number));
        holder.surahVerses.setText(holder.itemView.getResources().getString(R.string.verses_count, surah.numberOfVerses));
        holder.surahArabic.setText(surah.name.jsonMemberLong);
        holder.surahRoot.setOnClickListener((v)->{
            surahClickListener.onSurahClick(data.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SurahViewHolder extends RecyclerView.ViewHolder {

        public TextView surahName, surahRevelation, surahNumber, surahVerses, surahArabic;
        public ConstraintLayout surahRoot;

        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);

            surahRoot = itemView.findViewById(R.id.surah_root);
            surahName = itemView.findViewById(R.id.surah_name);
            surahRevelation = itemView.findViewById(R.id.surah_revelation);
            surahNumber = itemView.findViewById(R.id.surah_number);
            surahArabic = itemView.findViewById(R.id.surah_arabic);
            surahVerses = itemView.findViewById(R.id.surah_verses);
        }
    }
}
