package grigoriadis.javaexamples.mongo.springdata.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * //TODO Add more description
 * <p>
 * The Product model contains the following "mappings" (there is no mapping in mongo db):
 * <ul>
 * <li>Reference to an object that is stored in another collection ({@link #category})</li>
 * <li>A list with strings ({@link #images})</li>
 * <li>A list of embedded objects ({@link #offers}, {@link #images})</li>
 * <li>A {@link Map} ({@link #attributes})</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The Product has the following annotations
 * <ul>
 * <li>
 * {@link Document} annotation: instances of this class will be saved in a
 * collection named product (default collection name is the class name in lowercase, 
 * we can specify a custom name using {@code @Document(collection="Products")}
 * </li>
 * <li>
 * {@link TypeAlias} annotation: when an instance of this class is saved in
 * mongodb spring-data will add an extra field named "class".
 * This field will tell spring-data which class will be created for that entry 
 * (remember that we can save two different type of objects in the same collection).
 * If not specified spring-data will save the {@link Class#getName()} (grigoriadis.javaexamples.mongo.springdata.model.Product)
 * </li>
 * </ul>
 * </p>
 * 
 * 
 * @see ProductTester
 * @author eddiefullmetal
 * 
 */
@Document
@TypeAlias("product")
public class Product
{
    /**
     * The attributes of a product. 
     * 
     * <p>
     * Since the {@link Map} has an {@link Object} as a value the attributes can be as simple as
     * <pre>
     * attributes: {
     *      Brand:"Toshiba",
     *      CPU: "Intel i7"
     * }
     * </pre>
     * or as complex as
     * <pre>
     * attributes: {
     *      Brand:"Toshiba",
     *      Cpu:{
     *          Brand: "Intel",
     *          Model: "i7"
     *      }
     * }
     * </pre>
     * </p>
     * 
     */
    private Map<String, Object> attributes;

    /**
     * The {@link Category} that the product belongs to.
     * <p>
     * We are using the {@link DBRef} annotation so that when we save a product
     * instance this property is saved as a 
     * <a target="_blank" href="http://docs.mongodb.org/manual/applications/database-references/#dbref">DBRef</a> 
     * instead of an embedded object. Also when the {@link #getCategory()} is called 
     * a query to the database will be made automatically in order to retrieve the {@link Category}.
     * </p>
     */
    @DBRef
    private Category category;

    /**
     * The description of the product.
     */
    private String description;

    /**
     * Every object that is stored in a collection must have an {@link ObjectId} id field annotated with the {@link Id}. 
     * 
     * <p>
     * see <a target="_blank" href="http://docs.mongodb.org/manual/core/object-id/">Object Id</a>
     * </p>
     */
    @Id
    private ObjectId id;

    /**
     * A list with all the {@link Image}s for this product.
     * 
     * This field is stored in the same way as {@link #offers}.
     */
    private List<Image> images;

    /**
     * The name of the product. 
     * 
     * The {@link Indexed} annotation will ensure that spring-data will create an index 
     * for this field. Since it has {@link Indexed#unique()} set to true it will ensure 
     * that duplicate names do not exist. 
     */
    @Indexed(unique = true)
    private String name;

    /**
     * A list with all the {@link ProductOffer}s for this product.
     * 
     * This is stored as a list of embedded objects
     * 
     * <p>
     * see <a target="_blank" href="http://docs.mongodb.org/manual/tutorial/model-embedded-one-to-many-relationships-between-documents/">one-to-many relationship</a>
     * </p>
     */
    private List<ProductOffer> offers;

    /**
     * The tags of a product.
     * 
     * This field will be stored as a simple array of strings.
     */
    private List<String> tags;

    /**
     * Constructor. 
     * 
     * Because we initialize all arrays as well, any product will
     * have empty arrays when stored in mongodb.
     */
    public Product()
    {
        this.tags = new ArrayList<>();
        this.images = new ArrayList<>();
        this.attributes = new HashMap<>();
        this.offers = new ArrayList<>();
    }

    /**
     * Constructor.
     * 
     * @see #Product()
     * @param name
     *            The {@link #name}
     */
    public Product(final String name)
    {
        this();
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @see #Product()
     * @param name
     *            The {@link #name}
     * @param category
     *            The {@link #category}
     */
    public Product(final String name, final Category category)
    {
        this();
        this.category = category;
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @see #Product()
     * @param name
     *            The {@link #name}
     * @param description
     *            The {@link #description}
     */
    public Product(final String name, final String description)
    {
        this();
        this.description = description;
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @see #Product()
     * @param name
     *            The {@link #name}
     * @param tags
     *            Calls the {@link List#addAll(java.util.Collection)} in the
     *            {@link #tags}
     */
    public Product(final String name, final String... tags)
    {
        this();
        this.name = name;
        this.tags.addAll(Arrays.asList(tags));
    }

    /**
     * @return the {@link #attributes}
     */
    public Map<String, Object> getAttributes()
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
    public ObjectId getId()
    {
        return this.id;
    }

    /**
     * @return the {@link #images}
     */
    public List<Image> getImages()
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
     * @return the {@link #tags}
     */
    public List<String> getTags()
    {
        return this.tags;
    }

    /**
     * @param attributes
     *            the {@link #attributes} to set
     */
    public void setAttributes(final Map<String, Object> attributes)
    {
        this.attributes = attributes;
    }

    /**
     * @param category
     *            the {@link #category} to set
     */
    public void setCategory(final Category category)
    {
        this.category = category;
    }

    /**
     * @param description
     *            the {@link #description} to set
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * @param id
     *            the {@link #id} to set
     */
    public void setId(final ObjectId id)
    {
        this.id = id;
    }

    /**
     * @param images
     *            the {@link #images} to set
     */
    public void setImages(final List<Image> images)
    {
        this.images = images;
    }

    /**
     * @param name
     *            the {@link #name} to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * @param offers
     *            the {@link #offers} to set
     */
    public void setOffers(final List<ProductOffer> offers)
    {
        this.offers = offers;
    }

    /**
     * @param tags
     *            the {@link #tags} to set
     */
    public void setTags(final List<String> tags)
    {
        this.tags = tags;
    }

}
