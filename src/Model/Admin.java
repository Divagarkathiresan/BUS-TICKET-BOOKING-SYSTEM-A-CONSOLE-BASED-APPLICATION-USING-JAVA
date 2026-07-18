package src.Model;

public class Admin extends User{
    private String role;

    public Admin(int userId, String name, String email, String password, String role) {
        super(userId, name, email, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}