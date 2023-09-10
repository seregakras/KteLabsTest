package com.example.ktelabstest.mappers;

import com.example.ktelabstest.dto.GenericDTO;
import com.example.ktelabstest.model.GenericModel;

import java.util.List;

public interface Mapper<E extends GenericModel, D extends GenericDTO> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDTOs(List<E> entities);
}
