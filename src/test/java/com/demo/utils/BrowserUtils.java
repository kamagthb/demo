package com.demo.utils;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserUtils extends PageObject {
    private WebDriver driver;

    public BrowserUtils() {
        this.driver = getDriver();
    }

    public Map<String, String> getCookies() {
        Set<Cookie> cookiesSet = driver.manage().getCookies();
        Map<String, String> cookiesMap = new HashMap<>();
        cookiesSet.forEach(p -> cookiesMap.put(p.getName(), p.getValue()));
        return cookiesMap;
    }

    public void switchToNewWindow(String oldWindowHandle) {
        Set<String> windows = driver.getWindowHandles();
        for (String w : windows) {
            if (!w.equals(oldWindowHandle)) {
                driver.switchTo().window(w);
                break;
            }
        }
    }

    public void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(e)).click();
    }

    public void scrollDown(int y) {
        getJavascriptExecutorFacade().executeScript("window.scrollBy(0, " + y + ");");
    }

    public void scrollToElement(WebElement element) {
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToElement(WebElementFacade element) {
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDownTheWebPageAtTheBottomOfThePage() {
        getJavascriptExecutorFacade().executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void closeAllTabsExceptCurrent() {
        Set<String> windows = driver.getWindowHandles();
        if (windows.size() > 1) {
            String targetWindow = driver.getWindowHandle();
            for (String w : windows) {
                driver.switchTo().window(w);
                if (!driver.getWindowHandle().equals(targetWindow)) {
                    driver.close();
                }
            }
            driver.switchTo().window(targetWindow);
        }
    }

    public void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).build().perform();
    }

    public void hoverOver(WebElement element) {
        new Actions(driver).moveToElement(element).build().perform();
    }

    public void hoverOverAndClick(WebElement element) {
        new Actions(driver).moveToElement(element).pause(2000).click().build().perform();
    }

    public void doubleClick(WebElementFacade element) {
        new Actions(driver).doubleClick(element).build().perform();
    }

    public void hoverOver(WebElementFacade element) {
        new Actions(driver).moveToElement(element).build().perform();
    }

    public void hoverOverAndClick(WebElementFacade element) {
        new Actions(driver).moveToElement(element).pause(2000).click().build().perform();
    }

    public void clickWithJS(WebElement element) {
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView(true);", element);
        getJavascriptExecutorFacade().executeScript("arguments[0].click();", element);
    }

    public void clickWithJS(WebElementFacade element) {
        getJavascriptExecutorFacade().executeScript("arguments[0].scrollIntoView(true);", element);
        getJavascriptExecutorFacade().executeScript("arguments[0].click();", element);
    }

    public void clickWithJSWithNoScroll(WebElementFacade element){
        getJavascriptExecutorFacade().executeScript("arguments[0].click();", element);
    }

    public Point getLocation(WebElementFacade element) {
        Point location = element.getLocation();
        return location;
    }

    public void waitExplicitly(String expectedConditions, WebElementFacade webElementFacade) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);
        if (expectedConditions.equalsIgnoreCase("clickable")) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElementFacade));
        } else if (expectedConditions.equalsIgnoreCase("visibility")) {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElementFacade));
        } else if (expectedConditions.equalsIgnoreCase("selected")) {
            webDriverWait.until(ExpectedConditions.elementToBeSelected(webElementFacade));
        }
    }

    public void waitImplicitly(int timeOut) {
        getDriver().manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * sets time format as (dd/MM/yyyy, hh:mm aa)
     * works according to AM/PM structure
     * returns future time as String
     *
     * @param minute
     */
    public String setFutureTime(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy, hh:mm aa");
        return simpleDateFormat.format(calendar.getTime());
    }

}
