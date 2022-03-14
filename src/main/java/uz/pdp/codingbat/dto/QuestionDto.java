package uz.pdp.codingbat.dto;

import lombok.Data;

@Data
public class QuestionDto {
    private String name;
    private String title;
    private Integer themeId;
    private String answerQue;
}
