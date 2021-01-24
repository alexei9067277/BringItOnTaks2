package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesWithPO.SearchInfoOnPastebin;

public class Task2Tests {

    private WebDriver driver;
    protected SearchInfoOnPastebin searchInfoOnPastebin;

    @BeforeMethod(alwaysRun = true)// Метод, после аннотации, должен быть выполнен перед выполнением основного метода. Перед каждым методом, который помечен аннотацией @test
    public void BrowserSetup() {

        driver = new ChromeDriver(); // Объект браузера для драйвера
        driver.manage().window().maximize();
        searchInfoOnPastebin = new SearchInfoOnPastebin(driver);
    }

    @Test
    public void createNewPasteFields() throws InterruptedException {
        searchInfoOnPastebin.openPage();
        searchInfoOnPastebin.addCodeIntoTextarea("git config --global user.name  \"New Sheriff in Town\"\n" +
                                                            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                                                            "git push origin master --force");
        searchInfoOnPastebin.addFieldSyntaxHighlighting();
        searchInfoOnPastebin.addFieldPasteExpiration();
        searchInfoOnPastebin.addFieldPasteName("how to gain dominance among developers");
        searchInfoOnPastebin.clickToCreateNewPaste();
        searchInfoOnPastebin.CheckTitleOfThePage();
        searchInfoOnPastebin.CheckTypeOfSyntax();
        searchInfoOnPastebin.CheckTheActualCodeInTextarea();

        // проверка с TestNG
        // Assert.assertEquals(driver.getTitle(), "how to gain dominance among developers");

    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
        driver=null;
    }
}
