package grigoriadis.javaexamples.mongo.springdata.repository;

import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.model.ProductRating;
import grigoriadis.javaexamples.mongo.springdata.model.ProductReview;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;

/**
 * Custom repository implementation for the {@link ProductReviewRepository}.
 * See <a target="_blank" href="http://static.springsource.org/spring-data/data-mongodb/docs/current/reference/html/#repositories.custom-implementations">4.4. Custom implementations</a>
 * 
 * @author eddiefullmetal
 *
 */
public interface ProductReviewRepositoryCustom
{
    /**
     * Calculates {@link ProductRating}s for each {@link Product}.
     * 
     * <p>
     * We call the {@link MongoOperations#mapReduce(String, String, String, Class)}
     * method with the following arguments:
     * <ul>
     * <li>
     * inputCollectionName: The result of the {@link MongoOperations#getCollectionName(Class)} with the {@link ProductReview} 
     * class in order to dynamically retrieve the collection name.
     * </li>
     * <li>
     * mapFunction: "classpath:calculateProductRatings.map.js" in order to read the map function from the specified js file. 
     * We can also pass the javascript code directly as a string.
     * </li>
     * <li>
     * reduceFunction: "classpath:calculateProductRatings.reduce.js" in order to read the reduce function from the specified js file. 
     * We can also pass the javascript code directly as a string.
     * </li>
     * <li>
     * entityClass: The {@link ProductRating} class.
     * </li>
     * </ul>
     * </p>
     *  
     * <h2>Example</h2>
     * 
     * Assume that we have the following data in productReview collection:
     * 
     * <h3>Original Data</h3>
     * <pre>
     * {
     *  '_id': ObjectId1,
     *  'product': DBRef1,
     *  'rating': 4,
     *  'reviewText': 'ReviewText1',
     *  'userName': 'User1'
     * }
     * 
     * {
     *  '_id': ObjectId2,
     *  'product': DBRef1,
     *  'rating': 2,
     *  'reviewText': 'ReviewText2',
     *  'userName': 'User2'
     * }
     * 
     * {
     *  '_id': ObjectId3,
     *  'product': DBRef2,
     *  'rating': 5,
     *  'reviewText': 'ReviewText3',
     *  'userName': 'User1'
     * }
     * 
     * {
     *  '_id': ObjectId4,
     *  'product': DBRef1,
     *  'rating': 3,
     *  'reviewText': 'ReviewText4',
     *  'userName': 'User3'
     * }
     * </pre>
     * 
     * the calculateProductRatings.map.js is called first:
     * <pre>
     * function () { 
     *      emit(this.product.$id, { 
     *          rating: this.rating, 
     *          count: 1 
     *      });
     * }
     * </pre>
     * 
     * The above code will produce the following result:
     * 
     * <h3>Example Data representation after map function</h3>
     * <pre>
     * {
     *  'key': DBRef1,
     *  'values': [
     *      {
     *          'rating': 4,
     *          'count': 1          
     *      },
     *      {
     *          'rating': 2,
     *          'count': 1
     *      },
     *      {
     *          'rating': 3,
     *          'count': 1
     *      }
     *  ]
     * }
     * 
     * {
     *  'key': DBRef2,
     *  'values': [
     *      {
     *          'rating': 5,
     *          'count': 1
     *      }  
     *  ]
     * }
     * </pre>
     * <p>
     * <span style="font-weight:bold">NOTE:</span> The above is an example representation of data to help understand the result 
     * of the map function. 
     * </p>
     * 
     * Finally the reduce function calculateProductRatings.reduce.js is called
     * 
     * <pre>
     *  function (key, values) {
     *      var sum = 0;
     *      var rating = 0;
     *      
     *      for (var i = 0; i < values.length; i++){
     *          sum += values[i].count;
     *          rating += values[i].rating;
     *      }
     *      
     *      return {
     *          sum: sum,
     *          rating: rating/sum
     *      };
     *  }
     * </pre>
     * 
     * Which produces the following results
     * 
     * <h3>Reduce data</h3>
     * <pre>
     * {
     *  '_id': DBRef1,
     *  'value': {
     *      'sum': 3,
     *      'rating': 3
     *  }
     * },
     * {
     *  '_id': DBRef2,
     *  'value': {
     *      'sum': 1,
     *      'rating': 5
     *  }
     * }
     * </pre>
     * 
     * </p>
     * 
     * @return A {@link List} of product ratings
     */
    List<ProductRating> calculateProductRatings();
}
