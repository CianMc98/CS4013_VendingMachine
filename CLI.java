import java.util.*;
import java.io.*;
public class CLI
{
  public static void start()
		 {
	try{
				{ 
					VendingManager machine = new VendingManager();
					VendingMachineMenu menu = new VendingMachineMenu();
					menu.run(machine);
					
				}
		}
	catch (Exception e)
			 {
				 
			 }
			 
		 }
		 
		 }
		 