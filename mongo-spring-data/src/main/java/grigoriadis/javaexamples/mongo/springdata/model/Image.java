package grigoriadis.javaexamples.mongo.springdata.model;

/**
 * Represents one product image in 3 different sizes:
 * <ul>
 * <li>Thumbnail</li>
 * <li>Normal</li>
 * <li>Large</li>
 * </ul>
 * 
 * <p>
 * Since this class is used as an embedded object in the {@link Product} 
 * no attributes are required in order to store an {@link Image} object.
 * </p>
 * 
 * @see Product
 * @author eddiefullmetal
 *
 */
public class Image
{
    /**
     * The url for the large size product image
     */
    private String largeImageUrl;

    /**
     * The url for the normal size product image
     */
    private String normalImageUrl;

    /**
     * The url for the thumbnail image
     */
    private String thumbnailImageUrl;

    /**
     * Constructor.
     */
    public Image()
    {
    }

    /**
     * Constructor.
     * 
     * @param largeImageUrl The {@link #largeImageUrl}
     * @param normalImageUrl The {@link #normalImageUrl}
     * @param thumbnailImageUrl The {@link #thumbnailImageUrl}
     */
    public Image(final String largeImageUrl, final String normalImageUrl, final String thumbnailImageUrl)
    {
        super();
        this.largeImageUrl = largeImageUrl;
        this.normalImageUrl = normalImageUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * In order for two {@link Image} objects to be "equal" the 
     * {@link #largeImageUrl}, {@link #normalImageUrl} and {@link #thumbnailImageUrl} fields must be equal
     */
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
     * @see #equals(Object)
     */
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
