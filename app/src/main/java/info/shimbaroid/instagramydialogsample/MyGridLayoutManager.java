package info.shimbaroid.instagramydialogsample;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by yuki on 2016/11/18.
 */

public class MyGridLayoutManager extends GridLayoutManager {

    private boolean scrollable = true;

    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public void setScrollDisable() {
        scrollable = false;
    }

    public void setScrollEnable() {
        scrollable = true;
    }

    @Override
    public boolean canScrollVertically() {
        return super.canScrollVertically() && scrollable;
    }
}
