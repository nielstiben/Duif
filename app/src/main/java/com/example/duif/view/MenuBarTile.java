package com.example.duif.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.duif.R;

public class MenuBarTile extends RelativeLayout {
    private int icon;
    private int iconBeingPressed;
    private int iconSelected;

    private int state = 0;

    public MenuBarTile(Context context) {
        super(context);
        initTile();
    }

    public MenuBarTile(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTile();
    }

    public MenuBarTile(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTile();
    }

    public MenuBarTile(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTile();
    }

    Paint whiteLine;

    private void initTile() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_bar_tile, this);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        // initial state
        if(state == 0) {
            imageView.setImageResource(icon);
            this.setBackgroundColor(Color.LTGRAY);

        }
        // being pressed
        if(state == 1) {
            imageView.setImageResource(iconBeingPressed);

        }
        // selected
        if(state == 2) {
            imageView.setImageResource(iconSelected);
            this.setBackgroundColor(Color.rgb(225,235,235));
        }

        // set line color
        whiteLine = new Paint();
        whiteLine.setColor(Color.WHITE);

        // prepare logo
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight(), 0, 0, whiteLine);

    }
    public void setIcon(int icon){
        this.icon = icon;
        this.iconSelected = icon;
        this.iconBeingPressed = icon;
    }
    public void setIcon(int initial, int selected, int pressed){
        this.icon = initial;
        this.iconSelected = selected;
        this.iconBeingPressed = pressed;
    }
    public void setState(int state){
        this.state = state;
        initTile();
    }
}
