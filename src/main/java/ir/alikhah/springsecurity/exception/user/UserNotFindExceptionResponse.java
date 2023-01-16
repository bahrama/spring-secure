package ir.alikhah.springsecurity.exception.user;

public class UserNotFindExceptionResponse {

    private String username;

    public UserNotFindExceptionResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
