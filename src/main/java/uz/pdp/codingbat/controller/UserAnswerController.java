package uz.pdp.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.dto.ResponseDto;
import uz.pdp.codingbat.dto.UserAnswerDto;
import uz.pdp.codingbat.entity.Answer;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.entity.User;
import uz.pdp.codingbat.entity.UserAnswer;
import uz.pdp.codingbat.repository.AnswerRepository;
import uz.pdp.codingbat.repository.CategoryRepository;
import uz.pdp.codingbat.repository.UserAnswerRepository;
import uz.pdp.codingbat.repository.UserRepository;
import uz.pdp.codingbat.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userAnswer")
@RequiredArgsConstructor
public class UserAnswerController {
    final UserAnswerRepository userAnswerRepository;
    final AnswerRepository answerRepository;
    final UserRepository userRepository;
    @GetMapping()
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(userAnswerRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<UserAnswer> optional = userAnswerRepository.findById(id);
        return (optional.isPresent()) ? ResponseEntity.ok().body(optional.get()) :
                ResponseEntity.status(404).body(new UserAnswer());
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody UserAnswerDto answerDto) {
        Optional<Answer> optional = answerRepository.findById(answerDto.getAnswerId());
        if (!optional.isPresent()) return ResponseEntity.status(404).body("Bunday javob mavjud emas");
        Optional<User> userOptional = userRepository.findById(answerDto.getUserId());
        if (!userOptional.isPresent()) return ResponseEntity.status(404).body("Bunday user mavjud emas");
        Answer answer = optional.get();
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAnswer(answer);
        userAnswer.setUser(userOptional.get());
        UserAnswer save = userAnswerRepository.save(userAnswer);
        return ResponseEntity.status(201).body("Saved");
    }
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@RequestBody UserAnswerDto userAnswerDto, @PathVariable Integer id){
        Optional<UserAnswer> optional = userAnswerRepository.findById(id);
        if (!optional.isPresent()) return ResponseEntity.status(404).body("Bunday User Answer Topilmadi");
        Optional<Answer> answer = answerRepository.findById(userAnswerDto.getAnswerId());
        if (!answer.isPresent()) return ResponseEntity.status(404).body("Bunday javob mavjud emas");
        Optional<User> userOptional = userRepository.findById(userAnswerDto.getUserId());
        if (!userOptional.isPresent()) return ResponseEntity.status(404).body("Bunday user mavjud emas");
        UserAnswer userAnswer = optional.get();
        userAnswer.setUser(userOptional.get());
        userAnswer.setAnswer(answer.get());
        UserAnswer save = userAnswerRepository.save(userAnswer);
        return ResponseEntity.status(202).body("Edited");

    }
    @DeleteMapping("/{id}")
    public HttpEntity<?>del(@PathVariable Integer id){
        Optional<UserAnswer> optional = userAnswerRepository.findById(id);
        if (!optional.isPresent()) return ResponseEntity.status(404).body("Bunday User Answer Topilmadi");
        userAnswerRepository.delete(optional.get());
        return ResponseEntity.ok().body("deleted");
    }

}
