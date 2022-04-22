package pl.tkaras.api.mappers;

import org.springframework.stereotype.Component;
import pl.tkaras.api.documents.AppUser;
import pl.tkaras.api.dto.AppUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserMapper implements Mapper<AppUser, AppUserDto>{

    @Override
    public AppUserDto mapToDto(AppUser document) {
        return new AppUserDto().builder()
                .firstName(document.getFirstName())
                .lastName(document.getLastName())
                .email(document.getEmail())
                .gender(document.getGender())
                .score(document.getScore())
                .build();
    }

    @Override
    public List<AppUserDto> mapToDtos(List<AppUser> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
