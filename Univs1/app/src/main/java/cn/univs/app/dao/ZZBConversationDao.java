package cn.univs.app.dao;//package com.zbd.dao;
//
//import android.content.Context;
//
//import com.j256.ormlite.stmt.UpdateBuilder;
//import com.j256.ormlite.stmt.Where;
//import com.zbd.models.db.ZZBConversation;
//import com.j256.ormlite.dao.Dao;
//
//import java.sql.SQLException;
//import java.util.List;
//
//
//public class ZZBConversationDao extends BaseDaoInterface<ZZBConversation> {
//
//    public ZZBConversationDao(Context context) {
//        super(context);
//    }
//
//    @Override
//    public Dao getDao(DatabaseHelper helper) {
//        try {
//            return helper.getDao(ZZBConversation.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    @Override
//    public void add(ZZBConversation zzbConversation) {
//        super.add(zzbConversation);
//        try {
////            int id= (int) zzbConversation.id_;
////            ZZBConversation conversation = mDao.queryForId(id);
////            if(conversation!=null){
////                mDao.update(zzbConversation);
////            }else{
////            }
//            mDao.createOrUpdate(zzbConversation);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void delete(ZZBConversation zzbConversation) {
//        super.delete(zzbConversation);
//        try {
//            mDao.delete(zzbConversation);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void create(ZZBConversation zzbConversation){
//
//        try {
//            mDao.create(zzbConversation);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public List<ZZBConversation> findAll() {
//        try {
//            if (mDao != null)
//                return mDao.queryForAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return null;
//    }
//
//    public long countAll() {
//        try {
//            return mDao.countOf();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    @Override
//    public ZZBConversation findById(long id) {
//        try {
//            return mDao.queryForId(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return super.findById(id);
//    }
//    public int setRead(long svsId) {
//        try {
//            int row = mDao.executeRaw("UPDATE tb_zzbconversation SET unreadCount = '0' WHERE cid = " + svsId);
//            return row;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//}
