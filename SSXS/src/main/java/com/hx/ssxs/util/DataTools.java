package com.hx.ssxs.util;

import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类功能: 数据工具类
 * 
 * @author chen.jie
 * @date 2014-9-14
 */
public class DataTools {

	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(DataTools.class);

	/**
	 * 字符串转化成16进制编码
	 * 
	 * @param str
	 * @return
	 */
	public static String str2Hex(String str) {
		String retStr = "";
		System.out.println(str.length());
		for (int i = 0; i < str.length(); i++) {
			int ch = (int) str.charAt(i);
			String s4 = Integer.toHexString(ch);
			retStr = retStr + s4;
		}
		return retStr;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

	}

	/**
	 * 16进制编码转化成字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String hex2Str(byte[] data) {

		String str = bytesToHesString(data);
		byte[] baKeyword = new byte[str.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						str.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			str = new String(baKeyword, "utf-8");
		} catch (Exception e1) {
		}
		return str;
	}

	/**
	 * 字节数组转化成16进制字符串
	 * 
	 * @param byteArray
	 */
	public static String bytesToHesString(byte[] byteArray) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			String b = Integer.toHexString(byteArray[i] & 0xff);
			while (b.length() < 2) {
				b = "0" + b;
			}
			sb.append(b);
		}
		return sb.toString();
	}

	/**
	 * 16进制字符串转化成字节数组
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] hesStrToByte(String str) {
		String value = null;
		byte[] retByte = new byte[str.length() / 2];

		for (int i = 0; i < str.length(); i = i + 2) {
			value = str.substring(i, i + 2);
			retByte[i / 2] = (byte) Integer.parseInt(value, 16);
		}

		return retByte;
	}

	/**
	 * 获得2进制长度头(高字节在前，低字节在后)
	 * 
	 * @param len
	 *            位数
	 * @param num
	 * @return
	 */
	public static byte[] getLength(int len, int num) {
		byte[] lengthBuf = new byte[len];

		for (int i = 0; i < len; i++) {
			lengthBuf[i] = (byte) ((num >>> ((len - i - 1) * 8)) & 0xFF);
		}

		return lengthBuf;
	}

	/**
	 * 获得2进制长度头(低字节在前，高字节在后)
	 * 
	 * @param len
	 *            位数
	 * @param num
	 * @return
	 */
	public static byte[] getLength2(int len, int num) {
		byte[] lengthBuf = new byte[len];

		for (int i = 0; i < len; i++) {
			lengthBuf[i] = (byte) ((num >>> (i * 8)) & 0xFF);
		}

		// lengthBuf = lowToHeight(lengthBuf);

		return lengthBuf;
	}

	/**
	 * 字节数据高低位调换
	 * 
	 * @param head
	 * @return
	 */
	public static byte[] lowToHeight(byte[] head) {
		if (4 == head.length) {
			byte[] begin = new byte[2];
			System.arraycopy(head, 0, begin, 0, 2);

			System.arraycopy(head, 2, head, 0, 2);
			System.arraycopy(begin, 0, head, 2, 2);
		} else if (2 == head.length) {
			byte[] begin = new byte[1];
			System.arraycopy(head, 0, begin, 0, 1);

			System.arraycopy(head, 1, head, 0, 1);
			System.arraycopy(begin, 0, head, 1, 1);
		}
		return head;
	}

	/**
	 * 获得2进制实际长度(高字节在前，低字节在后)
	 * 
	 * @param begin
	 * @param head
	 * @return
	 */
	public static int getLenth(int begin, byte[] head) {

		int retLen = 0;
		int length = head.length;

		for (int i = begin; i < length; i++) {
			retLen = retLen + ((head[i] & 0xFF) << ((length - i - 1) * 8));
		}
		return retLen;
	}

	/**
	 * 获得2进制实际长度(低字节在前，高字节在后)
	 * 
	 * @param begin
	 * @param head
	 * @return
	 */
	public static int getLenth2(int begin, byte[] head) {

		// head = lowToHeight(head);

		int retLen = 0;
		int length = head.length;

		for (int i = begin; i < length; i++) {
			retLen = retLen + ((head[i] & 0xFF) << (i * 8));
		}
		return retLen;
	}

	public static byte[] long2bytes(long num) {
		byte[] byteNum = new byte[8];
		for (int ix = 0; ix < 8; ++ix) {
			int offset = 64 - (ix + 1) * 8;
			byteNum[ix] = (byte) ((num >> offset) & 0xff);
		}
		return byteNum;
	}

	public static long bytes2long(byte[] byteNum) {
		long num = 0;
		for (int ix = 0; ix < 8; ++ix) {
			num <<= 8;
			num |= (byteNum[ix] & 0xff);
		}
		return num;
	}

	/**
	 * 浮点转换为字节
	 * 
	 * @param f
	 * @return
	 */
	public static byte[] float2bytes(float f) {

		// 把float转换为byte[]
		int fbit = Float.floatToIntBits(f);

		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> (24 - i * 8));
		}

		return b;

	}

	/**
	 * 字节转换为浮点
	 * 
	 * @param b
	 *            字节（至少4个字节）
	 * @param index
	 *            开始位置
	 * @return
	 */
	public static float bytes2float(byte[] b, int index) {
		int l;
		l = b[index + 0];
		l &= 0xff;
		l |= ((long) b[index + 1] << 8);
		l &= 0xffff;
		l |= ((long) b[index + 2] << 16);
		l &= 0xffffff;
		l |= ((long) b[index + 3] << 24);
		return Float.intBitsToFloat(l);
	}

	public static byte[] double2bytes(double d) {
		byte writeBuffer[] = new byte[8];
		long v = Double.doubleToLongBits(d);
		writeBuffer[0] = (byte) (v >>> 56);
		writeBuffer[1] = (byte) (v >>> 48);
		writeBuffer[2] = (byte) (v >>> 40);
		writeBuffer[3] = (byte) (v >>> 32);
		writeBuffer[4] = (byte) (v >>> 24);
		writeBuffer[5] = (byte) (v >>> 16);
		writeBuffer[6] = (byte) (v >>> 8);
		writeBuffer[7] = (byte) (v >>> 0);
		return writeBuffer;

	}

	public static double bytes2double(byte[] readBuffer) {
		return Double
				.longBitsToDouble((((long) readBuffer[0] << 56)
						+ ((long) (readBuffer[1] & 255) << 48)
						+ ((long) (readBuffer[2] & 255) << 40)
						+ ((long) (readBuffer[3] & 255) << 32)
						+ ((long) (readBuffer[4] & 255) << 24)
						+ ((readBuffer[5] & 255) << 16)
						+ ((readBuffer[6] & 255) << 8) + ((readBuffer[7] & 255) << 0)));
	}

	/**
	 * 获得10进制长度头
	 * 
	 * @param len
	 *            位数
	 * @param length
	 *            长度值
	 * @param encoding
	 *            编码格式
	 * @return
	 */
	public static byte[] getASCIILength(int len, int length, String encoding) {

		byte[] retLen = null;
		String lenStr = "" + length;
		// 前补0
		while (lenStr.length() < len) {
			lenStr = "0" + lenStr;
		}

		try {
			retLen = lenStr.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
		}

		return retLen;
	}

	/**
	 * 数据补位
	 * 
	 * @param str
	 * @param length
	 * @param fillChar
	 * @return
	 */
	public static String fillChar(String str, int length, String fillChar) {

		StringBuilder strZero = new StringBuilder(str);
		while (strZero.length() < length) {
			strZero.append(fillChar);
		}
		return strZero.toString();
	}

	/**
	 * 数据前补0
	 * 
	 * @return
	 */
	public static String fillZero(int iCurrent, int length) {

		String strTmp = Integer.toString(iCurrent);

		int iLen = strTmp.length();

		StringBuilder strZero = new StringBuilder();
		for (int i = iLen; i < length; i++) {
			strZero.append("0");
		}

		strZero.append(strTmp);

		return strZero.toString();
	}

}
