package grigoriadis.javaexamples.jpa.springdata.dto;

import static ch.lambdaj.Lambda.*;
import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductAttribute;
import grigoriadis.javaexamples.jpa.springdata.model.ProductTag;

import java.util.List;
import java.util.Map;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

public class ConfigurableMapper extends ma.glasnost.orika.impl.ConfigurableMapper
{
    @Override
    protected void configure(final MapperFactory factory)
    {
        super.configure(factory);
        factory.getConverterFactory().registerConverter("attributes", new CustomConverter<List<ProductAttribute>, Map<String, String>>()
        {
            @Override
            public Map<String, String> convert(final List<ProductAttribute> source, final Type<? extends Map<String, String>> destinationType)
            {
                final Map<String, ProductAttribute> indexed = index(source, on(ProductAttribute.class).getName());
                return convertMap(indexed, on(ProductAttribute.class).getValue());
            }
        });

        factory.getConverterFactory().registerConverter("tags", new CustomConverter<List<ProductTag>, List<String>>()
        {
            @Override
            public List<String> convert(final List<ProductTag> source, final Type<? extends List<String>> destinationType)
            {
                return extract(source, on(ProductTag.class).getTag());
            }
        });

        ClassMapBuilder<?, ?> builder = factory.classMap(Product.class, ProductDto.class);
        builder = builder.fieldMap("attributes", "attributes").converter("attributes").add();
        builder = builder.fieldMap("tags", "tags").converter("tags").add();
        builder = builder.byDefault();

        factory.registerClassMap(builder.toClassMap());
    }
}
