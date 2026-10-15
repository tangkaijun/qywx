package com.github.qywx.aes;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * 回归测试（issue #18）：PKCS7 补位/去补位为纯逻辑，使用固定测试向量离线断言。
 */
public class PKCS7EncoderTest {

    @Test
    public void encodeShouldPadToBlockSize() {
        byte[] pad = PKCS7Encoder.encode(5);
        assertEquals(32 - 5, pad.length);
        for (byte b : pad) {
            assertEquals(27, b & 0xFF);
        }
    }

    @Test
    public void encodeShouldAddFullBlockWhenAlreadyAligned() {
        byte[] pad = PKCS7Encoder.encode(32);
        assertEquals(32, pad.length);
        for (byte b : pad) {
            assertEquals(32, b & 0xFF);
        }
    }

    @Test
    public void decodeShouldStripPadding() {
        byte[] data = new byte[] {1, 2, 3, 4, 5};
        byte[] pad = PKCS7Encoder.encode(data.length);
        byte[] combined = new byte[data.length + pad.length];
        System.arraycopy(data, 0, combined, 0, data.length);
        System.arraycopy(pad, 0, combined, data.length, pad.length);

        assertArrayEquals(data, PKCS7Encoder.decode(combined));
    }

    @Test
    public void decodeShouldIgnoreOutOfRangePadding() {
        byte[] data = new byte[] {1, 2, 3, 99};
        assertArrayEquals(data, PKCS7Encoder.decode(data));
    }

    @Test
    public void chrShouldMapToAsciiCharacter() {
        assertEquals('\u0001', PKCS7Encoder.chr(1));
        assertEquals(' ', PKCS7Encoder.chr(32));
    }
}
