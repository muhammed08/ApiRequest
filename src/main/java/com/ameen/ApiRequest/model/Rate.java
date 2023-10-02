package com.ameen.ApiRequest.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("Rate")
@Data
public class Rate implements Persistable<String> {

    @Id
    private String fromCurrencyCode;

    private String toCurrencyCode;

    private Float rate;

    @Transient
    private Boolean isNew;

    public Float getRate(){
        return this.rate;
    }

    public String getFromCurrencyCode(){
        return this.fromCurrencyCode;
    }

    @Override
    public String getId() {
        return this.fromCurrencyCode;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(Boolean value){
        isNew = value;
    }
}
