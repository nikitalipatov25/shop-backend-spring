package com.nikitalipatov.handmadeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String surname;
    private String name;
    private String secondName;
    private String email;
    private String phoneNumber;
    private String address;

    public UserDTO(String surname, String name, String secondName, String phoneNumber, String address) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
