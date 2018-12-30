package pk_Functions

/*Created By " Asmaa Elsayed Ibrahim
 * Date 25/12/2018
 * Function : This Class Contains Three function 
 * 1- First Function: clicking on certain item 
 * 2-Second Function: getting all items in Lov and numbers of items and  decide if it matched with expected one or not
 * Input : hierarchy of dropdown list , xpath/ of container , text , items of static lov 
 * Output :there is output for Second function as string (" matched , not matched ")
 */


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.Connection
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword


public class CS_ListUsingLiTag {

	private static TestObject dropdown = null;
	
	private static List<WebElement> allItems= null;
	private static WebElement dropDownList;

// clicking on certain item
	@Keyword
	def LovSearchFun (String dropdownObject , String ContainerObject , String Text) {

		// assuming you have to click the "dropdown" to open it
		TestObject  dropDown=findTestObject(dropdownObject)
        WebUI.click(dropDown) 

		WebDriver driver = DriverFactory.getWebDriver()
		
	//****	dropDownList=driver.findElements(By.xpath(dropdownObjectId));
		
		//geting all items in list called allItems 
		allItems = driver.findElements(By.xpath(ContainerObject+'/li'));

		int itemNo
		//loop on allItems to click on the desired text in lov 
		for( itemNo=1 ; itemNo<=allItems.size() ; itemNo++){
			if (allItems[itemNo-1].getText().equals(Text)) {
				allItems[itemNo-1].click(); // click the desired option
				// Once found desired text then break the for loop 
				break;
			}
		}
	}
	
	
	

	//getting all items in Lov and numbers of items and  decide if it matched with expected one or not
	@Keyword
	def LovMatchItems (String dropdownObjectId , String ContainerObjectId ,List<String> expectedItems ) {

		// assuming you have to click the "dropdown" to open it
		TestObject  dropDown = findTestObject(dropdownObjectId)
        WebUI.click(dropDown) 

		WebDriver driver = DriverFactory.getWebDriver()

		//geting all items in list called allItems
		allItems = driver.findElements(By.xpath(ContainerObjectId+'/li'));

		int itemsNum 
        String Status

		List<String> actualItems = new ArrayList<String>()

		// print Number of Items in lov 
		println ( "Number of Items :  "+ allItems.size())

		for( itemsNum=1 ; itemsNum<=allItems.size() ; itemsNum++){
              actualItems.add(allItems[itemsNum-1].getText())
			  // print each item in lov
              println (actualItems[itemsNum-1])
		   }

		//test matching between expected and actual result 
		if (actualItems.equals(expectedItems) ){
			Status= "Matched"
		   }else {
			Status= "Not Matched"
		         }

		       //print the status 
		       println Status
			   
		return 	Status
	}
}