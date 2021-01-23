package pagesWithPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchInfoOnPastebin extends AbstractPage{
    public SearchInfoOnPastebin(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://pastebin.com");
    }

    public void addCodeIntoTextarea(String codeInTextareaField){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postform-text")))
                .sendKeys(codeInTextareaField); // поиск textarea
    }

    public void addFieldSyntaxHighlighting(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("select2-postform-format-container")))
                .click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='select2-results']/descendant::li[text()='Bash']")))
                .click();
    }

    public void addFieldPasteExpiration() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("select2-postform-expiration-container")))
                .click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='select2-results']/descendant::li[text()='10 Minutes']")))
                .click();
    }

    public void addFieldPasteName(String textInTitleField) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("postform-name")))
                .sendKeys(textInTitleField);
    }

    public void clickToCreateNewPaste() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit' and contains(text(),'Create New Paste')]")))
                .click();
    }

    public void CheckTitleOfThePage() {
        String expectedTitle = "how to gain dominance among developers";
        String currentTitle = driver.findElement(By.xpath("//div[@class='info-top']/h1")).getText();
        Assert.assertEquals(currentTitle,expectedTitle);
    }

    public void CheckTypeOfSyntax() {
        String expectedSyntax = "Bash";
        String currentSyntax = driver.findElement(By.xpath("//div[@class='top-buttons']/div[@class='left']/a")).getText();
        Assert.assertEquals(currentSyntax,expectedSyntax);
    }
    public void CheckTheActualCodeInTextarea() {
        String expectedCode = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        String currentCode = driver.findElement(By.xpath("//div[@class='source']")).getText();
        Assert.assertEquals(expectedCode,currentCode);
    }

}
