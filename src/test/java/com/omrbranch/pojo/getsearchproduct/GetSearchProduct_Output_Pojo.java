package com.omrbranch.pojo.getsearchproduct;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSearchProduct_Output_Pojo {
	
	 public int status;
	    public String message;
	    public String currency;
	    public ArrayList<GetSearchproductList_Pojo> data;
	    public int cart_count;

}
