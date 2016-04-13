package com.example.xuzhili.bysjstudio.dao;//package com.zbd.dao;
//
//import android.content.Context;
//
//import com.j256.ormlite.dao.Dao;
//import com.zbd.models.db.ZZBConversation;
//import com.zbd.models.db.ZZBConversationAvatar;
//
//import java.sql.SQLException;
//import java.util.List;
//
///**
// * Created by xuzhili on 15/12/10.
// */
//public class ZZBConversationAvatarDao extends BaseDaoInterface<ZZBConversationAvatar> {
//
//    public ZZBConversationAvatarDao(Context context) {
//        super(context);
//    }
//
//    @Override
//    public Dao getDao(DatabaseHelper helper) {
//        try {
//            return helper.getDao(ZZBConversationAvatar.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public void add(ZZBConversationAvatar zzbConversationAvatar) {
//        try {
//            mDao.createOrUpdate(zzbConversationAvatar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void create(ZZBConversationAvatar zzbConversationAvatar){
//        try {
//            mDao.create(zzbConversationAvatar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void delete(ZZBConversationAvatar zzbConversationAvatar) {
//        super.delete(zzbConversationAvatar);
//        try {
//            mDao.delete(zzbConversationAvatar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void upDate(ZZBConversationAvatar zzbConversationAvatar){
//        try {
//            mDao.update(zzbConversationAvatar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ZZBConversationAvatar getById(long id){
//        ZZBConversationAvatar zzbConversationAvatar = null;
//        try {
//            zzbConversationAvatar = mDao.queryForId(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return zzbConversationAvatar;
//    }
//
//    public List<ZZBConversationAvatar> findByZZBconversationId(long zzbConversationId) {
//        try {
//            return mDao.queryBuilder().where().eq("zzbconversation_id", zzbConversationId)
//                    .query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
