package com.omrbranch.pojo.getsearchproduct;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeOption_Pojo {
	 public int id;
	    public int attribute_id;
	    public String value;
	    public String status;
	    public String created_at;
	    public String updated_at;
}
