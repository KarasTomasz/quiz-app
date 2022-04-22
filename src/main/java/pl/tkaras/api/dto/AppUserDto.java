package pl.tkaras.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import pl.tkaras.api.documents.Gender;
import pl.tkaras.api.documents.Score;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Score score;

}
