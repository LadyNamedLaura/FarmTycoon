package locale;

import java.util.ListResourceBundle;

public class lang_en extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
        return contents;
    }
	private Object[][] contents = {
			{ "SAVE", "save"},
			{ "FARM", "farm"},
			{ "FARMER", "farmer"},
			{ "WELCOMEMSG", "Welcome to Farm Tycoon"},
			{ "SAVEFOUND", "a previously saved game was found."},
			{ "ASKLOADGAME", "do you want to load this game? (yes/no)"},
			{ "yes", new String[]{"y","Y","Yes","yes","YES"}},
			{ "no", new String[]{"n","N","No","no","NO"}},
	};
}