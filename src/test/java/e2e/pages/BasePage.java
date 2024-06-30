package e2e.pages;

import e2e.enums.NavigationMenu;
import e2e.wait.Wait;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Wait getWait() {
        return new Wait(driver);
    }
    public void clickOnNavi(NavigationMenu navigationMenu){
        WebElement lidlLogo = driver.findElement(By.xpath(navigationMenu.getLocator()));
        lidlLogo.click();
    }


    private File takeScreenshot(WebElement element) {
        File tmp;
        if (element == null) {
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            System.out.println("Take screenshot page");
        } else {
            tmp = element.getScreenshotAs(OutputType.FILE);
            System.out.println("Take screenshot element");
        }

        return tmp;
    }

    private double calculateMaxDifferentPercentRation() {
        Dimension windowSize = driver.manage().window().getSize();
        int width = windowSize.width;
        int height = windowSize.height;

        return 0.01 * width * height;
    }

    private Process setCompareCommandToTerminal(String refImgFilePath, String tmpFilePath) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("compare", "-metric", "AE", refImgFilePath, tmpFilePath, "null:");
        System.out.println("Set compare command to terminal");
        return pb.start();
    }

    private double getDifferenceFromLogs(BufferedReader reader) throws IOException {
        String line;
        double difference = 0;
        while ((line = reader.readLine()) != null) {
            difference = Integer.parseInt(line.trim());
        }
        return difference;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    @Step("Take and compare screenshot{actualScreenshotName}")
    protected void takeAndCompareScreenshot(String actualScreenshotName, WebElement element) {
        String referenceImageFilePath = "reference/" + actualScreenshotName + ".png";
        String tmpFilePath = "reference/tmp_" + actualScreenshotName + ".png";
        File tmp = takeScreenshot(element);

        try {
            saveScreenshot(Files.readAllBytes(tmp.toPath()));
            Files.copy(tmp.toPath(), new File(tmpFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            File referenceImageFile = new File(referenceImageFilePath);
            if (!referenceImageFile.exists()) {
                throw new RuntimeException("Reference image file does not exist, but there is tmp file, need remove tmp_ from name file" + tmpFilePath);
            }
            double maxDiffPercent = calculateMaxDifferentPercentRation();
            Process process = setCompareCommandToTerminal(referenceImageFilePath, tmpFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            double difference = getDifferenceFromLogs(reader);
            reader.close();
            process.destroy();

            if (difference > maxDiffPercent) {
                throw new RuntimeException(referenceImageFilePath + " not equal " + tmpFilePath + " difference: " + difference);
            }

            Files.deleteIfExists(new File(tmpFilePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
