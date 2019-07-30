package ozon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ozon.annotations.FieldName;

import java.util.List;

public class LoginPage extends BasePage {

    @FieldName(name = "Тип логина")
    @FindBy(xpath = "//*[@class='block-with-links']/a")
    public List<WebElement> typeOfLogin;

    @FieldName(name = "Почта")
    @FindBy(xpath = "//*[contains(text(),'Вход по почте')]/following::input[1]")
    public WebElement loginField;

    @FieldName(name = "Пароль")
    @FindBy(xpath = "//*[contains(text(),'Вход по почте')]/following::input[2]")
    public WebElement passwordField;

    @FieldName(name = "Вход или регистрация")
    @FindBy(xpath = "//*[contains(text(),'Вход или регистрация')]")
    public WebElement firstSubPage;

    @FieldName(name = "Вход по почте")
    @FindBy(xpath = "//*[contains(text(),'Вход по почте')]")
    public WebElement secondSubPage;

    @FieldName(name = "Войти")
    @FindBy(xpath = "//button[contains(@class,'submit')]")
    public WebElement enterButton;

    @Override
    public WebElement getField(String name) throws ClassNotFoundException {
        return super.getFields(name,"ozon.pages.LoginPage");
    }
}
