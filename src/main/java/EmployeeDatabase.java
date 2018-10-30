import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements a database of employees.
 * <p>
 * This class models some functions of an employee database. We have two classes: EmployeeDatabase
 * and Employee. You only need to finish functions in this class, however you would have to refer to
 * the functions in the other class as well. And, of course, fix the checkstyle errors.
 *
 * @see <a href="https://cs125.cs.illinois.edu/lab/10/">Lab 10 Description</a>
 */
public class EmployeeDatabase {

    protected class Node {

        private int managers = -1;
        private int subordinates = -1;
        private Employee item;

        public Employee getItem() {
            return item;
        }

        public Node(Employee e) {
            item = e;
        }

    }

    /**
     * List of employees.
     */
    public List<Node> employees;

    /**
     * Constructor which initializes the employees list.
     * <p>
     * We suggest that you investigate the ArrayList class, which is one of the more useful built-in
     * data structures in Java.
     */
    public EmployeeDatabase() {
        employees = new ArrayList<Node>();
    }

    /**
     * Returns the manager for the given employee.
     *
     * @param employee
     * @return
     */
    Employee findManager(final Employee employee) {
        Employee manager = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getItem().getName() == employee.getManager()) {
                manager = employees.get(i).getItem();
                break;
            }
        }
        return manager;
    }

    /**
     * Count the number of managers above this employee.
     * <p>
     * Consider both a recursive and an iterative solution to this problem.
     *
     * @param employee name of the employee
     * @return int
     */
    public int countManagersAbove(final Employee employee) {
        if (employee == null) {
            return -1;
        }
        if (employee.getNumManagers() != -1) {
            return employee.getNumManagers();
        }
        int result = countManagersAbove(findManager(employee)) + 1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getItem() == employee) {
                employees.get(i).managers = result;
                return result;
            }
        }
        return result;
    }

    /**
     * Count the number of employees under this manager.
     * <p>
     * Consider both a recursive and an iterative solution to this problem.
     *
     * @param employee name of the employee
     * @return int
     */
    public int countEmployeesUnder(final Employee employee) {
        int count = 0;
        if (employee.getNumSubordinates() != -1) {
            return employee.getNumSubordinates();
        }
        for (Node n : employees) {
            for (Employee e = n.getItem(); e != null; e = findManager(e)) {
                if (e.getManager() == employee.getName()) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public void add(final Employee e) {
        employees.add(new Node(e));
        countManagersAbove(e);
        countEmployeesUnder(e);
        int originalS = 0;
        if (findManager(e) != null) {
            findManager(e).setNumSubordinates(findManager(e).getNumSubordinates() + 1);
        }
        for (Employee current = e; findManager(current) != null; current = findManager(current)) {
            int temp = originalS;
            originalS = current.getNumSubordinates();
            findManager(current).setNumSubordinates(findManager(current).getNumSubordinates()
                    - temp + current.getNumSubordinates());
        }
    }

    /**
     * Main method for testing.
     * <p>
     * You should understand, but do not need to modify, this function.
     *
     * @param unused unused input arguments
     */
    @SuppressWarnings("checkstyle:magicnumber")
    public static void main(final String[] unused) {

        EmployeeDatabase database = new EmployeeDatabase();

        Employee betty = new Employee("Betty", "Sam");
        database.add(betty);
        Employee bob = new Employee("Bob", "Sally");
        database.add(bob);
        Employee dilbert = new Employee("Dilbert", "Nathan");
        database.add(dilbert);
        Employee joseph = new Employee("Joseph", "Sally");
        database.add(joseph);
        Employee nathan = new Employee("Nathan", "Veronica");
        database.add(nathan);
        Employee sally = new Employee("Sally", "Veronica");
        database.add(sally);
        Employee sam = new Employee("Sam", "Joseph");
        database.add(sam);
        Employee susan = new Employee("Susan", "Bob");
        database.add(susan);
        Employee veronica = new Employee("Veronica", "");
        database.add(veronica);

        System.out.println("Welcome to the employee database\n\n");

        // Count employees under
        int answer = database.countEmployeesUnder(sally);
        System.out.println("Sally has " + Integer.toString(answer) + " employees under her.\n");

        answer = database.countEmployeesUnder(nathan);
        System.out.println("Nathan has " + Integer.toString(answer) + " employees under him.\n");

        answer = database.countEmployeesUnder(betty);
        System.out.println("Betty has " + Integer.toString(answer) + " employees under her.\n");

        answer = database.countEmployeesUnder(veronica);
        System.out.println("Veronica has " + Integer.toString(answer) + " employees under her.\n");

        // Count managers above
        answer = database.countManagersAbove(sally);
        System.out.println("Sally has " + Integer.toString(answer) + " managers above her.\n");

        answer = database.countManagersAbove(veronica);
        System.out.println("Veronica has " + Integer.toString(answer) + " managers above her.\n");

        answer = database.countManagersAbove(bob);
        System.out.println("Bob has " + Integer.toString(answer) + " managers above him.\n");

        answer = database.countManagersAbove(betty);
        System.out.println("Betty has " + Integer.toString(answer) + " managers above her.\n");

        Employee jotaro = new Employee("Jotaro", "Joseph");
        database.add(jotaro);

        answer = database.countEmployeesUnder(veronica);
        System.out.println("Veronica has " + Integer.toString(answer) + " employees under her.\n");

        answer = database.countEmployeesUnder(jotaro);
        System.out.println("Jotaro has " + Integer.toString(answer) + " employees under him.\n");

        answer = database.countManagersAbove(jotaro);
        System.out.println("Jotaro has " + Integer.toString(answer) + " managers above him.\n");
    }
}
