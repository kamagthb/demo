#This project used for end-to-end testing.


`page classes`
- Every page class must extend PageObject class. It's coming from Serenity

`steps`

Serenity Step Libraries
In Serenity, tests are broken down into reusable steps. An important principle behind Serenity is the idea that it is easier to maintain a test that uses several layers of abstraction to hide the complexity behind different parts of a test.

In an automated acceptance test, test steps represent the level of abstraction between the code that interacts with your application (for example, Page Objects in an automated web test, which are designed in terms of actions that you perform on a given page) and higher-level stories (sequences of more business-focused actions that illustrate how a given user story has been implemented). If your automated test is not UI-oriented (for example, if it calls a web service), steps orchestrate other more technical components such as REST clients. Steps can contain other steps, and are included in the Serenity reports. Whenever a step is executed, a screenshot is stored and displayed in the report.

Breaking down tests into steps
Suppose we are testing a Frequent Flyer programme, and need to illustrate the following business rules:

- Members should start with Bronze status

- Members should earn Silver status after flying 10,000 km.

Each of these can be broken down further into business tasks and verifications. For example, the first rule could be broken into two steps:

1. Create a new Frequent Flyer member

2. Check that the member has a status of Bronze

Second can be broken into three steps:

1. Create a new Frequent Flyer member

2. Make the member fly 10000 km

3. Check that the member has a status of Silver

`featues`

Features grouped in folders based on module names. Once created a feature, associate it with a Jira issue by using tags: @PT2323.Feature file are usually created per feature of the application.
Try to make steps reusable as much as possible:

    And user navigates to Programs

In this phrase, `Programs` is a parameter that Cucumber shows as a `{word}` in step definition.

```java
   public class CommonStepDefinitions {
   
       @Steps
       NavigationSteps navigationSteps;
   
       @When("user navigates to {word}")
       public void user_navigates_to(String moduleName) {
           navigationSteps.actorNavigatesTo(moduleName);
       }
   }
```

`step defintions`

Here we store code implementations. Please don't use page objects here, instead use @Steps:
```java
public class LoginStepDefinitions {
 
     @Steps
     LoginSteps loginSteps;
 
     @Given("user is on the landing page")
     public void user_is_on_the_landing_page() {
         loginSteps.openLoginPage();
     }
 
     @When("user logs in as {string}")
     public void user_logs_in_as(String role) {
         loginSteps.loginAsUser(role);
     }
 }
```
`page classes`

Please use *WebElementFacade* instead of *WebElement* and *ListOfWebElementFacades* instead of List\<WebElement\>. 
These are the in-built Serenity wrapper classes that can help to make our life easier.
 
`WebElementFacade`
```java
public class CommonPageElements extends PageObject {

    @FindBy(xpath = "//*[text()='Save']/..")
    public WebElementFacade saveButtonElement;

    @FindBy(xpath = "//button[.=' Delete ']")
    private WebElementFacade deleteButton;

    public void clickSave() {
        waitFor(saveButtonElement).click();
    }

    public ListOfWebElementFacades findElementsByText(String text) {
        waitABit(2000);
        return findAll("//*[contains(text(),'" + text + "')]");
    }

    public void clickMoreOptionsButtonOf(String title){
        $("//span[.=' "+title+" ']/../../..//div[@class='dropdown_item display-inline-block position-relative']").click();
    }

    public void clickDeleteButtonOf(String title){
        clickMoreOptionsButtonOf(title);
        $("//span[.=' "+title+" ']/../../..//div[@class='dropdown_item display-inline-block position-relative open']//span[.='Delete']/..").click();
        deleteButton.click();
    }

    public WebElementFacade getFollowingPopup(String popup){
        waitABit(500);
        return $("(//*[.='"+popup+"'])[5]");
    }
}
```
`running tests`

To be able to run the tests and get a report run test with `verify` goal.

Please make sure to specify thread.count and test name:

```shell
To run api smoke test for Accounts service
mvn clean verify -Dthread.count=1 -Dtest=AccountsServiceAPISmokeTest -Dbase.url=http://52.13.64.199

To run api smoke test for Assessments service
mvn clean verify -Dthread.count=1 -Dtest=AssessmentsServiceAPISmokeTest -Dbase.url=http://52.13.64.199

To run api smoke test for Assessments service on feature environment
mvn clean verify -Dthread.count=1 -Dtest=AssessmentsServiceAPISmokeTest -Denvironment=feature -Dbase.url=http://${{ github.event.repository.name }}-${{ github.event.number }}.shvp-xftw-ctdtm.com
```
NOTE:  `base.url = "http://${{ github.event.repository.name }}-${{ github.event.number }}.shvp-xftw-ctdtm.com"`  - will be replaced with an actual URL of the feature environment.

```shell
To run api smoke test for Assessments service on performance environment
mvn clean verify -Dthread.count=1 -Dtest=AssessmentsServiceAPISmokeTest -Denvironment=performance

To run api smoke test for Assessments service on test environment
mvn clean verify -Dthread.count=1 -Dtest=AssessmentsServiceAPISmokeTest -Denvironment=test

To run api smoke test for Assessments service on test environment
mvn clean verify -Dthread.count=1 -Dtest=AssessmentsServiceAPISmokeTest -Denvironment=staging

To run Cucumber tests on default
mvn clean verify -Dthread.count=1 -Dtest=TestRunner
```

 `base.url` parameter is optional.

In the file serenity.conf base.url = "http://${{ github.event.repository.name }}-${{ github.event.number }}.shvp-xftw-ctdtm.com" - placeholder that must be replaced with a -Dbase.url environment variable

![img.png](.github/img.png)

###Feature environment
To run tests on feature environment, specify environment and base url:

```shell
mvn clean verify -Dthread.count=1 -Dtest=TestRunner -Dbase.url=http://frondend-15.shvp-xftw-ctdtm.com -Denvironment=feature
```
