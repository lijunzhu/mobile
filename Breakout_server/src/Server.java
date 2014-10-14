import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Server {
	
	static String leader[][] = new String[100][3];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		Socket s = null;
		DataInputStream din = null;
		DataOutputStream dout = null;
				
		try{
			ss = new ServerSocket(8787);
			System.out.println("wait for connection...");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		while(true){
			try{
				s = ss.accept();
				System.out.println("connection established.");
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				String msg = din.readUTF();
				System.out.println("msg: " + msg);				
				
				if(msg.equals("Get leaderboard.")){
					//dout.writeUTF(outMsg("Leaderboard.txt"));	
					for(int i = 0; i < 10; i++){
						dout.writeUTF(leaderboard("Leaderboard.txt",i,1));
						dout.writeUTF(leaderboard("Leaderboard.txt",i,2));
					}
					
				}
				
				if(msg.equals("Get 2nd level.")){
					dout.writeUTF(outMsg("1.txt"));
				}
				
				if(msg.equals("Get 3rd level.")){
					dout.writeUTF(outMsg("2.txt"));
				}
				
				if(msg.equals("update")){
					String string = din.readUTF();
					String name = din.readUTF();
					String score = din.readUTF();
					int i = Integer.parseInt(string) - 1;	
					update_leader(i,name,score);
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}	
			finally{
				try{
					if(din != null){
						din.close();
					}
					
					if(dout != null){
						dout.close();
					}
					
					if(s != null){
						s.close();
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}

		}
	}
	
	 public static String outMsg(String filename) {  
	        StringBuffer result = new StringBuffer();  
	        FileReader fileReader;  
	        try {  
	            
	            fileReader = new FileReader(new File(filename));  
	            BufferedReader bufferedFileReader = new BufferedReader(fileReader);  
	            String line = null;  
	            
	            while ((line = bufferedFileReader.readLine()) != null) {  
	                result.append(line + "\n");  
	            }  
	            fileReader.close();  
	        } catch (FileNotFoundException e) {  
	       
	        } catch (IOException e) {  
	            
	        }  
	        return result.toString();  
	 }
	 
	 public static String leaderboard(String filename, int i, int j){
		 
		 Scanner inputStream = null;
			//PrintWriter outputStream = null;
			
			try{
				inputStream = new Scanner(new FileInputStream(filename));

			}catch(FileNotFoundException e){}
			
			int row = 0;
			while(inputStream.hasNextLine()){
				 String []ss=inputStream.nextLine().split(" ");
				 
				 leader[row][0] = ss[0];
				 leader[row][1] = ss[1];
				 leader[row][2] = ss[2];
				    
				 row++;
			}
			
			inputStream.close();
			return leader[i][j];
			
	 }
	 
	 public static void update_leader(int i, String name, String score){
		
		for(int j = i+1; j < 10; j++ ){
			leader[j][1] = leader[j-1][1];
		}
		 
		leader[i][1] = name;
		leader[i][2] = score;
		
		PrintWriter outputStream = null;
		 
		try {
			 outputStream = new PrintWriter(new FileOutputStream("Leaderboard.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int n = 0; n < 10; n++){
			outputStream.println(leader[n][0]+" "+leader[n][1]+" "+leader[n][2]);
		}
		
		outputStream.close();
		 
	 }

}
