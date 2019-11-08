package cn.dabzhuye.mainUI;

import java.awt.Component;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UiUtil {
	public UiUtil() {
		// TODO Auto-generated constructor stub
	}
	
	//根据名称获取对应的组件
	public static Component  getCompon(Container comp,String name) {
		Component returncom = null;
		int count =comp.getComponentCount();
	
		if(count > 0 &&  returncom == null) {
			for(int i=0;i<count;i++) {
				Component comptem = comp.getComponent(i);
				
				String thatname =comptem.getName();
				
				if(thatname != null && thatname.equals(name)) {
					
					returncom =  comptem;
					
					return returncom;
					
				}else {
					if(comptem instanceof Container) {
						returncom =  getCompon((Container)comptem,name);
						if(returncom != null){
							return returncom;
						}
					}
						
				}
			}
		}
		return returncom;
	}
	
	//格式化剩余时间--
	public static String getTimeFormat(int timestamp) {
		int day = timestamp/86400;
		int hour = (timestamp-day*86400)/3600;
		int min = (timestamp-day*86400-hour*3600)/60;
		int second = timestamp-day*86400-hour*3600-min*60;
		
		return day+"天"+hour+"时"+min+"分"+second+"秒";
	}
	
	//请求地址
	public static String sendRequest(String requesturl) {
		String string ="";
		try {
			//建立连接
			URL url = new URL(requesturl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			
			//获取输入流
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bf = new BufferedReader(inputStreamReader);
			String result = "";
			String data = bf.readLine();
			while( data != null) {
				result += data;
				data = bf.readLine();
			}
			return result;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
		
	}
	
	/**
     * @Title: unicodeEncode 
     * @Description: unicode编码
     * @param string
     * @return
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }
    
    /**
     * @Title: unicodeDecode 
     * @Description: unicode解码
     * @param str
     * @return
     */
    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }


	
}
