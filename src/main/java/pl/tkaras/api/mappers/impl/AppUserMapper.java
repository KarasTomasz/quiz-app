package pl.tkaras.api.mappers.impl;

import org.springframework.stereotype.Component;
import pl.tkaras.api.documents.AppUser;
import pl.tkaras.api.dto.AppUserDTO;
import pl.tkaras.api.mappers.IMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserMapper implements IMapper<AppUser, AppUserDTO> {

    @Override
    public AppUserDTO mapToDto(AppUser document) {
        return AppUserDTO.builder()
                .firstName(document.getFirstName())
                .lastName(document.getLastName())
                .email(document.getEmail())
                .build();
    }

    @Override
    public AppUser mapToDocument(AppUserDTO appUserDTO) {
        return AppUser.builder()
                .email(appUserDTO.getEmail())
                .firstName(appUserDTO.getFirstName())
                .lastName(appUserDTO.getLastName())
                .build();
    }

    @Override
    public List<AppUserDTO> mapToDtos(List<AppUser> list) {
        return list.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppUser> mapToDocuments(List<AppUserDTO> list) {
        return list.stream()
                .map(this::mapToDocument)
                .collect(Collectors.toList());
    }
}
