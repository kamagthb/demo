package com.demo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

//Every page class must extend PageObject class---which comes from Serenity
public class LoginPage extends PageObject {

    @FindBy(css = "[formcontrolname='username']")
    public WebElementFacade emailElement;

    @FindBy(xpath = "//*[@name='password']")
    public WebElementFacade passwordElement;

    public void clickTheSaveButton() {
        waitABit(500);
        $("//*[.='Save']").waitUntilClickable().waitUntilEnabled().waitUntilPresent().click();
    }

}
