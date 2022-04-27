package pl.tkaras.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tkaras.models.documents.Category;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private String id;

    private Category category;

    private String content;

    private List<String> answers;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer correctAnswer;
}
