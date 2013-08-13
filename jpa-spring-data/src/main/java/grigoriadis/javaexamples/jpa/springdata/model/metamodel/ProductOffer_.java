package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductOffer;

import java.util.Date;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductOffer.class)
public class ProductOffer_
{
    public static volatile SingularAttribute<ProductOffer, Integer> discount;
    public static volatile SingularAttribute<ProductOffer, Date> endDate;
    public static volatile SingularAttribute<ProductOffer, Long> id;
    public static volatile Attribute<ProductOffer, Product> product;
    public static volatile SingularAttribute<ProductOffer, Date> startDate;
}
