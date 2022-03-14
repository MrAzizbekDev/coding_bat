package uz.pdp.codingbat.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.entity.Theme;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme,Integer> {
    boolean existsByNameIgnoreCase(String name);

    Optional<Theme> findByCategory_Id(Integer category_id);
}