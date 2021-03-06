/**
 * 	#COMP 4521
 *      #Cheung Wai Yip	20126604	wycheungae@connect.ust.hk
 *      #Lau Tsz Hei		20113451	thlauac@connect.ust.hk
 *      #Ho Kam Ming	20112316	kmhoab@connect.ust.hk
 */

package hk.ust.comp4521.UasT.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Post extends ThreadItem {

	public String getContent() {
		return content;
	}

	//public String[] getAttachments() {return attachments;}

	//public String getDetails() {return details;}

	public String getAuthorId() {
		return authorId;
	}

	public long getTime() {
		return time;
	}

	public int getRating() {
		return rating;
	}

	private String content;
	private String[] attachments = new String[0];
	private String details;
	private String authorId;
	private String attachment = "";
	private long time;
	private int rating;

	@Override
	public void load(JSONObject obj) throws JSONException {
		title = obj.getString("title");
		if (obj.has("details"))
			details = obj.getString("details");
		if (obj.has("content"))
			content = obj.getString("content");
		sub = obj.getString("author");
		authorId = obj.getString("authorId");
		key = obj.getString("post");
		time = obj.getLong("time");
		
        if (obj.has("img")) {
            attachment = obj.getString("img");
        }

		if (obj.has("rating"))
			rating = obj.getInt("rating");

		if (obj.has("attachments")) {
			JSONArray attachments = obj.getJSONArray("attachments");
			this.attachments = new String[attachments.length()];
			for (int i = 0; i < attachments.length(); i++)
				this.attachments[i] = attachments.getString(i);
		}
	}

    public String getAttachment() {
        return this.attachment;
    }

}
