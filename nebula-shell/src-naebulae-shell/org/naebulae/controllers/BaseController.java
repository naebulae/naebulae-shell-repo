package org.naebulae.controllers;

import java.lang.reflect.Field;

public abstract class BaseController 
{
	public void setInputParam(String nk, String vk)
	throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException 
	{
		Field f = this.getClass().getField(nk);
		
		if(f.getType() == int.class) f.set(this, Integer.parseInt(vk));
		else if(f.getType() == double.class) f.set(this, Double.parseDouble(vk));
		else f.set(this, vk);
	}

	public abstract void processRequest();

	public void printHeader() 
	{
		System.out.println("---------BEGIN/CTRL--------");
	}
	
	public void printFooter() 
	{
		System.out.println("---------END/CTRL--------");
	}

	public void printHelp() 
	{
	}


}
