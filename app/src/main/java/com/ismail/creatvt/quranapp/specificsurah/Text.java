package com.ismail.creatvt.quranapp.specificsurah;

import com.google.gson.annotations.SerializedName;

public class Text{

	@SerializedName("transliteration")
	public  Transliteration transliteration;

	@SerializedName("arab")
	public  String arab;
}