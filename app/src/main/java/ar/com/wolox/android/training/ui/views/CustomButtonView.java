package ar.com.wolox.android.training.ui.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import ar.com.wolox.android.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomButtonView extends FrameLayout {

    @BindView(R.id.custom_button)
    Button mCustomButton;

    public CustomButtonView(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomButtonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomButtonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View view = inflate(getContext(), R.layout.custom_button_view, this);
        ButterKnife.bind(this, view);

        setText(R.string.empty_button);
        setTextColor(R.color.black);
        setColor(R.color.default_grey);
    }

    public void setText(int textRes) {
        mCustomButton.setText(textRes);
    }

    public void setTextColor(int colorRes) {
        mCustomButton.setTextColor(getResources().getColor(colorRes));
    }

    public void setBackgroundColor(int colorRes) {
        mCustomButton.setBackgroundColor(colorRes);
    }

    public void setColor(int color) {
        setBackgroundColor(getResources().getColor(color));
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener listener) {
        mCustomButton.setOnClickListener(listener);
    }
}
