package grigoriadis.javaexamples.jpa.springdata.repository;

import grigoriadis.javaexamples.jpa.springdata.model.Category;
import grigoriadis.javaexamples.jpa.springdata.model.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findByCategoryName(String name, Pageable pageable);

    List<Product> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);

    Product findByName(String name);

    List<Product> findByOffersDiscount(int discount, Pageable pageable);
}
