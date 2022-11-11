package model;

/**
 * Admin Staff Class
 */
public class AdminStaff extends User {
    /**
     * The accessCode of AdminStaff
     */
    private String accessCode;

    /**
     * constructor of admin staff
     * @param username
     * @param accessCode
     */
    public AdminStaff(String username, String accessCode) {
        super(username);
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
        return super.getUsername();
    }
}