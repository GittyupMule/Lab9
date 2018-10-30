/**
 * Class implementing an employee.
 *
 * @see <a href="https://cs125.cs.illinois.edu/lab/9/">Lab 9 Description</a>
 */
public class Employee {

    private String name;
    private String manager;
    private int numManagers = -1;
    private int numSubordinates = -1;

    /**
     * Constructor for initialization.
     * @param mName
     * @param mManager
     */
    public Employee(final String mName, final String mManager) {
        this.name = mName;
        this.manager = mManager;
    }

    /**
     * Getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     * @param mName
     */
    public void setName(final String mName) {
        this.name = mName;
    }

    /**
     * Getter for manager.
     * @return manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * Setter for mManager.
     * @param mManager
     */
    public void setManager(final String mManager) {
        this.manager = mManager;
    }

    void setNumManagers(final int mNumManagers) {
        this.numManagers = mNumManagers;
    }

    void setNumSubordinates(final int mNumSubordinates) {
        this.numSubordinates = mNumSubordinates;
    }

    int getNumManagers() {
        return numManagers;
    }

    int getNumSubordinates() {
        return numSubordinates;
    }
}
