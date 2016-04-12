package cn.univs.app.dao;//package com.zbd.dao;

import android.content.Context;
import android.database.sqlite.SQLiteException;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import cn.univs.api.bean.ArticleItme;


public class ArticleItemDao extends BaseDaoInterface<ArticleItme> {

    public ArticleItemDao(Context context) {
        super(context);
    }

    @Override
    public Dao getDao(DatabaseHelper helper) {
        try {
            return helper.getDao(ArticleItme.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e + ":无法创建MessageDao");
        }
    }

    @Override
    public void add(ArticleItme zzbChatMessage) {
        try {
//            int id= (int) zzbChatMessage.id_;
//            ZZBChatMessage zzbChatMessag = mDao.queryForId(id);
//            if(zzbChatMessag!=null){
//                mDao.update(zzbChatMessage);
//            }else{
//            }
            mDao.createOrUpdate(zzbChatMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ArticleItme zzbChatMessage) {
        try {
            mDao.delete(zzbChatMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int deleteByConversationId(long conversationId) {
        try {
            DeleteBuilder<ArticleItme,Long> deleteBuilder = mDao.deleteBuilder();
            deleteBuilder.where().eq("svsID", conversationId);
            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ArticleItme> findAll() {
        try {
            return mDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<ArticleItme> findAllUnread() {
        try {
            return mDao.queryForEq("isRead", false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ArticleItme> findAllBycvsId(long svsId) {
        try {
            return mDao.queryForEq("svsID", svsId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int setRead(long svsId) {
        try {
            int row = mDao.executeRaw("UPDATE tb_zzbchatmessage SET isRead = 'true' WHERE svsID = " + svsId);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
