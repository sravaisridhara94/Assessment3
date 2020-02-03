package com.northwind.shippingservice.Repositories.Implementation;

import com.northwind.shippingservice.API.ShippingRatesModel;
import com.northwind.shippingservice.Domain.ShippingRates;
import com.northwind.shippingservice.Helper.ShippingRatesMapper;
import com.northwind.shippingservice.Helper.ShippingRatesRowMapper;
import com.northwind.shippingservice.Repositories.Repository;
import com.northwind.shippingservice.Repositories.ShippingRatesMySQLRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class MySQLRepositoryImplementation implements ShippingRatesMySQLRepository {

    private DataSource dataSource;
    private ShippingRatesRowMapper shippingRatesRowMapperMapper;

    public MySQLRepositoryImplementation (DataSource dataSource, ShippingRatesRowMapper shippingRatesRowMapper){
        this.dataSource = dataSource;
        this.shippingRatesRowMapperMapper=shippingRatesRowMapper;
    }

    @Override
    public ShippingRates getById(long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT `ShippingRates`.`ShippingRateID`,\n" +
                "    `ShippingRates`.`Country`,\n" +
                "    `ShippingRates`.`FlatRate`,\n" +
                "    `ShippingRates`.`Version`,\n" +
                "    `ShippingRates`.`ObjectID`\n" +
                "FROM `shipping-db`.`ShippingRates`\n"+
                "WHERE ShippingRateID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        ShippingRates shippingRates = namedParameterJdbcTemplate
                .queryForObject(sql,params, shippingRatesRowMapperMapper);

        return shippingRates;
    }

    @Override
    public List<ShippingRates> getAllShippingRates() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        String sql = "SELECT `ShippingRates`.`ShippingRateID`,\n" +
                "    `ShippingRates`.`Country`,\n" +
                "    `ShippingRates`.`FlatRate`,\n" +
                "    `ShippingRates`.`Version`,\n" +
                "    `ShippingRates`.`ObjectID`\n" +
                "FROM `shipping-db`.`ShippingRates`;\n";

        List<ShippingRates> shippingRates = namedParameterJdbcTemplate.query(sql,shippingRatesRowMapperMapper);
        return shippingRates;
    }

    @Override
    public ShippingRates saveOrUpdate(ShippingRates shippingRates) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("country",shippingRates.getCountry())
                .addValue("flatRate", shippingRates.getFlatRate())
                .addValue("version", shippingRates.getVersion());

        if(Objects.isNull(getById(shippingRates.getId()))){
            //save new
            String sql = "INSERT INTO `shipping-db`.`ShippingRates`,\n" +
                    "(`ShippingRateID`,`Country`,`FlatRate`,`Version`)\n" +
                    "VALUES(:id, :country, :flatRate, :version)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder);
            return getById(keyHolder.getKey().longValue());

        }else{
            String sql = "UPDATE `shipping-db`.`ShippingRates`,\n" +
                    " SET `ShippingRates`.`Country`,\n" +
                    "    `ShippingRates`.`FlatRate`,\n" +
                    "    `ShippingRates`.`Version`,\n" +
                    "WHERE ShippingRateID = :id;\n";
            namedParameterJdbcTemplate.update(sql,mapSqlParameterSource);
            return getById(shippingRates.getId());
        }
    }

    @Override
    public void deleteById(long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        namedParameterJdbcTemplate.update("delete from ShippingRates where ShippingRateID = :id",mapSqlParameterSource);


    }
}
