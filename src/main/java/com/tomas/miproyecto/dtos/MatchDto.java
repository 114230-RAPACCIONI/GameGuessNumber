package com.tomas.miproyecto.dtos;

import com.tomas.miproyecto.models.MatchDifficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchDto {

    private Long id;

    private MatchDifficulty matchDifficulty;

    private Integer remainingTries;
}
