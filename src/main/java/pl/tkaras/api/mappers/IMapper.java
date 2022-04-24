package pl.tkaras.api.mappers;

import java.util.List;

public interface IMapper<T, DTO>{

    DTO mapToDto(T document);

    List<DTO> mapToDtos(List<T> list);

}
