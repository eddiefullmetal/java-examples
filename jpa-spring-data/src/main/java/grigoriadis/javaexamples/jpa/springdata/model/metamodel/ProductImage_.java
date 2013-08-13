package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductImage;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductImage.class)
public class ProductImage_
{
    public static volatile SingularAttribute<ProductImage, Long> id;
    public static volatile SingularAttribute<ProductImage, String> largeImageUrl;
    public static volatile SingularAttribute<ProductImage, String> normalImageUrl;
    public static volatile Attribute<ProductImage, Product> product;
    public static volatile SingularAttribute<ProductImage, String> thumbnailImageUrl;
}
