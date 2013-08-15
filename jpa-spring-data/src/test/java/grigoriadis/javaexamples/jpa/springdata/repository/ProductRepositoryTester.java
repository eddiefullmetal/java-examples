package grigoriadis.javaexamples.jpa.springdata.repository;

import static ch.lambdaj.Lambda.*;
import static org.testng.Assert.*;
import grigoriadis.javaexamples.jpa.springdata.model.Category;
import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductOffer;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.springframework.data.domain.PageRequest;
import org.testng.annotations.Test;

public class ProductRepositoryTester extends AbstractRepositoryTester
{
    @Resource
    private ProductRepository productRepository;

    @Test
    public void testFindByCategory()
    {
        // Initialize data
        this.beginTransaction();

        final Category category = new Category("Laptop");
        final Category category1 = new Category("Cat");

        this.entityManager.persist(category);
        this.entityManager.persist(category1);

        final Product product1 = new Product("Product1", "Descr1", category);
        final Product product2 = new Product("Product2", "Descr2", category1);
        final Product product3 = new Product("Product3", "Descr3", category);

        this.entityManager.persist(product1);
        this.entityManager.persist(product2);
        this.entityManager.persist(product3);

        this.commitTransaction();
        // --------

        this.beginTransaction();

        final List<Product> retrievedProducts = this.productRepository.findByCategory(category, null);
        assertEquals(retrievedProducts.size(), 2);
        assertEquals(extract(retrievedProducts, on(Product.class).getId()), Arrays.asList(product1.getId(), product3.getId()));

        this.commitTransaction();
    }

    @Test
    public void testFindByCategoryName()
    {
        // Initialize data
        this.beginTransaction();

        final Category category = new Category("Laptop");
        final Category category1 = new Category("Cat");

        this.entityManager.persist(category);
        this.entityManager.persist(category1);

        final Product product1 = new Product("Product1", "Descr1", category);
        final Product product2 = new Product("Product2", "Descr2", category1);
        final Product product3 = new Product("Product3", "Descr3", category);

        // TODO check why it fails when using repositories instead of entity
        // manager.
        this.entityManager.persist(product1);
        this.entityManager.persist(product2);
        this.entityManager.persist(product3);

        this.commitTransaction();
        // --------

        this.beginTransaction();

        final List<Product> retrievedProducts = this.productRepository.findByCategoryName(category.getName(), null);
        assertEquals(retrievedProducts.size(), 2);
        assertEquals(extract(retrievedProducts, on(Product.class).getId()), Arrays.asList(product1.getId(), product3.getId()));

        this.commitTransaction();
    }

    @Test
    public void testFindByDescriptionContainingIgnoreCase()
    {
        // Initialize data
        this.beginTransaction();

        final Category category = new Category("Consoles");

        this.entityManager.persist(category);

        final Product product1 = new Product("Product1", "XBOX controller, Wireless comfort, perfect ergonomics and full control", category);
        final Product product2 = new Product("Product2", "Product Type:Battery and charger for XBOX", category);
        final Product product3 = new Product("Product3", "Latest model 2012 XBOX 360 250GB", category);
        final Product product4 = new Product("Product4", "PS3 model", category);
        final Product product5 = new Product("Product5", "PS3 controller, Wireless comfort, perfect ergonomics and full control", category);

        this.entityManager.persist(product1);
        this.entityManager.persist(product2);
        this.entityManager.persist(product3);
        this.entityManager.persist(product4);
        this.entityManager.persist(product5);

        this.commitTransaction();
        // ------------

        this.beginTransaction();

        final List<Product> retrievedProducts = this.productRepository.findByDescriptionContainingIgnoreCase("xbox", new PageRequest(0, 3));

        assertNotNull(retrievedProducts);
        assertEquals(retrievedProducts.size(), 3);
        assertEquals(extract(retrievedProducts, on(Product.class).getId()), Arrays.asList(product1.getId(), product2.getId(), product3.getId()));

        this.commitTransaction();
    }

    @Test
    public void testFindByName()
    {
        // Initialize data
        this.beginTransaction();
        final Category category = new Category("Category");
        this.entityManager.persist(category);

        final Product product1 = new Product("Product1", "Descr1", category);
        final Product product2 = new Product("Product2", "Descr2", category);
        final Product product3 = new Product("Product3", "Descr3", category);

        this.entityManager.persist(product1);
        this.entityManager.persist(product2);
        this.entityManager.persist(product3);

        this.commitTransaction();
        // -------------

        this.beginTransaction();
        final Product retrievedProduct = this.productRepository.findByName(product2.getName());

        assertNotNull(retrievedProduct);
        assertEquals(retrievedProduct.getId(), product2.getId());

        this.commitTransaction();
    }

    @Test
    public void testFindByOffersDiscount()
    {
        // Initialize data
        this.beginTransaction();
        final Category category = new Category("Category");
        this.entityManager.persist(category);

        final Product product1 = new Product("Product1", "Descr1", category);
        this.entityManager.persist(product1);

        final ProductOffer offer1 = new ProductOffer(product1, 20, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate());
        final ProductOffer offer2 = new ProductOffer(product1, 10, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate());
        final ProductOffer offer3 = new ProductOffer(product1, 30, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate());

        this.entityManager.persist(offer1);
        this.entityManager.persist(offer2);
        this.entityManager.persist(offer3);

        final Product product2 = new Product("Product2", "Descr2", category);
        this.entityManager.persist(product2);

        final Product product3 = new Product("Product3", "Descr3", category);
        this.entityManager.persist(product3);

        final ProductOffer offer4 = new ProductOffer(product3, 10, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate());
        this.entityManager.persist(offer4);

        final Product product4 = new Product("Product4", "Descr4", category);
        this.entityManager.persist(product4);

        final ProductOffer offer5 = new ProductOffer(product4, 10, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate());
        final ProductOffer offer6 = new ProductOffer(product4, 10, new LocalDate(2012, 06, 05).toDate(), new LocalDate(2012, 06, 06).toDate());

        this.entityManager.persist(offer5);
        this.entityManager.persist(offer6);

        final Product product5 = new Product("Product5", "Descr5", category);
        this.entityManager.persist(product5);

        final ProductOffer offer7 = new ProductOffer(product5, 20, new LocalDate(2012, 05, 05).toDate(), new LocalDate(2012, 05, 06).toDate());
        this.entityManager.persist(offer7);
        // ------------

        final List<Product> retrievedProducts = this.productRepository.findByOffersDiscount(10, new PageRequest(0, 3));

        assertNotNull(retrievedProducts);
        assertEquals(retrievedProducts.size(), 3);
        assertEquals(extract(retrievedProducts, on(Product.class).getId()), Arrays.asList(product1.getId(), product3.getId(), product4.getId()));
    }
}
