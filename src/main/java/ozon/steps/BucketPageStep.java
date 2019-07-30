package ozon.steps;

import cucumber.api.java.ru.Допустим;
import org.junit.Assert;
import ozon.pages.BucketPage;

public class BucketPageStep {

    BucketPage bucketPage;

    @Допустим("проверить ранее добавленные товары и их сумму с товарами в корзине")
    public void checkGoods() {
        bucketPage = new BucketPage();
        bucketPage.matchingGoods();
    }

    @Допустим("удаление товаров из Корзины")
    public void deleteGoods() {
        bucketPage.deleteAndAccept();
    }

    @Допустим("этап разлогиниться")
    public void outLogging() {
        bucketPage.selectMenuPoint(bucketPage.headerButtons,"Кабинет");
        bucketPage.selectMenuPoint(bucketPage.cabinetMenu,"Выйти");
        bucketPage.toHomePage.click();
    }

    @Допустим("проверить отсутсвие товаров")
    public void checkEmtptyBucket() {
        Assert.assertEquals("Ошибка! Корзина не пустая","Корзина пуста",bucketPage.emptyBucket.getText());
    }
}
