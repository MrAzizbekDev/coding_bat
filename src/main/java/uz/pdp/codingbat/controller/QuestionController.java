package uz.pdp.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.dto.QuestionDto;
import uz.pdp.codingbat.entity.Question;
import uz.pdp.codingbat.repository.AnswerRepository;
import uz.pdp.codingbat.repository.QuestionRepository;
import uz.pdp.codingbat.service.QuestionService;

import java.util.Optional;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    final QuestionRepository questionRepository;
    final AnswerRepository answerRepository;
    final QuestionService questionService;

    @GetMapping("/all")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(questionRepository.findAll());
    }

    @GetMapping("/theme/{id}")
    public HttpEntity<?> getTheme(@PathVariable Integer id) {
        Optional<Question> optional = questionRepository.findByTheme_Id(id);
        return ResponseEntity.status(optional.isPresent() ? 200 : 404).body(optional.get());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok().body(questionRepository.getById(id));
    }

    @GetMapping("/active")
    public HttpEntity<?> getActive() {
        return ResponseEntity.ok().body(questionRepository.getByActiveIsTrue());
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody QuestionDto questionDto) {
        HttpEntity<?> entity = questionService.add(questionDto);
        return entity;
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?>del(@PathVariable Integer id){
        return questionService.del(id);
    }
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id,@RequestBody QuestionDto questionDto){

        return questionService.edit(id, questionDto);
    }
}
