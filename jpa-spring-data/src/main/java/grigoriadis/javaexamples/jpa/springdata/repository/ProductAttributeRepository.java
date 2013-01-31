package grigoriadis.javaexamples.jpa.springdata.repository;

import grigoriadis.javaexamples.jpa.springdata.model.ProductAttribute;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long>
{

}
