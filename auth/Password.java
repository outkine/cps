/* Anton Outkine
 * 11/19
 * Represents a password in plaintext
 */
class Password {
    private String password;

    public Password(String password_) {
        password = password_;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Password: " + password;
    }
}
