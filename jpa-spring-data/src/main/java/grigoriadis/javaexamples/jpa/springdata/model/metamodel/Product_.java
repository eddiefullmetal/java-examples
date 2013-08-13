package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Category;
import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductAttribute;
import grigoriadis.javaexamples.jpa.springdata.model.ProductImage;
import grigoriadis.javaexamples.jpa.springdata.model.ProductOffer;
import grigoriadis.javaexamples.jpa.springdata.model.ProductReview;
import grigoriadis.javaexamples.jpa.springdata.model.ProductTag;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_
{
    public static volatile ListAttribute<Product, ProductAttribute> attributes;
    public static volatile Attribute<Product, Category> category;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile ListAttribute<Product, ProductImage> images;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile ListAttribute<Product, ProductOffer> offers;
    public static volatile ListAttribute<Product, ProductReview> reviews;
    public static volatile ListAttribute<Product, ProductTag> tags;
}
