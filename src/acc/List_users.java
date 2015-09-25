package acc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class List_users {
	private String FilePath;
	public ArrayList<String> users;
	
	public ArrayList<String> Lister_utilisateurs() throws IOException{		
		int i=0;
		this.users = new ArrayList<String>();
		boolean is_on_table_flag = false;
		try {
			BufferedReader buff = new BufferedReader(new FileReader(FilePath));
			String line;
			while ((line = buff.readLine()) != null) {
				String[] newline = line.split(";");	
				if(!users.isEmpty()){
					//System.out.println("liste non vide");
					if(users.contains(newline[1])){
						is_on_table_flag = true;
					}
					if(is_on_table_flag == false){
						//System.out.println("ajout");
						users.add(newline[1]);
						i++;
					}
					else{
						is_on_table_flag = false;
					}			
				}
				else{
					//System.out.println("liste vide");
					users.add(newline[1]);
				}
			}
			buff.close();
		} catch (FileNotFoundException e) {		
			throw new RuntimeException();
		}
		for(int k=0;k<users.size();k++){		
				System.out.println(users.get(k));
		}
		return users;
	}
	
	public void toAFile() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter userWriter = new PrintWriter("users.txt", "UTF-8");
        for (String user : this.users) {
        	userWriter.println(user);
        }
        userWriter.close();
	}
	
	public void set_FilePath (String FilePath){
		this.FilePath = FilePath;
	}
}
