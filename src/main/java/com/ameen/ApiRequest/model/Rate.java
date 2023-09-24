package com.ameen.ApiRequest.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Rate")
@Data
public class Rate {

    @Id
    private String fromCurrencyCode;

    private String toCurrencyCode;

    private Float rate;

    public Float getRate(){
        return this.rate;
    }

    public String getFromCurrencyCode(){
        return this.fromCurrencyCode;
    }

}
