package com.lsm.mobilephonetools.base;


import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;


import com.lsm.base.ui.QMUIActivity;
import com.lsm.base.utils.QMUIDisplayHelper;
import com.lsm.base.utils.QMUIStatusBarHelper;

import java.util.concurrent.atomic.AtomicLong;

import androidx.annotation.Nullable;
import timber.log.Timber;

import static com.lsm.base.BaseApplication.getContext;


/**
 * <p>
 * 抽象应用程序中的其他活动必须实现的活动。它处理Dagger组件的创建，并确保ConfigPersistentComponent的实例跨配置更改存活。
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2018/11/28 10:04
 */

public class BaseActivity extends QMUIActivity {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    /**
     * AtomicLong是作用是对长整形进行原子操作。 线程安全
     */
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    /**
     * java1.8中新加入了一个新的原子类LongAdder，该类也可以保证Long类型操作的原子性，
     * 相对于AtomicLong，LongAdder有着更高的性能和更好的表现，可以完全替代AtomicLong的来进行原子操作
     * 但是对 java的版本有要求，这里就不使用 LongAdder了
     */
    // private static final LongAdder NEXT_ID = new LongAdder();

    /**
     * LongSparseArray是android里为<Long,Object> 这样的Hashmap而专门写的类,目的是提高效率，其核心是折半查找函数（binarySearch）。
     * SparseArray仅仅提高内存效率，而不是提高执行效率
     * ，所以也决定它只适用于android系统（内存对android项目有多重要）SparseArray不需要开辟内存空间来额外存储外部映射，从而节省内存。
     */


    private long mActivityId;

    /**
     * isChangingConfigurations()函数在是Api level 11（Android 3.0.x） 中引入的
     * 也就是用来检测当前的Activity是否 因为Configuration的改变被销毁了，然后又使用新的Configuration来创建该Activity。
     * 常见的案例就是 Android设备的屏幕方向发生变化，比如从横屏变为竖屏。
     */
    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.tag(getClassName()).i("销毁的configPersistentComponent id=%d", mActivityId);
        }
        super.onDestroy();
    }
    /*为了使用RxBus end  */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建ActivityComponent，如果配置更改后调用缓存的ConfigPersistentComponent，则重用它。
        mActivityId = savedInstanceState != null ? savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        //状态栏的颜色
        QMUIStatusBarHelper.setStatusBarLightMode(this);

    }


    protected String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }



    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }


    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
