package uz.pdp.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbat.dto.ResponseDto;
import uz.pdp.codingbat.dto.ThemeDto;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.entity.Theme;
import uz.pdp.codingbat.repository.CategoryRepository;
import uz.pdp.codingbat.repository.ThemeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeService {
    final ThemeRepository repository;
    final CategoryRepository categoryRepository;
    public ResponseDto add(ThemeDto themeDto) {
        Optional<Category> optional = categoryRepository.findById(themeDto.getCategoryId());
        if (!optional.isPresent()) return new ResponseDto("Category not found",false);
        if (repository.existsByNameIgnoreCase(themeDto.getName()))
            return new ResponseDto("Bunday tur mavjud",false);
        Category category = optional.get();
        Theme theme = new Theme();
        theme.setDescription(themeDto.getDescription());
        theme.setCategory(category);
        theme.setName(themeDto.getName());
        repository.save(theme);
        return new ResponseDto("Added",true);
    }

    public ResponseDto edit(Integer id,ThemeDto themeDto) {
        Optional<Theme> optionalTheme = repository.findById(id);
        if (!optionalTheme.isPresent()) return new ResponseDto("Not Found",false);
        Optional<Category> optional = categoryRepository.findById(themeDto.getCategoryId());
        if (!optional.isPresent()) return new ResponseDto("Category not found",false);
        if (repository.existsByNameIgnoreCase(themeDto.getName()))
            return new ResponseDto("Bunday tur mavjud",false);
        Category category = optional.get();
        Theme theme = optionalTheme.get();
        theme.setName(themeDto.getName());
        theme.setDescription(themeDto.getDescription());
        theme.setCategory(category);
        repository.save(theme);
        return new ResponseDto("Edited",true);
    }

    public ResponseDto del(Integer id) {
        Optional<Theme> optionalTheme = repository.findById(id);
        if (!optionalTheme.isPresent()) return new ResponseDto("Not Found",false);
        Theme theme = optionalTheme.get();
        theme.setActive(false);
        repository.save(theme);
        return new ResponseDto("Deleted",true);
    }
}
