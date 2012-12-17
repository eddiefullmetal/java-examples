package grigoriadis.javaexamples.mongo.springdata.repository;

import static ch.lambdaj.Lambda.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static org.testng.Assert.*;
import grigoriadis.javaexamples.mongo.springdata.model.Category;
import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.model.ProductOffer;

import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.testng.annotations.Test;

public class ProductRepositoryTester extends AbstractRepositoryTester
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Test
    public void testFindByCategory()
    {
        final Category category = new Category("Laptop");
        final Category category1 = new Category("Cat");

        this.categoryRepository.save(Arrays.asList(category, category1));

        final Product product1 = new Product("Product1", category);
        final Product product2 = new Product("Product2", category1);
        final Product product3 = new Product("Product3", category);

        this.productRepository.save(Arrays.asList(product1, product2, product3));

        final List<Product> retrievedProducts = this.productRepository.findByCategory(category, null);
        assertEquals(retrievedProducts.size(), 2);
        final List<ObjectId> ids = with(retrievedProducts).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product3.getId()));
    }

    @Test
    public void testFindByCategoryName()
    {
        final Category category = new Category("Laptop");
        final Category category1 = new Category("Cat");

        this.categoryRepository.save(Arrays.asList(category, category1));

        final Product product1 = new Product("Product1", category);
        final Product product2 = new Product("Product2", category1);
        final Product product3 = new Product("Product3", category);

        this.productRepository.save(Arrays.asList(product1, product2, product3));

        final List<Product> retrievedProducts = this.productRepository.findByCategoryName(category.getName(), null);
        assertEquals(retrievedProducts.size(), 2);
        final List<ObjectId> ids = with(retrievedProducts).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product3.getId()));
    }

    @Test
    public void testFindByDescriptionLike()
    {
        // Initialize data
        final Product product1 = new Product("Product1", "XBOX controller, Wireless comfort, perfect ergonomics and full control");
        final Product product2 = new Product("Product2", "Product Type:Battery and charger for XBOX");
        final Product product3 = new Product("Product3", "Latest model 2012 XBOX 360 250GB");
        final Product product4 = new Product("Product4", "PS3 model");
        final Product product5 = new Product("Product5", "PS3 controller, Wireless comfort, perfect ergonomics and full control");

        this.productRepository.save(Arrays.asList(product1, product2, product3, product4, product5));

        // ------------

        final List<Product> retrievedProducts = this.productRepository.findByDescriptionLike("(?i)xbox", new PageRequest(0, 3));

        assertNotNull(retrievedProducts);
        assertEquals(retrievedProducts.size(), 3);
        final List<ObjectId> ids = with(retrievedProducts).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product2.getId(), product3.getId()));
    }

    @Test
    public void testFindByName()
    {
        // Initialize data
        final Product product1 = new Product("Product1");

        final Product product2 = new Product("Product2");
        product2.getTags().addAll(Arrays.asList("Tag1", "Tag2", "Tag3"));

        final Product product3 = new Product("Product3");

        this.productRepository.save(Arrays.asList(product1, product2, product3));

        // -------------

        final Product retrievedProduct = this.productRepository.findByName(product2.getName());

        assertNotNull(retrievedProduct);
        assertEquals(retrievedProduct.getId(), product2.getId());
        assertEquals(retrievedProduct.getTags(), product2.getTags());
    }

    @Test
    public void testFindByOffersDiscount()
    {
        // Initialize data
        final Product product1 = new Product("Product1");
        product1.getOffers().add(new ProductOffer(20, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate()));
        product1.getOffers().add(new ProductOffer(10, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate()));
        product1.getOffers().add(new ProductOffer(30, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate()));

        final Product product2 = new Product("Product2");

        final Product product3 = new Product("Product3");
        product3.getOffers().add(new ProductOffer(10, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate()));

        final Product product4 = new Product("Product4");
        product4.getOffers().add(new ProductOffer(10, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate()));
        product4.getOffers().add(new ProductOffer(10, new LocalDate(2012, 06, 05).toDate(), new LocalDate(2012, 06, 06).toDate()));

        final Product product5 = new Product("Product5");
        product5.getOffers().add(new ProductOffer(20, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate()));

        this.productRepository.save(Arrays.asList(product1, product2, product3, product4, product5));

        // ------------

        final List<Product> retrievedProducts = this.productRepository.findByOffersDiscount(10, new PageRequest(0, 3));

        assertNotNull(retrievedProducts);
        assertEquals(retrievedProducts.size(), 3);
        final List<ObjectId> ids = with(retrievedProducts).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product3.getId(), product4.getId()));
    }

    @Test
    public void testFindByTagsAll()
    {
        // Initialize data
        final Product product1 = new Product("Product1", "laptop", "top", "offer");
        final Product product2 = new Product("Product2", "mp3", "top", "favorite");
        final Product product3 = new Product("Product3", "book", "favorite", "offer");
        final Product product4 = new Product("Product4", "laptop", "offer");
        final Product product5 = new Product("Product5", "book", "top", "offer");
        final Product product6 = new Product("Product6", "laptop", "hp");

        this.productRepository.save(Arrays.asList(product1, product2, product3, product4, product5, product6));

        // -----

        // Find all laptops
        final List<Product> allLaptops = this.productRepository.findByTagsAll("laptop");

        assertEquals(allLaptops.size(), 3);
        List<ObjectId> ids = with(allLaptops).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product4.getId(), product6.getId()));

        // Find all laptops and offers
        final List<Product> allLaptopsOffers = this.productRepository.findByTagsAll("laptop", "offer");

        assertEquals(allLaptopsOffers.size(), 2);
        ids = with(allLaptopsOffers).extract(on(Product.class).getId());
        assertEquals(ids, Arrays.asList(product1.getId(), product4.getId()));
    }

    @Test
    public void testUpdate()
    {
        final Product product = new Product("Product1");

        this.productRepository.save(product);

        // Add a tag
        product.getTags().add("Tag1");
        this.productRepository.save(product);

        // NOTE It updates all fields
        final Product retrievedProduct = this.productRepository.findOne(product.getId());
        assertEquals(retrievedProduct.getTags().size(), product.getTags().size());
        assertEquals(retrievedProduct.getTags(), product.getTags());
    }
}
