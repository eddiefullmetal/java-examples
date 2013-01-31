package grigoriadis.javaexamples.jpa.springdata.controller;

import grigoriadis.javaexamples.jpa.springdata.dto.ProductDto;
import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.repository.ProductRepository;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/products")
public class ProductsController
{
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

    private Mapper mapper;

    private ProductRepository productRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public ProductDto getById(@PathVariable final long id)
    {
        final Product product = this.productRepository.findOne(id);

        if (product == null)
        {
            logger.error("cannot find product with id: {}", id);
            throw new IllegalArgumentException("invalid product id");
        }

        return this.mapper.map(product, ProductDto.class);
    }

    @Autowired
    public void setMapper(final Mapper mapper)
    {
        this.mapper = mapper;
    }

    @Autowired
    public void setProductRepository(final ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }
}
