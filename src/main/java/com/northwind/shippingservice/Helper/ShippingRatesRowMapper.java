package com.northwind.shippingservice.Helper;

import com.northwind.shippingservice.Domain.ShippingRates;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippingRatesRowMapper implements RowMapper<ShippingRates>  {

    @Override
    public ShippingRates mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShippingRates shippingRates = new ShippingRates();
        shippingRates.setCountry(rs.getString("Country"));
        shippingRates.setFlatRate(rs.getString("FlatRate"));
        shippingRates.setId(rs.getLong("ShippingRateID"));
        shippingRates.setVersion(rs.getLong("Version"));
        return shippingRates;
    }
}
