public class Member {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String memberId;
    private String email;
    private String phoneNumber;

    // Constructor
    public Member(String firstName, String lastName, String username, String password, String memberId, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.memberId = memberId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    // other getters and setters can be here
}
