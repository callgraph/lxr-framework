package com.wuz.serv.core.ws.impl;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

//import org.jruby.CompatVersion;
//import org.jruby.Ruby;
//import org.jruby.RubyInstanceConfig;
//import org.jruby.embed.ScriptingContainer;
//import org.jruby.javasupport.JavaEmbedUtils;
//import org.jruby.runtime.builtin.IRubyObject;

import java.util.List;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;

import com.ibm.db2.jcc.resources.Resources;
import com.synchrobit.synchrojmx.tools.adaptor.interceptor.Invocable;

//�ں˷�����ͨ����������ȡSVGͼ�ķ���SVGImgService

public class chooseService {
	

	
	public String getPost(String url){
		chooseService chooServ=new chooseService();
		//String result = null;
		String fn="";
		int timeN=0;
		String content=null;
		String filename="plat.js";
		if(chooServ.FileisExit(filename))
		{
			content=chooServ.ReadFile(filename);
		}
		File file=new File("/usr/local/share/cg-rtl/lxr/version/192.169.1.35");
		FileWriter fw=null;
		BufferedWriter writer=null;
		try {
			fw=new FileWriter(file);
			writer=new BufferedWriter(fw);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	
	public String ReadFile(String fname){
		String pathname="/usr/local/share/cg-rtl/lxr/templates/html/html_head_btn_files/"+fname;
		URL url = null;
		String content=null;
		File filename=new File(pathname);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generatcatch block
			e1.printStackTrace();
			return filename.getPath();
		}
		String line=null;
		int num=1;
		try {
			while((line=reader.readLine())!=null)
			{
			String testline="dsy.add";
			num++;
			if(line.contains(testline))
			{
				char[]c=line.toCharArray();
				if(c[11]==',')
					content=line.substring(14);
				break;
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cont[];
		cont=content.split(",");
		int length=cont.length;
		int con1len=cont[0].length();
		String result=null;
		cont[0]=cont[0].substring(0,con1len-2);
		result=cont[0]+"&&";
		
		int contlen;
		for(int i=1;i<length-1;i++)
		{
			contlen=cont[i].length();
			cont[i]=cont[i].substring(1,contlen-2);
			result+=cont[i]+"&&";
		}
		int conlastlen=cont[length-1].length();
		cont[length-1]=cont[length-1].substring(1,conlastlen-5);
		result+=cont[length-1];
		return result;
	}

  

		
	public boolean FileisExit(String filename){   //�ļ���
	      
		String urlString="http://192.168.1.35/lxr/templates/html/html_head_btn_files/"+filename;
		URL url = null;
	
		try {
			url = new URL(urlString);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		URLConnection conn = null;
		try {
			conn = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	
	     HttpURLConnection urlcon = null;
		try {
			urlcon = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	     String message = urlcon.getHeaderField(0);//�ļ����ڡ�HTTP/1.1 200 OK�� �ļ������� ��HTTP/1.1 404 Not Found��
	     if (StringUtils.isNotEmpty(message) && message.startsWith("HTTP/1.1 200")) {
	   //����
	    	 System.out.println("exit");
				return true;
	     }
		
		else{
			System.out.println("not exit");
			return false;
		}
		
	}		
}
