package pl.tkaras.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private String id;

    private String questionId;

    private Integer correctAnswer;

    private boolean isAnswerRight;
}