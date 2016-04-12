package cn.univs.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;

import cn.univs.app.R;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoPreviewActivity extends Activity {

    private PhotoView photoView;
    private String image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);

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
}
