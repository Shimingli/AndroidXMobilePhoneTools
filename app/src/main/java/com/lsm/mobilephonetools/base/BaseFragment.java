package com.lsm.mobilephonetools.base;


import android.os.Bundle;

import com.lsm.base.ui.QMUIFragment;
import com.lsm.base.utils.QMUIDisplayHelper;
import com.lsm.base.utils.QMUIStatusBarHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.Nullable;
import timber.log.Timber;


/**
 * Created by shiming on 2018/1/7.
 */

public abstract class BaseFragment extends QMUIFragment {



    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.setStatusBarLightMode(getBaseFragmentActivity());

    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

}
