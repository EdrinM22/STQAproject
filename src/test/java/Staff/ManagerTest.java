package test.java.Staff;

import Staff.Gender;
import Staff.Manager;
import Staff.Worker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {

    @Test
    public void testIsCheckLibrarians() {
        Manager manager = new Manager();
        assertEquals(false, manager.isCheckLibrarians());
    }

    @Test
    public void testSetCheckLibrarians() {
        Manager manager = new Manager();
        manager.setCheckLibrarians(true);
        assertEquals(true, manager.isCheckLibrarians());
    }

    @Test
    public void testIsPermitionToPurchase() {
        Manager manager = new Manager();
        assertEquals(false, manager.isPermitionToPurchse());
    }

    @Test
    public void testSetPermitionToPurchase() {
        Manager manager = new Manager();
        manager.setPermitionToPurchse(true);
        assertEquals(true, manager.isPermitionToPurchse());
    }

    @Test
    public void testParameterizedConstructor() {
        Manager manager = new Manager("John Doe", "123-456-7890", "john.doe@example.com", 50000,
                "01/01/1990", Gender.MALE, "password123", Worker.ACCESSLEVEL.MANAGER, true, false);

        assertEquals("John Doe", manager.getFullName());
        assertEquals("123-456-7890", manager.getPhone());
        assertEquals("john.doe@example.com", manager.getEmail());
        assertEquals(50000.0, manager.getSalary(), 0.01);
        assertEquals("01/01/1990", manager.getDateOfBirth());
        assertEquals(Gender.MALE, manager.getGender());
        assertEquals("password123", manager.getPassword());
        assertEquals(true, manager.isPermitionToPurchse());
        assertEquals(false, manager.isCheckLibrarians());
    }
}
