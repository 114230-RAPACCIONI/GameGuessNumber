package com.tomas.miproyecto.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Match {
    private Long id;
    private User user;
    private MatchDifficulty matchDifficulty;
    private Integer numberToGuess;
    private Integer remainingTries;
    private MatchStatus matchStatus;
}
