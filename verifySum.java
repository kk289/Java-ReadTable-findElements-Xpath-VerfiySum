package verifyDataPack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class verifySum {

    WebDriver wd;

    @Before
    // Open ChromeBrowser
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // chrome browser
        wd = new ChromeDriver();
        wd.get("https://www.w3schools.com/sql/sql_count_avg_sum.asp");
    }

    @Test
    public void VerifySum() {
        // Get number of rows in table
        List<WebElement> Row_count = wd.findElements(By.xpath("//*[@id='main']/div[6]/table/tbody/tr"));
        System.out.println("Number of Rows= " + Row_count.size());

        // Get number of Columns in table
        List<WebElement> Col_count = wd.findElements(By.xpath("//*[@id='main']/div[6]/table/tbody/tr[1]/th"));
        System.out.println("Number of Columns = " + Col_count.size() + "\n");

        // divided Xpath In three parts to pass Row_count and Col_count values.
        String first_part = "//*[@id='main']/div[6]/table/tbody/tr[";
        String second_part = "]/td[";
        String third_part = "]";

        // xpath for Row Header and Extract the data value using Xpath
        String final_xpath = first_part + 1 + third_part;
        String Table_data = wd.findElement(By.xpath(final_xpath)).getText();
        for (String data : Table_data.split(" ")) {
            System.out.print(String.format("%-28s ", data));
        }
        System.out.println();

        // Print out dash line under Row Header
        for (int i = 0; i < 28 * 6; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Loop to extract all data inside table using xpath
        for (int i = 2; i <= Row_count.size(); i++) {
            for (int j = 1; j <= Col_count.size(); j++) {
                String final_xpath_1 = first_part + i + second_part + j + third_part;
                String Table_data_2 = wd.findElement(By.xpath(final_xpath_1)).getText();
                System.out.print(String.format("%-28s ", Table_data_2));
            }
            System.out.println("");
        }

        // Testing
        Float sum = 0.0f;   // init sum
        for (int i = 2; i <= Row_count.size(); i++) {
            String final_xpath_1 = first_part + i + second_part + 6 + third_part;
            String Table_data_2 = wd.findElement(By.xpath(final_xpath_1)).getText();
            Float floatVal = Float.valueOf(Table_data_2).floatValue();
            sum += floatVal;
        }

        System.out.println("\nThe total sum of Price is: " + sum);

        Float expectedSum = 90.35f;   // Enter total sum of price
        Assert.assertEquals(sum, expectedSum);
        System.out.println("\nAssertion Passed Successfully.");
    }

    @After
    // Close the browser
    public void Close() throws InterruptedException {
        wd.quit();
    }
}