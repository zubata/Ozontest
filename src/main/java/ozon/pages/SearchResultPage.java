package ozon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ozon.annotations.FieldName;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    @FieldName(name = "Список верхнего меню")
    @FindBy(xpath = "//*[@class='_33091']//span/..")
    public List<WebElement> headerButtons;

    @FieldName(name = "от")
    @FindBy(xpath = "//*[contains(@id,'from_')]")
    public WebElement priceFrom;

    @FieldName(name = "до")
    @FindBy(xpath = "//*[contains(@id,'to_')]")
    public WebElement priceTo;

    @FieldName(name = "результат поиска")
    @FindBy(xpath = "//div[@class='tiles']/div")
    public List<WebElement> resultList;

    @FieldName(name = "Клик по текс, чтобы применился корректно фильтр")
    @FindBy(xpath = "//div[@class='text']")
    public WebElement someText;

    static ArrayList<String> searchPrices = new ArrayList<>();

    public void goodsInBucket(boolean flag, int limit) {
        waiting.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='title']/span")));
        PageFactory.initElements(driver, this);
        int count = 0;
        for (WebElement item : resultList) {
            if (count == limit) break;
            int value = Integer.parseInt(item.getAttribute("data-index")) + 1;
            if (divideEvenOrOdd(flag, value)) {
                try {
                    item.findElement(By.xpath(".//*[@type='submit']")).click();
                    String temp = item.findElement(By.xpath(".//span[@data-test-id]")).getText().replaceAll(" ","");
                    searchPrices.add(temp.substring(0,temp.length()-1));
                    count++;
                } catch (NoSuchElementException e) {
                }
            }
        }
    }

    public boolean divideEvenOrOdd(boolean flag, int value) {
        if (value % 2 == 0) return flag;
        else return !flag;
    }

    @Override
    WebElement getField(String name) throws ClassNotFoundException {
        return super.getFields(name, "ozon.pages.SearchResultPage");
    }
}
