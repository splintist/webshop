package com.backend.repository;

import com.backend.model.OrderProduct;
import com.backend.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
