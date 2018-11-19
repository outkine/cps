/* Anton Outkine
 * 11/19
 * Runs
 */
class Runner {
    public static void main(String[] args) {
        UserAccount user = new UserAccount("Babbage", "2Bo!2b?Shkspr", "Master");
        System.out.println(user.toString());
        user.login("asdf", "asdf");
        System.out.println(user.toString());
        user.login("Babbage", "2Bo!2b?Shkspr");
        System.out.println(user.toString());
        user.logout();
        System.out.println(user.toString());
        UserAccount user2 = new UserAccount("Lovelace", "ttls,hI1derwUr!", "Admin");
        user2.login("Lovelace", "ttls,hI1derwUr!");
        System.out.println(user2.toString());
    }
}
