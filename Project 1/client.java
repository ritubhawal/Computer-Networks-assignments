/* Ritu Bidyut Bhawal
 * 1001387294
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket socket;
		int port=6789;
		String ip="localhost";
		 String socketType="TCP";
		 String protocolUsed;
	       
	        InetAddress inetAddr = InetAddress.getLocalHost();
	        //System.out.println(inetAddr);
	        byte[] address = inetAddr.getAddress();
	        String ipAddress = "";
	        for (int i = 0; i < address.length; i++) {
	            if (i > 0) {
	            ipAddress += ".";
	         }
	        ipAddress += address[i] & 0xFF;
	        
	        }
	        String hostname = inetAddr.getHostName();
	       
	        System.out.println("Client is running!!"); 
	        System.out.print("Enter the url of the web object in the form(eg http://localhost:6789/my.html):"); 
	        Scanner sc=new Scanner(System.in);
	        String urladdr=sc.nextLine();
	        URL u = new URL(urladdr);
	        protocolUsed=u.getProtocol();
			socket=new Socket(ip,port);
			
			 OutputStream output = socket.getOutputStream();
		        PrintWriter pw = new PrintWriter(output, false);
		         pw.print("GET " + u.getFile() + " HTTP/1.1\r\n");
		         pw.print("Host:" +socket.getLocalSocketAddress() + "\r\n");
		      
		         pw.print("Client-Host Name:"+hostname+"\r\n");
		         pw.print("Client-IP Addr:"+ipAddress+"\r\n");
		         pw.print("Socket_Type:"+socketType+"\r\n");
		        
		         pw.print("Client-Port:"+socket.getPort()+"\r\n");
		         pw.print("Client-Protocol Name:"+protocolUsed+"\r\n");
		    
		       
		         pw.print("\r\n");
		         pw.flush();
		         InputStream is = socket.getInputStream();
		       
		         InputStreamReader isr = new InputStreamReader(is);
		         BufferedReader br = new BufferedReader(isr);
		         int c;
		        while ((c = br.read()) != -1) {
		           System.out.print((char) c);
		         }
		        
		        br.close();
		        socket.close();
		        pw.close();


            
	
}
}
