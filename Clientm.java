import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
class Clientm
{
	public static void main(String dt[])
	{
		
		Socket skt=null;
	    DataInputStream dis=null;
		DataOutputStream dos=null;
		FileInputStream fis=null;
		BufferedReader br=null;
		InputStreamReader isr=null;
		FileOutputStream fos=null;
		try
		{
			isr= new InputStreamReader(System.in);
			br= new BufferedReader(isr);
			System.out.println("enter ur username");
			String us=br.readLine();
			System.out.println("enter ur password");
			String p=br.readLine();
			if(us.contentEquals("Ayush")==true && p.contentEquals("pingli")==true)
			{
				System.out.println("You can now access the server");
			}
			else
			{
				System.out.println("Invalid Choice");
				System.exit(0);
			}
			

			do{
				skt=new Socket("127.0.0.1",1234);
			    System.out.println("Connected to server");
				System.out.println("\t1.Downloading");
				System.out.println("\t2.Uploading");
				System.out.println("\t3.Exit");
				System.out.println("-----------------------------------------------");
				dos=new DataOutputStream(skt.getOutputStream());
				dis=new DataInputStream(skt.getInputStream());
				System.out.println("Enter your choice");
				String choice=br.readLine();
				dos.writeUTF(choice);
				switch(choice)
				{
					case "1":
					{
						String publ="D:\\ftpserver1\\server\\public\\";
						File fo=new File(publ);
						String a[]=fo.list();
						for(int i=0;i<a.length;i++)
						{
							System.out.println(a[i]);
						}
						System.out.println("enter the file u want to downlaod");
						String filename=br.readLine();
						dos.writeUTF(filename);
						if(dis.readBoolean())
						{
						System.out.println("downloading begins");
						fos=new FileOutputStream(filename);
						int c;
						byte b[]=new byte[4096];
						while((c=dis.read(b))!=-1)
						{
							fos.write(b,0,c);
						}
				
						System.out.println("downloading complete");
						}
						else
				
						{
							System.out.println("file not found");
						}
						skt.close();
						break;
						
					}
					
					case "2":
					{
						System.out.println("in which folder u want to upload the file");
						System.out.println("\t1 Public");
						System.out.println("\t1 Private");
						System.out.println("enter ur choice");
						String ch=br.readLine();
						dos.writeUTF(ch);
						System.out.println("enter the file which u want to upload");
					
						String u=br.readLine();
						dos.writeUTF(u);
					    System.out.println("uploading begins");
					    fis=new FileInputStream(u);//now server will put tht filename in object of file input stream reader which will read data from file and write that into socket thru dos .
						int c;
						byte b[]=new byte[4096];
						while((c=fis.read(b))!=-1)//reading data until we get -1
						{
							dos.write(b,0,c);//writing tht data onto output stream
						}
							System.out.println("uploading complete");
							skt.close();
						    break;
						  
					}
					case "3":
					{
						System.exit(0);
					}
					default:
					{
						System.out.println("invalid choice");
						break;
					}
				}
			
			
			
        }while(true);
				   }
				   catch(Exception e)
						{
						System.out.println(e);
						 }
					finally
					{
					try{
						br.close();
						isr.close();
						fis.close();
						fos.close();
						dis.close();
						dos.close();
				
						}
						catch(Exception e1)
						
					
						{
							System.out.println(e1);
						}
					}
}
}