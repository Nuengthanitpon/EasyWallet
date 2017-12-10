package com.example.easywallet.Model;

/**
 * Created by Admin on 2/12/2560.
 */

public class WalletItem {
    public final int id;
    public final String thaiName;
    public final String picture;
    public final String monney;

    public WalletItem(int id, String thaiName, String monney, String picture) {
        this.id = id;
        this.thaiName = thaiName;
        this.monney = monney;
        this.picture = picture;
    }
}
