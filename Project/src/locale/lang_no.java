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
	};
}
