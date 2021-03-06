/**
 * 	#COMP 4521
 *      #Cheung Wai Yip	20126604	wycheungae@connect.ust.hk
 *      #Lau Tsz Hei		20113451	thlauac@connect.ust.hk
 *      #Ho Kam Ming	20112316	kmhoab@connect.ust.hk
 */

package hk.ust.comp4521.UasT.json;

import org.json.JSONException;
import org.json.JSONObject;

import hk.ust.comp4521.UasT.data.Chat;

public class ApiResponseAddChat extends ApiResponseBase {

	private Chat chat;
	
	public void load(JSONObject obj) throws JSONException {
		super.load(obj);
		if(obj.has("chat")) {
			chat = new Chat();
			chat.load(obj.getJSONObject("chat"));
		}
	}
	
	public Chat getChat() {
		return chat;
	}
}
