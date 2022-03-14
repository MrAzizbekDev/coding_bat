package uz.pdp.codingbat.controller;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.dto.ResponseDto;
import uz.pdp.codingbat.dto.ThemeDto;
import uz.pdp.codingbat.entity.Theme;
import uz.pdp.codingbat.repository.ThemeRepository;
import uz.pdp.codingbat.service.ThemeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/theme")
@RequiredArgsConstructor
public class ThemeController {
    final ThemeRepository themeRepository;
    final ThemeService themeService;

    @GetMapping("/list")
    public HttpEntity<?> getAll() {
        return ResponseEntity.ok().body(themeRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Theme> optional = themeRepository.findById(id);
        return optional.isPresent() ? ResponseEntity.ok().body(optional.get())
                : ResponseEntity.status(404).body(new Theme());
    }

    @PostMapping()
    public HttpEntity<?> add(@Valid @RequestBody ThemeDto themeDto) {
        ResponseDto responseDto = themeService.add(themeDto);
        return ResponseEntity.status(responseDto.isSuccess() ? 201 : 409).body(responseDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody ThemeDto themeDto) {
        ResponseDto responseDto = themeService.edit(id, themeDto);
        return ResponseEntity.status(responseDto.isSuccess() ? 200 : 404).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> del(@PathVariable Integer id) {
        ResponseDto responseDto = themeService.del(id);
        return ResponseEntity.status(responseDto.isSuccess() ? 204 : 404).body(responseDto);
    }
    @GetMapping("/category_id")
    public HttpEntity<?>getCategoryId(@PathVariable Integer id){
        Optional<Theme> optional = themeRepository.findByCategory_Id(id);
        return optional.isPresent()?ResponseEntity.ok().body(optional.get())
                :ResponseEntity.status(404).body(new Theme());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
