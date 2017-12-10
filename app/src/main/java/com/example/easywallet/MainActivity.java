package com.example.easywallet;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.easywallet.Model.WalletItem;
import com.example.easywallet.adapter.WalletAdapter;
import com.example.easywallet.db.PhoneDb;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private PhoneDb mHelper;
    private SQLiteDatabase mDb;
    private Button mbutton1,mbutton2;
    public static ArrayList<WalletItem> aList = new ArrayList<>();
    private WalletAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelper = new PhoneDb(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new WalletAdapter(
                this,
                R.layout.item,
                aList
        );

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);

        mbutton1 = (Button) findViewById(R.id.button1);
        mbutton2 = (Button) findViewById(R.id.button2);

        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(intent);

            }
        });

        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OutcomeActivity.class);
                startActivity(intent);

            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                String[] items = new String[]{"NO", "YES"};

                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                          finish();

                        } else if (i == 1) {
                            WalletItem item = aList.get(position);
                            int phoneId = item.id;

                            mDb.delete(
                                    PhoneDb.TABLE_NAME_WALLET,
                                    PhoneDb.COL_ID + "=?",
                                    new String[]{String.valueOf(phoneId)}
                            );
                            loadDataFromDb();
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }
    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                PhoneDb.TABLE_NAME_WALLET,
                null,
                null,
                null,
                null,
                null,
                null
        );

        aList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(PhoneDb.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_TITLE));
            String monney = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_MONNEY));
            String picture = cursor.getString(cursor.getColumnIndex(PhoneDb.COL_PICTURE));

            WalletItem item = new WalletItem(id, title, monney, picture);
            aList.add(item);
        }
    }
}
