package grigoriadis.javaexamples.mongo.springdata.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class that represents a product review made from a user.
 * 
 * <p>
 * The ProductReview has the following annotations
 * <ul>
 * <li>
 * {@link Document} annotation: instances of this class will be saved in a
 * collection named productReview (default collection name is the class name in lowercase, 
 * we can specify a custom name using {@code @Document(collection="ProductReviews")}
 * </li>
 * <li>
 * {@link TypeAlias} annotation: when an instance of this class is saved in
 * mongodb spring-data will add an extra field named "class".
 * This field will tell spring-data which class will be created for that entry 
 * (remember that we can save two different type of objects in the same collection).
 * If not specified spring-data will save the {@link Class#getName()} (grigoriadis.javaexamples.mongo.springdata.model.ProductReview)
 * </li>
 * </ul>
 * </p>
 * 
 * @author eddiefullmetal
 *
 */
@Document
@TypeAlias("productReview")
public class ProductReview
{
    /**
     * Every object that is stored in a collection must have an {@link ObjectId} id field annotated with the {@link Id}. 
     * 
     * <p>
     * see <a target="_blank" href="http://docs.mongodb.org/manual/core/object-id/">Object Id</a>
     * </p>
     */
    @Id
    private ObjectId id;

    /**
     * The {@link Product} that this review refers to.
     * <p>
     * We are using the {@link DBRef} annotation so that when we save a product review
     * instance this property is saved as a 
     * <a target="_blank" href="http://docs.mongodb.org/manual/applications/database-references/#dbref">DBRef</a> 
     * instead of an embedded object. Also when the {@link #getProduct()} is called 
     * a query to the database will be made automatically in order to retrieve the {@link Product}.
     * </p>
     */
    @DBRef
    private Product product;

    /**
     * The rating of the product
     */
    private int rating;

    /**
     * The text of the review
     */
    private String reviewText;

    /**
     * The name of the user
     */
    private String userName;

    /**
     * Constructor.
     */
    public ProductReview()
    {
    }

    /**
     * Constructor.
     * 
     * @param product The {@link #product}
     * @param rating The {@link #rating}
     * @param reviewText The {@link #reviewText}
     * @param userName The {@link #userName}
     */
    public ProductReview(final Product product, final int rating, final String reviewText, final String userName)
    {
        this.product = product;
        this.rating = rating;
        this.reviewText = reviewText;
        this.userName = userName;
    }

    /**
     * @return the {@link #id}
     */
    public ObjectId getId()
    {
        return this.id;
    }

    /**
     * @return the {@link #product}
     */
    public Product getProduct()
    {
        return this.product;
    }

    /**
     * @return the {@link #rating}
     */
    public int getRating()
    {
        return this.rating;
    }

    /**
     * @return the {@link #reviewText}
     */
    public String getReviewText()
    {
        return this.reviewText;
    }

    /**
     * @return the {@link #userName}
     */
    public String getUserName()
    {
        return this.userName;
    }

    /**
     * @param id the {@link #id} to set
     */
    public void setId(final ObjectId id)
    {
        this.id = id;
    }

    /**
     * @param product the {@link #product} to set
     */
    public void setProduct(final Product product)
    {
        this.product = product;
    }

    /**
     * @param rating the {@link #rating} to set
     */
    public void setRating(final int rating)
    {
        this.rating = rating;
    }

    /**
     * @param reviewText the {@link #reviewText} to set
     */
    public void setReviewText(final String reviewText)
    {
        this.reviewText = reviewText;
    }

    /**
     * @param userName the {@link #userName} to set
     */
    public void setUserName(final String userName)
    {
        this.userName = userName;
    }

}
