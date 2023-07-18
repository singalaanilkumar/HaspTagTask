import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class HashtagCareersTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(14));
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }


    @Test
    public void testFunctionality() throws InterruptedException, AWTException {
        driver.get("https://www.hashtag-ca.com/careers/apply?jobCode=QAE001");

        // Functional Test Cases
        // Test case 1: Job Code Parameter Handling
        Assert.assertTrue(driver.getCurrentUrl().contains("jobCode=QAE001"), "Job code parameter is not as expected.");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, 450);");

        // Test case 2: Form Submission
        WebElement nameField = driver.findElement(By.cssSelector("input[placeholder='Enter your name']"));
        nameField.sendKeys("Anil Singala");

        // Test case 3: Element Visibility
        WebElement nameField1 = driver.findElement(By.cssSelector("input[placeholder='Enter your name']"));
        Assert.assertTrue(nameField1.isDisplayed(), "Name field is not visible.");

        WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
        emailField.sendKeys("singalaanilkumar@email.com");

        WebElement PhoneNumber = driver.findElement(By.xpath("//input[@placeholder='Enter your phone']"));
        PhoneNumber.sendKeys("8897211248");


        WebElement fileInput = driver.findElement(By.cssSelector("input#inputFile.form-control.hash-input"));

        // Provide the file path to be uploaded
        String filePath = "Anil_Resume_QA_Automation (1).pdf"; // Replace with the actual file path

        // Click on the file input to trigger the file dialog
        fileInput.click();

        // Use the Robot class to simulate keyboard input for the file path
        Robot robot = new Robot();

        // Type the file path into the file dialog
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Press Ctrl+V to paste the file path
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        WebElement Description = driver.findElement(By.xpath("//textarea[@placeholder='Briefly Describe Yourself']"));
        Description.sendKeys("Streamlining testing processes using tools to ensure software quality and efficiency.");

        WebElement ApplyButton = driver.findElement(By.xpath("//button[@class='btn form-button font-12 font-bold']"));
        ApplyButton.click();
    }


}
