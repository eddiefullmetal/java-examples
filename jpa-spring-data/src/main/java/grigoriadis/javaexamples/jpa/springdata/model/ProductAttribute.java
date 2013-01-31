package grigoriadis.javaexamples.jpa.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productAttributes", schema = "eshop")
public class ProductAttribute
{
    @Id
    private long id;

    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false, length = 100)
    private String value;

    public ProductAttribute()
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
     * @return the {@link #name}
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return the {@link #product}
     */
    public Product getProduct()
    {
        return this.product;
    }

    /**
     * @return the {@link #value}
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * @param id the {@link #id} to set
     */
    public void setId(final long id)
    {
        this.id = id;
    }

    /**
     * @param name the {@link #name} to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @param product the {@link #product} to set
     */
    public void setProduct(final Product product)
    {
        this.product = product;
    }

    /**
     * @param value the {@link #value} to set
     */
    public void setValue(final String value)
    {
        this.value = value;
    }

}
