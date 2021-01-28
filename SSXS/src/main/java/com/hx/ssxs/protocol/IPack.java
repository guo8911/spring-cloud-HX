package com.hx.ssxs.protocol;

import java.util.List;

public interface IPack<T> {

    /**
     *  拆包
     * @param data
     * @return T
     */
	public T unpack(byte[] data);

	/**
	 * 打包
	 * @param obj
	 * @return　T
	 */
	public byte[] pack(T obj);

	/**
	 * 多条拆包
	 * @param data
	 * @return　T
	 */
	public List<T> multiUnpack(byte[] data);

	/**
	 * 多条打包
	 * @param list
	 * @return　T
	 */
	public byte[] multiPack(List<T> list);
}
