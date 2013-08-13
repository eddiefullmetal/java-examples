package grigoriadis.javaexamples.jpa.springdata.model;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:model-tests-beans.xml")
public abstract class AbstractModelTester extends AbstractTestNGSpringContextTests
{

}
