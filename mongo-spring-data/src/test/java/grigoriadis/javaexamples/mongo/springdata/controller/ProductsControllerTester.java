package grigoriadis.javaexamples.mongo.springdata.controller;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;
import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.repository.ProductRepository;

import org.bson.types.ObjectId;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductsControllerTester
{
    private ProductsController controller;

    @Mock
    private ProductRepository productRepository;

    @BeforeClass
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        this.controller = new ProductsController(this.productRepository);
    }

    @Test
    public void testGetById()
    {
        final ObjectId id = new ObjectId();
        final String productName = "test";

        when(this.productRepository.findOne(id)).thenReturn(new Product(productName));

        final Product product = this.controller.getById(id.toString());

        assertEquals(product.getName(), productName);

        verify(this.productRepository).findOne(id);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByIdEmptyId()
    {
        this.controller.getById("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetByIdNullProduct()
    {
        this.controller.getById("23423423");
    }
}
