package uz.pdp.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.codingbat.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
  boolean existsByNameIgnoreCase(String name);

  @Query("select c from Category c where c.isActive = true")
  List<Category>findAllByActiveTrue();
}
