package grigoriadis.javaexamples.jpa.springdata.repository;

import grigoriadis.javaexamples.jpa.springdata.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>
{
    Category findByName(String name);
}
