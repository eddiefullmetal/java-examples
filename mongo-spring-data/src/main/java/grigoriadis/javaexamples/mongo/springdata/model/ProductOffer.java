package grigoriadis.javaexamples.mongo.springdata.model;

import java.util.Date;

/**
 * Class that represent a product offer. 
 * An offer has a period that is active ({@link #startDate}, {@link #endDate}) and a discount.
 * 
 * <p>
 * Since this class is used as an embedded object in the {@link Product} 
 * no attributes are required in order to store a {@link ProductOffer} object.
 * </p>
 * 
 * @author eddiefullmetal
 *
 */
public class ProductOffer
{
    /**
     * The discount of the offer.
     */
    private int discount;

    /**
     * The start date that the offer is valid.
     */
    private Date endDate;

    /**
     * The end date that the offer is valid.
     */
    private Date startDate;

    /**
     * Constructor.
     */
    public ProductOffer()
    {
    }

    /**
     * Constructor.
     * 
     * @param discount The {@link #discount}
     * @param startDate The {@link #startDate}
     * @param endDate The {@link #endDate}
     */
    public ProductOffer(final int discount, final Date startDate, final Date endDate)
    {
        this.discount = discount;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * In order for two {@link ProductOffer} objects to be "equal" the 
     * {@link #discount}, {@link #startDate} and {@link #endDate} fields must be equal
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final ProductOffer other = (ProductOffer) obj;
        if (this.discount != other.discount)
        {
            return false;
        }
        if (this.endDate == null)
        {
            if (other.endDate != null)
            {
                return false;
            }
        }
        else if (!this.endDate.equals(other.endDate))
        {
            return false;
        }
        if (this.startDate == null)
        {
            if (other.startDate != null)
            {
                return false;
            }
        }
        else if (!this.startDate.equals(other.startDate))
        {
            return false;
        }
        return true;
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
     * @return the {@link #startDate}
     */
    public Date getStartDate()
    {
        return this.startDate;
    }

    /**
     * @see #equals(Object)
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.discount;
        result = prime * result + ((this.endDate == null) ? 0 : this.endDate.hashCode());
        result = prime * result + ((this.startDate == null) ? 0 : this.startDate.hashCode());
        return result;
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
     * @param startDate the {@link #startDate} to set
     */
    public void setStartDate(final Date startDate)
    {
        this.startDate = startDate;
    }
}
