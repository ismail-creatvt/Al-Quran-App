package com.ismail.creatvt.quranapp.specificsurah;

import com.google.gson.annotations.SerializedName;

public class VersesItem{

	@SerializedName("number")
	public  Number number;

	@SerializedName("meta")
	public  Meta meta;

	@SerializedName("translation")
	public  Translation translation;

	@SerializedName("tafsir")
	public  Tafsir tafsir;

	@SerializedName("text")
	public  Text text;

	@SerializedName("audio")
	public  Audio audio;
}