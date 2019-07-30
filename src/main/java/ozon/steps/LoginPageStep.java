package ozon.steps;

import cucumber.api.java.ru.Допустим;
import org.junit.Assert;
import ozon.pages.LoginPage;

import java.util.Map;

public class LoginPageStep {

    LoginPage loginPage;

    public void subPageLoaded(String name) throws ClassNotFoundException {
        loginPage.getField(name).isDisplayed();
    }

    @Допустим("выбрать тип логина \"(.*)\"")
    public void pressLoginType(String pointName) throws ClassNotFoundException {
        loginPage = new LoginPage();
        subPageLoaded("Вход или регистрация");
        loginPage.selectMenuPoint(loginPage.typeOfLogin, pointName);
    }

    @Допустим("ввод данных и нажатие на кнопку \"(.*)\"")
    public void inputLoginAttributes(String name, Map<String,String> fields) throws ClassNotFoundException {
        fields.forEach((key,value) -> {
                    try {
                        fillField(key,value);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
        loginPage.getField(name).click();
    }

    @Допустим("заполняется поле \"(.*)\" с значением \"(.*)\"")
    public void fillField(String name, String value) throws ClassNotFoundException {
        subPageLoaded(name);
        loginPage.fillfield(name,value);
    }

    /*@Допустим("проверка, что заполненные поля не пустые")
    public void checkFields(Map<String, String> fields) {
        fields.forEach((key,value) -> {
            try {
                checkNotNullField(key);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }*/

    /*@Допустим("проверка поля \"(.*)\"")
    public void checkNotNullField(String field) throws ClassNotFoundException {
        Assert.assertNotNull("поле "+ field+" пустое",loginPage.getField(field).getAttribute("value"));
    }*/



}
