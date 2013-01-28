package grigoriadis.javaexamples.jpa.springdata.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Product
{
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    @Column(nullable = false)
    private String description;

    @Id
    private long id;

    @Column(length = 150, nullable = false)
    private String name;

    /**
     * Constructor.
     */
    public Product()
    {
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
     * @return the {@link #name}
     */
    public String getName()
    {
        return this.name;
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
     * @param name the {@link #name} to set
     */
    public void setName(final String name)
    {
        this.name = name;
    }
}
