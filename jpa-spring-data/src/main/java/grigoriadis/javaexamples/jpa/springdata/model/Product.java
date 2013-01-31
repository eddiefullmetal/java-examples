package grigoriadis.javaexamples.jpa.springdata.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products", schema = "eshop")
public class Product
{
    @OneToMany(mappedBy = "product")
    private List<ProductAttribute> attributes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    @Column(nullable = false)
    private String description;

    @Id
    private long id;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> images;

    @Column(length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "product")
    private List<ProductOffer> offers;

    @OneToMany(mappedBy = "product")
    private List<ProductReview> reviews;

    @OneToMany(mappedBy = "product")
    private List<ProductTag> tags;

    /**
     * Constructor.
     */
    public Product()
    {
    }

    /**
     * @return the {@link #attributes}
     */
    public List<ProductAttribute> getAttributes()
    {
        return this.attributes;
    }

    /**
     * @return the {@link #category}
     */
    public Category getCategory()
    {
        return this.category;
    }

    /**
     * @return the {@link #description}
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * @return the {@link #id}
     */
    public long getId()
    {
        return this.id;
    }

    /**
     * @return the {@link #images}
     */
    public List<ProductImage> getImages()
    {
        return this.images;
    }

    /**
     * @return the {@link #name}
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return the {@link #offers}
     */
    public List<ProductOffer> getOffers()
    {
        return this.offers;
    }

    /**
     * @return the {@link #reviews}
     */
    public List<ProductReview> getReviews()
    {
        return this.reviews;
    }

    /**
     * @return the {@link #tags}
     */
    public List<ProductTag> getTags()
    {
        return this.tags;
    }

    /**
     * @param attributes the {@link #attributes} to set
     */
    public void setAttributes(final List<ProductAttribute> attributes)
    {
        this.attributes = attributes;
    }

    /**
     * @param category the {@link #category} to set
     */
    public void setCategory(final Category category)
    {
        this.category = category;
    }

    /**
     * @param description the {@link #description} to set
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * @param id the {@link #id} to set
     */
    public void setId(final long id)
    {
        this.id = id;
    }

    /**
     * @param images the {@link #images} to set
     */
    public void setImages(final List<ProductImage> images)
    {
        this.images = images;
    }

    /**
     * @param name the {@link #name} to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @param offers the {@link #offers} to set
     */
    public void setOffers(final List<ProductOffer> offers)
    {
        this.offers = offers;
    }

    /**
     * @param reviews the {@link #reviews} to set
     */
    public void setReviews(final List<ProductReview> reviews)
    {
        this.reviews = reviews;
    }

    /**
     * @param tags the {@link #tags} to set
     */
    public void setTags(final List<ProductTag> tags)
    {
        this.tags = tags;
    }
}
