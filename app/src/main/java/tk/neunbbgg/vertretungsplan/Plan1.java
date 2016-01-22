package tk.neunbbgg.vertretungsplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Plan1 extends ActionBarActivity implements View.OnClickListener {

    TextView etzu;
    Button baktualisieren;
    WebView WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan1);

        etzu = (TextView) findViewById(R.id.etzu);
        baktualisieren = (Button) findViewById(R.id.baktualisieren);
        WebView =(WebView)findViewById(R.id.WebView);
        baktualisieren.setOnClickListener(this);
        etzu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.baktualisieren:



                String url ="http://gymglinde.de/typo40/fileadmin/vertretungsplan/VertretungAktuell/PH_heute.htm";
                WebView.loadUrl(url);


                break;
            case R.id.etzu:
                startActivity(new Intent(this, Menu.class));

                break;
        }

    }


    }


