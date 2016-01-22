package tk.neunbbgg.vertretungsplan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends ActionBarActivity implements View.OnClickListener {

    Button blogout;
    TextView th, tm;
    ImageView pm;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        blogout = (Button) findViewById(R.id.blogout);
        th = (TextView) findViewById(R.id.th);
        tm = (TextView) findViewById(R.id.tm);
        pm = (ImageView) findViewById(R.id.pm);
        cb = (CheckBox) findViewById(R.id.cb);


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

            case R.id.cb:

        }

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cb:
                if (checked){

                }
                // Put some meat on the sandwich
                else{

                }
                // Remove the meat
                break;

        }
    }
}