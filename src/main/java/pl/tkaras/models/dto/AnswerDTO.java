package pl.tkaras.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private String questionId;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer correctAnswer;

    private boolean isAnswerRight;
}