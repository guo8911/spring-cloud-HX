package com.hx.ssxs.protocol;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.hx.ssxs.data.XmlData;


public class XmlDataImpl implements IPack<XmlData>{
	
	private static Log log = LogFactory.getLog(XmlDataImpl.class);
	
	@Override
    public XmlData unpack(byte[] data) {

        if (log.isInfoEnabled()) {
            log.info("组件[XmlDataImpl] 方法[unpack]开始执行");
        }

        SAXBuilder builder = new SAXBuilder();
        Document doc = null;
        ByteArrayInputStream input = null;
        List<Map<String, String>> contain = new ArrayList<Map<String, String>>();
        XmlData xmlBean = new XmlData();
        Map<String, String> nodes = new HashMap<String, String>();
        try {
            input = new ByteArrayInputStream(data);
            doc = builder.build(input);
            Element rootEl = doc.getRootElement();
            nodes.put("#1", rootEl.getName());

            List<Element> list = rootEl.getChildren();
            for (Element e1 : list) {
                nodes.put(e1.getName(), e1.getValue());
            }
            contain.add(nodes);

            xmlBean.setContain(contain);

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("xml类型数据解析发生异常", e);
            }
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return xmlBean;
    }

	@Override
	public byte[] pack(XmlData xData) {

		Map<String, String> xMap =  xData.getContain().get(0);
		String content ="";
		for (String key : xMap.keySet()) {
			if (!key.equals("#1") && !key.equals("#2")) {
				content = content + "<"+key+">"+xMap.get(key)+"</"+key+"> ";
			}
		}
		String str = "";
		if (xMap.get("#2")!=null) {
			 str = "<"+xMap.get("#1")+">"+"<"+xMap.get("#2")+">"+ content +"</"+xMap.get("#2")+">" +"</"+xMap.get("#1")+">";
		}else {
			 str = "<"+xMap.get("#1")+">  " + content +"  </"+xMap.get("#1")+">";
		}
	
		byte[] strs = null;
		try {
			strs = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("不支持的编码格式！");
		}
		return strs;
	}

	@Override
	public List<XmlData> multiUnpack(byte[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] multiPack(List<XmlData> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
