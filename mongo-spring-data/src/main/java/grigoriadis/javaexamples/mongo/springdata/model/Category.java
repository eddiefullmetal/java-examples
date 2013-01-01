package grigoriadis.javaexamples.mongo.springdata.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class that represents a {@link Product} category. 
 * 
 * <p>
 * The Category has the following annotations
 * <ul>
 * <li>
 * {@link Document} annotation: instances of this class will be saved in a
 * collection named category (default collection name is the class name in lowercase, 
 * we can specify a custom name using {@code @Document(collection="Categories")}
 * </li>
 * <li>
 * {@link TypeAlias} annotation: when an instance of this class is saved in
 * mongodb spring-data will add an extra field named "class".
 * This field will tell spring-data which class will be created for that entry 
 * (remember that we can save two different type of objects in the same collection).
 * If not specified spring-data will save the {@link Class#getName()} (grigoriadis.javaexamples.mongo.springdata.model.Category)
 * </li>
 * </ul>
 * </p>
 * 
 * @author eddiefullmetal
 *
 */
@Document
@TypeAlias("category")
public class Category
{
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
     * The name of the category. 
     * 
     * The {@link Indexed} annotation will ensure that spring-data will create an index 
     * for this field. Since it has {@link Indexed#unique()} set to true it will ensure 
     * that duplicate names do not exist. 
     */
    @Indexed(unique = true)
    private String name;

    /**
     * The parent {@link Category}.
     * <p>
     * We are using the {@link DBRef} annotation so that when we save a category
     * instance this property will be saved as a 
     * <a target="_blank" href="http://docs.mongodb.org/manual/applications/database-references/#dbref">DBRef</a> 
     * instead of an embedded object. Also when the {@link #getParentCategory()} is called 
     * a query to the database will be made automatically in order to retrieve the parent {@link Category}.
     * </p>
     */
    @DBRef
    private Category parentCategory;

    /**
     * Constructor.
     */
    public Category()
    {
    }

    /**
     * Constructor.
     * 
     * @param name The {@link #name}
     */
    public Category(final String name)
    {
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @param name
     * @param parentCategory
     */
    public Category(final String name, final Category parentCategory)
    {
        this(name);
        this.parentCategory = parentCategory;
    }

    /**
     * @return the {@link #id}
     */
    public ObjectId getId()
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
     * @return the {@link #parentCategory}
     */
    public Category getParentCategory()
    {
        return this.parentCategory;
    }

    /**
     * @param id the {@link #id} to set
     */
    public void setId(final ObjectId id)
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
     * @param parentCategory the {@link #parentCategory} to set
     */
    public void setParentCategory(final Category parentCategory)
    {
        this.parentCategory = parentCategory;
    }

}
