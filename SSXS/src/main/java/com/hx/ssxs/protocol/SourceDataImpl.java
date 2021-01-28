package com.hx.ssxs.protocol;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.data.Source;

public class SourceDataImpl implements IPack<Source>{
	
	private static Log log = LogFactory.getLog(SourceDataImpl.class);
	
	@Override
	public Source unpack(byte[] data) {
		if (log.isInfoEnabled()) {
			log.info("组件[SourceDataImpl] 原码方法[unpack]开始执行"+"数据长度："+data.length);
		}
		Source source = new Source();
		//设备标识
		byte[] devBytes = new byte[4];
		System.arraycopy(data, 0, devBytes, 0, 4);
		int devMid = NumberBytes.bytesToInt(devBytes, Constant.BYTEORDER);
		source.setDevMid(devMid);
		//源码数据
		byte[] sourceBytes = new byte[data.length-4];
		System.arraycopy(data, 4, sourceBytes, 0, sourceBytes.length);
		source.setContent(sourceBytes);
		return source;
	}

	@Override
	public byte[] pack(Source source) {
		int devMid = source.getDevMid();
		//设备标识
		byte[] devBytes = NumberBytes.intToBytes(devMid, Constant.BYTEORDER);
		//源码数据
		byte[] sourceBytes = source.getContent();
		//返回结果
		byte[] result = new byte[devBytes.length+sourceBytes.length];
		System.arraycopy(devBytes, 0, result, 0, devBytes.length);
		System.arraycopy(sourceBytes, 0, result, 4, sourceBytes.length);
		return result;
	}

	@Override
	public List<Source> multiUnpack(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] multiPack(List<Source> list) {
		// TODO Auto-generated method stub
		return null;
	}


}
