package grigoriadis.javaexamples.mongo.springdata.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("product")
public class Product
{
    private Map<String, Object> attributes;

    @DBRef
    private Category category;

    private String description;

    @Id
    private ObjectId id;

    private List<Image> images;

    private String name;

    private List<ProductOffer> offers;

    @DBRef
    private List<ProductReview> reviews;

    private List<String> tags;

    public Product()
    {
        this.tags = new ArrayList<>();
        this.images = new ArrayList<>();
        this.attributes = new HashMap<>();
        this.reviews = new ArrayList<>();
        this.offers = new ArrayList<>();
    }

    public Product(final String name)
    {
        this();
        this.name = name;
    }

    public Product(final String name, final Category category)
    {
        this();
        this.category = category;
        this.name = name;
    }

    public Product(final String name, final String description)
    {
        this();
        this.description = description;
        this.name = name;
    }

    public Product(final String name, final String... tags)
    {
        this();
        this.name = name;
        this.tags.addAll(Arrays.asList(tags));
    }

    public Map<String, Object> getAttributes()
    {
        return this.attributes;
    }

    public Category getCategory()
    {
        return this.category;
    }

    public String getDescription()
    {
        return this.description;
    }

    public ObjectId getId()
    {
        return this.id;
    }

    public List<Image> getImages()
    {
        return this.images;
    }

    public String getName()
    {
        return this.name;
    }

    public List<ProductOffer> getOffers()
    {
        return this.offers;
    }

    public List<ProductReview> getReviews()
    {
        return this.reviews;
    }

    public List<String> getTags()
    {
        return this.tags;
    }

    public void setAttributes(final Map<String, Object> attributes)
    {
        this.attributes = attributes;
    }

    public void setCategory(final Category category)
    {
        this.category = category;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public void setId(final ObjectId id)
    {
        this.id = id;
    }

    public void setImages(final List<Image> images)
    {
        this.images = images;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setOffers(final List<ProductOffer> offers)
    {
        this.offers = offers;
    }

    public void setReviews(final List<ProductReview> reviews)
    {
        this.reviews = reviews;
    }

    public void setTags(final List<String> tags)
    {
        this.tags = tags;
    }

}
