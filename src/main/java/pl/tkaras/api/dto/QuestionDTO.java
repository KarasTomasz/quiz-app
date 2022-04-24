package pl.tkaras.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tkaras.api.documents.Category;

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
    private Integer correctAnswer;
}
