package pl.tkaras.models.documents;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Answer {

    @Id
    private String id;

    private String questionId;

    private Integer correctAnswer;

}