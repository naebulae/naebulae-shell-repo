package org.naebulae.controllers;

import org.naebulae.JapiMeaning;

import com.mongodb.MongoClient;

public class drop_mongo_tableController extends BaseController 
{
	@JapiMeaning("To specify the mongo host")
	public String __host = "localhost";
	
	@JapiMeaning("To specify the mongo port")
	public int __port = 27017;
	
	public void __port_setter(Object arg) 
	{ 
		System.out.println("Setting port to " + arg);
		__port = Integer.parseInt(arg.toString()); 
	}
	
	@JapiMeaning("To specify the database")
	public String __db = "";
	
	@JapiMeaning("To specify the table to drop")
	public String __table = "";
	
	@Override
	public void processRequest() 
	{
		System.out.println("drop mongo table [" + __table + "] in database ["+__db+"]");
		
		MongoClient mongo = new MongoClient(__host, __port);
		
		mongo.getDatabase(__db)
			.getCollection(__table)
			.drop();
		
		mongo.close();						
	}

}
