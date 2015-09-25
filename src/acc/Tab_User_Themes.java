package acc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeMap;

public class Tab_User_Themes {
	private String FilePath;
	//public String[] users;
	public Map<String, Integer> concordance_hm;
	public TreeMap TabTrie;
	public String temp;
	
	public Map<String, Integer> Comptage_occurence() throws IOException{		
		this.concordance_hm = new HashMap<String, Integer>();
		try {
			BufferedReader buff = new BufferedReader(new FileReader(FilePath));
			String line;
			while ((line = buff.readLine()) != null) {
				String[] newline = line.split(";");	
				newline[0] = newline[1].concat(" - "+newline[2]);
				if(!concordance_hm.isEmpty()){
					//System.out.println("liste non vide");
					if(concordance_hm.get(newline[0]) != null){
						
						/*concordance_hm.put(newline[0], concordance_hm.get(newline[0]) + 1);*/
						//System.out.println("increment");
						int l = concordance_hm.get(newline[0]);
						concordance_hm.remove(newline[0]);
						concordance_hm.put(newline[0], l+1);
					}
					else{
						//System.out.println("ajout");
						concordance_hm.put((newline[0]),1);
					}			
				}
				else{
					//System.out.println("liste vide");
					concordance_hm.put(newline[0], 1);
				}
			}
			buff.close();
		} catch (FileNotFoundException e) {		
			throw new RuntimeException();
		}
		this.TabTrie = new TreeMap<String, Integer>(this.concordance_hm);
		Set listKeys=TabTrie.keySet(); 
		Iterator iterateur=listKeys.iterator();
		while(iterateur.hasNext())
		{
			Object key= iterateur.next();
			System.out.println (key+" => "+TabTrie.get(key));
			String[] desc = key.toString().split(" - ");
			int val = (Integer) TabTrie.get(key);
		}
		return TabTrie;
	}
	
	public void ToAFile() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter matriceWriter = new PrintWriter("matrice.txt", "UTF-8");

        Iterator<String> keyIterator = this.TabTrie.keySet().iterator();
        Integer[][] cpt;
        while (keyIterator.hasNext()) {
        	Object key= keyIterator.next();
			//System.out.println (key+" => "+TabTrie.get(key));
			String[] desc = key.toString().split(" - ");
			int val = (Integer) TabTrie.get(key);
			if(this.temp != null){
				if(!desc[0].equals(temp)){
					System.out.println(desc[0]+"new name");
					matriceWriter.print("\n"+this.TabTrie.get(key)+"  ");
					this.temp = desc[0];
				}
				else{
					matriceWriter.print(this.TabTrie.get(key)+"  ");
					this.temp = desc[0];
					System.out.println(desc[0]+"same name");
				}
			}
			else{
				matriceWriter.print(this.TabTrie.get(key)+"  ");
				this.temp = desc[0];
				System.out.println(desc[0]+"init");
			}
         
        }
        matriceWriter.close();
	}
	
	public void set_FilePath (String FilePath){
		this.FilePath = FilePath;
	}
}
