package com.demo.steps;

import com.demo.pages.LoginPage;
import com.demo.utils.ConfigurationReader;
import net.thucydides.core.annotations.Step;

public class LoginSteps  {
    LoginPage loginPage;

    @Step("#actor is on the login page")
    public void openLoginPage() {
        loginPage.getDriver().get(ConfigurationReader.getProperty("base.url"));
    }

    @Step("#actor logs in as {0}")
    public void logsInToThePlatform(String role){
        String userName = "";
        String password = "";
        if(role.equalsIgnoreCase("admin")||role.equalsIgnoreCase("administrator")){
            userName = ConfigurationReader.getProperty("admin.username");
            password = ConfigurationReader.getProperty("admin.password");
        }else if(role.equalsIgnoreCase("student")){
            userName = ConfigurationReader.getProperty("student.username");
            password = ConfigurationReader.getProperty("student.password");
        }
        loginPage.emailElement.type(userName);
        loginPage.passwordElement.typeAndEnter(password);
    }

    @Step("#actor logs with {0} {1}")
    public void loginWithCredentials(String username,String password){
        loginPage.emailElement.type(username);
        loginPage.passwordElement.typeAndEnter(password);
    }

    @Step("#actor ensures that Page is displayed")
    public void actorEnsuresThatPageIsDisplayed(String pageName) {

    }


}
