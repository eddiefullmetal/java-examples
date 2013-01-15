package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Category;
import grigoriadis.javaexamples.mongo.springdata.model.Product;

import java.util.List;

import org.springframework.data.domain.Pageable;

/**
 * Custom repository implementation for the {@link ProductRepository}.
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.custom-implementations">4.4. Custom implementations</a>
 * 
 * @author eddiefullmetal
 *
 */
public interface ProductRepositoryCustom
{
    /**
     * Searches for products that belong to the {@link Category} with the specified name.
     * <p>
     * This method shows one of mongodb disadvantages, no joins. Spring data provides support for this kind of query 
     * if used with JPA (relational databases) but since mongodb doesn't support joins and we decided to implement the relationship using 
     * <a target="_blank" href="http://docs.mongodb.org/manual/applications/database-references/#dbref">DBRef</a> (see {@link Product#getCategory()} field)
     * we have to execute 2 queries in order to retrieve the results.
     * <ul>
     *  <li>{"name": name} query in the category collection</li>
     *  <li>{ "category" : { "$ref" : "category" , "$id" : { "$oid" : Category.id }}} query in the product collection using the {@link Category} from the first query</li>
     * </ul>
     * </p>
     * 
     * @param name The name of the {@link Category}
     * @param pageable The {@link Pageable}
     * @return A {@link List} of {@link Product}s that belong to the {@link Category} with the given name.
     */
    List<Product> findByCategoryName(String name, Pageable pageable);
}
