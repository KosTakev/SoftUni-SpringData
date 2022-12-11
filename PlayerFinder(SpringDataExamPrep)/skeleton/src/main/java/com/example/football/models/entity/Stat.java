package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity {

    //Because the variables are Float, it doesn't have to be nullable = false
    private Float shooting;

    private Float passing;

    private Float endurance;

    public Stat() {

    }

    public Float getShooting() {
        return shooting;
    }

    public Stat setShooting(Float shooting) {
        this.shooting = shooting;
        return this;
    }

    public Float getPassing() {
        return passing;
    }

    public Stat setPassing(Float passing) {
        this.passing = passing;
        return this;
    }

    public Float getEndurance() {
        return endurance;
    }

    public Stat setEndurance(Float endurance) {
        this.endurance = endurance;
        return this;
    }
}
