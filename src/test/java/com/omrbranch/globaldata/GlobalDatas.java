package com.omrbranch.globaldata;

import lombok.Data;

@Data
public class GlobalDatas {
	private int statusCode;
	private String logtoken;
	private String txtStatusID;
	private String cityID;
	private String txtAddressID;
	

	private static GlobalDatas globalData;

	private GlobalDatas() {
	}

	public static GlobalDatas getGlobalDataInstance() {
		if (globalData == null) {
			globalData = new GlobalDatas();
		}
		return globalData;

	}
	

}
