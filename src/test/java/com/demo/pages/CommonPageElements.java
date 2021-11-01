package com.demo.pages;

import com.demo.utils.BrowserUtils;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class CommonPageElements extends PageObject {

    private static final Logger logger = Logger.getLogger(CommonPageElements.class);

    @FindBy(xpath = "//*[text()='Save']/..")
    public WebElementFacade saveButtonElement;

    @FindBy(css = "button.button.button-modal-action.mb-none")
    public WebElementFacade deleteButton;

    public void waitForSeconds(int seconds) {
        waitABit(seconds * 1000);
    }

    public ListOfWebElementFacades findElementsByText(String text) {
        waitABit(2000);
        return $$("//*[normalize-space()='" + text + "' and text()]");
    }

    public WebElementFacade getTheActionUnderMoreButton(String actionName) {
        return $("//button[.=' " + actionName + " ']");
    }

    public WebElementFacade getFollowingPopup(String popup) {
        waitABit(500);
        return $("(//*[.='" + popup + "'])[5] | (//*[normalize-space()='" + popup + "'])[5] | (//*[.='" + popup + "'])[4]").waitUntilPresent();
    }

    public WebElementFacade getTheButtonsBasedOnText(String buttonText) {
        return $("//button[.=' " + buttonText + " '] | //button[normalize-space()='" + buttonText + "']");
    }

    public WebElementFacade getTheSpecificSearchBox(String elementType) { //case sensitive
        return $("[placeholder='Search " + elementType + "']");
    }

    public void clickElementByText(String text) {
        new BrowserUtils().clickWithJS($("//*[contains(text(),'" + text + "')]"));
    }


}
