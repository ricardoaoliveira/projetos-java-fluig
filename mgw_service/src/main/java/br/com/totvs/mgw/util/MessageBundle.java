package br.com.totvs.mgw.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageBundle {
	
	private static Map<String, ResourceBundle> bundles = new HashMap<String,ResourceBundle>();
	
	private static ResourceBundle loadBundle(String idioma) {
		ResourceBundle messages = ResourceBundle.getBundle(
				"messages", new Locale(idioma));
		
		bundles.put(idioma, messages);
		return messages;
	}
	
	private static ResourceBundle getResourceBundle(String idioma) {
		
		ResourceBundle rb = bundles.get(idioma);
		
		if (rb == null) {
			rb = loadBundle(idioma);
		}
		
		return rb;
	}
	
	public static String getText(String key, String idioma) {
		return getResourceBundle(idioma).getString(key);
	}
}
