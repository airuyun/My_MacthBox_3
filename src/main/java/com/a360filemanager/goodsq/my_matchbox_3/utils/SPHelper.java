package com.a360filemanager.goodsq.my_matchbox_3.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPHelper {

	private String mName;
	private Context mContext;
	private int mode = Context.MODE_PRIVATE;

	public SPHelper(String name, Context context) {
		mName = name;
		mContext = context;
	}

	// 存入数据
	public void putValue(Key_Value... kv) {
		SharedPreferences sp = mContext.getSharedPreferences(mName, mode);
		Editor et = sp.edit();
		for (Key_Value keyValue : kv) {
			save(et, keyValue);
		}
		et.commit();
	}

	public String getStringt(String key) {
		return mContext.getSharedPreferences(mName, mode).getString(key, null);
	}

	public int getInt(String key) {
		return mContext.getSharedPreferences(mName, mode).getInt(key, -1);
	}

	public Float getFloat(String key) {
		return mContext.getSharedPreferences(mName, mode).getFloat(key, -1f);
	}

	public Long getLong(String key) {
		return mContext.getSharedPreferences(mName, mode).getLong(key, -1);
	}

	public boolean getBoolean(String key) {
		return mContext.getSharedPreferences(mName, mode)
				.getBoolean(key, false);
	}

	// 分类型存储
	private void save(Editor et, Key_Value keyValue)
			throws IllegalArgumentException {
		String key = keyValue.key;
		Object value = keyValue.value;
		if (value instanceof String)
			et.putString(key, (String) value);
		else if (value instanceof Integer)
			et.putInt(key, (int) value);
		else if (value instanceof Float)
			et.putFloat(key, (float) value);
		else if (value instanceof Long)
			et.putLong(key, (long) value);
		else if (value instanceof Boolean)
			et.putBoolean(key, (boolean) value);
		else
			throw new IllegalArgumentException(
					"参数只允许String，int,float,long,boolean");
	}

	public static class Key_Value {
		String key;
		Object value;

		public Key_Value(String key, Object value) {
			this.key = key;
			this.value = value;
		}
	}
}
