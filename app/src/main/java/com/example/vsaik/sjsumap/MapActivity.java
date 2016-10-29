package com.example.vsaik.sjsumap;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
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

    static double a = 360 - 53.35;
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

        double origX = -121.880136;
        double origY = 37.338424;
       transformCoordinates(37.334359, -121.882999);


    }

    private void transformCoordinates(double y,double x){
        double tranfX = ( x*cos + y*sin) ;
        double tranfY = (-x*sin + y*cos) ;

        if( x > -121.87785){
            tranfX += 76.32320;
        }
        else if( x > -121.87980){
            tranfX += 76.32320;
        }
        else{
            tranfX += 76.32320;
        }

        if(y > 37.33550) {
            tranfY += 102.09841;

        }
        else if( y > 37.33250) {
            tranfY += 102.09751;
        }
        else{
            tranfY += 102.09708;
        }
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;



        System.out.println("transX : "+round(tranfX,4) +"::transY : "+
                round(tranfY,4) );

        float xi = (float)(tranfX*1000000/8462)*1200;
        float yi = (float)(tranfY*1000000/6043)*900;


        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.RED);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(15, Color.RED);
        drawable.setSize(50,25);
        ImageView location = (ImageView) findViewById(R.id.location);
        if(xi < 0)
            xi *= -1;
        if(yi < 0)
            yi*= -1;
        float xCoord = yi + 10   ;
        float yCoord = 1220 - xi ;
        location.setX(xCoord );
        location.setY(yCoord);
        location.setBackground(drawable);
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
