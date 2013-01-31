package grigoriadis.javaexamples.jpa.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productReviews", schema = "eshop")
public class ProductReview
{
    @Id
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false, length = 45)
    private String userName;

    public ProductReview()
    {
    }

    /**
     * @return the {@link #id}
     */
    public long getId()
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
    public void setId(final long id)
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
