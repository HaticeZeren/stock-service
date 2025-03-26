package com.example.stock_service.service;

import com.example.stock_service.repository.entity.Stock;
import com.example.stock_service.request.StockRequest;

import java.util.List;

public interface IStockService {
    Stock save(StockRequest stockRequest);
    Stock findStockByProductId(Long productId);
    List<Stock> findAllStock();
}
