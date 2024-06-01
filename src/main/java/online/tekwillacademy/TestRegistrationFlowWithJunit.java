package online.tekwillacademy;

import online.tekwillacademy.managers.DataGeneratorManager;
import online.tekwillacademy.managers.DriverManager;
import online.tekwillacademy.pageobjects.AccountPage;
import online.tekwillacademy.pageobjects.HomePage;
import online.tekwillacademy.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class TestRegistrationFlowWithJunit {

    static WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeAll
    public static void executeOnceBeforeAllTheTests(){
        System.out.println("The test suite has been started");
    }
    @BeforeEach
    public void executeTheCodeBeforeEachTest(){
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://www.tekwillacademy-opencart.online/");
        homePage = new HomePage(driver);
        homePage.navigateToRegisterPage();
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("Successfull refistration of a user by using valid credentials")
    public void registerWithValidData() throws InterruptedException {

        String name = DataGeneratorManager.getRandomName();
        String email = DataGeneratorManager.getRandomEmail();
        String password = DataGeneratorManager.getRandomPassword(10,20);
        System.out.println("Email: " + email + "Password:" + password);

        registerPage.completeTheRegisterForm(name, name, email, password);
        registerPage.enablePrivacyToggle();
        registerPage.clickOnContinueButton();

        Thread.sleep(1000);

        AccountPage accountPage = new AccountPage(driver);
        Assertions.assertTrue(accountPage.isLogOutButtonDisplayed(), "The logOutButton is diplayed");

        driver.quit();
    }

    @Test
    @DisplayName("Unable to register a user by using invalid password")
    public void registerWithInvalidData() throws InterruptedException {

        String name = DataGeneratorManager.getRandomName();
        String email = DataGeneratorManager.getRandomEmail();
        String password = DataGeneratorManager.getRandomPassword(1,2);
        System.out.println("Email: " + email + "Password:" + password);

        registerPage.completeTheRegisterForm(name, name, email, password);
        registerPage.enablePrivacyToggle();
        registerPage.clickOnContinueButton();

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("register");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The Url contain Register keyword");
    }

    @AfterEach
    public void executeScriptAfterEachTest(){
      DriverManager.getInstance().quitTheDriver();
    }

    @AfterAll
    public static void executeTheScriptAfterAllTheSuiteTests(){
        System.out.println("The test suite has been executed");
    }

}
