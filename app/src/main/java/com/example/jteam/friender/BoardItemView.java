//리스트뷰의 각 도시를 보여줄 리니어레이아웃
package com.example.jteam.friender;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jeong on 2016-07-27.
 */
public class BoardItemView extends LinearLayout{
    TextView DestinationView;
    TextView Route1View;
    TextView Route2View;
    TextView DateView;
    TextView PresentView;
    TextView FindingView;
    ImageView[] PictogramView = new ImageView[3];

    public BoardItemView(Context context) {
        super(context);

        init(context);
    }

    public BoardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        //initializing on each list view
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.board_item,this,true);

        DestinationView = (TextView) findViewById(R.id.destination);
        Route1View = (TextView) findViewById(R.id.route1);
        Route2View = (TextView) findViewById(R.id.route2);
        DateView = (TextView) findViewById(R.id.date);
        PresentView = (TextView) findViewById(R.id.present);
        FindingView = (TextView) findViewById(R.id.finding);
        PictogramView[0] = (ImageView) findViewById(R.id.pictogram1);
        PictogramView[1] = (ImageView) findViewById(R.id.pictogram2);
        PictogramView[2] = (ImageView) findViewById(R.id.pictogram3);
    }

    public void setDateView(String date) {
        DateView.setText(date);
    }

    public void setDestinationView(String destination) {
        DestinationView.setText(destination);
    }

    public void setFindingView(String finding) {
        FindingView.setText(finding);
    }

    public void setPresentView(String present) {
        PresentView.setText(present);
    }

    public void setRouteView(String route) {
        Route1View.setText(route);
    }

    public void setRouteView(String route1, String route2) {
        Route1View.setText(route1);
        Route2View.setText(route2);
    }

    public void setPictogramView(int[] pictogram) {
        PictogramView[0].setImageResource(pictogram[0]);
        PictogramView[1].setImageResource(pictogram[1]);
        PictogramView[2].setImageResource(pictogram[2]);
    }

}
