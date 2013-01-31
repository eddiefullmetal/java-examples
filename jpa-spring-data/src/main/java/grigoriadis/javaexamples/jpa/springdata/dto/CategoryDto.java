package grigoriadis.javaexamples.jpa.springdata.dto;

public class CategoryDto
{
    private int id;

    private String name;

    public CategoryDto()
    {
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

}
