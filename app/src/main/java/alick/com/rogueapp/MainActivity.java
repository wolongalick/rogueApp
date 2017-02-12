package alick.com.rogueapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T.showShort(this,"--->onCreate(),启动MainActivity");
        startService(new Intent(this,RogueService.class));

    }
}
