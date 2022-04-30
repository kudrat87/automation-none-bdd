package com.gem.tests;

import com.gem.page.CreateNewAccountPage;
import com.gem.utilities.ConfigFileReader;
import com.gem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class CreateNewBusinessAccount {
    CreateNewAccountPage createNewAccount = new CreateNewAccountPage();


    @AfterTest
    public void tearDown()  {
        Driver.quitDriver();
    }


    @Test (priority = 1)
    public void createNewBusinessAccountPositive(){

        createNewAccount.openBrowser();
        createNewAccount.clickOnCreateNewAccountLink();
        createNewAccount.handleCookies();
        createNewAccount.clickOnCreateABusinessAccountLink();

        // Verify user is on business registration page
        String expectedUrl = ConfigFileReader.getProperty("expectedUrl"); // Getting expected Url from configuration file
        String actualUrl = createNewAccount.getRegistrationPageUrl(); // Getting url from registration page
        Assert.assertEquals(actualUrl,expectedUrl,"Url does not match");

        // Verify Registration page Header Text Message
        String expectedHeaderText = ConfigFileReader.getProperty("registerPageHeaderText");
        Assert.assertEquals(createNewAccount.getHeaderTextMessage(),expectedHeaderText,"Header text does not match");

        createNewAccount.enterLegalBusinessName(ConfigFileReader.getProperty("businessName")); // Getting data from configuration file
        createNewAccount.clickOnCompanyTypeDropdown();
        createNewAccount.selectCompanyType();
        createNewAccount.clickOnStateDropdown();
        createNewAccount.selectState();
        createNewAccount.enterFirstName(ConfigFileReader.getProperty("firstname")); // Getting data from configuration file
        createNewAccount.enterLastname(ConfigFileReader.getProperty("lastname")); // Getting data from configuration file
        createNewAccount.enterEmail(ConfigFileReader.getProperty("email")); // Getting data from configuration file
        createNewAccount.clickOnCheckBox();
        createNewAccount.clickOnContinueButton();

        // Verify registration page success message
        String expectedSuccessMessage = ConfigFileReader.getProperty("successMessage");
        String actualSuccessMessage = createNewAccount.getRegisterSuccessMessageText();
        Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage,"Failed! Success message does not match");


    }

    @Test (priority = 2)
    public void createNewBusinessAccountNegative(){

        /** Lots of negative tests can be done, but I'll do negative test for 2-3 required fields**/
        createNewAccount.openBrowser();
        createNewAccount.clickOnCreateNewAccountLink();
        createNewAccount.clickOnCreateABusinessAccountLink();

        // Click on Continue button without filling out required fields and verify
        createNewAccount.clickOnContinueButton();

        // Loop through each error message and verify and print each error message
        for(WebElement eachElement : createNewAccount.getErrorMessages()){
            Assert.assertTrue(eachElement.isDisplayed());
            System.out.println(eachElement.getText());
        }

        createNewAccount.enterLegalBusinessName(ConfigFileReader.getProperty("businessName")); // Getting data from configuration file
        createNewAccount.clickOnCompanyTypeDropdown();
        createNewAccount.selectCompanyType();
        createNewAccount.clickOnStateDropdown();
        createNewAccount.selectState();

        // Click on Continue button without filling First and Last Name, and email and verify
        createNewAccount.clickOnContinueButton();
        for(WebElement eachElement : createNewAccount.getErrorMessages()){
            Assert.assertTrue(eachElement.isDisplayed());

        }

        createNewAccount.enterFirstName(ConfigFileReader.getProperty("firstname")); // Getting data from configuration file
        createNewAccount.enterLastname(ConfigFileReader.getProperty("lastname")); // Getting data from configuration file
        createNewAccount.enterEmail(ConfigFileReader.getProperty("email")); // Getting data from configuration file

        // Do not check user agreement checkbox and verify error message
        createNewAccount.clickOnContinueButton();
        String expectedErrorMessage = ConfigFileReader.getProperty("agreementErrorMessage");
        String actualMessage = createNewAccount.getErrorMessages().get(0).getText();
        Assert.assertEquals(actualMessage,expectedErrorMessage, "User agreement error message does not match");

    }

}
