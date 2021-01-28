package com.hx.ssxs.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.ssxs.data.DelayData;
import com.hx.ssxs.data.DelayGroupParameter;
import com.hx.ssxs.data.PackageParameter;
import com.hx.ssxs.data.Parameter;

public class DelayDataImpl implements IPack<DelayData>{

	private static Log log = LogFactory.getLog(DelayDataImpl.class);
	
	@Override
	public DelayData unpack(byte[] data) {
		DelayData dd = new DelayData();
		List<PackageParameter> ppList = new ArrayList<PackageParameter>();

		/* 包含的数据包个数 4字节 */
		byte[] num = new byte[4];
		System.arraycopy(data, 0, num, 0, 4);
		int n = NumberBytes.bytesToInt(num, Constant.BYTEORDER);
		dd.setNum(n);

		byte[] nData = new byte[data.length - 4];
		System.arraycopy(data, 4, nData, 0, nData.length);

		// 偏移量
		int pos = 0;
		/* 循环包 */
		for (int i = 0; i < n; i++) {

			PackageParameter pp = new PackageParameter();

			/* 数据类型 4字节 */
			byte[] type = new byte[4];
			System.arraycopy(nData, pos, type, 0, 4);
			int typeValue = NumberBytes.bytesToInt(type, Constant.BYTEORDER);
			pp.setType(typeValue);
			pos += 4;

			/* 信源设备编码 4字节 */
			byte[] sou = new byte[4];
			System.arraycopy(nData, pos, sou, 0, 4);
			int souValue = NumberBytes.bytesToInt(sou, Constant.BYTEORDER);
			pp.setSou(souValue);
			pos += 4;

			/* 数据采样时间（积日） 4字节 */
			byte[] jd = new byte[4];
			System.arraycopy(nData, pos, jd, 0, 4);
			int j = NumberBytes.bytesToInt(jd, Constant.BYTEORDER);
			pp.setJd(j);
			pos += 4;

			/* 数据采样时间（积秒） 8字节 */
			byte[] T = new byte[8];
			System.arraycopy(nData, pos, T, 0, 8);
			double t = NumberBytes.bytesToDouble(T, Constant.BYTEORDER);
			pp.setT(t);
			pos += 8;

			/* 标志位 0x00表示定长 0xFF表示变长 2字节 */
			byte[] flag = new byte[2];
			System.arraycopy(nData, pos, flag, 0, 2);
			int f = NumberBytes.bytesToInt(flag, Constant.BYTEORDER);
			pp.setFlag(f);
			pos += 2;

			/* 数据包长度（含PNum） 2字节 2+所有参数总长度 */
			byte[] plen = new byte[2];
			System.arraycopy(nData, pos, plen, 0, 2);
			int pl = NumberBytes.bytesToInt(plen, Constant.BYTEORDER);
			pp.setPlen(pl);
			pos += 2;

			/* 本包含有的组个数 2字节 */
			byte[] gnum = new byte[2];
			System.arraycopy(nData, pos, gnum, 0, 2);
			int gnu = NumberBytes.bytesToInt(gnum, Constant.BYTEORDER);
			pp.setPnum(gnu);
			pos += 2;
			List<DelayGroupParameter> dgpList = new ArrayList<DelayGroupParameter>();
			
			/* 循环组 */
			for (int k = 0; k < gnu; k++) {
				DelayGroupParameter dgp = new DelayGroupParameter();

				/* 本组参数积日 4字节 */
				byte[] gp_jd = new byte[4];
				System.arraycopy(nData, pos, gp_jd, 0, 4);
				int gp_j = NumberBytes.bytesToInt(gp_jd, Constant.BYTEORDER);
				dgp.setGp_jd(gp_j);
				pos += 4;

				/* 本组参数积秒 8字节 */
				byte[] gp_t = new byte[8];
				System.arraycopy(nData, pos, gp_t, 0, 8);
				double gp_ = NumberBytes.bytesToDouble(gp_t, Constant.BYTEORDER);
				dgp.setGp_t(gp_);
				pos += 8;

				/* 本组参数个数 2字节 */
				byte[] pnum = new byte[2];
				System.arraycopy(nData, pos, pnum, 0, 2);
				int pnu = NumberBytes.bytesToInt(pnum, Constant.BYTEORDER);
				dgp.setPnum(pnu);
				pos += 2;
				
				List<Parameter> plist = new ArrayList<Parameter>();
				switch (f) {
				/* 定长参数 */
				case 0x00:
					/* 循环参数 */
					for (int l = 0; l < pnu; l++) {
						Parameter parameter = new Parameter();
						/* 参数代号 2字节 */
						byte[] sec = new byte[4];
						System.arraycopy(nData, pos, sec, 2, 2);
						int tmNum = NumberBytes.bytesToInt(sec,
								Constant.BYTEORDER);
						parameter.setTmNum(tmNum);
						pos += 2;

						// 参数类型 1字节
						byte[] stDataType = new byte[2];
						System.arraycopy(nData, pos, stDataType, 1, 1);
						int tmType = NumberBytes.bytesToInt(stDataType,
								Constant.BYTEORDER);
						parameter.setTmType(tmType);
						pos += 1;

						// 参数超限标志 1字节
						byte[] stFlag = new byte[2];
						System.arraycopy(nData, pos, stFlag, 1, 1);
						int tmLevel = NumberBytes.bytesToInt(stFlag,
								Constant.BYTEORDER);
						parameter.setTmLevel(tmLevel);
						pos += 1;

						/* 参数结果 8字节 */
						byte[] ref = new byte[8];
						System.arraycopy(nData, pos, ref, 0, 8);
						String value = "";
						if (tmType == 0) {
							value = NumberBytes.bytesToDouble(ref,
									Constant.BYTEORDER) + "";
						} else if (tmType == 1) {
							value = NumberBytes.bytesToLong(ref,
									Constant.BYTEORDER) + "";
						} else if (tmType == 2) {
							value = NumberBytes.bytesToLong(ref,
									Constant.BYTEORDER) + "";
						}
						parameter.setTmValue(value);
						pos += 8;

						/* 参数原码 8字节 */
						byte[] tmSource = new byte[8];
						System.arraycopy(nData, pos, tmSource, 0, 8);
						parameter.setTmSource(tmSource);
						pos += 8;

						plist.add(parameter);
					}
					break;

				case 0xFF:
					/* 循环参数 */
					for (int l = 0; l < pnu; l++) {
						Parameter parameter = new Parameter();
						/* 参数代号 2字节 */
						byte[] bsec = new byte[4];
						System.arraycopy(nData, pos, bsec, 2, 2);
						int tmNum = NumberBytes.bytesToInt(bsec,
								Constant.BYTEORDER);
						parameter.setTmNum(tmNum);
						pos += 2;

						// 参数类型 1字节
						byte[] stDataType = new byte[2];
						System.arraycopy(nData, pos, stDataType, 1, 1);
						int tmType = NumberBytes.bytesToInt(stDataType,
								Constant.BYTEORDER);
						parameter.setTmType(tmType);
						pos += 1;

						// 参数超限标志 1字节
						byte[] stFlag = new byte[2];
						System.arraycopy(nData, pos, stFlag, 1, 1);
						int tmLevel = NumberBytes.bytesToInt(stFlag,
								Constant.BYTEORDER);
						parameter.setTmLevel(tmLevel);
						pos += 1;

						/* 参数长度 2字节 */
						byte[] blen = new byte[4];
						System.arraycopy(nData, pos, blen, 2, 2);
						int tmLength = NumberBytes.bytesToInt(blen,
								Constant.BYTEORDER);
						pos += 2;

						/* 参数结果 ble字节 */
						byte[] tmSource = new byte[tmLength];
						System.arraycopy(nData, pos, tmSource, 0, tmLength);
						parameter.setTmSource(tmSource);
						pos += tmLength;
						plist.add(parameter);
					}
					break;
				}
				dgp.setParameters(plist);
				dgpList.add(dgp);
			}
			pp.setDelayGroupParameters(dgpList);
			ppList.add(pp);
		}
		dd.setPackageParameters(ppList);
		return dd;
	}

	@Override
	public byte[] pack(DelayData delayData) {

		byte[] dd = null;
		
		//总输出流
		ByteArrayOutputStream pp = new ByteArrayOutputStream();

		try {
			//包参数集合
			List<PackageParameter> pplList = delayData.getPackageParameters();
			
			// 包含的数据包个数 4字节
			byte[] num = NumberBytes.intToBytes(pplList.size(),
					Constant.BYTEORDER);
			pp.write(num);
			
			for (int i = 0,pkgSize = pplList.size(); i <pkgSize ; i++) {
				PackageParameter pParameter = pplList.get(i);
				// 数据类型(4字节)
				byte[] type = NumberBytes.intToBytes(pParameter.getType(),
						Constant.BYTEORDER);
				pp.write(type);

				// 信源设备编码(4字节)
				byte[] sou = NumberBytes.intToBytes(pParameter.getSou(),
						Constant.BYTEORDER);
				pp.write(sou);

				// 数据时间（积日）(4字节)
				byte[] jd = NumberBytes.intToBytes(pParameter.getJd(),
						Constant.BYTEORDER);
				pp.write(jd);

				// 数据时间（积秒）(8字节)
				byte[] t = NumberBytes.doubleToBytes(pParameter.getT(),
						Constant.BYTEORDER);
				pp.write(t);

				// 标志位 0x00表示定长 0xFF表示变长(2字节)
				byte[] flag = NumberBytes.shortToBytes(pParameter.getFlag(),
						Constant.BYTEORDER);
				pp.write(flag);
				
				//延时组参数集合
				List<DelayGroupParameter> dgParameter = pParameter
						.getDelayGroupParameters();
				//组输出流
				ByteArrayOutputStream dgp = new ByteArrayOutputStream();

				for (int j = 0,groupSize = dgParameter.size(); j < groupSize; j++) {
					
					DelayGroupParameter dGroupParameter = dgParameter.get(j);
					// 本组参数积日(4字节)
					byte[] gp_jd = NumberBytes.intToBytes(
							dGroupParameter.getGp_jd(), Constant.BYTEORDER);
					dgp.write(gp_jd);

					// 本组参数积秒(8字节)
					byte[] gp_t = NumberBytes.doubleToBytes(
							dGroupParameter.getGp_t(), Constant.BYTEORDER);
					dgp.write(gp_t);

					// 本组所有参数
					List<Parameter> pList = dGroupParameter.getParameters();
					
					// 本组参数个数(2字节)
					byte[] pnum1 = NumberBytes.shortToBytes(
							pList.size(), Constant.BYTEORDER);
					dgp.write(pnum1);
				
					ByteArrayOutputStream data = new ByteArrayOutputStream();

					switch (pParameter.getFlag()) {
					case 0x00:// 定长
						for (int k = 0, size = pList.size(); k < size; k++) {
							Parameter parameter = pList.get(k);
							// 参数代号 (2字节)
							byte[] sec = NumberBytes.shortToBytes(
									parameter.getTmNum(), Constant.BYTEORDER);
							data.write(sec);

							// 参数类型+参数超限标志 2字节
							byte[] state = parameter.getParameterState();
							data.write(state);

							// 参数结果(8字节)
							byte[] ref = new byte[8];
							int dataType = parameter.getTmType();
							if (dataType == 0) { // 浮点型
								ref = NumberBytes.doubleToBytes(Double
										.parseDouble(parameter.getTmValue()),
										Constant.BYTEORDER);
							} else if (dataType == 1) { // 整型数
								ref = NumberBytes.longToBytes(
										Long.parseLong(parameter.getTmValue()),
										Constant.BYTEORDER);
							} else if (dataType == 2) { // 无符号整型
								ref = NumberBytes.longToBytes(
										Long.parseLong(parameter.getTmValue()),
										Constant.BYTEORDER);
							}
							data.write(ref);

							// 参数原码(8字节)
							byte[] org = new byte[8];
							byte[] tempOrg = parameter.getTmSource();
							if (tempOrg.length > 8) {
								System.arraycopy(tempOrg, 0, org, 0, 8);
							} else if (tempOrg.length < 8) {
								System.arraycopy(tempOrg, 0, org, org.length
										- tempOrg.length, tempOrg.length);
							} else {
								org = tempOrg;
							}
							data.write(org);
						}
						break;
					case 0xFF:// 变长
						for (int k = 0, size = pList.size(); k < size; k++) {
							Parameter parameter = pList.get(j);
							// 参数代号 (2字节)
							byte[] se = NumberBytes.shortToBytes(
									parameter.getTmNum(), Constant.BYTEORDER);
							data.write(se);

							// 参数类型+参数超限标志 2字节
							byte[] state = parameter.getParameterState();
							data.write(state);

							// 参数长度(2字节)
							int tmLength = parameter.getTmSource().length;
							byte[] len = NumberBytes.shortToBytes(tmLength,
									Constant.BYTEORDER);
							data.write(len);

							// 参数结果(len字节)
							byte[] ref1 = parameter.getTmSource();
							data.write(ref1);
						}
						break;
					}
					data.writeTo(dgp);
					data.close();
				}
				// 数据包长度（含GNum） 2+所有参数总长度 2字节
				byte[] plen = NumberBytes.shortToBytes(dgp.size() + 2,
						Constant.BYTEORDER);
				pp.write(plen);

				// 组个数 2字节
				byte[] pnum = NumberBytes.shortToBytes(dgParameter.size(),
						Constant.BYTEORDER);
				pp.write(pnum);
				//将一包数据字节存入总缓存
				dgp.writeTo(pp);
				
				dgp.close();
			}
			dd = pp.toByteArray();
			pp.close();
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("组件[PackData][delayData]执行异常", e);
			}
		}
		return dd;
	}

	@Override
	public List<DelayData> multiUnpack(byte[] data) {
		return null;
	}

	@Override
	public byte[] multiPack(List<DelayData> list) {
		return null;
	}


}
