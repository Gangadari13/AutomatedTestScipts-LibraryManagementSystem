public class Member {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    // Constructor
    public Member(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // other getters and setters
}


