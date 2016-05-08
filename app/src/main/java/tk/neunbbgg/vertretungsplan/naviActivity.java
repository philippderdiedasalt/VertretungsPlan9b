package tk.neunbbgg.vertretungsplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

public class naviActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public static String file_version_url = "https://dl.dropboxusercontent.com/u/270150900/version.txt";
    Button blogout1;
    ImageButton bup;
    ImageView pm1;
    CheckBox cb1;
    public static final String DEFAULT="N/A";
    public String path = "/variables";
    public String version;
    public static String serverip = "wji0znhdkmk4m6wr.myfritz.net";
    String versionofonapp = null;
    public String ver123, news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences1=getSharedPreferences("MyData", Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String autologin = sharedPreferences.getString("ischecked", DEFAULT);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://9b-gg.jimdo.com/"));
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_navi);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        blogout1 = (Button) findViewById(R.id.blogout1);
        bup = (ImageButton) findViewById(R.id.bup);
        bup.setOnClickListener(this);
        pm1 = (ImageView) findViewById(R.id.pm1);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        if (autologin.equals("1")){
            cb1.setChecked(true);
        }


        blogout1.setOnClickListener(this);
        pm1.setOnClickListener(this);

        new Thread(new Runnable() {
            public void run() {
                //news = getnews();
                getversion();
            }
        }).start();


    }
    private String getnews(){
        String message = null;
        try {
            Socket socket2 = new Socket(InetAddress.getByName(serverip), 8099);
            JSONObject jauth = new JSONObject();
            JSONObject data = new JSONObject();
            try {
                jauth.put("command", "news");
                data.put("nr", "5");
                jauth.put("data", data);
                jauth.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream())));

            pw.println(jauth);
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            message = br.readLine();
            System.out.println(message);
            news = message;

            pw.close();
            socket2.close();




        } catch (IOException e) {
            e.printStackTrace();

        }
        return message;
    }
    private void getversion() {
        String message = null;
        try {
            Socket socket = new Socket(InetAddress.getByName(serverip), 8099);
            JSONObject jauth = new JSONObject();
            JSONObject data = new JSONObject();
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("usernamelogin", null);
                jauth.put("command", "version");
                data.put("nr", "5");
                data.put("username", username);
                data.put("version", Build.VERSION.RELEASE);
                jauth.put("data", data);
                jauth.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            pw.println(jauth);
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = br.readLine();
            System.out.println(message);
            ver123 = message;
            message = br.readLine();
            System.out.println(message);
            news = message;
            news = news.replaceAll("@", "\n");
            news = news.replaceAll("ue", "ü");
            news = news.replaceAll("ae", "ä");
            news = news.replaceAll("oe", "ü");
            socket.close();
            SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("news", news);
            editor.commit();
            if(!(ver123.equals(MainActivity.appversion))){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog ad = new AlertDialog.Builder(naviActivity.this).create();
                        ad.setCancelable(false); // This blocks the 'BACK' button
                        ad.setMessage("Update verfügbar!\nVersion: " + ver123 + "\nNeue Funktionen:\n"+ news);
                        ad.setButton("Holen!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://github.com/philippDerDieDas/VertretungsPlan9b/raw/master/app/build/outputs/apk/app-debug.apk"));
                                startActivity(intent);

                                dialog.dismiss();
                            }
                        });
                        ad.show();

                    }

                });
            }


            pw.close();





        } catch (IOException e) {
            e.printStackTrace();

        }


    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_navi);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.action_aktu){
            new DownloadFileFromURL().execute(Login.file_heute_url, getFilesDir().getPath());
            new DownloadFileFromURL2().execute(Login.file_morgen_url, getFilesDir().getPath());
            new DownloadFileFromURL3().execute(Login.file_mensa_url, getFilesDir().getPath());
            new DownloadFileFromURLS().execute(stundenActivity.file_stunden_url, getFilesDir().getPath());
            Toast.makeText(getApplicationContext() , "Alles Aktualisiert", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.action_chpw){
            startActivity(new Intent(this, changepwActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, bilderActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this, mensaActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, plan1Activity.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(this, plan2Activity.class));
        }else if (id == R.id.nav_view) {
            startActivity(new Intent(this, haActivity.class));
        }else if (id == R.id.nav_k){
            startActivity(new Intent(this, termineActivity.class));
        }else if (id == R.id.stundenplan){
            startActivity(new Intent(this, stundenActivity.class));
        }else if (id == R.id.telefon){
            startActivity(new Intent(this, telefonActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_navi);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.blogout1:

                startActivity(new Intent(this, Login.class));

                break;

            case R.id.pm1:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://9b-gg.jimdo.com"));
                startActivity(browserIntent);

                break;
            case R.id.bup:
                new Thread(
                        new Runnable() {
                    public void run() {
                        //news = getnews();
                        getversion();
                    }
                }).start();



        }
    }



    public void onCheckboxClicked1(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cb1:
                if (checked){
                    SharedPreferences sharedPreferences=getSharedPreferences("MyData",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("ischecked", "1");
                    editor.commit();

                    Toast.makeText(this, "Gespeichert!", Toast.LENGTH_LONG).show();
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
