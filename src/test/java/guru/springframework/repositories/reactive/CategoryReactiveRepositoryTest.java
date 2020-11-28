package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import guru.springframework.domain.Recipe;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest extends TestCase {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testCategorySave(){
        Category category = new Category();
        category.setDescription("Awesome!");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L),count);
    }

    @Test
    public void testFindByDescription(){
        Category category = new Category();
        category.setDescription("Awesome");

        categoryReactiveRepository.save(category).block();

        Category fetchedCat=categoryReactiveRepository.findByDescription("Awesome").block();

        assertNotNull(fetchedCat.getId());
    }

}