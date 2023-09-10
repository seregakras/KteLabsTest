package com.example.ktelabstest.services;

import com.example.ktelabstest.dto.GenericDTO;
import com.example.ktelabstest.mappers.GenericMapper;
import com.example.ktelabstest.model.GenericModel;
import com.example.ktelabstest.repositories.GenericRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public abstract class GenericService<T extends GenericModel, N extends GenericDTO> {
    protected final GenericRepository<T> repository;
    protected final GenericMapper<T, N> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> repository, GenericMapper<T, N> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<N> listAll() {
        return mapper.toDTOs(repository.findAll());
    }

    public N getOne(final Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow());
    }

    public N create(N newDTO) {
        if (newDTO.getUUID() == null) newDTO.setUUID(UUID.randomUUID().toString());
        if (newDTO.getWhenCreated() == null) newDTO.setWhenCreated(LocalDateTime.now());
        return mapper.toDTO(repository.save(mapper.toEntity(newDTO)));
    }

    public N update(N updateDTO) {
        return mapper.toDTO(repository.save(mapper.toEntity(updateDTO)));
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }
}