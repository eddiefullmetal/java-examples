package grigoriadis.javaexamples.jpa.springdata.dto;

import java.util.Date;

public class ProductOfferDto
{
    private int discount;

    private Date endDate;

    private Date startDate;

    public ProductOfferDto()
    {
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
