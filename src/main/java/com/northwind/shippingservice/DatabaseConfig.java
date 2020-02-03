package com.northwind.shippingservice;

import com.northwind.shippingservice.Helper.ShippingRatesMapper;
import com.northwind.shippingservice.Helper.ShippingRatesRowMapper;
import com.northwind.shippingservice.Repositories.Implementation.MySQLRepositoryImplementation;
import com.northwind.shippingservice.Repositories.ShippingRatesMySQLRepository;
import com.northwind.shippingservice.Services.ShippingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.northwind.shippingservice")
public class DatabaseConfig {

   @Bean
    public DataSource dataSource(){

       DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
       driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
       driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/shipping-db");
       //While configuring db to project, why are we using "customers-user" while the username had to be "root"?
       driverManagerDataSource.setUsername("root");
       driverManagerDataSource.setPassword("password");
       return driverManagerDataSource;
   }

   @Bean
   public ShippingService shippingService(ShippingRatesMySQLRepository shippingRatesMySQLRepository) {
      return new ShippingService(shippingRatesMySQLRepository);
   }

   @Bean
   public ShippingRatesMySQLRepository shippingRatesMySQLRepository(DataSource source, ShippingRatesRowMapper mapper){
         return new MySQLRepositoryImplementation(source,mapper);
   }

   @Bean
   public ShippingRatesRowMapper shippingRatesRowMapper() { return new ShippingRatesRowMapper();}







}
