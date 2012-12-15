package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom
{
    List<Product> findByCategoryName(String name, Pageable pageable);
}
