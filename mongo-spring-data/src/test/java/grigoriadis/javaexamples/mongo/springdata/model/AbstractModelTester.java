package grigoriadis.javaexamples.mongo.springdata.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(locations = "classpath:model-tests-beans.xml")
public abstract class AbstractModelTester extends AbstractTestNGSpringContextTests
{
    @Autowired
    private MongoDbFactory mongoDbFactory;

    @Autowired
    protected MongoOperations mongoOperations;

    @AfterMethod
    public void cleanAfter()
    {
        // this.mongoDbFactory.getDb().dropDatabase();
    }

    @BeforeMethod
    public void cleanBefore()
    {
        this.mongoDbFactory.getDb().dropDatabase();
    }
}
