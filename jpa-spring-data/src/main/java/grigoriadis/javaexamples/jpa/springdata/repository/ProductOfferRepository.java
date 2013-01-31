package grigoriadis.javaexamples.jpa.springdata.repository;

import grigoriadis.javaexamples.jpa.springdata.model.ProductOffer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOfferRepository extends JpaRepository<ProductOffer, Long>
{

}
