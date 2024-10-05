package com.tomas.miproyecto.services;

import com.tomas.miproyecto.models.Match;
import com.tomas.miproyecto.models.MatchDifficulty;
import com.tomas.miproyecto.models.RoundMatch;
import com.tomas.miproyecto.models.User;
import org.springframework.stereotype.Service;

@Service
public interface MatchService {
    Match createMatch(User user, MatchDifficulty matchDifficulty);

    Match getMatchById(Long matchId);

    RoundMatch playMatch(Match match, Integer number);
}
