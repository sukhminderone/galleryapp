package com.example.sukhminder.proshoot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
Button camera,button3,button;
ImageView imageView2;
EditText caption;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this,"GALLERYDB.sqLite",null,1);//database calling
        databaseHelper.querydata("CREATE TABLE IF NOT EXISTS GALLERY (Id INTEGER PRIMARY KEY AUTOINCREMENT,caption VARCHAR,image Blog)");
button=(Button)findViewById(R.id.button);
button3=(Button)findViewById(R.id.button3);
        camera=(Button)findViewById(R.id.button2);
        caption=(EditText)findViewById(R.id.caption);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//for taking picture
                startActivityForResult(takePictureIntent,1);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                databaseHelper.insertData(caption.getText().toString().trim(),imageViewToByte(imageView2));//pass imageview
                    Toast.makeText(getApplicationContext(),"saved successfully",Toast.LENGTH_LONG).show();
                    caption.setText("");
                    imageView2.setImageResource(R.mipmap.ic_launcher);

                }
                catch (Exception e){
                    e.printStackTrace();

                }
            }
        });
                 button.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent=new Intent(MainActivity.this, gallerypage.class);
                         startActivity(intent);
                     }
                 });
    }
    private byte[] imageViewToByte(ImageView image){//converting image to byte array
Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();//creating buffer in memory
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//to show in image view//returning image
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");//get the returned image  from extra bitmap bmp
            imageView2.setImageBitmap(imageBitmap);
        }
    }
}
