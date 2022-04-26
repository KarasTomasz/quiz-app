package pl.tkaras.api.mappers;

import java.util.List;

public interface IMapper<T, DTO>{

    DTO mapToDto(T document);

    T mapToDocument(DTO dto);

    List<DTO> mapToDtos(List<T> list);

    List<T> mapToDocuments(List<DTO> list);

}
