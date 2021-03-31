package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccessPage {

    public LoginSuccessPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h3[text()='Login Successfully']")
    private WebElement loginSuccess_label;

    public boolean isLoginSuccessLabelDisplayed() {
        return loginSuccess_label.isDisplayed();
    }
}
