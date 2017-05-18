package kz.loungerIntertinment.FireHorse;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class MyConnection implements ServerConnecion{

	//private String url = "http://firehorse.j.dnr.kz/firehorse/";
	private String url = "http://localhost:8080/FireHorse/";
	
	MyConnection(){
		url = new Utilite().loadFile("/settings.txt").get(0);
		
    }
	
public void setText(String setter, String getter, String text){
	
	text = text.replace("'", "''");
	text = text.replace("\n", " ");
	

  	try {

  		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "set");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("setter", setter));
		params.add(new BasicNameValuePair("getter", getter));
		params.add(new BasicNameValuePair("text", text));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		    	
		    	Scanner sr = new Scanner(instream).useDelimiter("\\A");
		    	//System.out.println(sr.nextLine());
		        // do something useful
		    	//return sr.nextLine();
		    } finally {
		        instream.close();
		    }
		}

  	} catch (IOException e) {
		System.out.println(e);
	}

		
}

public String getText(String setter, String getter) {

	try {		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "get");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("setter", setter));
		params.add(new BasicNameValuePair("getter", getter));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    try {
		    	String line = new String();
		    	Scanner sr = new Scanner(instream).useDelimiter("\n");
		    	
		    	while(sr.hasNext()){
		    		line += sr.nextLine() + "\n";
		    	}
		    	return line;
		    } finally {
		        instream.close();
		    }
		}
	} catch (IOException e) {
		System.out.println(e);
		return null;
	}

	return null;
}



//get all user
public  List<String[]> getAllUser() {

	try {		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "getAllUser");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("data", "12345"));
		//params.add(new BasicNameValuePair("param-2", "Hello!"));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    Scanner sr = new Scanner(instream).useDelimiter("\n");
		    //String line = new String();
		    List<String[]> result = new ArrayList<String[]>();
		    try {		    	
		    			    	
		    	while(sr.hasNext()){
		    //		System.out.println(sr.nextLine());
		    		result.add(sr.nextLine().split(";"));
		    	}
		    	return result;
		    } finally {
		        instream.close();
		        sr.close();
		    }
		}
	} catch (IOException e) {
		System.out.println(e);
		return null;
	}

	return null;
    }


public  boolean login( String identifier, String password) {

	identifier = identifier.replace("'", "''");	
	password   = password.replace("'", "''");
	
	try {		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "login");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login", identifier));
		params.add(new BasicNameValuePair("password", password));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    Scanner sr = new Scanner(instream).useDelimiter("\n");
		    
		    try {
		    	String s = sr.nextLine();
		    	//System.out.println(s);
		    	return Boolean.valueOf(s);
		    } catch(Exception e){return false;}
		    finally {
		        instream.close();
		        sr.close();
		    }
		}
	} catch (IOException e) {
		System.out.println(e);
		return false;
	}

	return false;
    }


public  boolean doIHaveNewMessage( String setter) {

	setter = setter.replace("'", "''");	
	
	try {		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "doIHaveNewMessage");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("setter", setter));
	//	params.add(new BasicNameValuePair("password", password));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    Scanner sr = new Scanner(instream).useDelimiter("\n");
		    
		    try {
		    	String s = sr.nextLine();
		    	//System.out.println(s);
		    	return Boolean.valueOf(s);
		    } finally {
		        instream.close();
		        sr.close();
		    }
		}
	} catch (IOException e) {
		System.out.println(e);
		return false;
	}

	return false;
    }

public  boolean signUp( String name, String identifier, String password) {

	identifier = identifier.replace("'", "''");	
	password   = password.replace("'", "''");
	
	try {		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "signup");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("login", identifier));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("name", name));
		
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    InputStream instream = entity.getContent();
		    Scanner sr = new Scanner(instream).useDelimiter("\n");
		    
		    try {
		    	String s = sr.nextLine();
		    	//System.out.println(s);
		    	return Boolean.valueOf(s);
		    } finally {
		        instream.close();
		        sr.close();
		    }
		}
	} catch (IOException e) {
		System.out.println(e);
		return false;
	}

	return false;
    }


}
