package ar.com.wolox.android.training.ui.fullscreen_image;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.ui.signup.ISignupView;
import ar.com.wolox.android.training.ui.signup.SignupPresenter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

public class FullScreenImageFragment extends WolmoFragment<SignupPresenter> implements ISignupView {

    // Constants
    public static final String KEY_PICTURE = "ar.com.wolox.android.training.IMAGE_URL";

    // Variables
    @BindView(R.id.fragment_fullscren_image_image)
    SimpleDraweeView mImage;

    // Constructor
    @Inject
    public FullScreenImageFragment() {}

    @Override
    public int layout() {
        return R.layout.fragment_fullscreen_image;
    }

    @Override
    public void init() {
        String picture = getActivity().getIntent().getStringExtra(KEY_PICTURE);
        final ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(picture)).build();
        mImage.setImageRequest(imageRequest);
    }
}
