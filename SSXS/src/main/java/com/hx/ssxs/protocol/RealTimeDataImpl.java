package com.hx.ssxs.protocol;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.data.GroupParameter;
import com.hx.ssxs.data.Parameter;
import com.hx.ssxs.data.RealTimeData;

public class RealTimeDataImpl implements IPack<RealTimeData>{

	private static Log log = LogFactory.getLog(RealTimeDataImpl.class);
	
	/**
	 * 实时工程参数拆包
	 */
	@Override
	public RealTimeData unpack(byte[] data) {
		RealTimeData  rtd = new RealTimeData();
		List<GroupParameter>  glist  = new ArrayList<GroupParameter>();
		
		/*包含的参数组数  4字节*/
		byte[] num = new byte[4];
		System.arraycopy(data, 0, num, 0, 4);
		int n = NumberBytes.bytesToInt(num, Constant.BYTEORDER);
		rtd.setNum(n);
		
		byte[]  nData = new byte[data.length-4];
		System.arraycopy(data, 4, nData, 0, data.length-4);
		//偏移量
		int pos = 0;
		for (int i = 0; i < n; i++) {
			GroupParameter gp = new GroupParameter();
			List<Parameter>  plist  = new ArrayList<Parameter>();
			/*数据类型  4字节*/
			byte[] type = new byte[4];
			System.arraycopy(nData, pos, type, 0, 4);
			int typeValue = NumberBytes.bytesToInt(type, Constant.BYTEORDER);
			gp.setType(typeValue);
			pos += 4;
			
			/*信源设备编码 4字节*/
			byte[] sou = new byte[4];
			System.arraycopy(nData, pos, sou, 0, 4);
			int souValue = NumberBytes.bytesToInt(sou, Constant.BYTEORDER);
			gp.setSou(souValue);
			pos += 4;
			
			/*数据时间（积日） 4字节 */
			byte[] jd = new byte[4];
			System.arraycopy(nData, pos, jd, 0, 4);
			int j = NumberBytes.bytesToInt(jd, Constant.BYTEORDER);
			gp.setJd(j);
			pos += 4;
			
			/*数据时间（积秒） 8字节*/
			byte[]  T = new byte[8];
			System.arraycopy(nData, pos, T, 0, 8);
			Double t = NumberBytes.bytesToDouble(T, Constant.BYTEORDER);
			gp.setT(t);
			pos += 8;
			
			/*标志位  0x00表示定长  0xFF表示变长   2字节*/		
			byte[]  flag = new byte[2];
			System.arraycopy(nData, pos, flag, 0, 2);
			int f = NumberBytes.bytesToInt(flag, Constant.BYTEORDER);
			gp.setFlag(f);
			pos += 2;
			
			/*数据长度（含PNum） 2字节       2+所有参数总长度 */
			byte[]  plen = new byte[2];
			System.arraycopy(nData, pos, plen, 0, 2);
			int pl = NumberBytes.bytesToInt(plen, Constant.BYTEORDER);
			gp.setPlen(pl);
			pos += 2;
			
			/*本组参数总个数  2字节*/
			byte[]  pnum = new byte[2];
			System.arraycopy(nData, pos, pnum, 0, 2);
			int pn = NumberBytes.bytesToInt(pnum, Constant.BYTEORDER);
			gp.setPnum(pn);
			pos += 2;
			
			//判断是定长还是变长参数
			switch (f) {
				case 0x00://定长参数
					for (int k = 0; k < pn; k++) {
						Parameter parameter = new Parameter();
						//参数代号  2字节
						byte[]  sec = new byte[4];
						System.arraycopy(nData, pos, sec, 2, 2);
						int tmNum = NumberBytes.bytesToInt(sec, Constant.BYTEORDER);
						parameter.setTmNum(tmNum);
						pos += 2;
						
						//参数类型 1字节
						byte[]  stDataType = new byte[2];
						System.arraycopy(nData, pos, stDataType, 1, 1);
						int tmType = NumberBytes.bytesToInt(stDataType, Constant.BYTEORDER);
						parameter.setTmType(tmType);
						pos += 1;
						
						//参数超限标志 1字节
						byte[]  stFlag = new byte[2];
						System.arraycopy(nData, pos, stFlag, 1, 1);
						int tmLevel = NumberBytes.bytesToInt(stFlag, Constant.BYTEORDER);
						parameter.setTmLevel(tmLevel);
						pos += 1;
						
						/*参数结果   8字节*/
						byte[]  ref = new byte[8];
						System.arraycopy(nData, pos, ref, 0, 8);
						String value = "";
						if (tmType == 0) {
							value = NumberBytes.bytesToDouble(ref, Constant.BYTEORDER) + "";
						}else if (tmType == 1) {
							value = NumberBytes.bytesToLong(ref, Constant.BYTEORDER) + "";
						}else if (tmType == 2) {
							value = NumberBytes.bytesToLong(ref, Constant.BYTEORDER) + "";
						}
						parameter.setTmValue(value);
						pos += 8;
						
						/*参数原码  8字节*/
						byte[]  tmSource = new byte[8];
						System.arraycopy(nData, pos, tmSource, 0, 8);
						parameter.setTmSource(tmSource);
						pos += 8;
						
						plist.add(parameter);
					}
					break;
					
				case 0xff://变长参数
					for (int k = 0; k < pn; k++) {
						Parameter parameter = new Parameter();
						/*参数代号   2字节*/
						byte[]  bsec = new byte[4];
						System.arraycopy(nData, pos, bsec, 2, 2);
						int tmNum = NumberBytes.bytesToInt(bsec, Constant.BYTEORDER);
						parameter.setTmNum(tmNum);
						pos += 2;
						
						//参数类型 1字节
						byte[]  stDataType = new byte[2];
						System.arraycopy(nData, pos, stDataType, 1, 1);
						int tmType = NumberBytes.bytesToInt(stDataType, Constant.BYTEORDER);
						parameter.setTmType(tmType);
						pos += 1;
						
						//参数超限标志 1字节
						byte[]  stFlag = new byte[2];
						System.arraycopy(nData, pos, stFlag, 1, 1);
						int tmLevel = NumberBytes.bytesToInt(stFlag, Constant.BYTEORDER);
						parameter.setTmLevel(tmLevel);
						pos += 1;
						
						/*参数长度  2字节*/					
						byte[]  blen = new byte[4];
						System.arraycopy(nData, pos, blen, 2, 2);
						int tmLength = NumberBytes.bytesToInt(blen, Constant.BYTEORDER);
						pos += 2;
						
						/*参数结果  ble字节*/
						byte[]  tmSource = new byte[tmLength];
						System.arraycopy(nData, pos, tmSource, 0, tmLength);
						parameter.setTmSource(tmSource);
						pos += tmLength;
						plist.add(parameter);
					}
					break;
			}
			gp.setParameters(plist);
			glist.add(gp);
			
		}
		
		rtd.setGroupParameters(glist);
		return  rtd;
	}

	@Override
	public byte[] pack(RealTimeData rTData) {
		
		ByteArrayOutputStream rtd = new ByteArrayOutputStream();
		byte[] rtData = null;
		try {
			//参数组集合
			List<GroupParameter> gpList = rTData.getGroupParameters();
			//传入组为空，则返回空
			if(gpList == null || gpList.size() == 0){
				return rtData;
			}
			//包含的参数组数  4字节
			byte[] num = NumberBytes.intToBytes(gpList.size(), Constant.BYTEORDER);
				rtd.write(num);
	
			for (int i = 0; i < gpList.size(); i++) {
				GroupParameter gParameter = gpList.get(i);
				
				// 数据类型(4字节)
				byte[] type = NumberBytes.intToBytes(gParameter.getType(),
						Constant.BYTEORDER);
				rtd.write(type);
				
				// 信源设备编码(4字节)
				byte[] sou = NumberBytes.intToBytes(gParameter.getSou(),
						Constant.BYTEORDER);
				rtd.write(sou);
				
				//数据时间（积日）(4字节)
				byte[] jd = NumberBytes.intToBytes(gParameter.getJd(), Constant.BYTEORDER);
				rtd.write(jd);
				
				//数据时间（积秒）(8字节)
				byte[] t = NumberBytes.doubleToBytes(gParameter.getT(), Constant.BYTEORDER);
				rtd.write(t);
				
				//标志位          0x00表示定长    0xFF表示变长(2字节)
				byte[] flag = NumberBytes.shortToBytes(gParameter.getFlag(), Constant.BYTEORDER);
				rtd.write(flag);
							
				//参数集合
				List<Parameter> pList = gParameter.getParameters();
				ByteArrayOutputStream data = new ByteArrayOutputStream();
				
				switch (gParameter.getFlag()) {
					case 0x00://定长
						for (int j = 0,listSize=pList.size(); j < listSize; j++) {			
							Parameter parameter = pList.get(j);
							//参数代号  (2字节)
							byte[] sec = NumberBytes.shortToBytes(parameter.getTmNum(), Constant.BYTEORDER);
							data.write(sec);
							
							//参数类型+参数超限标志 2字节
							byte[] state = parameter.getParameterState();
							data.write(state);
							
							//参数结果(8字节)
							byte[] ref = new byte[8];
							int dataType = parameter.getTmType();
							if (dataType==0) { //浮点型
								ref = NumberBytes.doubleToBytes(Double.parseDouble(parameter.getTmValue()), Constant.BYTEORDER);
							}else if (dataType==1) {  //整型数
								ref = NumberBytes.longToBytes(Long.parseLong(parameter.getTmValue()), Constant.BYTEORDER);
							}else if (dataType==2) {  //无符号整型
								ref = NumberBytes.longToBytes(Long.parseLong(parameter.getTmValue()), Constant.BYTEORDER);
							}
							data.write(ref);
							
							//参数原码(8字节)
							byte[] org = null;
							byte[] tempOrg = parameter.getTmSource();
							if(tempOrg.length > 8){
								org = new byte[8];
								System.arraycopy(tempOrg, 0, org, 0, 8);
							}else if(tempOrg.length < 8){
								org = new byte[8];
								System.arraycopy(tempOrg, 0, org, org.length-tempOrg.length, tempOrg.length);
							}else{
								org = tempOrg;
							}
							data.write(org);
						}
						break;
					case 0xFF://变长
						for (int j = 0,listSize=pList.size(); j < listSize; j++) {			
							Parameter parameter = pList.get(j);
							//参数代号  (2字节)
							byte[] se = NumberBytes.shortToBytes(parameter.getTmNum(), Constant.BYTEORDER);
							data.write(se);
							
							//参数类型+参数超限标志 2字节
							byte[] state = parameter.getParameterState();
							data.write(state);
							
							//参数长度(2字节)
							int tmLength = parameter.getTmSource().length;
							byte[] len = NumberBytes.shortToBytes(tmLength, Constant.BYTEORDER);
							data.write(len);
							
							//参数结果(len字节)
							byte[] ref1 = parameter.getTmSource();
							data.write(ref1);
						}
						break;
				}
				
				//数据长度（含PNum）(2字节)
				byte[] plen = NumberBytes.shortToBytes(data.size()+2, Constant.BYTEORDER);
				rtd.write(plen);
						
				//本组参数总个数 (2字节)
				byte[] pnum = NumberBytes.shortToBytes(pList.size(), Constant.BYTEORDER);
				rtd.write(pnum);
				
				data.writeTo(rtd);
				data.close();			
			}
			
			rtData = rtd.toByteArray();
			rtd.close();
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("组件[RealTimeDataImpl][pack]执行异常", e);
			}
		}
		
		return  rtData;
	
	}

	@Override
	public List<RealTimeData> multiUnpack(byte[] data) {
		return null;
	}

	@Override
	public byte[] multiPack(List<RealTimeData> list) {
		return null;
	}


}
