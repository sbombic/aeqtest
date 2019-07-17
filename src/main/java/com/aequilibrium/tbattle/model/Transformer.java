package com.aequilibrium.tbattle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Table(name = "TRANSFORMERS")
public class Transformer implements Serializable, Comparable<Transformer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Affiliation type;

    private int strength;
    private int intelligence;
    private int speed;
    private int endurance;
    private int rank;
    private int courage;
    private int firepower;
    private int skill;

    @Override
    public int compareTo(Transformer o) {
        assert o != null;
        return Long.compare(o.getRank(), getRank());
    }

    @Transient
    @JsonIgnore
    public int getOverallRating() {
        return strength + intelligence + speed + endurance + firepower;
    }
}
