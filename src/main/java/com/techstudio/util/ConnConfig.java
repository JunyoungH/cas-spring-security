package com.techstudio.util;
import java.io.*;
import java.util.*;

public final class ConnConfig
{
	static String filename = "cas.properties";
	static Properties prop;
	
	private static String CAS_PASSWORD_VALIDATE_DAYS = "cas.password.validate.days";
	
	static{	
		try {
			if ( prop!=null )prop=null;
		 	prop = new Properties();
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)); 
		}catch (Exception e){
			System.out.println("Web Configuration file not found");
			e.printStackTrace();
		}
	}
	
	public static void reloadFile(){
		try {
			synchronized(prop){
			 	prop = new Properties();
				prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)); 
			}
		}catch (IOException e){}
	}

	/**
	 * Retrieve the value of configuration
	 */
	public static String getProperty(String key) {
		synchronized(prop){
			return prop.getProperty(key); 
		}
	}
	
	public static Integer getCasPasswordValidateDays()
	{
		synchronized( prop )
		{
			return Integer.valueOf(prop.getProperty( CAS_PASSWORD_VALIDATE_DAYS )); 
		}
	}
	
}
