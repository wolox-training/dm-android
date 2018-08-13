package ar.com.wolox.android.training.ui.views;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import ar.com.wolox.android.R;

public class ToolbarView extends Toolbar {

    private String mTitle;

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get the attributes specified in attrs.xml using the name we included
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ToolbarView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            mTitle = attributes.getString(R.styleable.ToolbarView_titleText);
        } finally {
            attributes.recycle();
        }

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setContentInsetsAbsolute(0, 0);
        inflate(getContext(), R.layout.toolbar, this);
        setToolbarTitle(mTitle);
    }

    public void setToolbarTitle(String title) {
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
    }
}
