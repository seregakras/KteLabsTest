package com.example.ktelabstest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GenericDTO {
    private Long id;
    private String UUID;
    private LocalDateTime whenCreated;
}
