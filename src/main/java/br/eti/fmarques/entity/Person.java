package br.eti.fmarques.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 5, max = 100)
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Past
    private LocalDate birthDay;

    public UUID getId() { return this.id; } 
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public LocalDate getBirthDay() { return this.birthDay; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBirthDay(LocalDate birthDay) { this.birthDay = birthDay; }    
}
