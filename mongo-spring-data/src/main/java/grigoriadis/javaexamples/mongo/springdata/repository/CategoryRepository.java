package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Category;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

/**
 * A {@link MongoRepository} for the {@link Category} model.
 * Since it inherits from the {@link Repository} spring-data will automatically "implement" this class. 
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories">Chapter 4. Repositories</a>.
 * 
 * @see ProductRepository
 * @author eddiefullmetal
 *
 */
public interface CategoryRepository extends MongoRepository<Category, ObjectId>
{
    /**
     * Searches for the category with the given name. 
     * Spring-data will automatically create the following query:
     * <pre>
     * {"name": name}
     * </pre>
     * by reading the method name. The findBy declares that we want to lookup an object using one (or more) of its fields.
     * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.query-methods.query-creation">4.3.2.2. Query creation</a>
     * and <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#mongodb.repositories.queries">Table 6.1. Supported keywords for query methods</a>.
     * 
     * @param name The {@link Category} name
     * 
     * @return The {@link Category} with the specified name
     */
    Category findByName(String name);
}
