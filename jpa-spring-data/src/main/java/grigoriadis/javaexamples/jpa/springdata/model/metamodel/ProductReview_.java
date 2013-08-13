package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductReview;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductReview.class)
public class ProductReview_
{
    public static volatile SingularAttribute<ProductReview, Long> id;
    public static volatile Attribute<ProductReview, Product> product;
    public static volatile SingularAttribute<ProductReview, Integer> rating;
    public static volatile SingularAttribute<ProductReview, String> reviewText;
    public static volatile SingularAttribute<ProductReview, String> userName;
}
