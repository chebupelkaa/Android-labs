package lab.lab1.activities;

import android.app.Activity;
import android.content.Intent;

import lab.lab1.R;

public class Themes {
    private static String _theme;

    public static void changeTheme(Activity activity, String theme) {
        _theme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void setTheme(Activity activity) {
        if (_theme != null) {
            switch (_theme) {
                case "default": {
                    activity.setTheme(R.style.Base_Theme_Default);
                    break;
                }
                case "dark": {
                    activity.setTheme(R.style.Base_Theme_Dark);
                    break;
                }
                case "color": {
                    activity.setTheme(R.style.Base_Theme_Color);
                    break;
                }
            }
        }
    }
}
