package com.sw.todaeat;

import android.app.Activity;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class StatusBarUtils {
    //바 컬러
    public enum StatusBarColorType {
        MAINCOLOR_STATUS_BAR( R.color.maincolor ),
        WHITE_STATUS_BAR( R.color.white ),
        BACKGROUND_STATUS_BAR( R.color.colorBackground );

        private int backgroundColorId;

        StatusBarColorType(int backgroundColorId){
            this.backgroundColorId = backgroundColorId;
        }

        public int getBackgroundColorId() {
            return backgroundColorId;
        }
    }

    public static void setStatusBarColor(Activity activity, StatusBarColorType colorType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, colorType.getBackgroundColorId()));
        }
    }
}
