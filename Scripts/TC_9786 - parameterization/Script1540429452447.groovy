import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable as GlobalVariable

TestObject tObjOriginal = findTestObject(
	'Object Repository/Application/android.widget.TextView - App - parameterized resource-id')
println CustomKeywords.'com.kazurayam.ksbackyard.TestObjectSupport.jsonifyActiveProperties'(tObjOriginal)
/*  here we got

 */

TestObject tObjInterpolated = findTestObject(
	'Object Repository/Application/android.widget.TextView - App - parameterized resource-id',
	[('packageName'): GlobalVariable.G_packageName]
	)
println CustomKeywords.'com.kazurayam.ksbackyard.TestObjectSupport.jsonifyActiveProperties'(tObjInterpolated)
/*  here we got

 */
