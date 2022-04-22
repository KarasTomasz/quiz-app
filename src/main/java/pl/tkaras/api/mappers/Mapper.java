package pl.tkaras.api.mappers;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Mapper <T, DTO>{

    public DTO mapToDto(T document);

    public List<DTO> mapToDtos(List<T> list);

}
