package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductAttribute;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductAttribute.class)
public class ProductAttribute_
{
    public static volatile SingularAttribute<ProductAttribute, Long> id;
    public static volatile SingularAttribute<ProductAttribute, String> name;
    public static volatile Attribute<ProductAttribute, Product> product;
    public static volatile SingularAttribute<ProductAttribute, String> value;
}
