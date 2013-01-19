package grigoriadis.javaexamples.mongo.springdata.controller;

import grigoriadis.javaexamples.mongo.springdata.model.Product;
import grigoriadis.javaexamples.mongo.springdata.repository.ProductRepository;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/products")
public class ProductsController
{
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductsController(final ProductRepository productRepository)
    {
        if (productRepository == null)
        {
            logger.error("null productRepository");
            throw new IllegalArgumentException("null productRepository");
        }

        this.productRepository = productRepository;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getById(@PathVariable final String id)
    {
        if (StringUtils.isBlank(id))
        {
            logger.error("id cannot be null or empty");
            throw new IllegalArgumentException("Id cannot be null or empty");
        }

        final Product product = this.productRepository.findOne(new ObjectId(id));

        if (product == null)
        {
            logger.error("cannot find product with id: {}", id);
            throw new IllegalArgumentException("invalid product id");
        }

        return product;
    }
}
