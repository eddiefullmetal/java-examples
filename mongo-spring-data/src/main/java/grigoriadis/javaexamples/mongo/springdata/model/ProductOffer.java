package grigoriadis.javaexamples.mongo.springdata.model;

import java.util.Date;

public class ProductOffer
{
    private int discount;

    private Date endDate;

    private Date startDate;

    public ProductOffer()
    {
    }

    public ProductOffer(final int discount, final Date startDate, final Date endDate)
    {
        super();
        this.discount = discount;
        this.endDate = endDate;
        this.startDate = startDate;
    }

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

    public int getDiscount()
    {
        return this.discount;
    }

    public Date getEndDate()
    {
        return this.endDate;
    }

    public Date getStartDate()
    {
        return this.startDate;
    }

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

    public void setDiscount(final int discount)
    {
        this.discount = discount;
    }

    public void setEndDate(final Date endDate)
    {
        this.endDate = endDate;
    }

    public void setStartDate(final Date startDate)
    {
        this.startDate = startDate;
    }

}
