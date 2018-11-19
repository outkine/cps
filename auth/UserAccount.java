/* Anton Outkine
 * 11/19
 * Represents an "account"
 */
class UserAccount {
    private String id;
    private Password password;
    private String accessLevel;
    private boolean isLoggedIn;

    public UserAccount(String id_, String password_, String access_) {
        id = id_;
        password = new Password(password_);
        accessLevel = access_;
        if (!access_.equals("Admin") && !access_.equals("SuperUser") && !access_.equals("Standard")) {
            accessLevel = "Standard";
        }
        isLoggedIn = false;
    }

    /* @param String
     * @param String
     * @return Boolean
     */
    public void login(String id_, String password_) {
        if (id_.equals(id) && password_.equals(password.getPassword())) {
            isLoggedIn = true;
        }
    }

    /* @param none
     * @return none
     */
    public void logout() {
        isLoggedIn = false;
    }

    public String toString() {
        if (isLoggedIn) {
            return "User: " + id + "\n" + password.toString() + "\nAccess Level: " + accessLevel;

        } else {
            return "Access Denied";
        }
    }

    public boolean setPassword(String newPassword) {
        password = new Password(newPassword);
        return true;
    }

    public boolean setPassword(String id_, String password_, String newPassword) {
        if (id_.equals(id) && password_.equals(password.getPassword())) {
            password = new Password(newPassword);
            return true;
        }
        return false;
    }

    private boolean checkPassword(String password) {
        return password.length() >= 8 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*(\\^|\\!|\\#|\\$|\\%|\\&|\\*|\\(|\\)|\\[|\\]|\\{|\\})).+$");
    }
}
