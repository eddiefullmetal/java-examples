package grigoriadis.javaexamples.mongo.springdata.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@TypeAlias("category")
public class Category
{
    @Id
    private ObjectId id;

    private String name;

    @DBRef
    private Category parentCategory;

    public Category()
    {
    }

    public Category(final String name)
    {
        super();
        this.name = name;
    }

    public Category(final String name, final Category parentCategory)
    {
        super();
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public ObjectId getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public Category getParentCategory()
    {
        return this.parentCategory;
    }

    public void setId(final ObjectId id)
    {
        this.id = id;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setParentCategory(final Category parentCategory)
    {
        this.parentCategory = parentCategory;
    }

}
