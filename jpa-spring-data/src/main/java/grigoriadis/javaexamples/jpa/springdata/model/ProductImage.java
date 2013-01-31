package grigoriadis.javaexamples.jpa.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productImages", schema = "eshop")
public class ProductImage
{
    @Id
    private long id;

    @Column(nullable = false, length = 255)
    private String largeImageUrl;

    @Column(nullable = false, length = 255)
    private String normalImageUrl;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @Column(nullable = false, length = 255)
    private String thumbnailImageUrl;

    public ProductImage()
    {
    }

    /**
     * @return the {@link #id}
     */
    public long getId()
    {
        return this.id;
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
     * @param id the {@link #id} to set
     */
    public void setId(final long id)
    {
        this.id = id;
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
