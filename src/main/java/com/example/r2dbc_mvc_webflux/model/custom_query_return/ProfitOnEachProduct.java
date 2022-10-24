package com.example.r2dbc_mvc_webflux.model.custom_query_return;

import lombok.Data;

@Data
public class ProfitOnEachProduct {
    String prod_name;
    float profit;
}
