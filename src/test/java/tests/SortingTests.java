package tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DressesCatalogue;

public class SortingTests extends BaseTest {

    @Test
    public void sortingByPriceAscTest(){
        DressesCatalogue dressesCatalogue = homePage.goToDressesCatalogue();
        dressesCatalogue.sortBy("Price: Lowest first");
        Assert.assertEquals(dressesCatalogue.getLastArticlePrice(),"$50.99");
    }

    @Test
    public void sortingFromZToATest(){
        DressesCatalogue dressesCatalogue = homePage.goToDressesCatalogue();
        dressesCatalogue.sortBy("Product Name: Z to A");
        Assert.assertEquals(dressesCatalogue.getLastArticleName(),"Printed Chiffon Dress");
    }

}
