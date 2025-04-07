package com.example.spring_uni_lab.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "goals", column = @Column(name = "goals")),
            @AttributeOverride(name = "assists", column = @Column(name = "assists"))
    })
    private Statistics statistics;

}
