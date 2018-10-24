import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable as GlobalVariable

String stringifyTestObject(TestObject tObj) {
	List<TestObjectProperty> lst = tObj.getActiveProperties()
	StringBuilder sb = new StringBuilder()
	for (TestObjectProperty top : lst) {
		sb.append('{')
		sb.append('\"name\":')
		sb.append("\"${top.getName()}\"")
		sb.append(',')
		sb.append('\"conditon\":')
		sb.append("\"${top.getCondition().toString()}\"")
		sb.append(',')
		sb.append('\"value\":')
		sb.append("\"${top.getValue()}\"")
		sb.append(',')
		sb.append('\"isActive\":')
		sb.append("${top.isActive()}")
		sb.append('}')
		sb.append('\n')
	}
	return sb.toString()
} 

TestObject tObj = findTestObject('Object Repository/Application/android.widget.TextView - App')
print stringifyTestObject(tObj)
/*  here we got
{"name":"class","conditon":"equals","value":"android.widget.TextView","isActive":true}
{"name":"instance","conditon":"equals","value":"3","isActive":true}
{"name":"text","conditon":"equals","value":"App","isActive":true}
{"name":"resource-id","conditon":"equals","value":"android:id/text1","isActive":true}
 */

// now we will overwrite the value of "resource-id" using GlobalVariable.packageName
String originalResourceId = tObj.findPropertyValue('resource-id')
String newResourceId = originalResourceId.replaceFirst('android', GlobalVariable.G_packageName)
tObj.addProperty('resource-id', ConditionType.EQUALS, newResourceId, true)

// check the TestObject if the resource-id is overwritten
print stringifyTestObject(tObj)
/* here we got
{"name":"class","conditon":"equals","value":"android.widget.TextView","isActive":true}
{"name":"instance","conditon":"equals","value":"3","isActive":true}
{"name":"text","conditon":"equals","value":"App","isActive":true}
{"name":"resource-id","conditon":"equals","value":"android:id/text1","isActive":true}
{"name":"resource-id","conditon":"equals","value":"com.kazurayam:id/text1","isActive":true}
   this is NOT what we want
 */

// TestObject has addProperty() method, but does not have setProperty() method.
 
// javadoc is here https://api-docs.katalon.com/studio/v4.8.0/api/com/kms/katalon/core/testobject/TestObject.html


// Conclusion: we can not overwrite the value of a property "resouce-id".


