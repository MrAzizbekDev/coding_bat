package uz.pdp.codingbat.repository;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Optional<Question>findByTheme_Id(Integer theme_id);
    @Query("select q from Question q where q.isActive = true")
    List<Question>getByActiveIsTrue();
}
