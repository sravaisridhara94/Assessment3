package com.northwind.shippingservice.Helper;

import com.northwind.shippingservice.API.ShippingRatesModel;
import com.northwind.shippingservice.Domain.ShippingRates;

 public class ShippingRatesMapper {

    private ShippingRatesMapper() {}

    public static ShippingRates ModeltoObject(ShippingRatesModel shippingRatesModel){
        ShippingRates shippingRate = new ShippingRates();
        shippingRate.setId(shippingRatesModel.getId());
        shippingRate.setCountry(shippingRatesModel.getCountry());
        shippingRate.setFlatRate(shippingRatesModel.getFlatRate());
        return shippingRate;
    }

     public static ShippingRatesModel ObjecttoModel(ShippingRates shippingRates){
         ShippingRatesModel shippingRatesModel = new ShippingRatesModel();
         shippingRatesModel.setId(shippingRates.getId());
         shippingRatesModel.setCountry(shippingRates.getCountry());
         shippingRatesModel.setFlatRate(shippingRates.getFlatRate());
         return shippingRatesModel;
     }
}
