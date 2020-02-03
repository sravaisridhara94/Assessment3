package com.northwind.shippingservice.Services;

import com.northwind.shippingservice.Domain.ShippingRates;
import com.northwind.shippingservice.Repositories.ShippingRatesMySQLRepository;

import java.util.List;

public class ShippingService {

    private ShippingRatesMySQLRepository shippingRatesMySQLRepository;

    public ShippingService(ShippingRatesMySQLRepository shippingRatesMySQLRepository) {
        this.shippingRatesMySQLRepository = shippingRatesMySQLRepository;
    }

    public ShippingRates getById(long id){ return shippingRatesMySQLRepository.getById(id);}
    public List<ShippingRates> getAllShippingRates(){ return  shippingRatesMySQLRepository.getAllShippingRates(); }
    public ShippingRates save(ShippingRates shippingRates) { return shippingRatesMySQLRepository.save(shippingRates);}
    public void deleteById(long id){ shippingRatesMySQLRepository.deleteById(id);}
}
