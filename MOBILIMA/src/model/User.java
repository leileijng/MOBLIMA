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
     * @param userName
     */
    public User(String userName){
        this.username = userName;
    }

    /**
     * get username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * set phone number for a user
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * set email for a user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * set age for a user
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
}