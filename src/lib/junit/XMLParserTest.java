package lib.junit;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lib.fileparser.XMLParser;

public class XMLParserTest {
	private final String xmlTestFile = "src\\lib\\junit\\testdata\\XMLParserTest.xml";
	private XMLParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new XMLParser(xmlTestFile);
	}

	@Test
	public void getAttributeValueTest() {
		ArrayList<String> expectedResult = new ArrayList<>();
		expectedResult.add("Result1");
		expectedResult.add("Result2");
		expectedResult.add("Result3");
		
		ArrayList<String> result = parser.getAttributeValues("AttrValueTest", "attr2");
		int i = 0;
		for(String attr : result) {
			Assert.assertEquals(expectedResult.get(i), attr);
			i++;
		}
	}
	/**
	@Test
	public void addInfoToXMLTest() {
		fail("To be implemented");
	}
	**/
	@Test
	public void existsTest() {
		String expectedResult = "Test string for XMLParser.equals method";
		Assert.assertTrue(parser.exists("EqualsTest", expectedResult));
	}
	
	@Test
	public void failExitsTest() {
		String fake = "This string does not exist in the file";
		Assert.assertFalse(parser.exists("EqualsTest", fake));
	}
	
	@Test
	public void getNodeValueTest() {
		ArrayList<String> expectedResult = new ArrayList<>();
		expectedResult.add("Test string for XMLParser.equals method");
		expectedResult.add("Adding an existant element to this");
		expectedResult.add("reza.haqiqi@opengi.co.uk");
		ArrayList<String> result = parser.getNodeValue("testElement");
		int i = 0;
		for(String text : result) {
			Assert.assertEquals(expectedResult.get(i), text);
			i++;
		}
	}
	

}
