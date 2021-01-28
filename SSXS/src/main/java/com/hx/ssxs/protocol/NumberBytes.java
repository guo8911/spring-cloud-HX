package com.hx.ssxs.protocol;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 对数字和字节进行转换<br>
 * 基础知识：<br>
 * Little-Endian就是低位字节排放在内存的低地址端，高位字节排放在内存的高地址端<br>
 * Big-Endian就是高位字节排放在内存的低地址端，低位字节排放在内存的高地址端<br>
 * int a = 0x12345678 <br>
 * 大端,内存里就是 0x12 0x34 0x56 0x78<br>
 * 小端，内存里就是 0x78 0x56 0x34 0x12<br>
 * 
 * 网络字节序：TCP/IP各层协议将字节序定义为Big-Endian，因此TCP/IP协议中使用的字节序通常称之为网络字节序<br>
 */
public class NumberBytes {

	/**
	 * 将一个2位字节数组转换为char字符。<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b
	 *            字节数组
	 * @return char字符
	 */
	public static char bytesToChar(byte[] b) {
		char c = (char) ((b[0] << 8) & 0xFF00L);
		c |= (char) (b[1] & 0xFFL);
		return c;
	}

	/**
	 * 将一个8位字节数组转换为双精度浮点数。(高字节在前，低字节在后)<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b
	 *            字节数组
	 * @return 双精度浮点数
	 */
	public static double bytesToDouble(byte[] bytes, ByteOrder byteOrder) {
		double value;
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(byteOrder);
		value = byteBuffer.getDouble();
		return value;
	}

	/**
	 * 将一个4位字节数组转换为浮点数。(低字节在前，高字节在后)<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b
	 *            字节数组
	 * @return 浮点数
	 */
	public static float bytesToFloat(byte[] bytes, ByteOrder byteOrder) {
		float value;
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(byteOrder);
		value = byteBuffer.getFloat();
		return value;
	}

	/**
	 * 将一个4位字节数组转换为4整数。(低字节在前，高字节在后)<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b
	 *            字节数组
	 * @return 整数
	 */
	public static int bytesToInt(byte[] bytes, ByteOrder byteOrder) {
		int value;

		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(byteOrder);
		if (bytes.length == 2) {
			value = byteBuffer.getShort();
		} else {
			value = byteBuffer.getInt();
		}
		return value;
	}

	/**
	 * 将一个8位字节数组转换为长整数。(高字节在前，低字节在后)<br>
	 * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
	 * 
	 * @param b
	 *            字节数组
	 * @return 长整数
	 */
	public static long bytesToLong(byte[] bytes, ByteOrder byteOrder) {
		long value;

		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		byteBuffer.order(byteOrder);
		value = byteBuffer.getLong();
		return value;
	}

	/**
	 * 将一个char字符转换位字节数组（2个字节），b[0]存储高位字符，大端
	 * 
	 * @param c
	 *            字符（java char 2个字节）
	 * @return 代表字符的字节数组
	 */
	public static byte[] charToBytes(char c) {
		byte[] b = new byte[8];
		b[0] = (byte) (c >>> 8);
		b[1] = (byte) c;
		return b;
	}

	/**
	 * 将一个双精度浮点数转换位字节数组（8个字节）
	 * 
	 * @param d
	 *            双精度浮点数
	 * @return 代表双精度浮点数的字节数组
	 */
	public static byte[] doubleToBytes(double d, ByteOrder byteOrder) {
		return longToBytes(Double.doubleToLongBits(d), byteOrder);
	}

	/**
	 * 将一个浮点数转换为字节数组（4个字节）
	 * 
	 * @param f
	 *            浮点数
	 * @return 代表浮点数的字节数组
	 */
	public static byte[] floatToBytes(float f, ByteOrder byteOrder) {
		return intToBytes(Float.floatToIntBits(f), byteOrder);
	}

	/**
	 * 将一个短整数转换位字节数组(2个字节)
	 * 
	 * @param i
	 *            整数
	 * @return 代表整数的字节数组
	 */
	public static byte[] shortToBytes(int value, ByteOrder byteOrder) {
		byte[] b = new byte[2];
		if (byteOrder == ByteOrder.BIG_ENDIAN) {
			b[0] = (byte) (value >>> 8);
			b[1] = (byte) value;
		} else {
			b[0] = (byte) value;
			b[1] = (byte) (value >>> 8);
		}
		return b;
	}

	/**
	 * 将一个整数转换位字节数组(4个字节)
	 * 
	 * @param i
	 *            整数
	 * @return 代表整数的字节数组
	 */
	public static byte[] intToBytes(int i, ByteOrder byteOrder) {
		byte[] b = new byte[4];

		if (byteOrder == ByteOrder.BIG_ENDIAN) {
			b[0] = (byte) (i >>> 24);
			b[1] = (byte) (i >>> 16);
			b[2] = (byte) (i >>> 8);
			b[3] = (byte) i;
		} else {
			b[0] = (byte) i;
			b[1] = (byte) (i >>> 8);
			b[2] = (byte) (i >>> 16);
			b[3] = (byte) (i >>> 24);
		}
		return b;
	}

	/**
	 * 将一个长整数转换位字节数组(8个字节)，(大端)
	 * 
	 * @param l
	 *            长整数
	 * @return 代表长整数的字节数组
	 */
	public static byte[] longToBytes(long l, ByteOrder byteOrder) {
		byte[] b = new byte[8];
		if (byteOrder == ByteOrder.BIG_ENDIAN) {
			b[0] = (byte) (l >>> 56);
			b[1] = (byte) (l >>> 48);
			b[2] = (byte) (l >>> 40);
			b[3] = (byte) (l >>> 32);
			b[4] = (byte) (l >>> 24);
			b[5] = (byte) (l >>> 16);
			b[6] = (byte) (l >>> 8);
			b[7] = (byte) (l);
		} else {
			b[0] = (byte) (l);
			b[1] = (byte) (l >>> 8);
			b[2] = (byte) (l >>> 16);
			b[3] = (byte) (l >>> 24);
			b[4] = (byte) (l >>> 32);
			b[5] = (byte) (l >>> 40);
			b[6] = (byte) (l >>> 48);
			b[7] = (byte) (l >>> 56);
		}
		return b;
	}

}