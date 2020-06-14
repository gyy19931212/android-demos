package com.github.gyy.githubapp1.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by 000286 on 2017/3/21.
 * Description  SharedPreferences存储的工具类
 */
public class SharedPrefGlobal {

  /**
   * TAG
   */
  private static final String TAG = SharedPrefGlobal.class.getSimpleName();

  /**
   * SP 声明
   */
  private SharedPreferences sp = null;
  /**
   * SP 编辑器声明
   */
  private SharedPreferences.Editor editor = null;

  /**
   * SP 构建者模式
   */
  public static class Builder {

    /**
     * SP 文件名
     */
    private String fileName;
    /**
     * SP 文件的分享模式
     */
    private int mode = Context.MODE_APPEND;
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 设置SP 文件名
     *
     * @param fileName 文件名
     * @return SP 构建者模式
     */
    public Builder setFileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    /**
     * 设置 SP 文件的共享模式
     *
     * @param mode 共享模式
     * @return SP 构建者模式
     */
    public Builder setMode(int mode) {
      this.mode = mode;
      return this;
    }

    /**
     * 创建/实例化SP文件
     *
     * @param mContext 上下文
     * @return SP实例化结果 SharedPrefGlobal
     */
    public SharedPrefGlobal create(Context mContext) {
      this.mContext = mContext;
      return new SharedPrefGlobal(this);
    }
  }

  private SharedPrefGlobal(Builder mBuilder) {
    sp = mBuilder.mContext.getSharedPreferences(mBuilder.fileName, mBuilder.mode);
    editor = sp.edit();
  }

  /**
   * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
   *
   * @param key 保存时对应的key
   * @param object 保存的值
   */
  public void put(String key, Object object) {
    if (object instanceof String) {
      editor.putString(key, (String) object);
    } else if (object instanceof Integer) {
      editor.putInt(key, (Integer) object);
    } else if (object instanceof Boolean) {
      editor.putBoolean(key, (Boolean) object);
    } else if (object instanceof Float) {
      editor.putFloat(key, (Float) object);
    } else if (object instanceof Long) {
      editor.putLong(key, (Long) object);
    } else {
      editor.putString(key, object.toString());
    }
    SharedPreferencesCompat.apply(editor);
  }

  /**
   * 得到保存数据的方法
   *
   * @param key 获取数据的key
   * @param defaultValue 当key不存在时的默认值
   * @return 返回值 string
   */
  public String get(String key, String defaultValue) {
    return sp.getString(key, defaultValue);
  }

  /**
   * 得到保存数据的方法
   *
   * @param key 获取数据的key
   * @param defaultValue 当key不存在时的默认值
   * @return 返回值 int
   */
  public int get(String key, int defaultValue) {
    return sp.getInt(key, defaultValue);
  }

  /**
   * 得到保存数据的方法
   *
   * @param key 获取数据的key
   * @param defaultValue 当key不存在时的默认值
   * @return 返回值 boolean
   */
  public boolean get(String key, boolean defaultValue) {
    return sp.getBoolean(key, defaultValue);
  }

  /**
   * 得到保存数据的方法
   *
   * @param key 获取数据的key
   * @param defaultValue 当key不存在时的默认值
   * @return 返回值 float
   */
  public float get(String key, float defaultValue) {
    return sp.getFloat(key, defaultValue);
  }

  /**
   * 得到保存数据的方法
   *
   * @param key 获取数据的key
   * @param defaultValue 当key不存在时的默认值
   * @return 返回值 long
   */
  public long get(String key, long defaultValue) {
    return sp.getLong(key, defaultValue);
  }

  /**
   * 移除某个key值已经对应的值
   *
   * @param key the key
   */
  public void remove(String key) {
    editor.remove(key);
    SharedPreferencesCompat.apply(editor);
  }

  /**
   * 清除所有数据
   */
  public void clear() {
    editor.clear();
    SharedPreferencesCompat.apply(editor);
  }

  /**
   * 查询某个key是否已经存在
   *
   * @param key the key
   * @return the boolean
   */
  public boolean contains(String key) {
    return sp.contains(key);
  }

  /**
   * 返回所有的键值对
   *
   * @return the all
   */
  public Map<String, ?> getAll() {
    return sp.getAll();
  }

  /**
   * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
   */
  private static class SharedPreferencesCompat {
    private static final Method APPLY_METHOD = findApplyMethod();

    /**
     * 反射查找apply的方法
     */
    @SuppressWarnings({ "unchecked", "rawtypes" }) private static Method findApplyMethod() {
      try {
        Class mClazz = SharedPreferences.Editor.class;
        return mClazz.getMethod("apply");
      } catch (NoSuchMethodException e) {
        Log.d(TAG, e.getMessage());
      }

      return null;
    }

    /**
     * 如果找到则使用apply执行，否则使用commit
     *
     * @param editor the editor
     */
    public static void apply(SharedPreferences.Editor editor) {
      try {
        if (APPLY_METHOD != null) {
          APPLY_METHOD.invoke(editor);
          return;
        }
      } catch (InvocationTargetException e) {
        Log.d(TAG, e.getMessage());
      } catch (IllegalArgumentException e) {
        Log.d(TAG, e.getMessage());
      } catch (IllegalAccessException e) {
        Log.d(TAG, e.getMessage());
      }
      editor.commit();
    }
  }
}



