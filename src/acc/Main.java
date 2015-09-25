package acc;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List_themes themes_C = new List_themes();
		List_users users_C = new List_users();
		Tab_User_Themes tab_C = new Tab_User_Themes();
		themes_C.set_FilePath("Log-clients-themes.txt");
		users_C.set_FilePath("Log-clients-themes.txt");
		tab_C.set_FilePath("Log-clients-themes.txt");
		
		try {
			themes_C.Lister_themes();
			themes_C.toAFile();
			
			users_C.Lister_utilisateurs();
			users_C.toAFile();
			
			tab_C.Comptage_occurence();
			tab_C.ToAFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		

	}

}
