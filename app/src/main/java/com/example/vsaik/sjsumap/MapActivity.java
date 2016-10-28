package com.example.vsaik.sjsumap;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.example.vsaik.sjsumap.R.id.toolbar;


public class MapActivity extends AppCompatActivity {

    static double a = 360 - 53.0872483;
    static double cos = Math.cos(a);
    static double sin = Math.sin(a);

    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public AddressData addressData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addressData = new AddressData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        showMyLocation();

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BuildingActivity.class);
                i.putExtra("address",addressData.getAddressList().get(0));
                startActivity(i);
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BuildingActivity.class);
                i.putExtra("address",addressData.getAddressList().get(1));
                startActivity(i);
            }
        });
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BuildingActivity.class);
                i.putExtra("address",addressData.getAddressList().get(2));
                startActivity(i);
            }
        });
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BuildingActivity.class);
                i.putExtra("address",addressData.getAddressList().get(3));
                startActivity(i);
            }
        });
        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),BuildingActivity.class);
                i.putExtra("address",addressData.getAddressList().get(4));
                startActivity(i);
            }
        });

    }

    private void showMyLocation(){
        double lat = 37.334652;
        double lon = -121.884651;


        double origX = -121.881289;
        double origY = 37.335833;
        transformCoordinates(origY,origX);


    }

    private void transformCoordinates(double y,double x){
        double tranfX = ( x*cos + y*sin) ;
        double tranfY = (-x*sin + y*cos) ;
        tranfX += 100.2223;
        tranfY += 78.7690;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //height_frames = 765339
        //length_frames = 693692

        System.out.println("transX : "+round(tranfX,4) +"::transY : "+
                round(tranfY,4) );
        float xi = (float)(tranfX*1000000/6936)*width;
        float yi = (float)(tranfY*1000000/7653)*height;

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        Bitmap lMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.loc,opt);
        Canvas lCanvas = new Canvas(lMap);

        ImageView location = (ImageView) findViewById(R.id.location);
        if(xi < 0)
            xi *= -1;
        if(yi < 0)
            yi*= -1;
        float xCoord = xi;
        float yCoord = height - yi;
        location.setX(xi);
        location.setY(yCoord);
        location.setImageDrawable(new BitmapDrawable(getResources(), lMap));
    }

    public static double round(double value, int places) {
        if(value < 0)
            value = value*-1;
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);


        return bd.doubleValue();
    }

}
