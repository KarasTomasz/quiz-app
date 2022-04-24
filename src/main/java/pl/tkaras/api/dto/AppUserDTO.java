package pl.tkaras.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tkaras.api.documents.Score;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Score score;

}
