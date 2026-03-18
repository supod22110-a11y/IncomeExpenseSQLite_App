
package theme;

import java.util.prefs.Preferences;

public class ThemeManager {
    private static final Preferences prefs =
            Preferences.userRoot().node(ThemeManager.class.getName());

    private static String currentTheme =
            prefs.get("theme", "LIGHT");

    public static String getTheme() {
        return currentTheme;
    }

    public static void setTheme(String theme) {
    if (!"DARK".equals(theme) && !"LIGHT".equals(theme)) {
        theme = "LIGHT";
    }
    currentTheme = theme;
    prefs.put("theme", theme);
}
    
}
