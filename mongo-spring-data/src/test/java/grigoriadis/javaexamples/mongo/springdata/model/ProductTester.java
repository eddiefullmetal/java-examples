package grigoriadis.javaexamples.mongo.springdata.model;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.annotations.Test;

public class ProductTester extends AbstractModelTester
{
    @Test
    public void testAttributeQueries()
    {
        // Initialize data
        final Product product1 = new Product("Product1");
        product1.getAttributes().put("Display Size", 15);
        product1.getAttributes().put("Brand", "Dell");

        final Product product2 = new Product("Product2");
        product2.getAttributes().put("Display Size", 17);
        product2.getAttributes().put("Brand", "Toshiba");

        final Product product3 = new Product("Product3");
        product3.getAttributes().put("Display Size", 18);
        product3.getAttributes().put("Brand", "Lenovo");

        final Product product4 = new Product("Product4");
        product4.getAttributes().put("Display Size", 23);
        product4.getAttributes().put("Brand", "HP");

        this.mongoOperations.insertAll(Arrays.asList(product1, product2, product3, product4));

        // Find screens greater than 18
        final Query findDisplaySizeGreaterThan18 = Query.query(Criteria.where("attributes.Display Size").gt(17));

        final List<Product> displaySizeGreaterThan18 = this.mongoOperations.find(findDisplaySizeGreaterThan18, Product.class);
        assertEquals(displaySizeGreaterThan18.size(), 2);
        List<ObjectId> ids = with(displaySizeGreaterThan18).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product3.getId(), product4.getId()));

        // Find dell or hp
        final Query findDellOrHp = Query.query(Criteria.where("attributes.Brand").in("Dell", "HP"));

        final List<Product> dellOrHp = this.mongoOperations.find(findDellOrHp, Product.class);
        assertEquals(dellOrHp.size(), 2);
        ids = with(dellOrHp).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product4.getId()));
    }

    @Test
    public void testInsert()
    {
        // -------------------------InitializeData-------------------------------
        final ProductReview productReview1 = new ProductReview(5, "Very nice", "dude5");
        final ProductReview productReview2 = new ProductReview(3, "Super!!!", "dude43");

        this.mongoOperations.insertAll(Arrays.asList(productReview1, productReview2));

        final Product product = new Product();

        // Basic Properties
        product.setName("Test1");
        product.setDescription("A very good product");

        // Category
        final Category parentParentCategory = new Category("PC");

        final Category parentCategory = new Category("Portable", parentParentCategory);

        final Category category = new Category("Tablets", parentCategory);

        this.mongoOperations.insert(parentParentCategory);
        this.mongoOperations.insert(parentCategory);
        this.mongoOperations.insert(category);

        product.setCategory(category);

        // Images
        final Image image1 = new Image("1", "2", "3");
        final Image image2 = new Image("4", "5", "6");

        product.getImages().add(image1);
        product.getImages().add(image2);

        // Tags
        product.getTags().addAll(Arrays.asList("tablet", "hot", "featured"));

        // Attributes
        final String key1 = "Screen Size";
        final String key2 = "OS";
        product.getAttributes().put(key1, 14.1);
        product.getAttributes().put(key2, "Android");

        // Reviews
        product.getReviews().add(productReview1);
        product.getReviews().add(productReview2);

        // Offers
        final ProductOffer productOffer1 = new ProductOffer(10, new LocalDate(2012, 05, 25).toDate(), new LocalDate(2012, 05, 30).toDate());
        final ProductOffer productOffer2 = new ProductOffer(20, new LocalDate(2012, 07, 01).toDate(), new LocalDate(2012, 07, 05).toDate());

        product.getOffers().add(productOffer1);
        product.getOffers().add(productOffer2);

        this.mongoOperations.insert(product);

        // -------------------------------------------------------------------------------

        // Retrieve the object
        final Product retrievedProduct = this.mongoOperations.findOne(Query.query(Criteria.where("name").is("Test1")), Product.class);

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
        assertEquals(retrievedProduct.getImages().size(), 2);
        assertEquals(retrievedProduct.getImages().get(0).getLargeImageUrl(), image1.getLargeImageUrl());
        assertEquals(retrievedProduct.getImages().get(0).getNormalImageUrl(), image1.getNormalImageUrl());
        assertEquals(retrievedProduct.getImages().get(0).getThumbnailImageUrl(), image1.getThumbnailImageUrl());

        assertEquals(retrievedProduct.getImages().get(1).getLargeImageUrl(), image2.getLargeImageUrl());
        assertEquals(retrievedProduct.getImages().get(1).getNormalImageUrl(), image2.getNormalImageUrl());
        assertEquals(retrievedProduct.getImages().get(1).getThumbnailImageUrl(), image2.getThumbnailImageUrl());

        // Tags
        assertEquals(retrievedProduct.getTags().size(), 3);
        assertEquals(retrievedProduct.getTags().get(0), "tablet");
        assertEquals(retrievedProduct.getTags().get(1), "hot");
        assertEquals(retrievedProduct.getTags().get(2), "featured");

        // Attributes
        assertEquals(retrievedProduct.getAttributes().size(), 2);
        assertTrue(retrievedProduct.getAttributes().containsKey(key1));
        assertTrue(retrievedProduct.getAttributes().containsKey(key2));
        assertEquals(retrievedProduct.getAttributes().get(key1), product.getAttributes().get(key1));
        assertEquals(retrievedProduct.getAttributes().get(key2), product.getAttributes().get(key2));

        // Reviews
        assertEquals(retrievedProduct.getReviews().size(), 2);
        assertEquals(retrievedProduct.getReviews().get(0).getId(), productReview1.getId());
        assertEquals(retrievedProduct.getReviews().get(1).getId(), productReview2.getId());

        // Offers
        assertEquals(retrievedProduct.getOffers().size(), 2);

        assertEquals(retrievedProduct.getOffers().get(0).getDiscount(), productOffer1.getDiscount());
        assertEquals(retrievedProduct.getOffers().get(0).getStartDate(), productOffer1.getStartDate());
        assertEquals(retrievedProduct.getOffers().get(0).getEndDate(), productOffer1.getEndDate());

        assertEquals(retrievedProduct.getOffers().get(1).getDiscount(), productOffer2.getDiscount());
        assertEquals(retrievedProduct.getOffers().get(1).getStartDate(), productOffer2.getStartDate());
        assertEquals(retrievedProduct.getOffers().get(1).getEndDate(), productOffer2.getEndDate());
    }

    @Test
    public void testTagQueries()
    {
        // Initialize data
        final Product product1 = new Product("Product1", "laptop", "top", "offer");
        final Product product2 = new Product("Product2", "mp3", "top", "favorite");
        final Product product3 = new Product("Product3", "book", "favorite", "offer");
        final Product product4 = new Product("Product4", "laptop", "offer");
        final Product product5 = new Product("Product5", "book", "top", "offer");
        final Product product6 = new Product("Product6", "laptop", "hp");

        this.mongoOperations.insertAll(Arrays.asList(product1, product2, product3, product4, product5, product6));

        // Find all laptops
        final Query findAllLaptops = new Query(Criteria.where("tags").is("laptop"));

        final List<Product> allLaptops = this.mongoOperations.find(findAllLaptops, Product.class);
        assertEquals(allLaptops.size(), 3);
        List<ObjectId> ids = with(allLaptops).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product4.getId(), product6.getId()));

        // Find all laptops and offers
        final Query findAllLaptopsOffers = new Query(Criteria.where("tags").all("laptop", "offer"));

        final List<Product> allLaptopsOffers = this.mongoOperations.find(findAllLaptopsOffers, Product.class);
        assertEquals(allLaptopsOffers.size(), 2);
        ids = with(allLaptopsOffers).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product4.getId()));

        // Find all favorite or top
        final Query findAllFavoriteOrTop = new Query(new Criteria().orOperator(Criteria.where("tags").is("favorite"), Criteria.where("tags")
                .is("top")));

        final List<Product> allFavoriteOrTop = this.mongoOperations.find(findAllFavoriteOrTop, Product.class);
        assertEquals(allFavoriteOrTop.size(), 4);
        ids = with(allFavoriteOrTop).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product2.getId(), product3.getId(), product5.getId()));
    }
}
