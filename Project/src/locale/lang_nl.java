package locale;

import java.util.ListResourceBundle;

public class lang_nl extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
        return contents;
    }
	private Object[][] contents = {
			{ "SAVE", "opslaan"},
			{ "FARM", "boerderij"},
			{ "FARMER", "boer"},
	};
}
