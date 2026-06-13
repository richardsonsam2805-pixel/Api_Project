package com.omrbranch.pojo.statelist;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateList_Output_Pojo {

	public int status;
	public String message;
	public ArrayList<StateList> data;

}
