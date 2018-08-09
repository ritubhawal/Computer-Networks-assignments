Ritu Bidyut Bhawal
1001387294

INSTRUCTIONS:

IDE: Eclipse Neon.3
Language:Java

The project consists of two parts:-
client.java: Consists of single threaded file that request for file
WebServer.java: Services multiple clients simultaneously


Run WebServer first and then run the client.

Running WebServer.java:
javac WebServer.java
java WebServer 6789

Running client.java
javac client.java
java client 
Client is running!!
Enter the url of the web object in the form(eg http://localhost:6789/my.html):http://localhost:6789/my.html

 
(Note:Enter the url in the client output console in the form http://localhost:6789/filename.ext or http://127.0.0.1:6789/filename.ext where 127.0.0.1 is loop back address as server and client runs on the same host.
The number 6789 is the port number to bind the client socket with the server.)

I have passed the html file path in the File object in WebServer.java as:

File f=new File("C:/Users/Ritu bhawal/workspace1/CN1/src/");

On the browser we can test the server by entering the url http://localhost:6789/filename.ext or http://127.0.0.1:6789/filename.ext
to get the file contents and the server details.

References:
[1]https://www.mkyong.com/java/how-to-read-file-in-java-fileinputstream/
[2] Computer Networking Top Down Approach 6th Edition 
[3] https://examples.javacodegeeks.com/core-java/net/socket/send-http-post-request-with-socket/
[4] https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html 
[5] https://www.techopedia.com/definition/438/clientserver-architecture
[6] https://www.shubhsblog.com/programming/multithreaded-webserver-java.html
[7] https://www.youtube.com/watch?v=G4Z2PQfOHdY
