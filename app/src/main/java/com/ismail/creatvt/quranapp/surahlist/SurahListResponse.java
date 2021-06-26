package com.ismail.creatvt.quranapp.surahlist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SurahListResponse{

	@SerializedName("code")
	public  int code;

	@SerializedName("data")
	public List<Surah> data;

	@SerializedName("message")
	public  String message;

	@SerializedName("status")
	public  String status;
}