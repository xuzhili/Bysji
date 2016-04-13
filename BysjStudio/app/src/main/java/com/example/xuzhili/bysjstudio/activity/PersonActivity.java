package com.example.xuzhili.bysjstudio.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xuzhili.bysjstudio.R;
import com.example.xuzhili.bysjstudio.util.UserUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PersonActivity extends Activity {

    private static final String TAG = "PersonActivity";
    private String bio = "";
    LinearLayout ll;
    LinearLayout llBack;
    RelativeLayout rlAvatar;
    ImageView ivAvatar;
    TextView tvUsername;
    TextView tvBio;
    RelativeLayout rlUsername;
    RelativeLayout rlBio;
    TextView tvMyTv;

    String avatar;
    private boolean isDestroyed;
    private RelativeLayout rlLoginout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initView();

        eventlistner();
    }

    private void eventlistner() {


        findViewById(R.id.ll_person_list_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rlLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PersonActivity.this)
                        .setTitle("退出")
                        .setCancelable(true)
                        .setMessage("您确定要退出吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                logout();
                            }
                        })
                        .setNegativeButton("取消", null).show();
            }
        });
    }

    private void logout() {
        UserUtils.clearAllData();
//        clearDB();
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {


        tvUsername = ((TextView) findViewById(R.id.tv_person_username));
        tvBio = ((TextView) findViewById(R.id.tv_person_bio));
        tvMyTv = ((TextView) findViewById(R.id.tv_my_tv));
        ivAvatar = (ImageView) findViewById(R.id.civ_person_avatar);
        rlLoginout = (RelativeLayout) findViewById(R.id.rl_profile_logout);

        String myAvatar = UserUtils.getMyAvatar();
        if (myAvatar != null) {
            ImageLoader.getInstance().displayImage(myAvatar, ivAvatar);
        } else {
            ivAvatar.setImageResource(R.drawable.avatar);
        }
        String myUsername = UserUtils.getMyUsername();
        if (myUsername != null)
            tvUsername.setText(myUsername);
        String bio = UserUtils.getBio();
        if (bio != null)
            tvBio.setText(bio);
        String city = UserUtils.getCity();
        if (city != null)
            tvMyTv.setText(city);

    }


}
