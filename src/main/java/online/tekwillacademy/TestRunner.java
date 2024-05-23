package online.tekwillacademy;

import online.tekwillacademy.managers.DataGeneratorManager;
import online.tekwillacademy.managers.DriverManager;
import online.tekwillacademy.managers.ScrollManager;
import org.openqa.selenium.*;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverManager.getInstance().getDriver();

        String currentTabName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.tekwillacademy-opencart.online/");
        System.out.println("The current urs is: " + driver.getCurrentUrl());
        System.out.println("The current page title is: " + driver.getTitle());

        WebElement userIconDropDown = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        userIconDropDown.click();

        WebElement registerOption = driver.findElement(By.xpath("//a[normalize-space()='Register']"));
        registerOption.click();

        System.out.println("The current urs is: " + driver.getCurrentUrl());
        System.out.println("The current page title is: " + driver.getTitle());

        WebElement firstNameInput = driver.findElement(By.id("input-firstname"));
        firstNameInput.sendKeys("Valeriu");

        WebElement lastNameInput = driver.findElement(By.id("input-lastname"));
        lastNameInput.sendKeys("Alexandrov");

        WebElement emailInput = driver.findElement(By.id("input-email"));
        String emailData = DataGeneratorManager.getRandomEmail();
        emailInput.sendKeys(emailData);
        System.out.println("Email:" + emailData);

        WebElement passwordInput = driver.findElement(By.id("input-password"));
        String password = DataGeneratorManager.getRandomPassword(10,15);
        passwordInput.sendKeys(password);
        System.out.println("Password: " + password);

        WebElement privacyToggle = driver.findElement(By.xpath("//input[@name='agree']"));
        ScrollManager.scrollToElement(privacyToggle);
        privacyToggle.click();

        WebElement continueButton = driver.findElement(By.xpath("//button[normalize-space()='Continue']"));
        continueButton.click();

        Thread.sleep(500);

        System.out.println("The current urs is: " + driver.getCurrentUrl());
        System.out.println("The current page title is: " + driver.getTitle());

        driver.close();

        driver.switchTo().window(currentTabName);
        driver.get("https://www.tekwillacademy-opencart.online/");
        driver.quit();

        System.out.println("The test is finished and the driver is closed");
    }
}