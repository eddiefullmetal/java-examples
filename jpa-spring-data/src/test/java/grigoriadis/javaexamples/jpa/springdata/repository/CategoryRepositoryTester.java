package grigoriadis.javaexamples.jpa.springdata.repository;

import static org.testng.Assert.*;
import grigoriadis.javaexamples.jpa.springdata.model.Category;

import javax.annotation.Resource;

import org.testng.annotations.Test;

public class CategoryRepositoryTester extends AbstractRepositoryTester
{
    @Resource
    private CategoryRepository repository;

    @Test
    public void testFindByName()
    {
        this.beginTransaction();

        final Category category = new Category("Cat1");
        final Category category1 = new Category("CatTest1", category);
        final Category category2 = new Category("CatTest2");

        this.entityManager.persist(category);
        this.entityManager.persist(category1);
        this.entityManager.persist(category2);

        this.commitTransaction();

        this.beginTransaction();

        final Category result = this.repository.findByName(category.getName());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getId(), result.getId());

        this.commitTransaction();
    }
}
