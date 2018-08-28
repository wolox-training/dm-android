package ar.com.wolox.android.training.ui.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import android.content.Context;
import android.util.AttributeSet;

import java.util.concurrent.Callable;

import ar.com.wolox.android.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolbarView extends Toolbar {

    @BindView(R.id.toolbar_title) TextView mTitle;
    @BindView(R.id.toolbar_image) ImageView mImage;

    public ToolbarView(Context context) {
        super(context);
        init();
    }

    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolbarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ToolbarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.setContentInsetsAbsolute(0, 0);
        View view = inflate(getContext(), R.layout.toolbar, this);
        ButterKnife.bind(this, view);
        setTitle(R.string.empty_toolbar);
        setImage(R.drawable.wolox_logo);
    }

    public void setTitle(int titleRes) {
        mTitle.setText(getResources().getString(titleRes));
    }

    public void setTitleColor(int colorRes) {
        mTitle.setTextColor(getResources().getColor(colorRes));
    }

    public void setImage(int imageRes) {
        mImage.setImageResource(imageRes);
    }

    public void setOnImageClick(OnClickListener onImageClick) {
        mImage.setOnClickListener(onImageClick);
    }

    public void setImageColorFilter(int colorRes) {
        mImage.setColorFilter(getResources().getColor(colorRes));
    }
}
