package tk.neunbbgg.vertretungsplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ImageView easter;
    Integer x = 0;
    public static String appversion="Beta 4.2.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easter = (ImageView) findViewById(R.id.easter);


        startActivity(new Intent(this, Login.class));
    }




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
