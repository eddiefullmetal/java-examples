package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductTag;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductTag.class)
public class ProductTag_
{
    public static volatile SingularAttribute<ProductTag, Long> id;
    public static volatile Attribute<ProductTag, Product> product;
    public static volatile SingularAttribute<ProductTag, String> tag;
}
