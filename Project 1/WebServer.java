/* Ritu Bidyut Bhawal
 * 1001387294
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class WebServer {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int port = 6789;

		// Establish the listen socket.
		@SuppressWarnings("resource")
		ServerSocket initial = new ServerSocket(port);
		System.out.println("Server initiated ...");
		try {
			// Process HTTP service requests in an infinite loop.
			while (true) {
				// Listen for a TCP connection request.
				Socket connectionSocket = initial.accept();

				// Construct an object to process the HTTP request message.
				HttpRequest request = new HttpRequest(connectionSocket);

				// Create a new thread to process the request.
				Thread thread = new Thread(request);

				// Start the thread.
				thread.start();
			}
		} catch (Exception e) {
			System.out.println("Exception" + e);
		}

}}


final class HttpRequest implements Runnable {
	final static String CRLF = "\r\n";
	Socket socket;

	// Constructor
	HttpRequest(Socket socket) throws Exception {
		this.socket = socket;
	}

	public void run() {
		try {
			processRequest();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void processRequest() throws Exception
	{
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());

		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//reads the input data

		 PrintWriter pw = new PrintWriter(out, false);
		String requestLine = br.readLine();// get /path/file.html version of http
		
		// Display the request line.
		System.out.println();
		System.out.println(requestLine);
		
		 InetAddress inetAddr = InetAddress.getLocalHost();
	        //System.out.println(inetAddr);
	        byte[] addr = inetAddr.getAddress();
	        String ipAddr = "";
	        String socketType="TCP";
	        for (int i = 0; i < addr.length; i++) {
	            if (i > 0) {
	            ipAddr += ".";
	         }
	        ipAddr += addr[i] & 0xFF;
	        
	        }
	        String hostname = inetAddr.getHostName();
	       	OutputStream os;
	        os=socket.getOutputStream();
	        System.out.println("The output stream is"+os);
			
		
		// HERE WE NEED TO DEAL WITH THE REQUEST
		// Extract the filename from the request line.
		StringTokenizer tokens = new StringTokenizer(requestLine);// this is a input method with deliminators
		tokens.nextToken(); // skip over the method, which should be "GET"
		String fileName = tokens.nextToken();

		// Prepend a "." so that file request is within the current directory.
		fileName ="."+ fileName;

		String headerLine = null;
		while ((headerLine = br.readLine()).length() != 0) {
		System.out.println(headerLine);
		}

		//Open the requested file.
		File f=new File("C:/Users/Ritu bhawal/workspace1/CN1/src/");	
		FileInputStream fis = null;
		boolean fileExists = true;
			
		try{	
		 fis = new FileInputStream(f+fileName);
		} catch (FileNotFoundException e) {
	 fileExists = false;
		}

		//Construct the response message.
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
	try
	{
		if (fileExists) {
		 statusLine = "HTTP/1.0 200 OK" + CRLF; //common success message
		 contentTypeLine = "Content-type: " + contentType( fileName ) + CRLF;
		 }//content info

		else {
		 statusLine = "HTTP/1.0 404 Not Found" + CRLF;//common error message
		 contentTypeLine = "Content-type: " + "text/html" + CRLF;//content info
		 entityBody = "<HTML>" +
		  "<HEAD><TITLE>Not Found</TITLE></HEAD>" +
		  "<BODY>Not Found</BODY></HTML>";
		}}
		catch(Exception e)
		{
		System.out.println("Exception:"+e );	
		}
		

	 pw.print("Server-Host:"+socket.getLocalSocketAddress()+"\r\n");
	   pw.print("Server-IP Addrr:" +ipAddr+"\r\n");
	   pw.print("Server-IP Host:"+hostname+"\r\n");
	 
	   pw.print("Socket-Type:"+socketType+"\r\n");
	   pw.print("Socket-Port:"+socket.getLocalPort()+"\r\n");
	
	   pw.print("\r\n");
	   pw.print("The HTTP Response is:\r\n");
	   pw.print("_____________________\r\n");
	   pw.print("\r\n");
	   pw.print("\r\n");
	   pw.print(statusLine);
	   pw.print("\r\n");
	   pw.print(contentTypeLine);
	   pw.print("\r\n");
	  
	   pw.print("_____________________\r\n\r\n");
	   out.writeBytes(CRLF);
	   pw.flush();
		if (fileExists) {
		 sendBytes(fis, os);
		
		fis.close();
		
		} else {
		 out.writeBytes(entityBody);
		}
		
		System.out.println("**********");
		System.out.println(fileName);//print out file request to console
		System.out.println("**********");
		
		
		out.close();
		br.close();
		socket.close();
		pw.close();



		}
	

	
	private static String contentType(String fileName) {
		if (fileName.endsWith(".htm") || fileName.endsWith(".html")) {
			return "text/html";
		}
		if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
			return "image/jpeg";
		}
		if (fileName.endsWith(".gif")) {
			return "image/gif";
		}
		return "application/octet-stream";
	}
	
	private static void sendBytes(FileInputStream fis, OutputStream ots) throws Exception {
		// Construct a 1K buffer to hold bytes on their way to the socket.
		byte[] buffer = new byte[1024];
		int bytes = 0;

		// Copy requested file into the socket's output stream.
		while ((bytes = fis.read(buffer)) != -1)// read() returns minus one,
												// indicating that the end of
												// the file
		{
			ots.write(buffer, 0, bytes);
		}
}
}