package entity;

public class User {
    private static String login;
    private static String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User getUser() {
        return user;
    }

    public  static final User user = new User("slavagaidukov@gmail.com","45Er5445");

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
