package acc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class List_themes {
	private String FilePath;
	public ArrayList<String> themes;
	
	public ArrayList<String> Lister_themes() throws IOException{
		int i=0;
		this.themes = new ArrayList<String>();
		boolean is_on_table_flag = false;
		try {
			BufferedReader buff = new BufferedReader(new FileReader(FilePath));
			String line;
			while ((line = buff.readLine()) != null) {
				String[] newline = line.split(";");	
				if(!themes.isEmpty()){
					//System.out.println("liste non vide");
					if(themes.contains(newline[1])){
						is_on_table_flag = true;
					}
					if(is_on_table_flag == false){
						//System.out.println("ajout");
						themes.add(newline[1]);
						i++;
					}
					else{
						is_on_table_flag = false;
					}			
				}
				else{
					//System.out.println("liste vide");
					themes.add(newline[1]);
				}
			}
			buff.close();
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}
		for(int k=0;k<themes.size();k++){		
				System.out.println(themes.get(k));
		}
		return themes;
	}
	
	public void toAFile() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter themeWriter = new PrintWriter("themes.txt", "UTF-8");
        for (String theme : this.themes) {
        	themeWriter.println(theme);
        }
        themeWriter.close();
	}
	
	public void set_FilePath (String FilePath){
		this.FilePath = FilePath;
	}
}
