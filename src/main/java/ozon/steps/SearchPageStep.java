package ozon.steps;

import cucumber.api.java.ru.Допустим;
import ozon.pages.SearchResultPage;

public class SearchPageStep {

    SearchResultPage searchResultPage;

    @Допустим("ограничить цену \"(.*)\" \"(.*)\" \"(.*)\" \"(.*)\"")
    public void limit(String from, String fromPrice, String to, String toPrice) throws ClassNotFoundException {
        searchResultPage = new SearchResultPage();
        searchResultPage.fillfield(from,fromPrice);
        searchResultPage.fillfield(to,toPrice);
        searchResultPage.someText.click();
    }

    @Допустим("выбрать \"(.*)\" первые \"(.*)\" товаров")
    public void choice(String evenOrOdd,String limits) {
        boolean flag = evenOrOdd.equals("четные");
        int limit = Integer.parseInt(limits);
        searchResultPage.goodsInBucket(flag,limit);
    }

    @Допустим("перейти на страницу \"(.*)\"")
    public void goToBucket(String pointName) {
        searchResultPage.selectMenuPoint(searchResultPage.headerButtons,pointName);
    }

}
