package com.example.xuzhili.bysjstudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.xuzhili.bysjstudio.MyHttpAPIControl;
import com.example.xuzhili.bysjstudio.R;
import com.example.xuzhili.bysjstudio.adapter.ArticleAdapter;
import com.example.xuzhili.bysjstudio.bean.ArticleItme;
import com.example.xuzhili.bysjstudio.dao.ArticleItemDao;
import com.example.xuzhili.bysjstudio.dao.DatabaseHelper;
import com.example.xuzhili.bysjstudio.util.ScreenUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import library.PullToRefreshListView;

public class SaveActivity extends AppCompatActivity {

    private PullToRefreshListView mPullListView;
    private ArticleAdapter articleAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        initView();

        initData();

        eventListner();

        loadData();
    }

    private void loadData() {


        ArticleItemDao articleItemDao = new ArticleItemDao(this);
        articleItemDao.getDao(DatabaseHelper.getHelper(SaveActivity.this));
        List<ArticleItme> all = articleItemDao.findAll();
        if (all == null) {
            Toast.makeText(this, "没有任何收藏", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<ArticleItme> datas = new ArrayList<>();
            for (ArticleItme articleItme : all) {
                datas.add(articleItme);
            }
            articleAdapter.addData(datas);
        }
    }

    private void initData() {

        articleAdapter = new ArticleAdapter(this);
        mPullListView.setAdapter(articleAdapter);
    }

    private void eventListner() {

        mPullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ArticleItme item = articleAdapter.getItem(position - 1);
                MyHttpAPIControl.newInstance().getTongji(item.getContentid()+"",
                        new AsyncHttpResponseHandler() {
                            //要调用统计接口进行统计
                        });


                if (item.getModelid() == 10) {
                    Intent intent = new Intent(SaveActivity.this,
                            SpecialActivity2.class);
                    intent.putExtra("SpecialActivity", item);
                    startActivity(intent);
                } else if (item.getModelid() == 2) {
                    Intent intent = new Intent(SaveActivity.this,
                            PhotosActivity.class);
                    intent.putExtra("SpecialActivity", item);
                    startActivity(intent);
                } else if (item.getModelid() == 1) {
                    Intent intent = new Intent(SaveActivity.this,
                            NewsActivity.class);
                    intent.putExtra("SpecialActivity", item);
                    startActivity(intent);
                }
            }
        });

    }

    private void initView() {

        mPullListView = (PullToRefreshListView) findViewById(R.id.mylistView);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("个人收藏");
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
