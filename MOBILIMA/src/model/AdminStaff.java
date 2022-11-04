package model;
import java.io.*;
import java.util.*;

public class AdminStaff {
    private String accessCode;
    private String userName;

    public AdminStaff(String userName, String accessCode) {
        this.userName = userName;
        this.accessCode = accessCode;
    }
}