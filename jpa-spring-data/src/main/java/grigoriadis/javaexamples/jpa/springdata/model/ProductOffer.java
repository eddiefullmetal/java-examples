package grigoriadis.javaexamples.jpa.springdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productOffers")
public class ProductOffer
{
    @Column(nullable = false)
    private int discount;

    @Column(nullable = false)
    private Date endDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false)
    private Date startDate;

    public ProductOffer()
    {
    }

    public ProductOffer(final Product product, final int discount, final Date startDate, final Date endDate)
    {
        this.discount = discount;
        this.endDate = endDate;
        this.product = product;
        this.startDate = startDate;
    }

    /**
     * @return the {@link #discount}
     */
    public int getDiscount()
    {
        return this.discount;
    }

    /**
     * @return the {@link #endDate}
     */
    public Date getEndDate()
    {
        return this.endDate;
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
     * @return the {@link #startDate}
     */
    public Date getStartDate()
    {
        return this.startDate;
    }

    /**
     * @param discount the {@link #discount} to set
     */
    public void setDiscount(final int discount)
    {
        this.discount = discount;
    }

    /**
     * @param endDate the {@link #endDate} to set
     */
    public void setEndDate(final Date endDate)
    {
        this.endDate = endDate;
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
     * @param startDate the {@link #startDate} to set
     */
    public void setStartDate(final Date startDate)
    {
        this.startDate = startDate;
    }
}
