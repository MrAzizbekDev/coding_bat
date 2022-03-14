package uz.pdp.codingbat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbat.dto.ResponseDto;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    public ResponseDto add(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            return new ResponseDto("This category exist",false);
        }
        categoryRepository.save(category);
        return new ResponseDto("Saved",true);
    }

    public ResponseDto edit(Integer id, Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            return new ResponseDto("This category exist",false);
        }
        Category edit = categoryRepository.getById(id);
        edit.setName(category.getName());
        categoryRepository.save(edit);
        return new ResponseDto("Edited",true);
    }

    public ResponseDto del(Integer id) {
        Category category = categoryRepository.getById(id);
        category.setActive(false);
        categoryRepository.save(category);
        return new ResponseDto("Deleted",true);
    }
}
