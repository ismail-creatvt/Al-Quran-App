package com.ismail.creatvt.quranapp.specificsurah;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("hizbQuarter")
	public  int hizbQuarter;

	@SerializedName("ruku")
	public  int ruku;

	@SerializedName("manzil")
	public  int manzil;

	@SerializedName("page")
	public  int page;

	@SerializedName("sajda")
	public  Sajda sajda;

	@SerializedName("juz")
	public  int juz;
}