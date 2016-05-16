package tk.neunbbgg.vertretungsplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class telefonActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rvt;
    private List<Telefon> telefons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefon);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://9b-gg.jimdo.com/"));
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_telefon);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rvt = (RecyclerView)findViewById(R.id.rvt);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvt.setLayoutManager(llm);
        rvt.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        telefons = new ArrayList<>();
       /** try {
            Socket lolsocket = new Socket(Inet4Address.getByName(Login.serverip), 8099);
            JSONObject jauth = new JSONObject();

            try {
                jauth.put("command", "telefon");
                jauth.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(lolsocket.getOutputStream())));

            pw.println(jauth);
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(lolsocket.getInputStream()));
            boolean ws = true;
            String message;
            while(ws)
            {
                message = br.readLine();
                if (message.isEmpty()){
                    ws = false;
                    System.out.wrt

                }
                else{
                    telefons.add(new Telefon(message, ""));
                }
            }


            JSONObject jexit = new JSONObject();
            jexit.put("command", "exit");
            jexit.toString();
            pw.println(jexit);
            pw.flush();
            pw.close();
            lolsocket.close();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        **/
        telefons.add(new Telefon("Kaschin Abdullah", "27866197", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04027866197"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Pilar Binet", "71009790", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04071009790"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Anneke Brandt", "7135860", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0407135860"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Jason Brötzmann", "72910829", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04072910829"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Arne Butkereit", "71095205", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04071095205"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Nele Butkereit", "7101274", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0407101274"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Thorben Dietrich", "75366453", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04075366453"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Masi Fabian", "21998670", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04021998670"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Daniel Fidel", "67585512", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04067585512"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Robert Gieser", "71141682", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04071141682"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Ana Grimm", "2701915", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0402701915"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Philipp Harms", "71006403", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04071006403"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("David Jaeschke", "7111002", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0407111002"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Jonas Janzer", "74109920", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04074109920"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Fiona Kisjeloff", "7149856", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0407149856"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Eric Klemp", "55555466", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04055555466"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Felix Kreutzfeld", "7136784", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0407136784"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Tom Lührs", "73598289", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04073598289"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Leon Maleki", "55005827", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04055005827"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Veronika Rybalkin", "36858134", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04036858134"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Sara Schäfer", "7101249", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0407101249"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Tamim Shams", "35705956", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04035705956"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Mussa Yacob", "8005787", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:0408005787"));
                startActivity(i);
            }
        }));
        telefons.add(new Telefon("Thomas Zonca", "32038356", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:04032038356"));
                startActivity(i);
            }
        }));
        

    }


    private void initializeAdapter(){
        TelefonAdapter adaptert = new TelefonAdapter(telefons);
        rvt.setAdapter(adaptert);
    }







    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_telefon);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.telefon, menu);
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
            //Selber
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_telefon);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
