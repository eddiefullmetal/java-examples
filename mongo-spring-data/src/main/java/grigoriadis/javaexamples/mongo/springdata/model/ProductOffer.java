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
