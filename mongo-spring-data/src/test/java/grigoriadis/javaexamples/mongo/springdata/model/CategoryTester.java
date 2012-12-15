package grigoriadis.javaexamples.mongo.springdata.model;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class CategoryTester extends AbstractModelTester
{

    @Test
    private void testInsert()
    {
        // -------------------------InitializeData-------------------------------

        final Category category1 = new Category("Cat1");

        final Category category2 = new Category("Cat2", category1);

        final Category category3 = new Category("Cat3", category2);

        final Category category4 = new Category("Cat4", category3);

        this.mongoOperations.insert(category1);
        this.mongoOperations.insert(category2);
        this.mongoOperations.insert(category3);
        this.mongoOperations.insert(category4);

        // -------------------------------------------------------------------------------

        // Retrieve the object
        final Category retrievedCategory = this.mongoOperations.findOne(query(where("name").is(category4.getName())), Category.class);

        assertEquals(retrievedCategory.getId(), category4.getId());

        assertNotNull(retrievedCategory.getParentCategory());
        assertEquals(retrievedCategory.getParentCategory().getId(), category3.getId());

        assertNotNull(retrievedCategory.getParentCategory().getParentCategory());
        assertEquals(retrievedCategory.getParentCategory().getParentCategory().getId(), category2.getId());

        assertNotNull(retrievedCategory.getParentCategory().getParentCategory().getParentCategory());
        assertEquals(retrievedCategory.getParentCategory().getParentCategory().getParentCategory().getId(), category1.getId());
    }
}
