package tk.neunbbgg.vertretungsplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ImageView easter;
    Integer x = 0;
    public static String appversion="3.8.3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easter = (ImageView) findViewById(R.id.easter);
        easter.setOnClickListener(this);
        startActivity(new Intent(this, Login.class));
    }
    // Restart service every 30 seconds






    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.easter:

                x = x+1;
                if (x>10){
                   startActivity( new Intent(this, easterActivity.class));
                }
                break;
        }
    }
}
