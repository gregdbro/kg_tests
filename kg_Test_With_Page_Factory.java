package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageFactory.kg_Page_Factory;

public class kg_Test_With_Page_Factory {
	
	/*
	TestOne		searchForCarvelaBags	~30 seconds
	TestTwo		searchForMensBoots		~25 seconds
	TestThree	checkoutOneProduct		~40 seconds
	Total								~60 seconds
	*/

	//Constants
    String driverPath = "C:\\Users\\Greg\\Interview_Test_For_KG\\ChromeDriver\\chromedriver.exe";
    WebDriver driver;
    kg_Page_Factory ref_page;

    //Initiate the chrome driver
    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //retrieve the webpage
        driver.get("https://www.kurtgeiger.com/");
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        //maximize the browser (preference)
        driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    @AfterTest
	public void closeChromeDriver() {
    	//close the chrome driver
		driver.close();
	}
    
    /*
	Test 1 Actions
	Navigate to the home page
	Select Carvela from the navigation bar
	Select Bags from the list
	Check the title of the received page = Carvela Bags
	Check the filters are Brands / Carvela / Bags
	Check the URL contains carvela and bags
	*/
    @Test(testName = "Test 1 - Search for Carvela Bags", priority = 1)
	public void searchForCarvelaBags () throws InterruptedException {
    	//link to the object page
		ref_page = new kg_Page_Factory(driver);
    	
		//basic click of carvela bags. cursor hovers over "carvela" and then clicks on "bags"
    	ref_page.clickNavigationMenuCarvelaBags();
    	
    	//output the title of the screen
    	String expected = "Carvela Bags";
    	String actual = ref_page.validateScreenTitle();
    	
    	//validate the strings are identical
    	Assert.assertEquals(actual, expected);
    	
    	System.out.println("Received the page: " + actual);
    	
    	//output the text from the filters
    	System.out.println("Received the filter: " + ref_page.validateFilterOne());
    	//output the text from the filters (remove the additional "X"
    	System.out.println("Received the filter: " + ref_page.validateFilterTwo());
    	
    	System.out.println("Received the filter: " + ref_page.validateFilterThree());
    	
    	//assign the current url to a string
    	String url = driver.getCurrentUrl();
    	
    	//test to validate the url is correct
    	try
    	{
    		//if the url contains "carvela" and "bags" then the test is successful
    		//if it does not contain both strings then there is a failure
    		if(url.contains("carvela")&&(url.contains("bags"))){
        			System.out.println("Succes. The URL: " + url + " contains both 'carvela' and 'bags'");
        		}
    	} catch (Exception e) {
    		System.out.println("Failure. Received a different URL than expected: " + url);
    	}
    }
    //further test: obtain amount of mens boots in DB v total product count
    		
    
	/*Test 2 Actions
	Navigate to the home page
	Select Men from the navigation bar
	Check the title of the received page = Men's Shoes
	Select Boots option from filters list
	Check the title of the received page = Men's Boots
	Check the filters are Mens / Shoes / Boots
	*/
    @Test(dataProvider = "ParametersProviderTestTwo", testName = "Test 2 - Search for Mens Boots", priority = 2)
	public void searchForMensBoots (String pixelValue) throws InterruptedException {
		//link to the object page
		ref_page = new kg_Page_Factory(driver);
    	
		//basic click of "Men" from the navigation bar
    	ref_page.clickNavigationMenuMen();
    	
    	String expectedMensShoes = "Men's Shoes";
    	String actualMensShoes = ref_page.validateScreenTitle();
    	
    	System.out.println("Received the page: " + actualMensShoes);
    	
    	//ensure the title of the screen is what was expected
    	Assert.assertEquals(actualMensShoes, expectedMensShoes);

    	//scroll X pixels on y axis (negative numbers = up. positive numbers = down.)
    	ref_page.scroller(pixelValue);
    	
    	//basic click of boots category
    	ref_page.clickFiltersCategoriesBoots();

    	String expectedMensBoots = "Men's Boots";
    	String actualMensBoots = ref_page.validateScreenTitle();

    	//ensure the title of the screen is what was expected
    	Assert.assertEquals(actualMensBoots, expectedMensBoots);
    	
    	System.out.println("Received the page: " + actualMensBoots);
    	
    	System.out.println("Received the filter: " + ref_page.validateFilterOne());
    	
    	System.out.println("Received the filter: " + ref_page.validateFilterTwo());
    	
    	System.out.println("Received the filter: " + ref_page.validateFilterThree());
    	
    	String url = driver.getCurrentUrl();
    	
    	//same test as test one
    	try
    	{
    		if(url.contains("men")&&(url.contains("boots"))){
    			System.out.println("Succes. The URL: " + url + " contains both 'men' and 'boots'");
        		}
    		} catch (Exception e) {
    		System.out.println("Failure. Received a different URL than expected: " + url);
    		}
    	}
	//further test: obtain amount of mens boots in DB v total product count
    
    /*
	Test 3 Actions
	Navigate to the home page
	Select Mens from the navigation bar
	Check the title of the received page = Men's Shoes
	Select Size from Filters
	Select Colour from Filters
	Select Product
	Ensure filters are taken BEIGE / 6
	Check bag is empty
	Click Add to Bag
	Validate item is in basket
	Click Proceed to Checkout
	Validate My Bag 1 Item
	*/
	//dataProvider. define the parameters to be passed to the test
	//testName. give the test a name.
	//priority. define the execution priority
    @Test(dataProvider = "ParametersProviderTestThree", testName = "Test 3 - Checkout one pair of Vegan Loaded Knit Men's shoes", priority = 3)
	public void checkoutOneProduct (String pixelValue1, String size, String pixelValue2, String colour, String pixelValue3) throws InterruptedException {
    	ref_page = new kg_Page_Factory(driver);

    	ref_page.clickNavigationMenuMen();

    	String expectedMensShoes = "Men's Shoes";
    	String actualMensShoes = ref_page.validateScreenTitle();

    	System.out.println("Received the page: " + actualMensShoes);
    	
    	Assert.assertEquals(actualMensShoes, expectedMensShoes);

    	ref_page.scroller(pixelValue1);
    	
    	ref_page.clickFiltersSizeUK6EU40(size);

    	ref_page.scroller(pixelValue2);

    	ref_page.clickFiltersColourBeige(colour);
    	
    	String productDescBeforeClick = ref_page.validateProductDescription();
    	System.out.println("Test of Before: " + productDescBeforeClick);
    	
    	ref_page.selectProductVeganLoadedKnitMens();
    	System.out.println("After selectProductVeganLoadedKnitMens");
    	String productDescAfterClick = ref_page.validateProductTitle();
    	System.out.println("Test of After: " + productDescAfterClick);
    	
    	System.out.println(productDescBeforeClick.equalsIgnoreCase(productDescAfterClick));
    	
    	String url = driver.getCurrentUrl();
    	
    	try
    	{
    		if(url.contains("beige")){
    			System.out.println("Success. The URL: " + url + " contains 'beige'");
        		}
    		} catch (Exception e) {
    		System.out.println("Failure. Received a different URL than expected: " + url);
    	}
    	
    	ref_page.scroller(pixelValue1);

    	ref_page.clickAddToBag();

    	ref_page.scroller(pixelValue3);

    	ref_page.clickBasket();

    	String expected = "My Bag";
    	String actual = ref_page.validateCheckoutScreenTitle();
    	
    	System.out.println("Actual screen title: " + actual);
    	
    	Assert.assertEquals(actual, expected);
    		
    	String cartUrl = driver.getCurrentUrl();
    	
    	try
    	{
    		if(url.contains("secure.kurtgeiger")&&(url.contains("cart"))){
    			System.out.println("Success. The URL: " + cartUrl + " contains 'secure' and 'cart'");
        		}
    		} catch (Exception e) {
    		System.out.println("Failure. Received a different URL than expected: " + cartUrl);
    	}
    }
    //further test: obtain amount of mens boots in DB v total product count
    //further test: validate details v details gained throughout the test such as product description, colour and size. price could also be included
    
    //provides the data which will be passed
    //i put the parameters within the dataProvider so it is easier for the developer to see what params are to be passed
    @DataProvider(name = "ParametersProviderTestTwo")
	public static String [][] getDataFromDataProviderTestTwo() {
    	//(String pixelValue)
		String[][] testData = {{ "500" }};
		return testData;
	}
    
    //for the purpose of the test two parameterProviders were required
    //they require a unique name
    @DataProvider(name = "ParametersProviderTestThree")
   	public static String[][] getDataFromDataProviderTestThree() {
    	//String pixelValue1, String size, String pixelValue2, String colour, String pixelValue3
    	String[][] testData = {{ "750", "6 / 40", "1000", "Beige", "-750" }};
   		return testData;
   	}
}