package model;

/**
 * Admin Staff Class
 */
public class AdminStaff {
    /**
     * The accessCode of AdminStaff
     */
    private String accessCode;
    /**
     * The userName of AdminStaff
     */
    private String userName;

    /**
     * constructor of admin staff
     * @param userName
     * @param accessCode
     */
    public AdminStaff(String userName, String accessCode) {
        this.userName = userName;
        this.accessCode = accessCode;
    }

    /**
     * get the accessCode
     * @return accessCode
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * get the userName
     * @return
     */
    public String getUserName() {
        return userName;
    }
}