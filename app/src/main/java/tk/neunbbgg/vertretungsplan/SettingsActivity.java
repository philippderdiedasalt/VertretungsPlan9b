package tk.neunbbgg.vertretungsplan;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;





public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton btnacht ;
    ImageButton ibkontakt;
    TextView tcolor;
    Button btnver, btngithub;
    Button btnentw, bkontakt;
//TODO: Farbeinstellungen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnentw = (Button) findViewById(R.id.btnentw);
        btnentw.setOnClickListener(this);
        btnacht = (ToggleButton) findViewById(R.id.tbnacht);
        btnacht.setOnClickListener(this);
        btnver = (Button) findViewById(R.id.btnver);
        btnver.setText("App Version: " + MainActivity.appversion);
        btngithub = (Button) findViewById(R.id.btngithub);
        btngithub.setOnClickListener(this);
        bkontakt = (Button) findViewById(R.id.bkontakt);
        bkontakt.setOnClickListener(this);
        ibkontakt = (ImageButton) findViewById(R.id.ibkontakt);
        ibkontakt.setOnClickListener(this);

   //     tcolor = (TextView) findViewById(R.id.tfarbe);
    //    tcolor.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://9bgg.tk/"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tbnacht:
                if (btnacht.isChecked()){
                    SharedPreferences sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("nacht", "0");
                    editor.commit();
                    RelativeLayout bilder = (RelativeLayout) findViewById(R.id.drawer_layout_bild);
                    RelativeLayout ha = (RelativeLayout) findViewById(R.id.drawer_layout_ha);
                    RelativeLayout mensa = (RelativeLayout) findViewById(R.id.drawer_layout_mensa);
                    RelativeLayout navi = (RelativeLayout) findViewById(R.id.drawer_layout_navi);
                    RelativeLayout plan1 = (RelativeLayout) findViewById(R.id.drawer_layout_plan1);
                    RelativeLayout plan2 = (RelativeLayout) findViewById(R.id.drawer_layout_plan22);
                    RelativeLayout stunden = (RelativeLayout) findViewById(R.id.drawer_layout_stunden);
                    RelativeLayout termine = (RelativeLayout) findViewById(R.id.drawer_layout_termine);

                    bilder.setBackgroundColor(0xffffff);
                    ha.setBackgroundColor(0xffffff);
                    mensa.setBackgroundColor(0xffffff);
                    navi.setBackgroundColor(0xffffff);
                    plan1.setBackgroundColor(0xffffff);
                    plan2.setBackgroundColor(0xffffff);
                    stunden.setBackgroundColor(0xffffff);
                    termine.setBackgroundColor(0xffffff);
                    Toast.makeText(this, "Gespeichert!", Toast.LENGTH_LONG).show();
                }
                // Put some meat on the sandwich
                else{
                    SharedPreferences sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("nacht", "1");
                    editor.commit();
                    RelativeLayout bilder = (RelativeLayout) findViewById(R.id.drawer_layout_bild);
                    RelativeLayout ha = (RelativeLayout) findViewById(R.id.drawer_layout_ha);
                    RelativeLayout mensa = (RelativeLayout) findViewById(R.id.drawer_layout_mensa);
                    RelativeLayout navi = (RelativeLayout) findViewById(R.id.drawer_layout_navi);
                    RelativeLayout plan1 = (RelativeLayout) findViewById(R.id.drawer_layout_plan1);
                    RelativeLayout plan2 = (RelativeLayout) findViewById(R.id.drawer_layout_plan22);
                    RelativeLayout stunden = (RelativeLayout) findViewById(R.id.drawer_layout_stunden);
                    RelativeLayout termine = (RelativeLayout) findViewById(R.id.drawer_layout_termine);

                    bilder.setBackgroundColor(0x787878);
                    ha.setBackgroundColor(0x787878);
                    mensa.setBackgroundColor(0x787878);
                    navi.setBackgroundColor(0x787878);
                    plan1.setBackgroundColor(0x787878);
                    plan2.setBackgroundColor(0x787878);
                    stunden.setBackgroundColor(0x787878);
                    termine.setBackgroundColor(0x787878);
                    Toast.makeText(this, "Gespeichert!", Toast.LENGTH_LONG).show();
                }
                // Remove the meat
                break;
            case R.id.btnentw:
                AlertDialog ad3 = new AlertDialog.Builder(this).create();
                ad3.setCancelable(false); // This blocks the 'BACK' button
                ad3.setMessage("Entwickelt für dich von: \n Philipp Harms");
                ad3.setButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                ad3.show();
                break;
            case R.id.btngithub:
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/philippDerDieDas/VertretungsPlan9b/"));
                startActivity(intent);
                break;


            case R.id.bkontakt:
                Intent intent1 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "philippharms1@web.de", null));
                startActivity(Intent.createChooser(intent1, "Email senden"));
                break;
            case R.id.ibkontakt:
                Intent intent2 = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "philippharms1@web.de", null));
                startActivity(Intent.createChooser(intent2, "Email senden"));
                break;
        }
    }

}
