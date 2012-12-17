package grigoriadis.javaexamples.mongo.springdata.repository;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static org.testng.Assert.*;
import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.model.ProductRating;
import grigoriadis.javaexamples.mongo.springdata.model.ProductReview;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.random.RandomData;
import org.apache.commons.math3.random.RandomDataImpl;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class ProductReviewRepositoryTester extends AbstractRepositoryTester
{
    private static final Logger logger = LoggerFactory.getLogger(ProductReviewRepositoryTester.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Test
    public void testCalculateProductRatings()
    {
        final RandomData random = new RandomDataImpl();

        final int numOfProducts = random.nextInt(100, 500);

        for (int i = 0; i < numOfProducts; i++)
        {
            final Product product = new Product(RandomStringUtils.randomAlphabetic(random.nextInt(10, 25)));

            this.productRepository.save(product);

            final int numOfReviews = random.nextInt(5, 10);

            for (int j = 0; j < numOfReviews; j++)
            {
                final ProductReview review = new ProductReview(product, random.nextInt(1, 5),
                        RandomStringUtils.randomAscii(random.nextInt(100, 500)), RandomStringUtils.randomAlphabetic(random.nextInt(5, 15)));

                this.productReviewRepository.save(review);
            }
        }

        final List<ProductRating> productRatings = this.productReviewRepository.calculateProductRatings();

        for (final ProductRating productRating : productRatings)
        {
            final Product product = this.productRepository.findOne(new ObjectId(productRating.getId()));
            final List<ProductReview> productReviews = this.productReviewRepository.findByProduct(product);

            assertEquals(productReviews.size(), productRating.getValue().getSum());

            final int totalRating = with(productReviews).sum(on(ProductReview.class).getRating());
            assertEquals((float) totalRating / productReviews.size(), productRating.getValue().getRating());
        }
    }
}
