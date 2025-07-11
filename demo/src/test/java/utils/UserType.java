package utils;

public enum  UserType {
     STANDARD("standard_user", "secret_sauce"),
    LOCKED_OUT("locked_out_user", "secret_sauce");

    public final String username;
    public final String password;

    UserType(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
