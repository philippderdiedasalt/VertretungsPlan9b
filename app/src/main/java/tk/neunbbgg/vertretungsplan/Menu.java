package tk.neunbbgg.vertretungsplan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends ActionBarActivity implements View.OnClickListener {

    Button blogout;
    TextView th, tm;
    ImageView pm;
    CheckBox cb;
    public static final String DEFAULT="N/A";
    public String path = "/variables";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String autologin = sharedPreferences.getString("ischecked", DEFAULT);



        blogout = (Button) findViewById(R.id.blogout);
        th = (TextView) findViewById(R.id.th);
        tm = (TextView) findViewById(R.id.tm);
        pm = (ImageView) findViewById(R.id.pm);
        cb = (CheckBox) findViewById(R.id.cb);


        if (autologin.equals("1")){
            cb.setChecked(true);
        }

        th.setOnClickListener(this);
        tm.setOnClickListener(this);
        blogout.setOnClickListener(this);
        pm.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blogout:

                startActivity(new Intent(this, Login.class));

                break;

            case R.id.th:

                startActivity(new Intent(this, Plan1.class));

                break;

            case R.id.tm:

                startActivity(new Intent(this, Plan2.class));

                break;
            case R.id.pm:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://9bgg.tk"));
                startActivity(browserIntent);

                break;



        }

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cb:
                if (checked){
                    SharedPreferences sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("ischecked", "1");
                    editor.commit();

                    Toast.makeText(this,"Gespeichert!",Toast.LENGTH_LONG).show();
                }
                // Put some meat on the sandwich
                else{
                    SharedPreferences sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("ischecked", "0");
                    editor.commit();

                    Toast.makeText(this,"Gespeichert!",Toast.LENGTH_LONG).show();
                }
                // Remove the meat
                break;

        }
    }
}