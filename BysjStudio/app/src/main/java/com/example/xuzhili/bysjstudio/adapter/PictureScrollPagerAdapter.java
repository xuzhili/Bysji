package com.example.xuzhili.bysjstudio.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xuzhili.bysjstudio.R;
import com.example.xuzhili.bysjstudio.activity.NewsActivity;
import com.example.xuzhili.bysjstudio.activity.PhotosActivity;
import com.example.xuzhili.bysjstudio.activity.SpecialActivity2;
import com.example.xuzhili.bysjstudio.bean.ArticleItme;
import com.example.xuzhili.bysjstudio.util.BVBitmapUtil;
import com.example.xuzhili.bysjstudio.util.ScreenSizeUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ArticleFragment 头部viewpager adapter
 *
 */
public class PictureScrollPagerAdapter extends PagerAdapter {

	List<ArticleItme> list;
	List<ImageView> imageViews = new ArrayList<>();
	Context context;
	DisplayImageOptions options;
	ImageLoader imageLoader;


	public PictureScrollPagerAdapter(List<ArticleItme> list, Context context) {
		this.list = list;
		this.context = context;
		int screenWidth = ScreenSizeUtil.getScreenWidth(context);
		options = new DisplayImageOptions.Builder()
				.displayer(new MyDisPlay(screenWidth, screenWidth / 100 * 56))
				.showImageForEmptyUri(R.drawable.def_image)
				.showImageOnFail(R.drawable.def_image).cacheInMemory(true)
				.cacheOnDisc(true).build();
		imageLoader = ImageLoader.getInstance();
	}

	public List<ArticleItme> getList() {
		return list;
	}

	public List<ImageView> getImages() {
		return imageViews;
	}

	public void setList(List<ArticleItme> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final ArticleItme topshow = list.get(position);
		ImageView image = new ImageView(context);
		image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (topshow.getModelid() == 10) {
					Intent intent = new Intent(context, SpecialActivity2.class);
					intent.putExtra("SpecialActivity", topshow);
					context.startActivity(intent);
				} else if (topshow.getModelid() == 2) {
					Intent intent = new Intent(context, PhotosActivity.class);
					intent.putExtra("SpecialActivity", topshow);
					context.startActivity(intent);
				} else if (topshow.getModelid() == 1) {
					Intent intent = new Intent(context, NewsActivity.class);
					intent.putExtra("SpecialActivity", topshow);
					context.startActivity(intent);
				}

			}
		});
		imageLoader.displayImage(topshow.getThumb(), image, options);
		((ViewPager) container).addView(image,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		imageViews.add(image);
		return image;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
		imageViews.remove(position);
	}

	class MyDisPlay implements BitmapDisplayer {
		private int newWidth;
		private int newHeight;

		public MyDisPlay(int newWidth, int newHeight) {
			this.newHeight = newHeight;
			this.newWidth = newWidth;
		}
		@Override
		public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
			Bitmap scaleImg = BVBitmapUtil
					.scaleImg(bitmap, newWidth, newHeight);
			imageAware.setImageBitmap(scaleImg);
		}
	}

}
