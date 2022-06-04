package business;

import java.io.Serializable;

final public class User implements Serializable {
    private static final long serialVersionUID = 5147265048973262104L;
    private String id;

    private String password;
    private AuthType authType;

    public User(String id, String password, AuthType authType) {
        this.id = id;
        this.password = password;
        this.authType = authType;
    }
    public boolean login(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public AuthType getAuthType() {
        return authType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", authType=" + authType +
                '}';
    }
}
