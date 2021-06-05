package com.ismail.creatvt.quranapp.surahlist;

import com.google.gson.annotations.SerializedName;

public class Name{

	@SerializedName("translation")
	public Translation translation;

	@SerializedName("short")
	public String jsonMemberShort;

	@SerializedName("long")
	public String jsonMemberLong;

	@SerializedName("transliteration")
	public Transliteration transliteration;
}