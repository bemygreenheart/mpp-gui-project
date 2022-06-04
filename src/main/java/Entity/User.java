package Entity;

public class User {
    private Role role;
    private int id;
    private String password;

    public User(Role role, int id, String password) {
        this.role = role;
        this.id = id;
        this.password = password;
    }

    public boolean login(int id, String password) {
        return this.id == id && this.password.equals(password);
    }

    public Role getRole() {
        return role;
    }
}
