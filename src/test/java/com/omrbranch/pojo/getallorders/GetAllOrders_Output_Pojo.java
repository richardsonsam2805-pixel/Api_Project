package com.omrbranch.pojo.getallorders;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllOrders_Output_Pojo {
	public int status;
    public String message;
    public String currency;
    public ArrayList<GetAllOrders_Pojo> data;
}
