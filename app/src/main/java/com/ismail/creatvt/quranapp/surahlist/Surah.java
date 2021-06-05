package com.ismail.creatvt.quranapp.surahlist;

import com.google.gson.annotations.SerializedName;

public class Surah {

	@SerializedName("number")
	public int number;

	@SerializedName("sequence")
	public int sequence;

	@SerializedName("numberOfVerses")
	public int numberOfVerses;

	@SerializedName("revelation")
	public Revelation revelation;

	@SerializedName("name")
	public Name name;

	@SerializedName("tafsir")
	public Tafsir tafsir;
}