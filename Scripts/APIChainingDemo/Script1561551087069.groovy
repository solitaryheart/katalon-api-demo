import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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

response1 = WS.sendRequest(findTestObject('RESTWebServices/ListUsers'))

//Parsing the userName out of the response by using JSONSlurper
def slurper = new groovy.json.JsonSlurper()

def result = slurper.parseText(response1.getResponseBodyContent())

//extracts user Tracey
def value = result.data[2].first_name

println('.. value extracted is : ' + value)

/* Usage 1 - by using global variable 

GlobalVariable.userName = value

println('..Global variable is : ' + GlobalVariable.userName)

WS.sendRequestAndVerify(findTestObject('RESTWebServices/UpdateUser'))
*/

// Usage 1- by using parsed vale stored in variable "value directly

WS.sendRequest(findTestObject('RESTWebServices/UpdateUser', [('userName') : value]))


