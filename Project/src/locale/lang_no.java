package locale;

import java.util.ListResourceBundle;

public class lang_no extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
        return contents;
    }
	private Object[][] contents = {
			{ "SAVE", "lagre"},
			{ "FARM", "gård"},
			{ "FARMER", "bonde"},
			{ "WELCOMEMSG", "Velkommen til Farm Tycoon"},
			{ "SAVEFOUND", "Vi har funnet ei tildligar lagred spil."},
			{ "ASKLOADGAME", "vill du laste in spillet? (ja/nei)"},
			{ "yes", new String[]{"j","ja"}},
			{ "no", new String[]{"n","nei"}},
	};
}
