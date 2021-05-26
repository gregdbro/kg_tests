package PageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class kg_Page_Factory {
	//define constants
    WebDriver driver;
    
    //define webelements and how to locate them
    //Reusable Objects
    @FindBy(xpath = "//*[@id=\"plp-title\"]/div/h1")
    private WebElement screenTitle;
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/div/div/div[1]/div/span")
    private WebElement filterOne;
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/div/div/div[1]/div/button[1]")
    private WebElement filterTwo;
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/div/div/div[1]/div/button[2]/span[1]")
    private WebElement filterThree;
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/div/div/div[1]/div/button[3]/span[1]")
    private WebElement filterFour;
    
    //Test 1 Objects
    @FindBy(xpath = "//*[@id=\"top-nav\"]/ul/li[7]/div[1]/a/span")
    private WebElement navMenuCarvela;
    
    @FindBy(xpath = "//*[@id=\"top-nav\"]/ul/li[7]/div[2]/div/div/ul/li[6]/a")
    private WebElement navMenuCarvelaBags;

    //Test 2 Objects
    @FindBy(xpath = "//*[@id=\"top-nav\"]/ul/li[4]/div[1]/a/span")
    private WebElement navMenuMen;
    
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[3]/div[1]/div/div/div[2]/div[1]/div/ol/li[3]/button/span")
    private WebElement filtersCategoriesBoots;
    
    //Test 3 Objects
    @FindBy(xpath = "//*[@id=\"social_proof_plp_8117140979\"]/h2/a")
    private WebElement selectProductVeganLoadedKnitMens;
    
    @FindBy(xpath = "//*[@id=\"product-layout\"]/div[3]/h1")
    private WebElement productTitle;

    @FindBy(xpath = "//*[@id=\"ss17-wrapLinksSearch\"]/ul/li[3]/ul/div[2]/li/a")
    private WebElement basket;
    
    @FindBy(id = "//*[@id=\"ss17-wrapLinksSearch\"]/ul/li[3]/ul/div[1]/li/div")
    private WebElement basketMessage;
    
    @FindBy(xpath = "//*[@id=\"add-to-cart-ss17\"]")
    private WebElement clickAddToBag;
    
    @FindBy(xpath = "//*[@id=\"ss17-wrapLinksSearch\"]/ul/li[3]/ul/div[2]/li/div/div/div[3]/button")
    private WebElement clickProceedToCheckout;
    
    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[1]/h1/span")
    private WebElement checkoutScreenTitle;
    
    //Constructor
    public kg_Page_Factory(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
        }
    
    //Reusable Object Actions
    public void implicitlyWaitSecond(int sec) {
    	//implicit wait is superior to thread.sleep(10000) 
    	//as imp wait waits only until the element is available whereas thread.sleep waits for the defined time
    	this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	}
    
    public String validateScreenTitle() {
    	implicitlyWaitSecond(10);
    	//acquire the text of the element (trimmed to remove spaces if there are any) and return the string
		String title = this.screenTitle.getText().trim();
		return title;
		}
    
    public String validateFilterOne() {
    	implicitlyWaitSecond(10);
		String title = this.filterOne.getText().trim();
		return title;
		}
    
    public String validateFilterTwo() {
    	implicitlyWaitSecond(10);
		String title = this.filterTwo.getText().trim();
		//if the title contains an X then split. this is not optimal as a filter may have an X in the middle of the name
		//for example when the size of a product is XXL
		if(title.contains("X")) {
			title = title.split("X")[0];
			}
		return title;
		}
    
    public String validateFilterThree() {
    	implicitlyWaitSecond(10);
		String title = this.filterThree.getText().trim();
		if(title.contains("X")) {
			title = title.split("X")[0];
			}
		return title;
		}
    
    public String validateFilterFour() {
    	implicitlyWaitSecond(10);
		String title = this.filterFour.getText().trim();
		return title;
		}
    
    //Test One Object Actions
    public void clickNavigationMenuCarvelaBags() throws InterruptedException {
    	implicitlyWaitSecond(10);
    	
    	//requirement to "hover" over the menuCarvela without clicking
    	Actions actions = new Actions(driver);
		
		WebElement carvela = navMenuCarvela;
		implicitlyWaitSecond(10);
		//the line to hover
	    actions.moveToElement(carvela).perform();
	    implicitlyWaitSecond(10);
	    
	    WebElement carvelaBags = navMenuCarvelaBags;
	    implicitlyWaitSecond(10);
	    //the same happens over the menuCarvelaBags option
	    actions.moveToElement(carvelaBags).perform();
	    implicitlyWaitSecond(10);
	    //select the Bags option
	    carvelaBags.click();
	    implicitlyWaitSecond(10);
	    }

    //Test Two Object Actions
    public void clickNavigationMenuMen() {
    	implicitlyWaitSecond(10);
    	//basic click option
    	this.navMenuMen.click();
    	}
    
    public void clickFiltersCategoriesBoots() {
    	implicitlyWaitSecond(10);
    	this.filtersCategoriesBoots.click();
    	}
    
    public void scroller(String pixelValue) {
    	implicitlyWaitSecond(10);
    	//requirement to scroll up and down so elements are visible
    	//not optimal as user has to define the amount of pixels in the dataParameters section of kg_Test_With_Page_Factory
    	//optimal would be scroll up / down until the element required is visible
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0," + pixelValue + ")");
    	}
    
    //Test Three Object Actions
    public void clickFiltersSizeUK6EU40(String size) throws InterruptedException {
    	implicitlyWaitSecond(10);
    	//desire to not use xpaths to locate the element
    	//therefore find the element using a user input (dataParameters) choice. This allows the user to swap 6 / 40 for 7 / 41 for example
    	WebElement myelement = driver.findElement(By.xpath("//*[text()='" + size + "']"));
    	JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    	jse2.executeScript("arguments[0].scrollIntoView()", myelement); 
    	myelement.click();
    	}
    
    public void clickFiltersColourBeige(String colour) {
    	implicitlyWaitSecond(10);
    	WebElement test = driver.findElement(By.xpath("//*[text()='" + colour + "']"));
    	test.click();
    	}
    
    public void selectProductVeganLoadedKnitMens() {
    	implicitlyWaitSecond(10);
    	this.selectProductVeganLoadedKnitMens.click();
    	}
    
    public String validateProductDescription() {
    	implicitlyWaitSecond(10);
		String title = this.selectProductVeganLoadedKnitMens.getText().trim();
		return title;
		}
    
    public String validateProductTitle() {
    	implicitlyWaitSecond(10);
		String title = this.productTitle.getText().trim();
		return title;
		}
    
    public String validateBasketMessage() throws InterruptedException {
    	implicitlyWaitSecond(10);
		WebDriverWait wait = new WebDriverWait(driver,5);
		Actions actions = new Actions(driver);
		
		WebElement elem = basket;
		
		//waiting for the element to be available before clicking
        wait.until(ExpectedConditions.elementToBeClickable(basket));
        actions.moveToElement(elem).perform();
        
        String title = this.basketMessage.getText().trim();
        System.out.println(title);
		return title;
	    }
    
    public void clickBasket() throws InterruptedException {
    	implicitlyWaitSecond(10);
    	
    	Actions actions = new Actions(driver);
		
    	//very similar to carvelaBags option
		WebElement itemsInBasket = basket;
		implicitlyWaitSecond(10);
	    actions.moveToElement(itemsInBasket).perform();
	    implicitlyWaitSecond(10);
	    
	    WebElement checkoutButton = clickProceedToCheckout;
	    implicitlyWaitSecond(10);
	    actions.moveToElement(checkoutButton).perform();
	    implicitlyWaitSecond(10);
	    checkoutButton.click();
	    implicitlyWaitSecond(10);
        
	    }
    
    public void clickAddToBag() {
    	implicitlyWaitSecond(10);
    	this.clickAddToBag.click();
    	}
    
    public String validateCheckoutScreenTitle() {
    	implicitlyWaitSecond(10);
		String title = this.checkoutScreenTitle.getText().trim();
		return title;
		}
}