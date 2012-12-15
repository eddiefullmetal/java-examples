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

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final Image other = (Image) obj;
        if (this.largeImageUrl == null)
        {
            if (other.largeImageUrl != null)
            {
                return false;
            }
        }
        else if (!this.largeImageUrl.equals(other.largeImageUrl))
        {
            return false;
        }
        if (this.normalImageUrl == null)
        {
            if (other.normalImageUrl != null)
            {
                return false;
            }
        }
        else if (!this.normalImageUrl.equals(other.normalImageUrl))
        {
            return false;
        }
        if (this.thumbnailImageUrl == null)
        {
            if (other.thumbnailImageUrl != null)
            {
                return false;
            }
        }
        else if (!this.thumbnailImageUrl.equals(other.thumbnailImageUrl))
        {
            return false;
        }
        return true;
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

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.largeImageUrl == null) ? 0 : this.largeImageUrl.hashCode());
        result = prime * result + ((this.normalImageUrl == null) ? 0 : this.normalImageUrl.hashCode());
        result = prime * result + ((this.thumbnailImageUrl == null) ? 0 : this.thumbnailImageUrl.hashCode());
        return result;
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
