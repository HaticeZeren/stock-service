package com.example.stock_service.service.impl;


import com.example.stock_service.repository.StockRepository;
import com.example.stock_service.repository.entity.Stock;
import com.example.stock_service.request.StockRequest;
import com.example.stock_service.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockRepositoryServiceImpl implements IStockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockRepositoryServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    @Override
    public Stock save(StockRequest stockRequest) {
        Stock stock = new Stock();
        stock.setPrice(stockRequest.getPrice());
        stock.setQuantity(stockRequest.getQuantity());
        stock.setProductName(stockRequest.getProductName());
        stock.setProductId(stockRequest.getProductId());
        return stockRepository.save(stock);
    }

    @Override
    public Stock findStockByProductId(Long productId) {
        return stockRepository.findStockByProductId(productId);
    }

    @Override
    public List<Stock> findAllStock() {
        return stockRepository.findAll();
    }
}
