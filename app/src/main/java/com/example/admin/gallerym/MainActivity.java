package com.example.admin.gallerym;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGES = 1;
    ImageView imageView;
    TextView textView;

    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv=findViewById(R.id.iv);
        TextView tv=findViewById(R.id.tv);

        typeface=Typeface.createFromAsset(getAssets(),"SEASRN__.ttf");

        tv.setTypeface(typeface);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGES);
            }
        });

    }

    @Override

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode==RESULT_LOAD_IMAGES&&resultCode==RESULT_OK&& null!=data){


            Uri selectImages= data.getData();
            String[] filePathColumn={MediaStore.Images.Media.DATA};

            Cursor cursor=getContentResolver().query(selectImages,filePathColumn,null,null,null);
            cursor.moveToFirst();

            int ColumnIndex=cursor.getColumnIndex(filePathColumn[0]);
            String picturepath=cursor.getString(ColumnIndex);
            cursor.close();

            ImageView imageView1 = findViewById(R.id.iv);
            imageView1.setImageBitmap(BitmapFactory.decodeFile(picturepath));

        }

    }


}
