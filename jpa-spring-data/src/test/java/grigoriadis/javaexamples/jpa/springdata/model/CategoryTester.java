package grigoriadis.javaexamples.jpa.springdata.model;

import static org.testng.Assert.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testng.annotations.Test;

public class CategoryTester extends AbstractModelTester
{
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private PlatformTransactionManager transactionManager;

    @Test
    public void testInsert() throws Exception
    {
        // -------------------------InitializeData-------------------------------
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        final Category category1 = new Category("Cat1");

        final Category category2 = new Category("Cat2", category1);

        final Category category3 = new Category("Cat3", category2);

        final Category category4 = new Category("Cat4", category3);

        this.entityManager.persist(category1);
        this.entityManager.persist(category2);
        this.entityManager.persist(category3);
        this.entityManager.persist(category4);

        this.transactionManager.commit(status);

        // Retrieve the object
        status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        final Category retrievedCategory = (Category) this.entityManager.createQuery("from Category where name=:name")
                .setParameter("name", category4.getName()).getSingleResult();

        assertEquals(retrievedCategory.getId(), category4.getId());
        assertEquals(retrievedCategory.getName(), category4.getName());

        assertNotNull(retrievedCategory.getParentCategory());
        assertEquals(retrievedCategory.getParentCategory().getId(), category3.getId());
        assertEquals(retrievedCategory.getParentCategory().getName(), category3.getName());

        assertNotNull(retrievedCategory.getParentCategory().getParentCategory());
        assertEquals(retrievedCategory.getParentCategory().getParentCategory().getId(), category2.getId());
        assertEquals(retrievedCategory.getParentCategory().getParentCategory().getName(), category2.getName());

        assertNotNull(retrievedCategory.getParentCategory().getParentCategory().getParentCategory());
        assertEquals(retrievedCategory.getParentCategory().getParentCategory().getParentCategory().getId(), category1.getId());
        assertEquals(retrievedCategory.getParentCategory().getParentCategory().getParentCategory().getName(), category1.getName());

        this.transactionManager.commit(status);
    }
}
