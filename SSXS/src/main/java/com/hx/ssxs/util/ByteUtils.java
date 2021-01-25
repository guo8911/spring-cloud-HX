package com.hx.ssxs.util;

public class ByteUtils {
  public static byte bitToByte(String bit) {
    int re;
    if (bit == null) {
		return 0;
	} 
    int len = bit.length();
    if (len != 4 && len != 8) {
		return 0;
	} 
    if (len == 8) {
      if (bit.charAt(0) == '0') {
        re = Integer.parseInt(bit, 2);
      } else {
        re = Integer.parseInt(bit, 2) - 256;
      } 
    } else {
      re = Integer.parseInt(bit, 2);
    } 
    return (byte)re;
  }
  
  public static String getBit(byte[] bys) {
    StringBuffer sb = new StringBuffer();
    byte b;
    int i;
    byte[] arrayOfByte;
    for (i = (arrayOfByte = bys).length, b = 0; b < i; ) {
      byte by = arrayOfByte[b];
      sb.append(by >> 7 & 0x1).append(by >> 6 & 0x1)
        .append(by >> 5 & 0x1).append(by >> 4 & 0x1)
        .append(by >> 3 & 0x1).append(by >> 2 & 0x1)
        .append(by >> 1 & 0x1).append(by >> 0 & 0x1);
      b++;
    } 
    return sb.toString();
  }
  
  public static long getInt(byte[] by) {
    long addr = 0L;
    if (by.length == 1) {
      addr = (by[0] & 0xFFL) << 0L;
    } else if (by.length == 2) {
      addr = (by[0] & 0xFFL) << 8L | (by[1] & 0xFFL) << 0L;
    } else if (by.length == 3) {
      addr = (by[0] & 0xFFL) << 16L | (by[1] & 0xFFL) << 8L | (
        by[2] & 0xFFL) << 0L;
    } else if (by.length == 4) {
      addr = (by[0] & 0xFFL) << 24L | (
        by[1] & 0xFFL) << 16L | (
        by[2] & 0xFFL) << 8L | (
        by[3] & 0xFFL) << 0L;
    } else if (by.length == 5) {
      addr = (by[0] & 0xFFL) << 32L | (
        by[1] & 0xFFL) << 24L | (
        by[2] & 0xFFL) << 16L | (
        by[3] & 0xFFL) << 8L | (
        by[4] & 0xFFL) << 0L;
    } else if (by.length == 6) {
      addr = (by[0] & 0xFFL) << 40L | (
        by[1] & 0xFFL) << 32L | (
        by[2] & 0xFFL) << 24L | (
        by[3] & 0xFFL) << 16L | (
        by[4] & 0xFFL) << 8L | (
        by[5] & 0xFFL) << 0L;
    } else if (by.length == 7) {
      addr = (by[0] & 0xFFL) << 48L | (
        by[1] & 0xFFL) << 40L | (
        by[2] & 0xFFL) << 32L | (
        by[3] & 0xFFL) << 24L | (
        by[4] & 0xFFL) << 16L | (
        by[5] & 0xFFL) << 8L | (
        by[6] & 0xFFL) << 0L;
    } else if (by.length == 8) {
      addr = (by[0] & 0xFFL) << 56L | (
        by[1] & 0xFFL) << 48L | (
        by[2] & 0xFFL) << 40L | (
        by[3] & 0xFFL) << 32L | (
        by[4] & 0xFFL) << 24L | (
        by[5] & 0xFFL) << 16L | (
        by[6] & 0xFFL) << 8L | (
        by[7] & 0xFFL) << 0L;
    } 
    return addr;
  }
  
  public static String bytesToHesString(byte[] byteArray) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < byteArray.length; i++) {
      String b = Integer.toHexString(byteArray[i] & 0xFF);
      while (b.length() < 2) {
		b = "0" + b;
	} 
      sb.append(b);
    } 
    return sb.toString();
  }
  
  public static void main(String[] args) {
    String t = "00526";
    int k = 0;
    for (int i = 0; i < t.length(); i++) {
      if (!"0".equals(Character.valueOf(t.charAt(i)))) {
        k = i;
        break;
      } 
    } 
    System.out.println(t.substring(k));
  }
}
