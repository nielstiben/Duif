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
import android.widget.RelativeLayout;

import com.example.duif.R;

public class MenuBarTile extends RelativeLayout {
    private Drawable icon;
    private boolean selected;

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

        // set background color
        this.setBackgroundColor(Color.LTGRAY);

        // set line color
        whiteLine = new Paint();
        whiteLine.setColor(Color.WHITE);

        // prepare logo
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight(), 0, 0, whiteLine);


    }
}
