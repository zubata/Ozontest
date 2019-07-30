package ozon.steps;

import cucumber.api.java.ru.Допустим;
import ozon.pages.HomePage;

public class HomePageStep {

    HomePage homePage = new HomePage();

    @Допустим("одобрить cookie")
    public void acceptCookie() {
        homePage.acceptCookie.click();
    }

    @Допустим("зайти на страницу \"(.*)\"")
    public void pressHeaderButton(String pointName) {
        homePage.selectMenuPoint(homePage.headerButtons,pointName);
    }

    @Допустим("выполнить \"(.*)\" по позиции \"(.*)\"")
    public void searchBy(String search,String searchName) throws ClassNotFoundException {
        homePage.waitToClose(homePage.modalWrapper);
        homePage.fillfield(search, searchName);
        homePage.searchButton.click();
    }


}
