package hk.ust.comp4521.UasT.data;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DataType {
	public abstract void load(JSONObject obj) throws JSONException;
}