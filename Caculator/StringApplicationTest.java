package calculator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringApplicationTest {
    StringApplication application;

    @BeforeClass
    void setUp(){
        application = new StringApplication();
    }

    //Data driven testing
    @DataProvider
    Object[][] vowelsDataTest(){
        return new Object[][]{
                {"a",true},
                {"o",true},
                {"e",true},
                {"i",true},
                {"u",true},
                {"A",true},
                {"b", false},
                {"ho",false},
                {"B",false},
                {"AAAA",false},
                {"1",false}
        };
    }

    @Test(dataProvider = "vowelsDataTest")
    void aIsVowel(String input, boolean expectedResult){
        Assert.assertEquals(application.isNguyenAm(input),expectedResult);
    }

}
