package com.example.stock_service.repository;

import com.example.stock_service.repository.entity.Stock;
import com.example.stock_service.request.StockRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository  extends JpaRepository<Stock, Long> {
    Stock findStockByProductId(Long productId);
}
