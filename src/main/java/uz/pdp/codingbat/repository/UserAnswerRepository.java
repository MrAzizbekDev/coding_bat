package uz.pdp.codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbat.entity.Answer;
import uz.pdp.codingbat.entity.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer> {
}
