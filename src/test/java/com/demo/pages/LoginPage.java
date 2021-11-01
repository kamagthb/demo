package com.demo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

public class LoginPage extends PageObject {

    @FindBy(css = "[formcontrolname='username']")
    public WebElementFacade emailElement;

    @FindBy(xpath = "//*[@name='password']")
    public WebElementFacade passwordElement;



}
