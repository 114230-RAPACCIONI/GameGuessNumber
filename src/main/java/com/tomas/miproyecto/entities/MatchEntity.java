package com.tomas.miproyecto.entities;

import com.tomas.miproyecto.models.MatchDifficulty;
import com.tomas.miproyecto.models.MatchStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    private MatchDifficulty matchDifficulty;

    private Integer numberToGuess;

    private Integer remainingTries;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updateAt;
}
