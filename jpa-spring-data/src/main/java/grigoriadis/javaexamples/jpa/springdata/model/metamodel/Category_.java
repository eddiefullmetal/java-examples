package grigoriadis.javaexamples.jpa.springdata.model.metamodel;

import grigoriadis.javaexamples.jpa.springdata.model.Category;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
public class Category_
{
    public static volatile SingularAttribute<Category, Integer> id;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile Attribute<Category, Category> parentCategory;
}
