/**
 * 	#COMP 4521
 *      #Cheung Wai Yip	20126604	wycheungae@connect.ust.hk
 *      #Lau Tsz Hei		20113451	thlauac@connect.ust.hk
 *      #Ho Kam Ming	20112316	kmhoab@connect.ust.hk
 */

package hk.ust.comp4521.UasT;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import hk.ust.comp4521.UasT.data.ApiHandler;
import hk.ust.comp4521.UasT.data.ApiManager;
import hk.ust.comp4521.UasT.data.CourseSharing;
import hk.ust.comp4521.UasT.data.Database;
import hk.ust.comp4521.UasT.data.ThreadItem;
import hk.ust.comp4521.UasT.json.ApiResponseLike;

// Used in course group list
public class CourseSharingCardView extends ThreadCardView {
    private CourseSharing courseSharing;

	public CourseSharingCardView(Context context) {
		super(context);
	}

	public CourseSharingCardView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public CourseSharingCardView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}
	
	@Override
	protected ViewHolder createViewHolder() {
		return new ViewHolder();
	}
	
	@Override
	protected void initViews() {
		super.initViews();
		ViewHolder vh = (ViewHolder) getTag();
        vh.likeMessage = (TextView)findViewById(R.id.likeMessage);
        vh.likeButton = (ImageView)findViewById(R.id.likeButton);
	}

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setTag(createViewHolder());
        initViews();
    }

	@Override
	public void setThread(ThreadItem thread) {
        courseSharing = (CourseSharing)thread;
		ViewHolder vh = (ViewHolder) getTag();

        vh.likeMessage.setText(courseSharing.getRating() + " Likes");

        vh.likeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //ViewHolder vh = (ViewHolder) getTag();
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseSharingCardView.this.getContext());
                ProgressBar bar = new ProgressBar(CourseSharingCardView.this.getContext(), null,
                        android.R.attr.progressBarStyleHorizontal);
                bar.setIndeterminate(true);
                builder.setCancelable(true).setTitle("Sending request to server").setView(bar);
                final AlertDialog dialog = builder.show();

                ApiManager.likeSharing(courseSharing.getCourseId(), courseSharing.getKey(), Database.getUser().getITSC(),
                        new ApiHandler<ApiResponseLike>() {

                            @Override
                            public void onSuccess(ApiResponseLike response) {
                                dialog.dismiss();
                                ViewHolder vh = (ViewHolder) getTag();

                                if (response.getMessage().equals("Like")) {
                                    vh.likeButton.setBackgroundResource(R.drawable.icon_like);
                                } else {
                                    vh.likeButton.setBackgroundResource(R.drawable.icon_dislike);
                                }
                                vh.likeMessage.setText(response.getNum() + " Likes");
                            }

                            @Override
                            public void onFailure(String message) {
                                dialog.dismiss();
                                Toast.makeText(CourseSharingCardView.this.getContext(), message,
                                        Toast.LENGTH_LONG).show();
                            }

                        });
            }
        });

		super.setThread(thread);
	}
	
	protected static class ViewHolder extends ThreadCardView.ViewHolder {
        public TextView likeMessage;
        public ImageView likeButton;
	}
	
}

