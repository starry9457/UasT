/**
 * 	#COMP 4521
 *      #Cheung Wai Yip	20126604	wycheungae@connect.ust.hk
 *      #Lau Tsz Hei		20113451	thlauac@connect.ust.hk
 *      #Ho Kam Ming	20112316	kmhoab@connect.ust.hk
 */

package hk.ust.comp4521.UasT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Date;

import hk.ust.comp4521.UasT.data.CalendarEvent;

/**
 * Created by Darren on 17/4/2015.
 * Main purpose: To choose time through GUI.
 */
public class choose_time extends BaseFragment {
    private String curStr;
    private Record _parent;
    private MatchRecord _parent2;
    private String _target;

    private View view;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button BtnAccept;
    private Button BtnDecline;

    private Date cur;

    public void setParam(String time, Record parent, String target) {
        this.curStr = time;
        this._parent = parent;
        this._parent2 = null;
        this._target = target;
    }

    public void setParam(String time, MatchRecord parent, String target) {
        this.curStr = time;
        this._parent = null;
        this._parent2 = parent;
        this._target = target;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.choose_time, container, false);

        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);

        BtnAccept = (Button) view.findViewById(R.id.BtnAccept);
        BtnDecline = (Button) view.findViewById(R.id.BtnDecline);

        cur = CalendarEvent.StringToDate(curStr);
        if (cur == null) {
            if (_parent != null)
                cur = _parent.getFromTime();
            else
                cur = new Date();
        }
        datePicker.init(cur.getYear() + 1900, cur.getMonth(), cur.getDate(), null);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(cur.getHours());
        timePicker.setCurrentMinute(cur.getMinutes());

        BtnAccept.setOnClickListener(AcceptListener);
        BtnDecline.setOnClickListener(DeclineListener);

        return view;
    }

    @Override
    public String getTitle() {
        return "Calendar";
    }

    private final OnClickListener AcceptListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            cur = new Date(datePicker.getYear()-1900,datePicker.getMonth(),datePicker.getDayOfMonth(),timePicker.getCurrentHour(),timePicker.getCurrentMinute());
            if (_parent != null) _parent.updateText(_target, cur);
            if (_parent2 != null) _parent2.updateText(_target, cur);

            ((MainActivity) getActivity()).popFragment();
        }
    };

    private final OnClickListener DeclineListener = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            ((MainActivity) getActivity()).popFragment();
        }
    };
}
