package uz.pdp.codingbat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.dto.ResponseDto;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.repository.CategoryRepository;
import uz.pdp.codingbat.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryRepository categoryRepository;
    final CategoryService categoryService;

    @GetMapping()
    public HttpEntity<?> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return ResponseEntity.ok().body(categoryList);
    }
    @GetMapping("/active")
    public HttpEntity<?>getActive(){
        return ResponseEntity.ok().body(categoryRepository.findAllByActiveTrue());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.<HttpEntity<?>>map(category -> ResponseEntity.ok().body(category)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category()));
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody Category category) {
        ResponseDto responseDto = categoryService.add(category);
        return ResponseEntity.status(responseDto.isSuccess() ? 201 : 409).body(responseDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody Category category) {
        ResponseDto responseDto = categoryService.edit(id, category);
        return ResponseEntity.status(responseDto.isSuccess() ? 200 : 409).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ResponseDto del = categoryService.del(id);
        return ResponseEntity.status(del.isSuccess() ? 204 : 404).body(del);
    }
}
