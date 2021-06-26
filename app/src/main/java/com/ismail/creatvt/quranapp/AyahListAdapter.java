package com.ismail.creatvt.quranapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ismail.creatvt.quranapp.specificsurah.VersesItem;

import java.util.List;

public class AyahListAdapter extends RecyclerView.Adapter<AyahListAdapter.AyahViewHolder>{

    private final List<VersesItem> ayahs;

    public AyahListAdapter(List<VersesItem> ayahs) {
        this.ayahs = ayahs;
    }

    @NonNull
    @Override
    public AyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ayah_item_layout, parent, false);
        return new AyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyahViewHolder holder, int position) {
        VersesItem ayah = ayahs.get(position);

        holder.ayahArabic.setText(ayah.text.arab);
        holder.ayahTranslation.setText(ayah.translation.en);
    }

    @Override
    public int getItemCount() {
        return ayahs.size();
    }

    public static final class AyahViewHolder extends RecyclerView.ViewHolder {

        public TextView ayahArabic, ayahTranslation;

        public AyahViewHolder(@NonNull View itemView) {
            super(itemView);
            ayahArabic = itemView.findViewById(R.id.ayah_arabic);
            ayahTranslation = itemView.findViewById(R.id.ayah_translation);
        }
    }
}
