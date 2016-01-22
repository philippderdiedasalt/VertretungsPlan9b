package tk.neunbbgg.vertretungsplan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends ActionBarActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername;
    EditText etPassword;
    ImageView pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        pl = (ImageView) findViewById(R.id.pl);
        pl.setOnClickListener(this);
        bLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:

            if (etUsername.getText().toString().equals("9b")){

                if (etPassword.getText().toString().equals("9berta")){
                    startActivity(new Intent(this, Menu.class));
                    break;
                }
                
            }
                AlertDialog ad = new AlertDialog.Builder(this).create();
                ad.setCancelable(false); // This blocks the 'BACK' button
                ad.setMessage("Benutzername oder Passwort falsch");
                ad.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();


                break;
            case R.id.pl:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://9bgg.tk"));
                startActivity(browserIntent);

                break;

        }
    }
}
