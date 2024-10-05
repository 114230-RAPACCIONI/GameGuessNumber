package com.tomas.miproyecto.dtos;

import com.tomas.miproyecto.models.MatchDifficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserMatchDto {

    private MatchDifficulty difficulty;
}
