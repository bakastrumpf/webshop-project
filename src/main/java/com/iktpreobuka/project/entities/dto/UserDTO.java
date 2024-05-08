package com.iktpreobuka.project.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;

public class UserDTO {

// TODO    • 3.6 kreirati UserDto za potrebe kreiranja naloga korisnika
//• first name, last name, username, password, repeatedPassword, email
//• odgovarajući geteri i seteri i konstruktor bez parametara
//• zameniti u odgovarajućoj metodi UserController-a User sa UserDto

    @Column
    @JsonProperty("first_name")
    public String firstName;

    @Column
    @JsonProperty("last_name")
    public String lastName;

    @Column
    @JsonProperty("username")
    public String username;

    @Column
    @JsonProperty("password")
    public String password;

    @Column
    @JsonProperty("repeated_name")
    public String repeatedPasswod;

    @Column
    @JsonProperty("email")
    public String email;

    public UserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPasswod() {
        return repeatedPasswod;
    }

    public void setRepeatedPasswod(String repeatedPasswod) {
        this.repeatedPasswod = repeatedPasswod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
