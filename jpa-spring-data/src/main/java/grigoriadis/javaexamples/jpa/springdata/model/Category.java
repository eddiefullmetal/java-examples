package grigoriadis.javaexamples.jpa.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class that represents a {@link Product} category.
 * 
 * @author eddiefullmetal
 *
 */
@Entity
@Table(name = "categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 45, nullable = false)
    private String name;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentCategory")
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
        super();
        this.name = name;
    }

    /**
     * Constructor.
     * 
     * @param name The {@link #name}
     * @param parentCategory The {@link #parentCategory}
     */
    public Category(final String name, final Category parentCategory)
    {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    /**
     * @return the {@link #id}
     */
    public int getId()
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
    public void setId(final int id)
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
