package grigoriadis.javaexamples.jpa.springdata.model;

import static ch.lambdaj.Lambda.*;
import static org.testng.Assert.*;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.testng.annotations.Test;

public class ProductTester extends AbstractModelTester
{
    @Test
    public void testInsert()
    {
        // -------------------------InitializeData-------------------------------
        this.beginTransaction();

        final Product product = new Product();

        // Basic Properties
        product.setName("Test1");
        product.setDescription("A very good product");

        // Category
        final Category parentParentCategory = new Category("PC");

        final Category parentCategory = new Category("Portable", parentParentCategory);

        final Category category = new Category("Tablets", parentCategory);

        this.entityManager.persist(parentParentCategory);
        this.entityManager.persist(parentCategory);
        this.entityManager.persist(category);

        product.setCategory(category);

        this.entityManager.persist(product);

        // Images
        final ProductImage image1 = new ProductImage(product, "1", "2", "3");
        final ProductImage image2 = new ProductImage(product, "4", "5", "6");

        this.entityManager.persist(image1);
        this.entityManager.persist(image2);

        product.getImages().addAll(Arrays.asList(image1, image2));

        // Tags
        final ProductTag tag1 = new ProductTag(product, "tablet");
        final ProductTag tag2 = new ProductTag(product, "hot");
        final ProductTag tag3 = new ProductTag(product, "featured");

        this.entityManager.persist(tag1);
        this.entityManager.persist(tag2);
        this.entityManager.persist(tag3);

        product.getTags().addAll(Arrays.asList(tag1, tag2, tag3));

        // Attributes
        final ProductAttribute attribute1 = new ProductAttribute(product, "Screen Size", "14.1");
        final ProductAttribute attribute2 = new ProductAttribute(product, "OS", "Android");

        this.entityManager.persist(attribute1);
        this.entityManager.persist(attribute2);

        product.getAttributes().addAll(Arrays.asList(attribute1, attribute2));

        // Offers
        final ProductOffer productOffer1 = new ProductOffer(product, 10, new LocalDate(2012, 05, 25).toDate(), new LocalDate(2012, 05, 30).toDate());
        final ProductOffer productOffer2 = new ProductOffer(product, 20, new LocalDate(2012, 07, 01).toDate(), new LocalDate(2012, 07, 05).toDate());

        this.entityManager.persist(productOffer1);
        this.entityManager.persist(productOffer2);

        product.getOffers().addAll(Arrays.asList(productOffer1, productOffer2));

        this.commitTransaction();

        // -------------------------------------------------------------------------------

        this.beginTransaction();

        // Retrieve the object
        final Product retrievedProduct = (Product) this.entityManager.createQuery("from Product where name=:name")
                .setParameter("name", product.getName()).getSingleResult();
        // Basic properties
        assertEquals(retrievedProduct.getName(), product.getName());
        assertEquals(retrievedProduct.getDescription(), product.getDescription());
        // Category
        assertEquals(retrievedProduct.getCategory().getId(), category.getId());

        assertNotNull(retrievedProduct.getCategory().getParentCategory());
        assertEquals(retrievedProduct.getCategory().getParentCategory().getId(), parentCategory.getId());

        assertNotNull(retrievedProduct.getCategory().getParentCategory().getParentCategory());
        assertEquals(retrievedProduct.getCategory().getParentCategory().getParentCategory().getId(), parentParentCategory.getId());

        // Images
        assertEquals(retrievedProduct.getImages().size(), product.getImages().size());
        assertEquals(extract(retrievedProduct.getImages(), on(ProductImage.class).getId()),
                extract(product.getImages(), on(ProductImage.class).getId()));

        // Tags
        assertEquals(retrievedProduct.getTags().size(), product.getTags().size());
        assertEquals(extract(retrievedProduct.getTags(), on(ProductTag.class).getId()), extract(product.getTags(), on(ProductTag.class).getId()));

        // Attributes
        assertEquals(retrievedProduct.getAttributes().size(), product.getAttributes().size());
        assertEquals(extract(retrievedProduct.getAttributes(), on(ProductAttribute.class).getId()),
                extract(product.getAttributes(), on(ProductAttribute.class).getId()));

        // Offers
        assertEquals(retrievedProduct.getOffers().size(), product.getOffers().size());
        assertEquals(extract(retrievedProduct.getOffers(), on(ProductOffer.class).getId()),
                extract(product.getOffers(), on(ProductOffer.class).getId()));

        this.commitTransaction();
    }
}
