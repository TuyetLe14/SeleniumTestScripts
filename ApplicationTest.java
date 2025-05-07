package calculator;
import org.testng.Assert;
import org.testng.annotations.*;
public class ApplicationTest {
    @Test
    public void addTwoNumber(){
        Application app= new Application();
        int result = app.add(1,1);
        Assert.assertEquals(result,2);
    }

    @Test
    public void subTwoNumber(){
        Application app= new Application();
        int result = app.sub(1,1);
        Assert.assertEquals(result,0);
    }

    @Test
    public void mulTwoNumber(){
        Application app= new Application();
        int result = app.mul(2,1);
        Assert.assertEquals(result,2);
    }

    @Test
    public void divTwoNumber(){
        Application app= new Application();
        int result = app.div(1,1);
        Assert.assertEquals(result,1);
    }

    @Test
    public void testprime(){
        System.out.print("@Test: testprime");
        Application app= new Application();
        boolean Num = app.TestIsPrimeNumber(7919);
        Assert.assertEquals(Num,true);
    }

    @Test
    public void IsLeapYear(){
        Application app = new Application();
        Assert.assertTrue(app.TestYear(2004));
    }

    @Test
    public  void IsLeapTestFalse(){
        Application app = new Application();
        Assert.assertFalse(app.TestYear(2005));
    }
}
