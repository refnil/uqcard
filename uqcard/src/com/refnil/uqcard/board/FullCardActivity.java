package com.refnil.uqcard.board;

import com.refnil.uqcard.R;
import com.refnil.uqcard.R.id;
import com.refnil.uqcard.R.layout;
import com.refnil.uqcard.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;

public class FullCardActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_card);
        
        Intent i = this.getIntent();
        int idImage = i.getExtras().getInt("id");
        
        ImageView image = (ImageView) findViewById(R.id.ImageFullScreen);
        image.setImageResource(idImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_full_card, menu);
        return true;
    }
}
