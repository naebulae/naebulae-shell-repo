package org.naebulae;

import org.naebulae.controllers.BaseController;

public class Shell 
{
	public static void main(String[] args) 
	throws Exception
	{
//		args = ("drop-mongo-db "
//				+ "-db test "
//				+ "-port 27017 "
//				+ "-out  $/out-check1.txt "
//				+ "-show true").split("\\s+");
		
		if(args.length == 0) 
		{
			System.out.println("AppMain: no arguments");
			return;
		}
		
		BaseController cl = findTargetController(args[0], BaseController.class); 

		String nk = null, vk = null;
		for(int k=1; k+1<args.length; )
		try
		{
			nk = "_" + args[k++].trim().replace("-", "_");
			vk = args[k++];
			
			System.out.println("Accepting " + nk + " -> " + vk);
			cl.setInputParam(nk, vk);
		}
		catch(java.lang.NoSuchFieldException xp) 
		{ 
			System.out.println("AppMain: Invalid parameter " + nk);
			cl.printHelp();
			return;
		}

		cl.printHeader();
		cl.processRequest();
		cl.printFooter();
	}

	private static BaseController findTargetController(String cmd, Class<?> root)
	{
		cmd = root.getPackage().getName() + "." + cmd.trim().replace("-", "_") + "Controller";
		System.out.println("Executing " + cmd);
		
		Class<?> cl = null;
		try { cl = Class.forName(cmd); }
		catch(Exception xp) { 
			xp.printStackTrace(); 
			return null; 
		}
		
		Object res = null;
		try { res = cl.newInstance(); }
		catch(Exception xp) 
		{
			xp.printStackTrace(); 			
			return null; 
		}
		
		if(res instanceof BaseController) return (BaseController)res;
				
		return null;
	}

	
//	args = "war-sumtar -show true   -war $/migrate-tabngo.jar   -cmp $/out-check1.txt     -out  $/out-done1.txt".split("\\s+"); 
//	args = "folder-sum -show true   -in c:/opt/apps   -out  $/out-check2.txt".split("\\s+"); 
//	args = "folder-sumtar -show true   -in c:/opt/apps   -cmp $/out-check2.txt    -out  $/out-done2.txt".split("\\s+"); 
//	args = "mongo-sum -show true  -db data-trenzi105   -out $/out-check3.txt".split("\\s+");
//	args = "mongo-sumtar -show true  -db data-trenzi105   -cmp $/out-check3.txt -out $/out-done3.txt".split("\\s+");
//	args = ("mongo-dsum -show true  -db TabnGo -out $/out-dump.txt  "
//			+ "-rfrom http://192.168.100.60:7070/ "
//			+ "-rto http://hoang.dinh.hung.com:7070/").split("\\s+");

//	args = CommandTarget.debugParams("cmp-files "
//	+ "-show true "
//	+ "-out $/out1.txt "
//	+ "-sfile $/migrate-tabngo.jar "
//	+ "-tfile $/migrate-tabngo.jar");
	

}
