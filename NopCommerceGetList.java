package swdDemoRev;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NopCommerceGetList 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Search element in search bar: ");
		String item= sc.nextLine();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		//Enter email id
		WebElement email= driver.findElement(By.id("Email"));
		email.clear();
		email.sendKeys("admin@yourstore.com");
		
		//enter password
		WebElement pwd= driver.findElement(By.id("Password"));
		pwd.clear();
		pwd.sendKeys("admin");
		
		//click on login
		WebElement btn =driver.findElement(By.xpath("//button[text()='Log in']"));
		Actions a = new Actions(driver);
		a.moveToElement(btn).click().perform();
		
		//search element
		WebElement search= driver.findElement(By.xpath("//input[@class='form-control admin-search-box typeahead tt-input']//parent::span"));
		a.moveToElement(search).click().sendKeys(item).perform();		
		
		//getlist and select an item
		boolean IsExist= false;
		List<WebElement> items= driver.findElements(By.xpath("//div[@id='user-selection']"));
		for(WebElement it: items)
		{
			String list=it.getText();
					
			//select an item
			if(list.contains(item))
			{
				IsExist= true;
					a.keyDown(Keys.CONTROL).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).perform();
					Thread.sleep(2000);
					a.keyDown(Keys.ENTER).perform();
					break;
				}

			if(IsExist==false)
			{
				System.out.println("No such item exist");
			}
		}		
	}
}
