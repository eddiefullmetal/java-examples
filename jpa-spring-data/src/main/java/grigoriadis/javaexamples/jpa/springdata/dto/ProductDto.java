package grigoriadis.javaexamples.jpa.springdata.dto;

import java.util.List;
import java.util.Map;

public class ProductDto
{
    private Map<String, String> attributes;

    private CategoryDto category;

    private String description;

    private long id;

    private List<ProductImageDto> images;

    private String name;

    private List<ProductOfferDto> offers;

    private List<String> tags;

    public ProductDto()
    {
    }

    /**
     * @return the {@link #attributes}
     */
    public Map<String, String> getAttributes()
    {
        return this.attributes;
    }

    /**
     * @return the {@link #category}
     */
    public CategoryDto getCategory()
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
    public List<ProductImageDto> getImages()
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
    public List<ProductOfferDto> getOffers()
    {
        return this.offers;
    }

    /**
     * @return the {@link #tags}
     */
    public List<String> getTags()
    {
        return this.tags;
    }

    /**
     * @param attributes the {@link #attributes} to set
     */
    public void setAttributes(final Map<String, String> attributes)
    {
        this.attributes = attributes;
    }

    /**
     * @param category the {@link #category} to set
     */
    public void setCategory(final CategoryDto category)
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
    public void setImages(final List<ProductImageDto> images)
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
    public void setOffers(final List<ProductOfferDto> offers)
    {
        this.offers = offers;
    }

    /**
     * @param tags the {@link #tags} to set
     */
    public void setTags(final List<String> tags)
    {
        this.tags = tags;
    }
}
