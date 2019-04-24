/* Anton Outkine
 * 11/19
 * Represents a password in plaintext
 */
class Password implements Comparable {
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

    public int compareTo(Object o) {
        Password password = (Password) o;
        return getPassword().length() - password.getPassword().length();
    }
}
