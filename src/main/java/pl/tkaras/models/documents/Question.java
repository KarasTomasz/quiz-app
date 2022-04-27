package pl.tkaras.models.documents;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document
public class Question {

    @Id
    private String id;

    private Category category;

    private String content;

    private List<String> answers;

    private Integer correctAnswer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
