/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chromeAutomation;

import java.util.Date;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Ben
 */
public class DemoGetter {
    //Web automation
    public void demoRun() throws EmailException, InterruptedException{
    //Print statement at start of automation
    System.out.println("==========STARTING AUTOMATION==========");
    //Chrome local host driver
    System.setProperty("webdriver.chrome.driver","C:\\SeleniumDrivers\\chromedriver.exe");
    //Creates a new webdirver and opens chrome
    WebDriver driver = new ChromeDriver();
    //Driver gets the link 
    driver.get("https://account.uvm.edu/guestnet-admin");
    //Driver finds element anf inputs username and password
    driver.findElement(By.name("username")).sendKeys("bhazzard");
    driver.findElement(By.name("password")).sendKeys("121Benman");
    driver.findElement(By.name("submit")).click();
    //Waiting untill the page loads
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(By.id("add-guest")));
    //Page 2
    driver.findElement(By.id("add-guest")).click();
    //Adds name to Name/Description
    driver.findElement(By.name("comment")).sendKeys("bhazzard");
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
    //Remove User
    driver.findElement(By.xpath("//div/button[@class='left ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")).click();
    //Closing automation
    driver.close();
    //End of automation
    System.out.println("==========AUTOMATION ENDED==========");
    //email(newPassword);
    email(newPassword);
    //Call method for timer loop()
    timer();

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
        email.setMsg("Hello, bhazzard here is this weeks password: \n"+newPassword);
        email.addTo("2038028314@vtext.com");
        email.send();
        
    }
    
    public void timer() throws EmailException, InterruptedException{
        System.out.println("==========TIMER STARTED ON:"+new Date()+"==========");
        Thread.sleep(5000);
        System.out.println("==========TIMER STARTED ON:"+new Date()+"==========");
        demoRun();
            
        
        
    }
    
}
