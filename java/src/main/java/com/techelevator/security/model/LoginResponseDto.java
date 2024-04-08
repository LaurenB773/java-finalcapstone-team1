<<<<<<<< HEAD:java/src/main/java/com/techelevator/security/model/LoginResponseDto.java
package com.techelevator.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

========
package com.techelevator.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.model.User;
>>>>>>>> 45031a91ae9cabba0db806b4bada7dc5d1d776ea:java/src/main/java/com/techelevator/model/auth/LoginResponseDto.java
/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, CredentialsDto represents the data a client must
    pass to the server for a login endpoint, and TokenDto represents the object that's returned from the server
    to the client from a login endpoint.
 */
public class LoginResponseDto {

    private String token;
    private User user;

    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @JsonProperty("token")
    String getToken() {
        return token;
    }

    void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
