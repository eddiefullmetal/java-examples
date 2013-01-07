package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Category;
import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.model.ProductOffer;
import grigoriadis.javaexamples.mongo.springdata.repository.impl.ProductRepositoryImpl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mongodb.DBCursor;

/**
 * <p>
 * This repository uses all three possible variations for query definition:
 * <ul>
 * <li>
 * Method Name: Spring-data will automatically generate the query from the method name.
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#mongodb.repositories.queries">Table 6.1. Supported keywords for query methods</a>
 * </li>
 * <li>
 * {@link Query} annotation: Spring-data will read the {@link Query#value()} that contains the mongo query. 
 * The ?0 means that it will be replaced by the first method parameter. 
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#d0e4054">6.3.2. MongoDB JSON based query methods and field restriction</a>.
 * </li>
 * <li>
 * Custom interface: Spring-data will call our custom implementation class ({@link ProductRepositoryImpl}) 
 * for the methods that are defined in our custom interface ({@link ProductRepositoryCustom}).
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.custom-implementations">4.4. Custom implementations</a>
 * </li>
 * </ul>
 * <p>
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories">Chapter 4. Repositories</a>.
 * 
 * 
 * @author eddiefullmetal
 *
 */
public interface ProductRepository extends MongoRepository<Product, ObjectId>, ProductRepositoryCustom
{
    /**
     * Search for all products that belong to the given category.
     * 
     * <p>
     * Spring-data will automatically create the following query:
     * <pre>
     * { "category" : { "$ref" : "category" , "$id" : { "$oid" : Category.id }}}
     * </pre>
     * </p>
     * 
     * <p>
     * Since the method also takes a {@link Pageable} parameter, spring-data will call 
     * {@link DBCursor#skip(int)},{@link DBCursor#limit(int)} and {@link DBCursor#sort(com.mongodb.DBObject)} depending on the pageable.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.special-parameters">4.3.2.3. Special parameter handling</a>.
     * </p>
     * 
     * @param category The {@link Category}
     * @param pageable The {@link Pageable}
     * @return A {@link List} of {@link Product}s that belong to the given category.
     */
    List<Product> findByCategory(Category category, Pageable pageable);

    /**
     * Searches for all products that their description matches the given regex.
     * 
     * <p>
     * Spring-data will automatically create the following query:
     * <pre>
     * { "description" : { "$regex" : descriptionRegex}}
     * </pre>
     * by reading the method name. The findBy declares that we want to lookup an object using one (or more) of its fields.
     * Since the property name is followed by "Like" spring-data will search the description property using the $regex operator.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.query-methods.query-creation">4.3.2.2. Query creation</a>
     * and <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#mongodb.repositories.queries">Table 6.1. Supported keywords for query methods</a>.
     * </p>
     * 
     * <p>
     * Since the method also takes a {@link Pageable} parameter, spring-data will call 
     * {@link DBCursor#skip(int)},{@link DBCursor#limit(int)} and {@link DBCursor#sort(com.mongodb.DBObject)} depending on the pageable.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.special-parameters">4.3.2.3. Special parameter handling</a>.
     * </p>
     * 
     * @param descriptionRegex The regular expression to match the description field
     * @param pageable The {@link Pageable}
     * @return A {@link List} of {@link Product}s with description that matches the descriptionRegex
     */
    List<Product> findByDescriptionLike(String descriptionRegex, Pageable pageable);

    /**
     * Searches for the product with the given name. 
     * Spring-data will automatically create the following query:
     * <pre>
     * {"name": name}
     * </pre>
     * by reading the method name. The findBy declares that we want to lookup an object using one (or more) of its fields.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.query-methods.query-creation">4.3.2.2. Query creation</a>
     * and <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#mongodb.repositories.queries">Table 6.1. Supported keywords for query methods</a>.
     *  
     * @param name The {@link Product} name
     * 
     * @return The {@link Product} with the specified name
     */
    Product findByName(String name);

    /**
     * Searches for all the products that have at least one {@link ProductOffer} with the specific discount.
     * 
     * <p> 
     * Spring-data will automatically create the following query:
     * <pre>
     * {"offers.discount": discount}
     * </pre>
     * by reading the method name. The findBy declares that we want to lookup an object using one (or more) of its fields,
     * spring-data is also able to understand nested properties for embedded objects.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.query-methods.query-creation">4.3.2.2.1. Property expressions</a>.
     * </p>
     * 
     * <p>
     * Since the method also takes a {@link Pageable} parameter, spring-data will call 
     * {@link DBCursor#skip(int)},{@link DBCursor#limit(int)} and {@link DBCursor#sort(com.mongodb.DBObject)} depending on the pageable.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.special-parameters">4.3.2.3. Special parameter handling</a>.
     * </p>
     * 
     * @param discount The desired discount
     * @param pageable The {@link Pageable}
     * 
     * @return A {@link List} of {@link Product}s that contain at least one {@link ProductOffer} with the specified discount.
     */
    List<Product> findByOffersDiscount(int discount, Pageable pageable);

    /**
     * Searches for all the products that have all the specified tags.
     * 
     * The $all operator is not supported so we must provide the mongodb query using the {@link Query} annotation.
     * <pre>
     * {@code @Query("{ 'tags' : { '$all' : ?0}}") }
     * </pre>
     * The ?0 will be replaced by the first method parameter which is an array of tags.
     * 
     * @param tags The array of tags
     * @return A {@link List} of {@link Product}s that have all the specified tags
     */
    @Query("{ 'tags' : { '$all' : ?0}}")
    List<Product> findByTagsAll(String... tags);
}
