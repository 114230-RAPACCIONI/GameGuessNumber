package com.tomas.miproyecto.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundMatchDto {
    private MatchDto matchDto;

    private String response;
}

