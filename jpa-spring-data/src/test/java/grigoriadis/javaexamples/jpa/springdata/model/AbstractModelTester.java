package grigoriadis.javaexamples.jpa.springdata.model;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@ContextConfiguration(locations = "classpath:model-tests-beans.xml")
public abstract class AbstractModelTester extends AbstractTestNGSpringContextTests
{
    @PersistenceContext
    protected EntityManager entityManager;

    private TransactionStatus status;

    @Resource
    private PlatformTransactionManager transactionManager;

    protected void beginTransaction()
    {
        if (this.status != null)
        {
            throw new RuntimeException("Trying to start a new transaction before commiting the old");
        }

        this.status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    protected void commitTransaction()
    {
        this.transactionManager.commit(this.status);
        this.status = null;
    }
}
