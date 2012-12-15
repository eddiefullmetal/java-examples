package grigoriadis.javaexamples.mongo.springdata.model;

public class Image
{
    private String largeImageUrl;

    private String normalImageUrl;

    private String thumbnailImageUrl;

    public Image()
    {
    }

    public Image(final String largeImageUrl, final String normalImageUrl, final String thumbnailImageUrl)
    {
        super();
        this.largeImageUrl = largeImageUrl;
        this.normalImageUrl = normalImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getLargeImageUrl()
    {
        return this.largeImageUrl;
    }

    public String getNormalImageUrl()
    {
        return this.normalImageUrl;
    }

    public String getThumbnailImageUrl()
    {
        return this.thumbnailImageUrl;
    }

    public void setLargeImageUrl(final String largeImageUrl)
    {
        this.largeImageUrl = largeImageUrl;
    }

    public void setNormalImageUrl(final String normalImageUrl)
    {
        this.normalImageUrl = normalImageUrl;
    }

    public void setThumbnailImageUrl(final String thumbnailImageUrl)
    {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}
