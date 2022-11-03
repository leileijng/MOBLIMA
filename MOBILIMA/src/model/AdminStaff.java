package model;
import java.io.*;
import java.util.*;

public class AdminStaff extends User {
    private String accessCode;
    private User user;

    public AdminStaff(String accessCode, User user, String userName, String phone, String email, int age) {
        super(userName, phone, email, age);
        this.accessCode = accessCode;
        this.user = user;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    
}