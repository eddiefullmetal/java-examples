package grigoriadis.javaexamples.jpa.springdata.repository;

import grigoriadis.javaexamples.jpa.springdata.model.Category;
import grigoriadis.javaexamples.jpa.springdata.model.Product;
import grigoriadis.javaexamples.jpa.springdata.model.ProductAttribute;
import grigoriadis.javaexamples.jpa.springdata.model.ProductImage;
import grigoriadis.javaexamples.jpa.springdata.model.ProductOffer;
import grigoriadis.javaexamples.jpa.springdata.model.ProductReview;
import grigoriadis.javaexamples.jpa.springdata.model.ProductTag;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testng.annotations.AfterMethod;

@ContextConfiguration(locations = "classpath:repository-tests-beans.xml")
public abstract class AbstractRepositoryTester extends AbstractTestNGSpringContextTests
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

    @AfterMethod
    public void cleanAfter()
    {
        // In case an exception was thrown from a test
        this.commitTransaction();

        // Delete all data
        this.beginTransaction();

        final Class[] modelClasses = new Class[] { ProductAttribute.class, ProductImage.class, ProductOffer.class, ProductReview.class,
                ProductTag.class, Product.class, Category.class };

        for (final Class modelClass : modelClasses)
        {
            this.entityManager.createQuery("DELETE FROM " + modelClass.getSimpleName()).executeUpdate();
        }

        this.commitTransaction();
    }

    protected void commitTransaction()
    {
        if (this.status != null)
        {
            this.transactionManager.commit(this.status);
            this.status = null;
        }
    }
}
