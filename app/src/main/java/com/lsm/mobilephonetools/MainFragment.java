package com.lsm.mobilephonetools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lsm.mobilephonetools.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;


/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2019/4/15 19:23
 */
public class MainFragment  extends BaseFragment implements View.OnClickListener {


    private View mNoiseLayout;

    @Override
    protected View onCreateView() {
        NestedScrollView layout = (NestedScrollView) LayoutInflater.from(getActivity()).inflate(R.layout.main_fragment_layout, null);
        return layout;
    }

    @Override
    protected void initListener() {
        mNoiseLayout.setOnClickListener(this);
    }
    @Override
    protected void initView(FragmentActivity fragmentActivity) {
        mNoiseLayout = fragmentActivity.findViewById(R.id.rl_noise_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_noise_layout:
                startFragment(new NoiseFragment());
                break;
        }
    }
}
