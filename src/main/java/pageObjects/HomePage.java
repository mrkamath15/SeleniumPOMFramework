package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement username_textField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password_textField;

    @FindBy(xpath = "//input[@name='submit']")
    private WebElement submit_button;

    public void enterUsername(String username) {
        username_textField.sendKeys(username);
    }

    public void enterPassword(String password) {
        password_textField.sendKeys(password);
    }

    public void clickSubmit() {
        submit_button.click();
    }
}
