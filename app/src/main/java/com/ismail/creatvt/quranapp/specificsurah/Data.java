package com.ismail.creatvt.quranapp.specificsurah;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("number")
	public  int number;

	@SerializedName("sequence")
	public  int sequence;

	@SerializedName("numberOfVerses")
	public  int numberOfVerses;

	@SerializedName("revelation")
	public  Revelation revelation;

	@SerializedName("name")
	public  Name name;

	@SerializedName("tafsir")
	public  Tafsir tafsir;

	@SerializedName("preBismillah")
	public  Object preBismillah;

	@SerializedName("verses")
	public  List<VersesItem> verses;
}