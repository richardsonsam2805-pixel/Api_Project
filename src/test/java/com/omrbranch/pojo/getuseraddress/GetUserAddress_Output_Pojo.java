package com.omrbranch.pojo.getuseraddress;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserAddress_Output_Pojo {

	public int status;
	public String message;
	public ArrayList<GetList> data;

}
