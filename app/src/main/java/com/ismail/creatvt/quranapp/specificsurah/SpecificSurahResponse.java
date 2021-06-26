package com.ismail.creatvt.quranapp.specificsurah;

import com.google.gson.annotations.SerializedName;

public class SpecificSurahResponse{

	@SerializedName("code")
	public  int code;

	@SerializedName("data")
	public  Data data;

	@SerializedName("message")
	public  String message;

	@SerializedName("status")
	public  String status;
}