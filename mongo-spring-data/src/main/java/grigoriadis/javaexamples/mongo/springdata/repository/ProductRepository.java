package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Category;
import grigoriadis.javaexamples.mongo.springdata.model.Product;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, ObjectId>, ProductRepositoryCustom
{
    List<Product> findByCategory(Category category, Pageable pageable);

    List<Product> findByDescriptionLike(String description, Pageable pageable);

    Product findByName(String name);

    List<Product> findByOffersDiscount(int discount, Pageable pageable);

    @Query("{ 'tags' : { '$all' : ?0}}")
    List<Product> findByTagsAll(String... tags);
}
