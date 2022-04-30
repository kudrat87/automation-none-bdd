package com.gem.page;

import com.gem.utilities.ConfigFileReader;
import com.gem.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class CreateNewAccountPage {
    public CreateNewAccountPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();




    @FindBy(xpath = "//a[@data-testid='goToRegister']")
    public WebElement createNewAccountLink;

    @FindBy(xpath = "//a[@data-testid='register-go-to-institution-register']")
    public WebElement createABusinessAccountLink;

    @FindBy (name = "company.legalName")
    public WebElement legalBusinessNameInput;

    @FindBy(id = "companyTypeDropdown")
    public WebElement companyTypeDropdown;

    @FindBy(xpath ="//*[text()='Broker-Dealer']")
    public WebElement companyTypeName;

    @FindBy(id = "stateDropdown")
    public WebElement stateDropdown;

    @FindBy(xpath = "//div[@class='css-m09i48 e1vsinnk0' and text()='NY']")
    public WebElement selectState;

    @FindBy(name = "personal.legalName.firstName")
    public WebElement personalLegalFirstName;

    @FindBy(name = "personal.legalName.lastName")
    public WebElement personalLegalLastName;

    @FindBy(name = "personal.email")
    public WebElement personalEmail;

    @FindBy(xpath = "//div/label/input[@type='checkbox']")
    public WebElement agreementCheckBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class='NarrowTitle']")
    public WebElement thankYouPageText;

    @FindBy(xpath = "//div[@class='FormHeader']/h3")
    public WebElement headerTextMessage;

    @FindBy(xpath = "//div[@class='AlertBody']//li")
    public List<WebElement> errorMessage;

    @FindBy(xpath = "//a[text()='OK']")
    public WebElement cookies;
    public void handleCookies(){
        if (cookies.isDisplayed()){
            cookies.click();
        }
    }


    public List<WebElement> getErrorMessages(){
        return errorMessage;
    }


    public String getHeaderTextMessage(){
        return headerTextMessage.getText();
    }

    public String getRegisterSuccessMessageText(){
        return thankYouPageText.getText();
    }

    public void clickOnContinueButton(){
        js.executeScript("arguments[0].click();",continueButton);

    }

    public void clickOnCheckBox(){
        js.executeScript("arguments[0].click();",agreementCheckBox);
    }

    public void enterEmail(String email){
        personalEmail.sendKeys(Keys.CLEAR+ email);
    }

    public void enterLastname(String lastName){
        personalLegalLastName.sendKeys(lastName);
    }


    public void enterFirstName(String firstName){
        personalLegalFirstName.sendKeys(firstName);
    }

    public void clickOnStateDropdown(){
        stateDropdown.sendKeys("NY");
    }

    public void selectState(){
        js.executeScript("arguments[0].click();",selectState);

    }


    public void selectCompanyType(){
        companyTypeName.click();
    }

    public void clickOnCompanyTypeDropdown(){
        companyTypeDropdown.click();
    }

    public void enterLegalBusinessName(String businessName){

        legalBusinessNameInput.sendKeys(businessName);
    }

    public void clickOnCreateNewAccountLink(){
        createNewAccountLink.click();
    }

    public void clickOnCreateABusinessAccountLink(){
        js.executeScript("arguments[0].click();",createABusinessAccountLink);
        //createABusinessAccountLink.click();
    }
    public void openBrowser(){
        String url = ConfigFileReader.getProperty("url");
        Driver.getDriver().get(url);
    }

    public String getRegistrationPageUrl(){

       return Driver.getDriver().getCurrentUrl();

    }




}
