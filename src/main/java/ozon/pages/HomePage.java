package ozon.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ozon.annotations.FieldName;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {

    @FieldName(name = "Список верхнего меню")
    @FindBy(xpath = "//*[@class='_33091']//span/..")
    public List<WebElement> headerButtons;

    @FieldName(name = "Поиск")
    @FindBy(xpath = "//*[@placeholder='Искать на Ozon']")
    public WebElement searchField;

    @FieldName(name = "Кнопка поиска")
    @FindBy(xpath = "//div[@class='search-button-wrap']/button")
    public WebElement searchButton;

    @FieldName(name = "Всплывающее окно обёртки")
    @FindBy(xpath = "//*[@class='modal-wrapper']")
    public WebElement modalWrapper;

    @FieldName(name = "Подтвердить cookie")
    @FindBy(xpath = "//*[contains(@aria-label,'Закрыть сообщение')]")
    public
    WebElement acceptCookie;

    public void waitToClose(WebElement element) {
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            waiting.until(ExpectedConditions.invisibilityOfAllElements(element)); }
        catch (TimeoutException e) { return;}
    }

    @Override
    WebElement getField(String name) throws ClassNotFoundException {
        return super.getFields(name,"ozon.pages.HomePage");
    }
}
