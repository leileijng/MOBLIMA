package interfaces;

/**
 * interface of admin configuration
 */
public interface ISystemConfiguration {
    /**
     * set filter for moviegoer / view by revenue or ratings
     */
    public abstract void settingFilter();

    /**
     * update public holiday list
     */
    public abstract void updatePublicHoliday();

    /**
     * update price table (rules / rates)
     */
    public abstract void updatePriceTable();
}
