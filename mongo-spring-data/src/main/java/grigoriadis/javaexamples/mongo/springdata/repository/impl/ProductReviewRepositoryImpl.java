package grigoriadis.javaexamples.mongo.springdata.repository.impl;

import grigoriadis.javaexamples.mongo.springdata.model.ProductRating;
import grigoriadis.javaexamples.mongo.springdata.model.ProductReview;
import grigoriadis.javaexamples.mongo.springdata.repository.ProductReviewRepositoryCustom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

public class ProductReviewRepositoryImpl implements ProductReviewRepositoryCustom
{
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<ProductRating> calculateProductRatings()
    {
        final String collectionName = this.mongoOperations.getCollectionName(ProductReview.class);

        final MapReduceResults<ProductRating> mapReduceResults = this.mongoOperations.mapReduce(collectionName,
                "classpath:calculateProductRatings.map.js", "classpath:calculateProductRatings.reduce.js", ProductRating.class);

        final List<ProductRating> productRatings = new ArrayList<>();
        final Iterator<ProductRating> iterator = mapReduceResults.iterator();

        while (iterator.hasNext())
        {
            productRatings.add(iterator.next());
        }

        return productRatings;
    }

}
