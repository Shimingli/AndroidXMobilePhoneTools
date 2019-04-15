package com.lsm.mobilephonetools;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.lsm.mobilephonetools.base.BaseFragment;

import androidx.core.widget.NestedScrollView;


/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2019/4/15 19:23
 */
public class MainFragment  extends BaseFragment {


    @Override
    protected View onCreateView() {
        NestedScrollView layout = (NestedScrollView) LayoutInflater.from(getActivity()).inflate(R.layout.main_fragment_layout, null);
        return layout;
    }

}
