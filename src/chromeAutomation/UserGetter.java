/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromeAutomation;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Scanner;

//Selenium imports
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//Email imports
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.DefaultAuthenticator;

//Imports for GUI
import GUI.FXMLDocumentController;


public class UserGetter {
    private String username, password, contactInfo, phoneNumber, phoneCarrier;
    private Scanner s = new Scanner(System.in);
    FXMLDocumentController f = new FXMLDocumentController();
    
    public UserGetter(String username, String password, String phonenumber, String phoneCarrier) throws EmailException{
            this.username = username;
            this.password = password;
            this.phoneNumber = phonenumber;
            this.phoneCarrier = phoneCarrier;
        phone();
        getInfo();
    }
    //Web automation
    public void getInfo() throws EmailException{
    //Print statement at start of automation
    System.out.println("==========STARTING AUTOMATION==========");
    //Chrome local host driver
    System.setProperty("webdriver.chrome.driver","C:\\SeleniumDrivers\\chromedriver.exe");
    //Creates a new webdirver and opens chrome
    WebDriver driver = new ChromeDriver();
    //Driver gets the link 
    driver.get("https://account.uvm.edu/guestnet-admin");
    //Driver finds element anf inputs username and password
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("submit")).click();
    //Waiting untill the page loads
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.id("add-guest")));
    //Page 2
    driver.findElement(By.id("add-guest")).click();
    //Adds name to Name/Description
    driver.findElement(By.name("comment")).sendKeys(username);
    //Adds second part of username
    driver.findElement(By.name("username")).sendKeys("BensWIFIbot");
    //Adds the users email address
    driver.findElement(By.name("email_address")).sendKeys("fake_info@fakeNet.com");
    driver.findElement(By.id("network-network")).click();
    //Submits the info
    WebElement element = driver.findElement(By.xpath("//div/button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span[@class='ui-button-text']"));
    Actions action = new Actions(driver);
    action.moveToElement(element).click().perform();
    //Third Page
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr/td[@class=' sorting_1']")));
    driver.findElement(By.xpath("//tbody/tr/td[@class=' sorting_1']")).click();
    //Fourth Page
    //Getting the password to be sent to email/ text
    String newPassword = driver.findElement(By.cssSelector(".section>p>code")).getText();
    //Closing automation
    driver.close();
    //End of automation
    System.out.println("==========AUTOMATION ENDED==========");
    //email(newPassword);
    email(newPassword);
    //Call method for timer loop()
    }  
    //Sends the user an email with the WIFI username and password
    public void email(String newPassword) throws EmailException{
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("uvmguestnetwork@gmail.com", "this@password"));
        email.setSSLOnConnect(true);
        email.setFrom("lineskis20@gmail.com");
        email.setSubject("UVM Guest Network Password");
        email.setMsg("Hello, "+username+" here is this weeks password: \n"+newPassword);
        email.addTo(contactInfo);
        email.send();
        
    }
    //Sends the user a text with the WIFI username and password
    public void phone(){
        if(phoneCarrier == "T Mobile"){
          contactInfo = phoneNumber + "@tmomail.net";  
        }
        else if(phoneCarrier == "Virgin Mobile"){
           contactInfo = phoneNumber + "@vmobl.com"; 
        }
        else if(phoneCarrier == "Vingularme"){
           contactInfo = phoneNumber + "@cingularme.com"; 
        }
        else if(phoneCarrier == "Sprint"){
            contactInfo = phoneNumber + "@messaging.sprintpcs.com";
        }
        else if(phoneCarrier == "Verizon"){
            contactInfo = phoneNumber + "@vtext.com";
        }
        else{
            contactInfo = phoneNumber + "@messaging.nextel.com";
        }
  
    }
    public void timer() throws InterruptedException{
        System.out.println("========Timer Started On:"+ new Date()+"========");
        Thread.sleep(604800000);
        System.out.println("========Timer Compleated On:"+ new Date()+"========");

        
    }
}
