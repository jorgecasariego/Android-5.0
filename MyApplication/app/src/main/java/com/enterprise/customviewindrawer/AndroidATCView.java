package com.enterprise.customviewindrawer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;

/**
 * Created by jorgecasariego on 31/10/15.
 */
public class AndroidATCView extends View {
    //circle and text colors
    private int squareColor, labelColor;

    //label text
    private String squareText;

    //paint for drawing custom view
    private Paint squarePaint;


    public AndroidATCView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        //paint object for drawing in onDraw
        squarePaint = new Paint();

        //Get the attributes specified in attrs.xml
        //using the name you included
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.AndroidATCView,
                0,
                0
        );

        try {
            //Get the text and colors specified using
            //the names in attrs
            squareText = array.getString(R.styleable.AndroidATCView_squareLabel);
            squareColor = array.getInteger(R.styleable.AndroidATCView_squareColor, 0);
            labelColor = array.getInteger(R.styleable.AndroidATCView_labelColor, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //set drawing properties
        squarePaint.setStyle(Paint.Style.FILL);
        squarePaint.setAntiAlias(true);

        squarePaint.setColor(squareColor);

        //Set the paint color using the circle color specified
        canvas.drawRect(0,0,this.getMeasuredWidth(), this.getMeasuredHeight(), squarePaint);

        //set the text color using the color specified
        squarePaint.setColor(labelColor);

        //set the properties
        squarePaint.setTextAlign(Paint.Align.CENTER);
        squarePaint.setTextSize(50);

        //Draw the text using the string attribute and chosen properties
        canvas.drawText(squareText, this.getMeasuredWidth()/2, this.getMeasuredHeight()/2, squarePaint);
    }

    public int getSquareColor() {
        return squareColor;
    }

    public int getLabelColor() {
        return labelColor;
    }

    public String getSquareText() {
        return squareText;
    }

    public Paint getSquarePaint() {
        return squarePaint;
    }

    public void setSquareColor(int squareColor) {
        //update the instance variable
        this.squareColor = squareColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelColor(int labelColor) {
        //update the instance variable
        this.labelColor = labelColor;

        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setSquareText(String squareText) {
        this.squareText = squareText;
    }

    public void setSquarePaint(Paint squarePaint) {
        //update the instance variable
        this.squarePaint = squarePaint;

        //redraw the view
        invalidate();
        requestLayout();
    }
}
