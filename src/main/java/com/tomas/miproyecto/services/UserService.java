package com.tomas.miproyecto.services;

import com.tomas.miproyecto.models.Match;
import com.tomas.miproyecto.models.MatchDifficulty;
import com.tomas.miproyecto.models.RoundMatch;
import com.tomas.miproyecto.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(String userName, String email);

    Match createUserMatch(Long userId, MatchDifficulty matchDifficulty);

    RoundMatch playUserMatch(Long userId, Long matchId, Integer numberToPlay);

}
