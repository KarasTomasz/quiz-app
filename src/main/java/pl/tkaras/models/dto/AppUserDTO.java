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
public class AppUserDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
