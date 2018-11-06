package com.github.jonpereiradev.integrator.client.proxy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class JSONArrayMock extends JSONArray {

    @Override
    public Object get(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, new JSONObjectMock());
        }

        return super.get(index);
    }

    @Override
    public boolean getBoolean(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, true);
        }

        return super.getBoolean(index);
    }

    @Override
    public double getDouble(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, 1D);
        }

        return super.getDouble(index);
    }

    @Override
    public int getInt(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, 1);
        }

        return super.getInt(index);
    }

    @Override
    public long getLong(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, 1L);
        }

        return super.getLong(index);
    }

    @Override
    public JSONArray getJSONArray(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, new JSONArrayMock());
        }

        return super.getJSONArray(index);
    }

    @Override
    public String getString(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, "1");
        }

        return super.getString(index);
    }

    @Override
    public JSONObject getJSONObject(int index) throws JSONException {
        if (opt(index) == null) {
            put(index, new JSONObjectMock());
        }

        return super.getJSONObject(index);
    }

    @Override
    public int length() {
        return super.length() == 0 ? 1 : super.length();
    }

}
