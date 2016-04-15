package com.example.xuzhili.bysjstudio.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.xuzhili.bysjstudio.R;
import com.example.xuzhili.bysjstudio.util.ScreenUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoPreviewActivity extends AppCompatActivity {

    private PhotoView photoView;
    private String image_url;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
        initView();

        image_url = getIntent().getStringExtra("image_url");
        if (image_url == null) return;

        photoView = ((PhotoView) findViewById(R.id.photo_view));
        ImageLoader.getInstance().displayImage(image_url, photoView);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();

            }
        });

    }

    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.tool_bar_preview);
        setSupportActionBar(toolbar);
        toolbar.setTitle("图片预览");
        toolbar.setTitleTextColor(getResources().getColor(R.color.ff_666));
        toolbar.setBackgroundColor(getResources().getColor(R.color.action_bar));
        ScreenUtils.compat(this, ScreenUtils.colorBurn(getResources().getColor(R.color.action_bar)));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
