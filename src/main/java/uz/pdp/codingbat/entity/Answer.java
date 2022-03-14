package uz.pdp.codingbat.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20000)
    private String answerQuestion;
    private boolean isActive;
    @OneToOne
    private Question question;

}
