package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.ProductRating;

import java.util.List;

public interface ProductReviewRepositoryCustom
{
    List<ProductRating> calculateProductRatings();
}
