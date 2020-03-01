package de.nuttercode.util.buffer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataQueueTest {

	@Test
	void test() {
		DataQueue queue = new DataQueue();
		long testLong = 0xff000000ff000000L;
		int testInt = 0xf00f;
		byte testByte = (byte) 0xe5;
		short testShort = (short) 0x2b88;
		char testChar = '*';
		String testString = "Hello World!";
		double testDouble = 42.42424242;
		float testFloat = 21.21212121f;

		queue.putLong(testLong);
		queue.putInt(testInt);
		queue.putByte(testByte);
		queue.putShort(testShort);
		assertEquals(queue.getLong(), testLong);
		assertEquals(queue.getInt(), testInt);
		queue.putChar(testChar);
		assertEquals(queue.getByte(), testByte);
		assertEquals(queue.getShort(), testShort);
		queue.putString(testString);
		queue.putDouble(testDouble);
		queue.putFloat(testFloat);
		assertEquals(queue.getChar(), testChar);
		assertEquals(queue.getString(), testString);
		assertEquals(queue.getDouble(), testDouble);
		assertEquals(queue.getFloat(), testFloat);
		assertEquals(queue.available(), 0);
	}

}
