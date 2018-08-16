package ar.com.wolox.android.training.ui.views;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import ar.com.wolox.android.R;

public class ToolbarView extends Toolbar {

    private String mTitle;
    private int mImageResource;

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ToolbarView, 0, 0);

        try {
            mTitle = attributes.getString(R.styleable.ToolbarView_titleText);
            mImageResource = attributes.getResourceId(R.styleable.ToolbarView_imageResource, -1);
        } finally {
            attributes.recycle();
        }

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setContentInsetsAbsolute(0, 0);
        inflate(getContext(), R.layout.toolbar, this);
        setToolbarTitle(mTitle);
        setImageResource(mImageResource);
    }

    public void setToolbarTitle(String title) {
        ((TextView) findViewById(R.id.toolbar_title)).setText(title);
    }

    public void setImageResource(int imageResource) {
        ((ImageView) findViewById(R.id.toolbar_image)).setImageResource(imageResource);
    }
}
