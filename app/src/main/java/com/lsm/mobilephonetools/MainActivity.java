package com.lsm.mobilephonetools;



import android.os.Bundle;
import com.lsm.mobilephonetools.base.BaseFragment;
import com.lsm.mobilephonetools.base.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {


    private MainFragment mMainFragment;
    @Override
    protected int getContextViewId() {
        return R.id.main_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = getFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
    private BaseFragment getFragment() {
        if (mMainFragment==null) {
            mMainFragment = new MainFragment();
        }
        return mMainFragment;
    }

}
