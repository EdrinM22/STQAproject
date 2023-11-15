package test.java.Staff;

import Staff.Manager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {

    @Test
    public void testIsCheckLibrarians() {
        Manager manager = new Manager();
        assertEquals(false, manager.isCheckLibrarians());
    }
}
