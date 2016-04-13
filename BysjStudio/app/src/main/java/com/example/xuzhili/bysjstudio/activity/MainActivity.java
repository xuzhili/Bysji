package com.example.xuzhili.bysjstudio.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuzhili.bysjstudio.MyHttpAPIControl;
import com.example.xuzhili.bysjstudio.ParseUtils;
import com.example.xuzhili.bysjstudio.R;
import com.example.xuzhili.bysjstudio.adapter.NewsFragmentPagerAdapter;
import com.example.xuzhili.bysjstudio.bean.Category;
import com.example.xuzhili.bysjstudio.bean.UnivsDataBase;
import com.example.xuzhili.bysjstudio.fragment.ArticlePageFragment;
import com.example.xuzhili.bysjstudio.util.ScreenSizeUtil;
import com.example.xuzhili.bysjstudio.util.SharedPMananger;
import com.example.xuzhili.bysjstudio.util.UserUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends ActionBarActivity implements PlatformActionListener {
    private static final int MA_REQUESTCODE = 1001;
    private MainActivity mInstance;
    private ViewPager mViewpager;
    //    private ColumnHorizontalScrollView mNaviga_scroll;
//    private LinearLayout mNavigation;
    private int columnSelectIndex = 0;
    private int mScreenWidth = 0;
    private View mContentView;
    private View mFailure_view;
    private SlidingTabLayout slidingTabLayout;
    private DrawerLayout drawerLayout;
    private RelativeLayout rlMyInfo, rlSave, rlLoginPhone, rlLoginWeibo;
    private TextView tvUsername;
    private CircleImageView cirAvatar;
    private LinearLayout llLeft, llSliding;
    private Handler handler = new Handler();
    private Toolbar toolbar;

    //    final UMSocialService mLogin = UMServiceFactory
//            .getUMSocialService("com.umeng.login");
    private NewsFragmentPagerAdapter viewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInstance = this;
        mScreenWidth = ScreenSizeUtil.getScreenWidth(mInstance);
        setContentView(R.layout.activity_main);
        initView();

        eventListner();
    }

    private void eventListner() {

        rlMyInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserUtils.getSessionKey() == null) {
                    Toast.makeText(MainActivity.this, "您还未登陆", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                    startActivity(intent);
                }
            }
        });

        rlSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserUtils.getSessionKey() == null) {
                    Toast.makeText(MainActivity.this, "您还未登陆", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SaveActivity.class);
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.left).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.add_naviga_itme_bt).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(mInstance,
                                ChannelActivity.class), MA_REQUESTCODE);
                    }
                });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                colorChange((ArticlePageFragment) viewpagerAdapter.getItem(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rlLoginWeibo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    loginSharedSdk(SinaWeibo.NAME);
            }
        });

//        rlLoginWeibo.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mLogin.doOauthVerify(MainActivity.this, SHARE_MEDIA.SINA, new SocializeListeners.UMAuthListener() {
//                    @Override
//                    public void onError(SocializeException e, SHARE_MEDIA platform) {
//                    }
//
//                    @Override
//                    public void onComplete(Bundle value, SHARE_MEDIA platform) {
//                        if (value != null && !TextUtils.isEmpty(value.getString("uid"))) {
//                            Toast.makeText(MainActivity.this, "授权成功.", Toast.LENGTH_SHORT).show();
//                            mLogin.getPlatformInfo(MainActivity.this, SHARE_MEDIA.SINA, new SocializeListeners.UMDataListener() {
//                                @Override
//                                public void onStart() {
//                                    Toast.makeText(MainActivity.this, "获取平台数据开始...", Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onComplete(int status, Map<String, Object> info) {
//
//                                    if (status == 200 && info != null) {
////                                        Toast.makeText(MainActivity.this, "getInfo", Toast.LENGTH_SHORT).show();
////                                        final StringBuilder sb = new StringBuilder();
////                                        Set<String> keys = info.keySet();
////                                        for (String key : keys) {
////                                            Log.i("TestData", "TestData:" + key);
////                                            Log.i("TestData", "TestData:" + info.get(key).toString());
////                                            sb.append(key + "=" + info.get(key).toString() + "\r\n");
////                                        }
//                                        Me me = new Me(info.get("screen_name").toString()
//                                                , info.get("profile_image_url").toString()
//                                                , info.get("access_token").toString()
//                                                , info.get("description").toString()
//                                                , info.get("location").toString()
//                                                , info.get("uid").toString());
//                                        UserUtils.resetMyInfo(MainActivity.this, me);
//                                        loadUserINfo();
//                                    } else {
//                                        Toast.makeText(MainActivity.this, "erro", Toast.LENGTH_SHORT).show();
//                                        Log.e("TestData", "TestData:" + "发生错误：" + status);
//                                    }
//                                }
//
//                            });
//                        } else {
//                            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA platform) {
//                    }
//
//                    @Override
//                    public void onStart(SHARE_MEDIA platform) {
//                    }
//                });
//
//            }
//        });

    }

    private void loginSharedSdk(String name) {

        try {
            Platform platform = ShareSDK.getPlatform(this, name);
            if (platform != null) {
                if (platform.isValid()) {
                    platform.removeAccount(true);
                }
                ShareSDK.deleteCache();
                ShareSDK.removeCookieOnAuthorize(true);
                platform.setPlatformActionListener(this);
                platform.SSOSetting(false);
//            platform.showUser(null);
                platform.authorize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 界面颜色的更改
     */
    @SuppressLint("NewApi")
    private void colorChange(ArticlePageFragment articlePageFragment) {
        // 用来提取颜色的Bitmap
        ImageView imageView = articlePageFragment.getImageView();
        if (imageView == null || imageView.getDrawable() == null)
            return;
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant == null) return;
                /* 界面颜色UI统一性处理,看起来更Material一些 */
                llSliding.setBackgroundColor(vibrant.getRgb());
                slidingTabLayout.setTextSelectColor(vibrant.getTitleTextColor());
                slidingTabLayout.setTextUnselectColor(vibrant.getTitleTextColor());
                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明
                slidingTabLayout.setIndicatorColor(colorBurn(vibrant.getRgb()));

                toolbar.setBackgroundColor(vibrant.getRgb());
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }


    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }


    private void initView() {
        cirAvatar = (CircleImageView) findViewById(R.id.cir_avatar);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setLogo(R.drawable.icon_copper);
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        rlMyInfo = (RelativeLayout) findViewById(R.id.rl_myinfo);
        rlLoginPhone = (RelativeLayout) findViewById(R.id.rl_login_phone);
        rlLoginWeibo = (RelativeLayout) findViewById(R.id.rl_login_weibo);
        rlSave = (RelativeLayout) findViewById(R.id.rl_save);
        llLeft = (LinearLayout) findViewById(R.id.left);
        llSliding = (LinearLayout) findViewById(R.id.ll_sliding);
        drawerLayout = ((DrawerLayout) findViewById(R.id.drag_layout));

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);
        mContentView = findViewById(R.id.content);
        mFailure_view = findViewById(R.id.failure_view);
        findViewById(R.id.reload).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getChanneldata();
            }
        });
        initChannel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initChannel() {
        try {
            String channels = SharedPMananger.getString(
                    SharedPMananger.UNIVS_CHANNEL_LIST, "");
            columnSelectIndex = 0;
            if ("".equals(channels)) {
                getChanneldata();
            } else {
                initNaigation(channels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initNaigation(String json) {
        try {
            Type tp = new TypeToken<UnivsDataBase<Category>>() {
            }.getType();
            UnivsDataBase<Category> ss = (UnivsDataBase<Category>) ParseUtils
                    .Gson2Object(json, tp);
            if (ss != null && ss.isState()) {
                ArrayList<Category> naviga = ss.getData();
                ArrayList<Category> myChannel = new ArrayList<Category>();
                for (Category category : naviga) {
                    if (category.isSelect()) {
                        myChannel.add(category);
                    }
                }
                setViewPagerV(myChannel);
//                setNavigation(myChannel);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void getChanneldata() {
        MyHttpAPIControl.newInstance().getNewsCategory(
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                        mFailure_view.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        mFailure_view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onSuccess(int statusCode, String content) {
                        super.onSuccess(statusCode, content);
                        Type tp = new TypeToken<UnivsDataBase<Category>>() {
                        }.getType();
                        UnivsDataBase<Category> ss = (UnivsDataBase<Category>) ParseUtils
                                .Gson2Object(content, tp);
                        if (ss != null && ss.isState()) {
                            ArrayList<Category> naviga = ss.getData();
                            for (int i = 0; i < naviga.size(); i++) {
                                if (i < 5) {
                                    naviga.get(i).setSelect(true);
                                }
                            }
                            String object2Json = ParseUtils.Object2Json(ss);
                            SharedPMananger.putString(
                                    SharedPMananger.UNIVS_CHANNEL_LIST,
                                    object2Json);
                            initNaigation(object2Json);
                        }
                    }

                });
    }


    private void setViewPagerV(ArrayList<Category> naviga) {
        if (naviga.size() == 0) return;
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpagerAdapter = new NewsFragmentPagerAdapter(
                getSupportFragmentManager(), naviga);
        mViewpager.setAdapter(viewpagerAdapter);
        mViewpager.setOffscreenPageLimit(8);
        String[] titles = new String[naviga.size()];
        for (int i = 0; i < naviga.size(); i++) {
            titles[i] = naviga.get(i).getCatname();
        }
//        mViewpager.setCurrentItem(0);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingLayout);
        slidingTabLayout.setViewPager(mViewpager, titles);
        Log.d("MainActivity", "slidingTabLayout.getCurrentTab():" + slidingTabLayout.getCurrentTab());
//        mViewpager.setOnPageChangeListener(new SimpleOnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int arg0) {
//                selectTab(arg0);
//            }
//
//        });
    }

//    /**
//     * 选择的Column里面的Tab
//     */
//    private void selectTab(int tab_postion) {
//        columnSelectIndex = tab_postion * 2;
//        // for (int i = 0; i < mNavigation.getChildCount(); i++) {
//        View checkView = mNavigation.getChildAt(tab_postion * 2);
//        int k = checkView.getMeasuredWidth();
//        int l = checkView.getLeft();
//        int i2 = l + k / 2 - mScreenWidth / 2;
//        // rg_nav_content.getParent()).smoothScrollTo(i2, 0);
//        mNaviga_scroll.smoothScrollTo(i2, 0);
//        // mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
//        // mItemWidth , 0);
//        // }
//        // 判断是否选中
//        for (int j = 0; j < mNavigation.getChildCount(); j++) {
//            View checkView1 = mNavigation.getChildAt(j);
//            boolean ischeck;
//            if (j == tab_postion * 2) {
//                ischeck = true;
//            } else {
//                ischeck = false;
//            }
//            checkView1.setSelected(ischeck);
//        }
//    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (arg0 == MA_REQUESTCODE
                && arg1 == SettingChannelActivity.SCA_RESULTCODE) {
            columnSelectIndex = 0;
            initChannel();
        }
    }

    @Override
    public void onBackPressed() {
//
//        new AlertDialog.Builder(MainActivity.this).setTitle("提示").setPositiveButton("确  认", new AlertDialog.OnClickListener() {
//
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        }).setMessage("确认退出?").create().show();
        AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle("提示").setNegativeButton("取消", null).setMessage("确认退出?").setPositiveButton("确  认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
        loadUserINfo();
    }


    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);


    }

    private void loadUserINfo() {


        if (UserUtils.getSessionKey() != null) {
            rlLoginWeibo.setVisibility(View.GONE);
            rlLoginPhone.setVisibility(View.GONE);
            ImageLoader.getInstance().displayImage(UserUtils.getMyAvatar(), cirAvatar);
            tvUsername.setText(UserUtils.getMyUsername());
        } else {
            rlLoginWeibo.setVisibility(View.VISIBLE);
            rlLoginPhone.setVisibility(View.VISIBLE);
            cirAvatar.setImageResource(R.drawable.avatar);
            tvUsername.setText("未登录");
        }

    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        final String access_token = platform.getDb().getToken();
        String site_user_id = platform.getDb().getUserId();
//        Log.i(TAG,site_user_id + ":site_user_id");
//        Log.i(TAG,access_token + ":access_token");
        String site_user_name = platform.getDb().getUserName();
        String site_user_avatar_usrl = platform.getDb().getUserIcon();
        String site_user_bio = "";
        String wechat_union_id = platform.getDb().get("unionid");
        String refresh_token = platform.getDb().get("refresh_token");

        Log.d("MainActivity", "platform:" + platform.getDb().exportData());
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
