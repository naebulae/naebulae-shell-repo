package org.naebulae.controllers;

import org.naebulae.JapiMeaning;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class drop_mongo_dbController extends BaseController 
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
	
	@JapiMeaning("The database to drop")
	public String __db = "";
	
	@JapiMeaning("The output file for result")
	public String __out = "";
	
	@JapiMeaning("To show or not to show the result file")	
	public String __show = "";
	
	@Override
	public void processRequest() 
	{
		System.out.println("drop mongo database [" + __db + "]");
		
		MongoClient mongo = new MongoClient(__host, __port);
		
		MongoDatabase db = mongo.getDatabase(__db);
		db.drop();
		
		mongo.close();						
	}

}
