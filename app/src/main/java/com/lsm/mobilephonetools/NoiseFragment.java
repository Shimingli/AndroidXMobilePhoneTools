package com.lsm.mobilephonetools;

import android.view.LayoutInflater;
import android.view.View;

import com.lsm.mobilephonetools.base.BaseFragment;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;

/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2019/4/15 19:53
 */
public class NoiseFragment extends BaseFragment {

    private SoundDiscView mSoundDiscView;

    @Override
    protected View onCreateView() {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.noise_layout, null);
        return layout;
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initView(FragmentActivity fragmentActivity) {
        mSoundDiscView = fragmentActivity.findViewById(R.id.soundDiscView);
    }

}
