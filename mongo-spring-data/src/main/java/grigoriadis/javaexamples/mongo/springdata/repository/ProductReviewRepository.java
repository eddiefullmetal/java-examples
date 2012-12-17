package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.model.ProductReview;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductReviewRepository extends MongoRepository<ProductReview, ObjectId>, ProductReviewRepositoryCustom
{

    List<ProductReview> findByProduct(Product product);
}
