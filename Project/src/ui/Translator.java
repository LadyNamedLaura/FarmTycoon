package ui;

/**
 * @author Rigès De Witte, Simon Peeters,Barny Pieters,Laurens Van Damme
 * 
 *         wrapper around java.util.ResourceBundle to make its use more straight
 *         forward
 */
public abstract class Translator {
	private static final String BUNDLENAME = "locale.lang";
	private static java.util.ResourceBundle bundle = null;

	private static java.util.ResourceBundle getBundle() {
		if (bundle == null)
			bundle = java.util.ResourceBundle.getBundle(BUNDLENAME);
		return bundle;
	}

	/**
	 * Set the language to use.
	 * 
	 * @param lang
	 *            language to use
	 */
	public static void setLocale(String lang) {
		setLocale(new java.util.Locale(lang));
	}

	/**
	 * Set the language and region to use.
	 * 
	 * @param lang
	 *            language to use.
	 * @param region
	 *            region to use.
	 */
	public static void setLocale(String lang, String region) {
		setLocale(new java.util.Locale(lang, region));
	}

	/**
	 * Set the language, region and vendor to use.
	 * 
	 * @param lang
	 *            language to use.
	 * @param region
	 *            region to use.
	 * @param vendor
	 *            vendor to use.
	 */
	public static void setLocale(String lang, String region, String vendor) {
		setLocale(new java.util.Locale(lang, region, vendor));
	}

	/**
	 * Set the locale to use.
	 * 
	 * @param locale
	 *            locale to use.
	 */
	public static void setLocale(java.util.Locale locale) {
		bundle = java.util.ResourceBundle.getBundle(BUNDLENAME, locale);
	}

	/**
	 * Get the translated version of a string.
	 * 
	 * @param key
	 *            key of a string to get.
	 * @return the translated string.
	 */
	public static String getString(String key) {
		return getBundle().getString(key);
	}

	/**
	 * Get an translated array of strings.
	 * 
	 * @param key
	 *            key to the array of strings.
	 * @return the translated array of strings.
	 */
	public static String[] getStringArray(String key) {
		return getBundle().getStringArray(key);
	}

	/**
	 * Get an translated version of an object.
	 * 
	 * @param key
	 *            key to the object.
	 * @return the translated object.
	 */
	public static Object getObject(String key) {
		return getBundle().getObject(key);
	}

	public static boolean matchStringToArray(String key, String needle) {
		return java.util.Arrays.asList(getStringArray(key)).contains(
				needle.toLowerCase());
	}
}
