package alick.com.rogueapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import alick.com.rogueapp.service.LocalService;
import alick.com.rogueapp.service.RomoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T.showShort(this,"--->onCreate(),启动MainActivity");
//        startService(new Intent(this,RogueService.class));
//        startService(new Intent(this,DameonService.class));
        startService(new Intent(this,LocalService.class));
        startService(new Intent(this,RomoteService.class));

    }
}
