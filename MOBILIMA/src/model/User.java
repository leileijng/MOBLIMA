package model;
/**
 * A user class stores individual data
 */
public class User {
    /**
     * username of a User
     */
    private String username;
    /**
     * phone number of a User
     */
    private String phone;
    /**
     * email of a User
     */
    private String email;
    /**
     * age of a User
     */
    private int age;

    /**
     * constructor of a user
     * @param userName name of the user
     */
    public User(String userName){
        this.username = userName;
    }

    /**
     * get username
     * @return name of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set phone number for a user
     * @param phone phone number of the user
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * set email for a user
     * @param email email number of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set age for a user
     * @param age age of the user
     */
    public void setAge(int age) {
        this.age = age;
    }
}