package lib.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import lib.structs.LogData;
import lib.structs.SevereLogData;

public class LogDataTest {
	LogData log;
	@Test
	public void testSetLogTime() {
		log = new SevereLogData("Set 29, 2016 12:12:12 PM", "Test");
		assertEquals("Set 29, 2016 12:12:12 PM", log.getLogTime());
	}

	@Test
	public void testSetLogType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGenerateReport() {
		fail("Not yet implemented");
	}

}
