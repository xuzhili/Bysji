package com.example.xuzhili.bysjstudio.widget;

import android.content.Context;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by zhili on 2015/11/10.
 */
public class ShareSDKUtils {

    public static void initShareSDK(Context context) {
        try {
            ShareSDK.initSDK(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param context  //上下文
     * @param title    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
     * @param tilteUrl // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
     * @param text     // text是分享文本，所有平台都需要这个字段
     * @param imageUrl // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
     * @param url      // url仅在微信（包括好友和朋友圈）中使用
     * @param comment  // comment是我对这条分享的评论，仅在人人网和QQ空间使用
     * @param site     // site是分享此内容的网站名称，仅在QQ空间使用
     * @param siteUrl  // siteUrl是分享此内容的网站地址，仅在QQ空间使用
     */
    public static void oneKeyShare(Context context, String platForm, String title, String tilteUrl, String text, String imageUrl, String url, String comment, String site, String siteUrl) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();


// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        if (platForm != null)
            oks.setPlatform(platForm);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        if (title != null)
            oks.setTitle(title);
        if (tilteUrl != null)
            oks.setTitleUrl(tilteUrl);
        if (text != null)
            oks.setText(text);
        if (comment != null)
            oks.setComment(comment);
        if (imageUrl != null)
            oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片
        if (url != null)
            oks.setUrl(url);

        if (site != null)
            oks.setSite(site);
        if (siteUrl != null)
            oks.setSiteUrl(siteUrl);

// 启动分享GUI
        oks.show(context);
    }

    public static void stopShare(Context context) {

        ShareSDK.stopSDK(context);

    }

    public static void shareTest(String platform, Context context) {
        ShareSDK.initSDK(context);
        OnekeyShare onekeyShare = new OnekeyShare();
        onekeyShare.setPlatform(platform);
//        onekeyShare.disableSSOWhenAuthorize();
//        onekeyShare.setTitle("直播");
//        onekeyShare.setTitleUrl("http://www.baidu.com");

        onekeyShare.setText("分享内容");

//        onekeyShare.setUrl(String.format(Urls.ARTICLE_CONTENT, articalCode));
//        onekeyShare.setComment("分享Comment");
        onekeyShare.setImageUrl("https://www.baidu.com/img/bd_logo1.png");
//        onekeyShare.setSite("EveryDay");
//        onekeyShare.setSiteUrl("http://www.baidu.com");

        onekeyShare.show(context);

    }


    static PlatformActionListener listener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(Platform platform, int i) {

        }
    };

    public static void shareWeibo(Context context, String text, String imageUrl) {

//        ShareSDK.initSDK(context);
        initShareSDK(context);
        OnekeyShare oks = new OnekeyShare();

        oks.setPlatform(SinaWeibo.NAME);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setText(text);
        oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片

// 启动分享GUI
        oks.show(context);

//        SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
//        sp.setText("测试文本");
//        sp.setImagePath("https://www.baidu.com/img/bd_logo1.png");
//
//        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//        weibo.setPlatformActionListener(listener); // 设置分享事件回调
//// 执行图文分享
//        weibo.share(sp);

    }

    public static void shareWeiboFaceTwitter(Context context, String platform, String text, String imageUrl, String url) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();

        oks.setPlatform(platform);

        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
        if (url != null) oks.setUrl(url);
        oks.setText(text);
        oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片

// 启动分享GUI
        oks.show(context);
    }

    public static void shareWechat(Context context, String title, String text, String imageUrl) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();

        oks.setPlatform(Wechat.NAME);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片
        oks.setUrl(imageUrl);

// 启动分享GUI
        oks.show(context);
    }

    public static void shareFaceBookWetTwitter(Context context, String platForm, String text, String imageUrl, String title, String url) {

//        ShareSDK.initSDK(context);
        initShareSDK(context);
        OnekeyShare oks = new OnekeyShare();

        oks.setPlatform(platForm);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        if (title != null)
            oks.setTitle(title);
//        if (url != null)
//            oks.setUrl(url);
        if (text != null)
            oks.setText(text);
//        if (imageUrl != null)
//            oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片

// 启动分享GUI
        oks.show(context);
    }
}
