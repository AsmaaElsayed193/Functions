package pk_Functions

/*Created By " Asmaa Elsayed Ibrahim
 * Date 25/12/2018
 * Function : This Class Contains Three function 
 * 1- First one for clicking  on dropdown list
 * 2- Second one for clicking on certain item i want in LOV 
 * 3- Third one for getting all items in Lov and numbers of items and decide if it matched with expected one or not 
 * Input : hierarchy of dropdown list , xpath/ of container , text , items of static lov 
 * Output :there is only output for third one as string (" matched , not matched ") 
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


public class CS_List {

	private static TestObject dropdown = null;
	private static List<WebElement> allProduct= null;

	@Keyword

	def LovFun (String dropdownList , String ContainerList ) {



		dropdown=findTestObject(dropdownList)

		WebUI.click(dropdown) // assuming you have to click the "dropdown" to open it

		WebDriver driver = DriverFactory.getWebDriver()

		allProduct = driver.findElements(By.xpath(ContainerList+'/li'));
	}


	@Keyword
	def LovSearchFun (String dropdownList , String ContainerList , String Text) {

		LovFun(dropdownList ,ContainerList )

		int items
		for( items=1 ; items<=allProduct.size() ; items++){


			if (allProduct[items-1].getText().equals(Text)) {
				allProduct[items-1].click(); // click the desired option
				break;
			}
		}
	}

	@Keyword
	def LovMatchItems (String dropdownList , String ContainerList ,List<String> expectedItems ) {

		LovFun(dropdownList ,ContainerList  )

		int itemsNum

		String Status

		List<String> actualItems = new ArrayList<String>()

		println ( "Number of Items :  "+ allProduct.size())

		for( itemsNum=1 ; itemsNum<=allProduct.size() ; itemsNum++){

			actualItems.add(allProduct[itemsNum-1].getText())

			println (actualItems[itemsNum-1])
		}

		if (actualItems.equals(expectedItems) ){

			Status= "Matched"
		}else {
			Status= "Not Matched"
		}

		println Status
		return 	Status
	}
}