package com.prashant.android.flipv2;

/**
 * Created by Prashant on 26-05-2015.
 */
public class rowHolder {
    String coupons,cashback,img;

    public rowHolder(String coupons, String cashback, String img) {
        this.coupons = coupons;
        this.cashback = cashback;
        this.img = img;
    }

    public String getCoupons() {
        return coupons;
    }

    public String getCashback() {
        return cashback;
    }

    public String getImg() {
        return img;
    }
}
