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

import javax.xml.crypto.Data;


public class UiUtil {
	public UiUtil() {
		// TODO Auto-generated constructor stub
	}
	
	//�������ƻ�ȡ��Ӧ�����
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
	
	//��ʽ��ʣ��ʱ��
	public static String getTimeFormat(int timestamp) {
		int day = timestamp/86400;
		int hour = (timestamp-day*86400)/3600;
		int min = (timestamp-day*86400-hour*3600)/60;
		int second = timestamp-day*86400-hour*3600-min*60;
		
		return day+"��"+hour+"ʱ"+min+"��"+second+"��";
	}
	
	//�����ַ
	public static String sendRequest(String requesturl) {
		String string ="";
		try {
			//��������
			URL url = new URL(requesturl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			
			//��ȡ������
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
	
}