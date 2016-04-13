package com.example.xuzhili.bysjstudio.widget;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xuzhili.bysjstudio.R;
import com.example.xuzhili.bysjstudio.bean.ShareContent;
import com.example.xuzhili.bysjstudio.util.AnimationUtilsLive;

import java.util.HashMap;

import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by zhili on 2015/11/10.
 */
public class SharePopupWindow extends PopupWindow implements View.OnClickListener {

    private Context context;
    private View contentView;

    TextView tvTitle;
    RelativeLayout rlWetChat;
    RelativeLayout rlWetChatComment;
    RelativeLayout rlSina;
    LinearLayout ll_ChaneseShare;

    LinearLayout shareView;

    private HashMap<String, String> shareParams;
    private boolean isSharePhoto;
    private ShareContent shareContent;
    private boolean isHasShareToFollowers;

    public SharePopupWindow(Context context) {
        super(context);
        this.context = context;
        initData();


        eventListner();
    }

    public SharePopupWindow(Context context, ShareContent shareContent) {
        super(context);
        this.context = context;
        this.shareContent = shareContent;
        initData();

        eventListner();
    }

    public void setShareParams(HashMap<String, String> shareParams) {

        this.shareParams = shareParams;

    }

    public ShareContent getShareContent() {
        return shareContent;
    }

    public void setShareContent(ShareContent shareContent) {
        this.shareContent = shareContent;
    }

    private void initData() {
        this.setFocusable(true);
        this.setTouchable(true);
        this.setOutsideTouchable(true);
//        this.setAnimationStyle(R.style.anim_share_popup_window);
        this.setBackgroundDrawable(new ColorDrawable(0xb0000000));

        contentView = LayoutInflater.from(context).inflate(R.layout.share_view, null);
        initViewId(contentView);
        setContentView(contentView);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initViewId(View contentView) {

        tvTitle = ((TextView) contentView.findViewById(R.id.tvTitle));
        rlWetChat = ((RelativeLayout) contentView.findViewById(R.id.rlWetchat));
        rlWetChatComment = ((RelativeLayout) contentView.findViewById(R.id.rlWetChatComment));
        rlSina = ((RelativeLayout) contentView.findViewById(R.id.rlSina));
        ll_ChaneseShare = ((LinearLayout) contentView.findViewById(R.id.ll_Chanese_share));
        shareView = ((LinearLayout) contentView.findViewById(R.id.ll_share_view));
    }

    public void setIsHasBackGround(boolean isHasBackGround) {
        if (isHasBackGround) {
            this.setBackgroundDrawable(new ColorDrawable(0xb0000000));
        } else {
            this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        }
    }

    private void eventListner() {

        rlSina.setOnClickListener(this);
        rlWetChat.setOnClickListener(this);
        rlWetChatComment.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (shareContent != null) {
            switch (v.getId()) {
                case R.id.rlWetchat:
                    ShareSDKUtils.shareFaceBookWetTwitter(context, Wechat.NAME, shareContent.getText(), shareContent.getImage_url(), shareContent.getTitle(), shareContent.getUrl());
                    break;
                case R.id.rlWetChatComment:
                    ShareSDKUtils.shareFaceBookWetTwitter(context, WechatMoments.NAME, shareContent.getText(), shareContent.getImage_url(), shareContent.getTitle(), shareContent.getUrl());
                    break;
                case R.id.rlSina:
                    ShareSDKUtils.shareWeibo(context, shareContent.getTitle() + " " + shareContent.getText() + " " + shareContent.getUrl(), shareContent.getImage_url());
                    break;
                case R.id.tvTitle:
                    return;
            }
            this.dismiss();
        }
    }

    public void share(String platForm) {

        ShareSDKUtils.oneKeyShare(context, platForm, shareParams.get("title"), shareParams.get("titleUrl"), shareParams.get("text"), shareParams.get("imageUrl"), shareParams.get("url"), shareParams.get("comment"), shareParams.get("site"), shareParams.get("siteUrl"));

    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
            this.update();
            shareView.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AnimationUtilsLive.TranslationClass translationClass = new AnimationUtilsLive.TranslationClass(new Point(0, shareView.getMeasuredHeight()), new Point(0, 0), 150);
                    shareView.startAnimation(translationClass);
                    shareView.setVisibility(View.VISIBLE);
                }
            }, 10);
        } else {
            this.dismiss();
        }
    }

    @Override
    public void dismiss() {
        AnimationUtilsLive.TranslationClass translationClass = new AnimationUtilsLive.TranslationClass(new Point(0, 0), new Point(0, shareView.getMeasuredHeight()), 150);
        shareView.startAnimation(translationClass);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharePopupWindow.super.dismiss();
            }
        }, 145);
    }
}