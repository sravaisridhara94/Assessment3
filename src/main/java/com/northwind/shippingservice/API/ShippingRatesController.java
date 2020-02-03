package com.northwind.shippingservice.API;

import com.northwind.shippingservice.API.ShippingRatesModel;
import com.northwind.shippingservice.Domain.ShippingRates;
import com.northwind.shippingservice.Helper.ShippingRatesMapper;
import com.northwind.shippingservice.Helper.ShippingRatesRowMapper;
import com.northwind.shippingservice.Repositories.ShippingRatesMySQLRepository;
import com.northwind.shippingservice.Services.ShippingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shippingRates")
public class ShippingRatesController {

    private ShippingService shippingService;

    public ShippingRatesController(ShippingService shippingService){
        this.shippingService=shippingService;
    }

    @GetMapping
    public ResponseEntity<List<ShippingRatesModel>> getAllShippingRates(){
        List<ShippingRatesModel> shippingRates = shippingService.getAllShippingRates()
                .stream().map(c->ShippingRatesMapper.ObjecttoModel(c))
                .collect(Collectors.toList());

            return new ResponseEntity<>(shippingRates, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ShippingRatesModel> getShippinRates(@PathVariable long id){
        ShippingRates shippingRates = shippingService.getById(id);
        if(Objects.isNull(shippingRates)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ShippingRatesMapper.ObjecttoModel(shippingRates));
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteShippingRatesById(@PathVariable long id){

        if(Objects.isNull(shippingService.getById(id))){
           return;
       }
        shippingService.deleteById(id);
    }

    @PostMapping()
    public ResponseEntity<ShippingRatesModel> saveOrUpdateShippingRates(@RequestBody ShippingRatesModel shippingRatesModel){
        ShippingRates shippingRates = ShippingRatesMapper.ModeltoObject(shippingRatesModel);
        return ResponseEntity.ok().body(ShippingRatesMapper.ObjecttoModel(shippingRates));
    }

}
