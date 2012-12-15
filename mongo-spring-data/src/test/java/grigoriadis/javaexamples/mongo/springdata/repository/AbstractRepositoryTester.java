package grigoriadis.javaexamples.mongo.springdata.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(locations = "classpath:repository-tests-beans.xml")
public abstract class AbstractRepositoryTester extends AbstractTestNGSpringContextTests
{
    @Autowired
    private MongoDbFactory mongoDbFactory;

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
