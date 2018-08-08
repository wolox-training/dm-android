package ar.com.wolox.android.login.ui.training;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ar.com.wolox.android.R;
import ar.com.wolox.android.login.ui.viewpager.ViewpagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class TrainingFragment extends WolmoFragment<ExamplePresenter> implements IExampleView {

    @Inject
    public TrainingFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), ViewpagerActivity.class);
        startActivity(intent);
    }
}
