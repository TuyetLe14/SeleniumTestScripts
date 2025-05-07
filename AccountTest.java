package Account;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountTest {
    AccountApplication account;

    @BeforeClass
    void setAccount() {account = new AccountApplication();}

    //Data driven testing
    @DataProvider
    Object[][] passwordsDataTest(){
        return new Object[][]{
                {"Km@123",false},
                {"Km@1235sfri",true},
                {"K@133543275",false},
                {"k%hns1235456",false},
                {"Kmshdb54164123",false},
                {"Kmhdhsgdh@*jshd",false},
                {"Ujsnh@$456254",true},
                {"Uhsgd45662",false},
                {"Ujsn@hsb4566",true},
                {"Kmhs73663266",false}
        };
    }

    @Test(dataProvider = "passwordsDataTest")
    void TestPassword(String input, boolean expectedResult){
        Assert.assertEquals(account.TestPassword(input),expectedResult);
    }
}


