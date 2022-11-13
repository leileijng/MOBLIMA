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
     * @param username default: admin
     * @param accessCode default password provided in the phase of development
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
     * @return userName
     */
    public String getUserName() {
        return super.getUsername();
    }
}