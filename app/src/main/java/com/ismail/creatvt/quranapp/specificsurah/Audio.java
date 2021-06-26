package com.ismail.creatvt.quranapp.specificsurah;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Audio{

	@SerializedName("secondary")
	public List<String> secondary;

	@SerializedName("primary")
	public  String primary;
}