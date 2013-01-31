package grigoriadis.javaexamples.jpa.springdata.dto;

public class ProductImageDto
{
    private String largeImageUrl;

    private String normalImageUrl;

    private String thumbnailImageUrl;

    public ProductImageDto()
    {
    }

    /**
     * @return the {@link #largeImageUrl}
     */
    public String getLargeImageUrl()
    {
        return this.largeImageUrl;
    }

    /**
     * @return the {@link #normalImageUrl}
     */
    public String getNormalImageUrl()
    {
        return this.normalImageUrl;
    }

    /**
     * @return the {@link #thumbnailImageUrl}
     */
    public String getThumbnailImageUrl()
    {
        return this.thumbnailImageUrl;
    }

    /**
     * @param largeImageUrl the {@link #largeImageUrl} to set
     */
    public void setLargeImageUrl(final String largeImageUrl)
    {
        this.largeImageUrl = largeImageUrl;
    }

    /**
     * @param normalImageUrl the {@link #normalImageUrl} to set
     */
    public void setNormalImageUrl(final String normalImageUrl)
    {
        this.normalImageUrl = normalImageUrl;
    }

    /**
     * @param thumbnailImageUrl the {@link #thumbnailImageUrl} to set
     */
    public void setThumbnailImageUrl(final String thumbnailImageUrl)
    {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}
