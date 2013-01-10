package com.refnil.uqcard;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HandActivity extends Activity{
    ImageView imageView = null;
    ImageAdapter adapter;
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	adapter = new ImageAdapter(this);
    	  
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand);
        
        Gallery ga = (Gallery)findViewById(R.id.Gallery01);
        ga.setAdapter(adapter);
        
        imageView = (ImageView)findViewById(R.id.ImageView01);
        ga.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), FullCardActivity.class);
				int id = (int) adapter.getItemId(arg2);
				i.putExtra("id", id);
				startActivity(i);
				
			}
        	
        });
        
        
    }    
}