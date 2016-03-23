package tk.neunbbgg.vertretungsplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class pwActivity extends Activity implements View.OnClickListener {

    EditText etpwb, etpwe;
    Button bwp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw);

        etpwb = (EditText) findViewById(R.id.etpwb);
        etpwe = (EditText) findViewById(R.id.etpwe);
        bwp = (Button) findViewById(R.id.bpwreset);
        bwp.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

    }
}
