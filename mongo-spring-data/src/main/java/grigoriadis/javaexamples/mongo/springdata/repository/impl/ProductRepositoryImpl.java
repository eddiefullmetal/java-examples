package grigoriadis.javaexamples.mongo.springdata.repository.impl;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import grigoriadis.javaexamples.mongo.springdata.model.Category;
import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.repository.CategoryRepository;
import grigoriadis.javaexamples.mongo.springdata.repository.ProductRepositoryCustom;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Implementation of the {@link ProductRepositoryCustom}
 * 
 * @author eddiefullmetal
 *
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom
{
    private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<Product> findByCategoryName(final String name, final Pageable pageable)
    {
        if (StringUtils.isBlank(name))
        {
            logger.error("parameter name cannot be null or empty");
            throw new IllegalArgumentException("parameter name cannot be null or empty");
        }

        final Category category = this.categoryRepository.findByName(name);

        if (category == null)
        {
            return new ArrayList<>();
        }

        final Query query = query(where("category.$id").is(category.getId()));

        if (pageable != null)
        {
            query.with(pageable);
        }

        final List<Product> products = this.mongoOperations.find(query, Product.class);

        return products;
    }
}
