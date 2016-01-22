package tk.neunbbgg.vertretungsplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Plan2 extends ActionBarActivity implements View.OnClickListener {

    TextView etzu2;
    Button baktualisieren2;
    WebView WebView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan2);

        etzu2 = (TextView) findViewById(R.id.etzu2);
        baktualisieren2 = (Button) findViewById(R.id.baktualisieren2);
        WebView2 =(WebView)findViewById(R.id.WebView2);
        baktualisieren2.setOnClickListener(this);
        etzu2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.baktualisieren2:



                String url2 ="http://gymglinde.de/typo40/fileadmin/vertretungsplan/VertretungAktuell/PH_morgen.htm";
                WebView2.loadUrl(url2);


                break;
            case R.id.etzu2:
                startActivity(new Intent(this, Menu.class));

                break;
        }

    }


}


