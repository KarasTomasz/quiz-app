package pl.tkaras.api.mappers.impl;

import org.springframework.stereotype.Component;
import pl.tkaras.api.documents.AppUser;
import pl.tkaras.api.dto.AppUserDTO;
import pl.tkaras.api.mappers.IMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserIMapper implements IMapper<AppUser, AppUserDTO> {

    @Override
    public AppUserDTO mapToDto(AppUser document) {
        return AppUserDTO.builder()
                .firstName(document.getFirstName())
                .lastName(document.getLastName())
                .email(document.getEmail())
                .score(document.getScore())
                .build();
    }

    @Override
    public List<AppUserDTO> mapToDtos(List<AppUser> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
