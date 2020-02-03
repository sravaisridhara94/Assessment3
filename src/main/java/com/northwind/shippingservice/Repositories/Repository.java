package com.northwind.shippingservice.Repositories;

import java.util.List;

public interface Repository<T> {

    public T getById(long id);
    public List<T> getAllShippingRates();
    public T saveOrUpdate(T t);
    public void deleteById(long id);

}
