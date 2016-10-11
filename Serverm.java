import java.net.ServerSocket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
class Serverm
{
	public static void main(String dt[])
	{
		ServerSocket sskt=null;
		Socket skt=null;
	    DataInputStream dis=null;
		DataOutputStream dos=null;
		FileInputStream fis=null;
		FileOutputStream fos=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try
		{	
			do
			{
				sskt=new ServerSocket(1234);
				System.out.println("waiting for client........");
				skt=sskt.accept();
				System.out.println("connected to client");
				dis=new DataInputStream(skt.getInputStream());
				dos=new DataOutputStream(skt.getOutputStream());
				String choice1=dis.readUTF();
			    switch(choice1)
			    {
				   case "1":
				   {
				  	String filename=dis.readUTF();//filename which we want to dwnload is sent by client and is read from socket by dis
					File f=new File(filename);//we hve put tht filename into a file so that we can acess d properties of tht file
					if(f.exists())//now server will check if tht file is existing or not
					{
						dos.writeBoolean(true);//if it is existing then it will send true on socket output stream
						fis=new FileInputStream(filename);//now server will put tht filename in object of file input stream reader which will read data from file and write that into socket thru dos .
						int c;
						byte b[]=new byte[4096];
						while((c=fis.read(b))!=-1)//reading data until we get -1
						{
							dos.write(b,0,c);//writing tht data onto output stream
						}
					}
					else
					{
						dos.writeBoolean(false);
					}
					skt.close();
					sskt.close();
					break;
			   }
				
				case "2":
				{
				   String ch=dis.readUTF();
					String u=dis.readUTF();
					switch(ch)
					{
						case "1":
						{
							String pub="D:\\ftpserver1\\server\\public\\";
							fos=new FileOutputStream(pub+u);
							int c;
							byte b[]=new byte[4096];
							while((c=dis.read(b))!=-1)
							{
								fos.write(b,0,c);
							}
							fos.close();
							skt.close();
							sskt.close();
							break;
						}
						case "2":
						{
							String pri="D:\\ftpserver1\\server\\private\\";
							fos=new FileOutputStream(pri+u);
							int c;
							byte b[]=new byte[4096];
							while((c=dis.read(b))!=-1)
							{
								fos.write(b,0,c);
							}
							fos.close();
							skt.close();
							sskt.close();
							break;
						}
					}
					
					
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