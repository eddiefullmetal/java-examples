package grigoriadis.javaexamples.mongo.springdata.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("productReview")
public class ProductReview
{
    @Id
    private ObjectId id;

    @DBRef
    private Product product;

    private int rating;

    private String reviewText;

    private String userName;

    public ProductReview()
    {
    }

    public ProductReview(final Product product, final int rating, final String reviewText, final String userName)
    {
        super();
        this.product = product;
        this.rating = rating;
        this.reviewText = reviewText;
        this.userName = userName;
    }

    public ObjectId getId()
    {
        return this.id;
    }

    public Product getProduct()
    {
        return this.product;
    }

    public int getRating()
    {
        return this.rating;
    }

    public String getReviewText()
    {
        return this.reviewText;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setId(final ObjectId id)
    {
        this.id = id;
    }

    public void setProduct(final Product product)
    {
        this.product = product;
    }

    public void setRating(final int rating)
    {
        this.rating = rating;
    }

    public void setReviewText(final String reviewText)
    {
        this.reviewText = reviewText;
    }

    public void setUserName(final String userName)
    {
        this.userName = userName;
    }
}
