package fr.forum.faluchard.exceptions;

public class UsernameTakenException extends RuntimeException {
    
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "Username " + this.username + " already taken";
    }

}
