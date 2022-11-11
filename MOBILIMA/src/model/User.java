package model;

public class User {
    private String username;
    private String phone;
    private String email;
    private int age;
    public User(String userName){
        this.username = userName;
        this.phone = "98987676";
        this.email = userName + "@gmail.com";
        this.age = 22;
    }
    public User(String userName, String phone, String email, int age) {
        this.username = userName;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
}