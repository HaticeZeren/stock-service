package com.example.stock_service.controller;

import com.example.stock_service.repository.entity.Stock;
import com.example.stock_service.request.StockRequest;
import com.example.stock_service.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller yerine @RestController kullandık
@RequestMapping(value = "/stock")
public class StockController {

    private final IStockService stockService;

    @Autowired
    public StockController(IStockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping(value = "/save")
    public Stock save(@RequestBody StockRequest stockRequest) {
        return stockService.save(stockRequest);
    }

    @GetMapping(value = "/findStockByProductId")
    public Stock findStockByProductId(@RequestParam("productId") Long productId) {
        return stockService.findStockByProductId(productId);
    }

    @GetMapping(value = "/findAllStock")
    public List<Stock> findAllStock() {
        return stockService.findAllStock();
    }
}
