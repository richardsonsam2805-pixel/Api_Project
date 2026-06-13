package com.omrbranch.pojo.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrder_Output_Pojo {
	public int status;
    public String message;
    public String currency;
    public CreateOrderList_pojo data;
    public int order_id;
}
