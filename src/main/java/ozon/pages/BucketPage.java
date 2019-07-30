package ozon.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ozon.annotations.FieldName;

import java.util.ArrayList;
import java.util.List;

public class BucketPage extends BasePage {

    @FieldName(name = "список товаров")
    @FindBy(xpath = "//*[@class='main split-item']")
    public List<WebElement> orderList;

    @FieldName(name = "удаление товаров")
    @FindBy(xpath = "//*[contains(text(),'Удалить выбранные')]")
    public WebElement deleteAllGoods;

    @FieldName(name = "подтверждение удаления")
    @FindBy(xpath = "//button[@class='button button blue']")
    public WebElement acceptDeleting;

    @FieldName(name = "Список верхнего меню")
    @FindBy(xpath = "//*[@class='_33091']//span/..")
    public List<WebElement> headerButtons;

    @FieldName(name = "Меню кабинета")
    @FindBy(xpath = "//li[contains(@data-test-id,'header')]")
    public List<WebElement> cabinetMenu;

    @FieldName(name = "пустая корзина")
    @FindBy(xpath = "//h1")
    public WebElement emptyBucket;

    @FieldName(name = "на главную страницу")
    @FindBy(xpath = "//div[@class='_57b55']/a")
    public WebElement toHomePage;

    @FieldName(name = "итоговая цена")
    @FindBy(xpath = "//div[@data-test-id='total-price-block']//span[@class='price-number']")
    public WebElement finalPrice;


    public void matchingGoods() {
        ArrayList<String> bucketPrices = new ArrayList<>();
        orderList.forEach(item-> bucketPrices.add(item.findElement(By.xpath(".//*[@class='price-number']")).getText().replaceAll(" ","")));
        boolean flag=false;
        for(String price1 : bucketPrices) {
            for (String price2: SearchResultPage.searchPrices) {
                if(price2.equals(price1)) flag=true;
            }
            if(!flag)Assert.fail("Такого товара нет "+ price1);
        }
        int ordersum = bucketPrices.stream().mapToInt(Integer::parseInt).sum();
        int finalsum = Integer.parseInt(finalPrice.getText().replaceAll(" ",""));
        Assert.assertEquals("Cумма добавленных товаров неравна итоговой сумме на сайте",finalsum,ordersum);
    }

    public void deleteAndAccept() {
        deleteAllGoods.click();
        acceptDeleting.click();
    }

    @Override
    public WebElement getField(String name) throws ClassNotFoundException {
        return super.getFields(name,"ozon.pages.BucketPage");
    }
}
