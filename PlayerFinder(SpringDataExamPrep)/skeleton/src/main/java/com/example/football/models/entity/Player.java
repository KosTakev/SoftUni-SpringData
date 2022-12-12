package com.example.football.models.entity;

import com.example.football.models.entity.enums.PlayerPosition;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private PlayerPosition position;

    @ManyToOne(optional = false)
    private Town town;

    @OneToOne
    private Stat stat;

    @ManyToOne(optional = false)
    private Team team;

    public Player() {

    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Player setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Player setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public Player setPosition(PlayerPosition position) {
        this.position = position;
        return this;
    }

    public Town getTown() {
        return town;
    }

    public Player setTown(Town town) {
        this.town = town;
        return this;
    }

    public Stat getStat() {
        return stat;
    }

    public Player setStat(Stat stat) {
        this.stat = stat;
        return this;
    }

    public Team getTeam() {
        return team;
    }

    public Player setTeam(Team team) {
        this.team = team;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(email, player.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
