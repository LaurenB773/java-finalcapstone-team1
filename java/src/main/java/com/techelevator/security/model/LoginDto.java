<<<<<<<< HEAD:java/src/main/java/com/techelevator/security/model/LoginDto.java
package com.techelevator.security.model;
========
package com.techelevator.model.auth;
>>>>>>>> 45031a91ae9cabba0db806b4bada7dc5d1d776ea:java/src/main/java/com/techelevator/model/auth/LoginDto.java
/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, LoginDto represents the data a
    client must pass to the server for a login endpoint, and LoginResponseDto represents the object that's returned
    from the server to the client from a login endpoint.
 */
public class LoginDto {

   private String username;
   private String password;

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

   @Override
   public String toString() {
      return "LoginDTO{" +
              "username='" + username + '\'' +
              ", password='" + password + '\'' +
              '}';
   }
}
