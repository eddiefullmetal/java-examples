package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.model.ProductReview;
import grigoriadis.javaexamples.mongo.springdata.repository.impl.ProductReviewRepositoryImpl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * A {@link MongoRepository} for the {@link ProductReview} model.
 * 
 * <p>
 * This repository uses the following two methods for query definition:
 * <ul>
 * <li>
 * Method Name: Spring-data will automatically generate the query from the method name.
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#mongodb.repositories.queries">Table 6.1. Supported keywords for query methods</a>
 * </li>
 * <li>
 * Custom interface: Spring-data will call our custom implementation class ({@link ProductReviewRepositoryImpl}) 
 * for the methods that are defined in our custom interface ({@link ProductReviewRepositoryCustom}).
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.custom-implementations">4.4. Custom implementations</a>
 * </li>
 * </ul>
 * <p>
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories">Chapter 4. Repositories</a>.
 * 
 * @see ProductRepository
 * @author eddiefullmetal
 *
 */
public interface ProductReviewRepository extends MongoRepository<ProductReview, ObjectId>, ProductReviewRepositoryCustom
{
    /**
     * Search for all product reviews that belong to the given product.
     * 
     * <p>
     * Spring-data will automatically create the following query:
     * <pre>
     * { "product" : { "$ref" : "product" , "$id" : { "$oid" : Product.id }}}
     * </pre>
     * </p>
     * 
     * @param product The {@link Product}
     * @return A {@link List} of {@link Product}s that belong to the given category.
     */
    List<ProductReview> findByProduct(Product product);
}
