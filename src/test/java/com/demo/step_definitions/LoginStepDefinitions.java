package com.demo.step_definitions;

import com.demo.steps.LoginSteps;
import io.cucumber.java.en.*;

public class LoginStepDefinitions {
    LoginSteps loginSteps;

    @When("user logs in with {word} and {word} credentials")
    public void user_logs_in_with_and_credentials(String username, String password) {
        loginSteps.loginWithCredentials(username,password);
    }

    @Given("{string} logs in to the platform")
    public void logs_in_to_the_platform(String role) {
        loginSteps.openLoginPage();
        loginSteps.logsInToThePlatform(role);
    }

    @Then("{string} page should be displayed")
    public void page_should_be_displayed(String page) {
        loginSteps.actorEnsuresThatPageIsDisplayed(page);
    }
    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        loginSteps.openLoginPage();
    }

    @Then("user logs in with {string} and {string}")
    public void userLogsInWithAnd(String email, String password) {
        loginSteps.loginWithCredentials(email,password);
    }

    @When("{string} logs in")
    public void logs_in(String role) {
        loginSteps.logsInToThePlatform(role);
    }
}
