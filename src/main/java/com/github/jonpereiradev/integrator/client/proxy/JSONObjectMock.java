package com.github.jonpereiradev.integrator.client.proxy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class JSONObjectMock extends JSONObject {

    @Override
    public Object get(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, "1");
        }

        return super.get(key);
    }

    @Override
    public JSONObject getJSONObject(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, new JSONObjectMock());
        }

        return super.getJSONObject(key);
    }

    @Override
    public boolean getBoolean(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, true);
        }

        return super.getBoolean(key);
    }

    @Override
    public double getDouble(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, 1D);
        }

        return super.getDouble(key);
    }

    @Override
    public int getInt(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, 1);
        }

        return super.getInt(key);
    }

    @Override
    public long getLong(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, 1L);
        }

        return super.getLong(key);
    }

    @Override
    public String getString(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, "1");
        }

        return super.getString(key);
    }

    @Override
    public JSONArray getJSONArray(String key) throws JSONException {
        if (opt(key) == null) {
            put(key, new JSONArrayMock());
        }

        return super.getJSONArray(key);
    }
}
