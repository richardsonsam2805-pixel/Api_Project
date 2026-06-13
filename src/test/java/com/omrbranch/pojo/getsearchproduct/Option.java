package com.omrbranch.pojo.getsearchproduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    public int id;
    public int product_id;
    public int variation_id;
    public String attribute_id;
    public int attribute_value_id;
    public String status;
    public String created_at;
    public String updated_at;
    public VariationOption_Pojo attribute_option;

}
