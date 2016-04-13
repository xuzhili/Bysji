package com.example.xuzhili.bysjstudio.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2015-10-26.
 */
public abstract class BaseDaoInterface<T> {

  protected Dao<T, Long> mDao;
  protected DatabaseHelper helper;
  public BaseDaoInterface(Context context) {
    try {
      helper = DatabaseHelper.getHelper(context);
      //noinspection unchecked
      mDao = getDao(helper);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public abstract  Dao getDao(DatabaseHelper helper);


  public void add(T t)  {}

  public void delete(T t){}

  public void update(T t){
    try {
      mDao.update(t);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public T findById(long id){
    try {
      return mDao.queryForId(id);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
  public List<T> findAll(){
    try {
      return mDao.queryForAll();
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }
}
