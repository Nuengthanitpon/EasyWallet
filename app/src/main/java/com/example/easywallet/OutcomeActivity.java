package com.example.easywallet;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.easywallet.db.PhoneDb;

import java.util.Locale;

public class OutcomeActivity extends AppCompatActivity {
    private static final String TAG = OutcomeActivity.class.getName();

    private EditText mDetailEditText, mMonneyEditText;
    private Button mSaveButton;ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        mDetailEditText = findViewById(R.id.phone_title_edit_text);
        mMonneyEditText = findViewById(R.id.phone_number_edit_text);
        mSaveButton = findViewById(R.id.save_button);
        imageView = findViewById(R.id.phone_image_view2);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDb();
            }
        });
        getSupportActionBar().setTitle(
                String.format(
                        Locale.getDefault(),
                        "%s",
                        "บันทึกรายจ่าย"
                )
        );
    }
    private void saveDataToDb() {
        String Detail = mDetailEditText.getText().toString();
        String Monney = mMonneyEditText.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(PhoneDb.COL_TITLE, Detail);
        cv.put(PhoneDb.COL_MONNEY, Monney);
        cv.put(PhoneDb.COL_PICTURE, "ic_expense.png");

        PhoneDb dbHelper = new PhoneDb(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert(PhoneDb.TABLE_NAME_WALLET, null, cv);
        Intent intent = new Intent(OutcomeActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
