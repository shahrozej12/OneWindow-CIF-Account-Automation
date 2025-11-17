package define;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class MyAutomation {

    // This method handles the Enter key simulation
    public void certificate() throws AWTException {
        try {
            // Simulate pressing "Enter"
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);  // Press the Enter key
        } catch (AWTException e) {
            throw new AWTException("Error simulating Enter key press: " + e.getMessage());
        }
    }
}
