package com.example.easywallet.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easywallet.Model.WalletItem;
import com.example.easywallet.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 4/12/2560.
 */

public class WalletAdapter extends ArrayAdapter<WalletItem> {

    private Context mContext;
    private int mLayoutResId;
    private ArrayList<WalletItem> mWalletList;

    public WalletAdapter(Context context, int resource, ArrayList<WalletItem> objects){
        super(context, resource, objects);

        this.mContext = context;
        this.mLayoutResId = resource;
        this.mWalletList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View v = convertView;
        if(v == null){
            v = inflater.inflate(mLayoutResId, null);
        }

        ImageView iv1 = v.findViewById(R.id.imageView1);
        TextView tv1 = v.findViewById(R.id.textView1);
        TextView tv2 = v.findViewById(R.id.textView2);

        WalletItem walletItem = mWalletList.get(position);


        tv1.setText(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        walletItem.thaiName
                )
        );
        tv2.setText(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        walletItem.monney
                )
        );

        String pictureFileName = walletItem.picture;

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv1.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            iv1.setImageDrawable(drawable);
        }



        return v;
    }
}
