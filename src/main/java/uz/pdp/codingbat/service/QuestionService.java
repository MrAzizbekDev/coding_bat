package uz.pdp.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.codingbat.dto.QuestionDto;
import uz.pdp.codingbat.entity.Answer;
import uz.pdp.codingbat.entity.Question;
import uz.pdp.codingbat.entity.Theme;
import uz.pdp.codingbat.repository.AnswerRepository;
import uz.pdp.codingbat.repository.QuestionRepository;
import uz.pdp.codingbat.repository.ThemeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    final AnswerRepository answerRepository;
    final QuestionRepository questionRepository;
    final ThemeRepository themeRepository;
    public HttpEntity<?> add(QuestionDto questionDto) {
        Optional<Theme> optional = themeRepository.findById(questionDto.getThemeId());
        if (!optional.isPresent()) return ResponseEntity.status(404).body(new Question());
        Theme theme = optional.get();
        Answer answer = new Answer();
        Question question = new Question();
        question.setName(questionDto.getName());
        question.setTheme(theme);
        answer.setAnswerQuestion(questionDto.getAnswerQue());
        answer.setQuestion(question);
        answerRepository.save(answer);
        questionRepository.save(question);
        return ResponseEntity.ok().body("Added");
    }

    public HttpEntity<?> del(Integer id) {
        Optional<Question> optional = questionRepository.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(404).body("Not found");
        }
        Question question = optional.get();
        question.setActive(false);
        questionRepository.save(question);
        return ResponseEntity.ok().body("Deleted");
    }

    public HttpEntity<?> edit(Integer id, QuestionDto questionDto) {
        Optional<Question> optional = questionRepository.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(404).body("Not found");
        }
        Optional<Theme> byId = themeRepository.findById(questionDto.getThemeId());
        if (!byId.isPresent()) return ResponseEntity.status(404).body("Bunday Theme yoq");
        Optional<Answer> answerOptional = answerRepository.findById(id);
        Question question = optional.get();
        question.setName(questionDto.getName());
        question.setTheme(byId.get());
        question.setTitle(questionDto.getTitle());
        questionRepository.save(question);
        return ResponseEntity.ok().body("Edited");

    }
}
