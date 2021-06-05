package com.ismail.creatvt.quranapp.surahlist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SurahListResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	public List<Surah> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;
}