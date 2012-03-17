package locale;

import java.util.ListResourceBundle;

public class lang_fr extends ListResourceBundle {

	@Override
	public Object[][] getContents() {
		return contents;
	}

	private Object[][] contents = { { "SAVE", "save" }, { "FARM", "farm" },
			{ "FARMER", "farmer" }, };
}
