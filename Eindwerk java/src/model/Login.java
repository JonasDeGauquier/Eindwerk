package model;

public class Login {
    private Integer loginId;
    private static String username;
    private static String Password;
    private User user;

    public Login(Integer loginId) {
        this.loginId = loginId;
    }

    public Login(Integer loginId, String username, String password, User user) {
        this.loginId = loginId;
        Login.username = username;
        Password = password;
        this.user = user;
    }

    public Login(int id,String username, String password) {
        loginId = id;
        Login.username = username;
        Password = password;
    }

    public Login(String username, String password) {
        Login.username = username;
        Password = password;
    }

    public Login(){}

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Login.username = username;
    }

    public static String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
