package ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ozon.annotations.FieldName;
import ozon.core.Initial;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    WebDriver driver;
    WebDriverWait waiting;

    public BasePage() {
        driver = Initial.getDriver();
        waiting = new WebDriverWait(driver, 3, 200);
        PageFactory.initElements(driver, this);
        pageWaitingLoad();
    }

    public WebElement waitelement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        return waiting.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void pageWaitingLoad() {
        long time;
        do { time = (long) ((JavascriptExecutor) driver).executeScript("return window.performance.timing.loadEventEnd"); }
        while (time == 0);
    }

    abstract WebElement getField(String name) throws ClassNotFoundException;

    public void fillfield(String name, String value) throws ClassNotFoundException {
        WebElement field = getField(name);
        waitelement(field);
        int limit = field.getAttribute("value").length();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            temp.append("\b");
        }
        temp.append(value);
        field.sendKeys(temp);
    }


    public WebElement getFields(String name, String className) throws ClassNotFoundException {
        Class classExample = Class.forName(className);
        List<Field> fields = Arrays.asList(classExample.getFields());
        for (Field field : fields){
            if (field.getAnnotation(FieldName.class).name().equals(name)){
                return driver.findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()));
            }
        }
        Assert.fail("Не объявлен метод " + name);
        return null;
    }

    public void selectMenuPoint(List<WebElement> listelemnts, String name) {
        for (WebElement item : listelemnts) {
            if (item.getText().contains(name)) {
                waitelement(item).click();
                return;
            }
        }
        Assert.fail(String.format("Элемент %s на странице не найден",name));
    }
}
