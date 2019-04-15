package com.lsm.mobilephonetools.base;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.lsm.base.ui.QMUIFragmentActivity;


/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2018/12/6 15:51
 */

public abstract class BaseFragmentActivity  extends QMUIFragmentActivity {

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

}
