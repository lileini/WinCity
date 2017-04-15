package wincity.litao.com.util;

import android.content.Context;
import android.graphics.Typeface;

public class CustomFontsLoader {


	public static final int DINPRO_BOLD = 0;
	public static final int DINPRO_LIGHT = 1;
	public static final int DINPRO_MEDIUM = 2;
	public static final int DINPRO_REGULAR = 3;

	private static final int NUM_OF_CUSTOM_FONTS = 4;

	private static boolean fontsLoaded = false;

	private static Typeface[] fonts = new Typeface[NUM_OF_CUSTOM_FONTS];

	private static String[] fontPath = { "fonts/dinpro_bold.ttf",
		"fonts/dinpro_light.ttf","fonts/dinpro_medium.ttf","fonts/dinpro_regular.ttf"
	};

	/**
	 * Returns a loaded custom font based on it's identifier.
	 * 
	 * @param context
	 *            - the current context
	 * @param fontIdentifier
	 *            = the identifier of the requested font
	 * 
	 * @return Typeface object of the requested font.
	 */
	public static Typeface getTypeface(Context context, int fontIdentifier) {
		
		if (fontIdentifier>=NUM_OF_CUSTOM_FONTS) {
			return null;
		}
		
		if (!fontsLoaded) {
			try {
				loadFonts(context);
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.e("font load error",fontIdentifier+"error happned"+e.getMessage());
			}
		}
		return fonts[fontIdentifier];
	}

	public static void loadFonts(Context context) throws Exception{
		for (int i = 0; i < NUM_OF_CUSTOM_FONTS; i++) {
			fonts[i] = Typeface.createFromAsset(context.getAssets(),fontPath[i]);
	}
		fontsLoaded = true;

	}
}