package com.example.xuzhili.bysjstudio.bean;

/**
 * Created by xuzhili on 16/4/7.
 */
public class Me {

    public String username;
    public String avatar_url;
    public String session_key;
    public String bio;
    public String city;
    public String uid;

    public Me(String username, String avatar_url, String session_key, String bio, String city, String uid) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.session_key = session_key;
        this.bio = bio;
        this.city = city;
        this.uid = uid;
    }
}
