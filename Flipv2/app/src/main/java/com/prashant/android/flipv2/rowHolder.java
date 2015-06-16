package com.prashant.android.flipv2;

import android.graphics.Bitmap;

/**
 * Created by Prashant on 26-05-2015.
 * Modified on 16-06-2015.
 */
public class rowHolder {
    String store;
    Bitmap bitmap;

    public void setStore(String store) {
        this.store = store;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getStore() {
        return store;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
