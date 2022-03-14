package uz.pdp.codingbat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    @NotNull(message ="Name bosh bolmasligi kerak" )
    private String name;
    @NotNull(message = "Description topilmadi")
    private String description;
    @NotNull(message = "Bunday gategory mavjud emas")
    private Integer categoryId;
}
