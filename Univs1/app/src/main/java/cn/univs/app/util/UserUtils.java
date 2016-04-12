package cn.univs.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import cn.univs.api.bean.Me;
import cn.univs.app.UnivsApplication;

/**
 * Created by xuzhili on 16/1/4.
 */
public class UserUtils {

    public static String getCity() {
        SharedPreferences sp = UnivsApplication.getInstance().getSharedPreferences("user", UnivsApplication.MODE_PRIVATE);
        String myId = sp.getString("city", null);
        return myId;
    }

    public static String getBio() {
        SharedPreferences sp = UnivsApplication.getInstance().getSharedPreferences("user", UnivsApplication.MODE_PRIVATE);
        String myId = sp.getString("bio", null);
        return myId;
    }

    public static String getMyAvatar() {
        SharedPreferences sp = UnivsApplication.getInstance().getSharedPreferences("user", UnivsApplication.MODE_PRIVATE);
        String myAvatar = sp.getString("avatar", null);
        return myAvatar;
    }

    public static void clearAllData() {
        SharedPreferences sp = UnivsApplication.getInstance().getSharedPreferences("user", UnivsApplication.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    public static String getMyUsername() {
        SharedPreferences sp = UnivsApplication.getInstance().getSharedPreferences("user", UnivsApplication.MODE_PRIVATE);
        String username = sp.getString("username", null);
        return username;
    }

    public static String getSessionKey() {
        SharedPreferences sp = UnivsApplication.getInstance().getSharedPreferences("user", UnivsApplication.MODE_PRIVATE);
        String sessionKey = sp.getString("session_key", null);
        return sessionKey;
    }

    public static void resetMyInfo(Context context, Me me) {

        if (me != null) {
            SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("session_key", me.session_key);
            editor.putString("username", me.username);
            editor.putString("avatar", me.avatar_url);
            editor.putString("city", me.city);
            editor.putString("bio", me.bio);
            editor.apply();
        }

    }

}
