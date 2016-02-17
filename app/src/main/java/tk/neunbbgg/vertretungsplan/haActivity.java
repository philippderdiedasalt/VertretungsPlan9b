package tk.neunbbgg.vertretungsplan;

import android.content.Intent;
import android.net.Uri;
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
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

public class haActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    WebView wha;
    ImageButton bka;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wha = (WebView) findViewById(R.id.wha);
        String urlka = "https://dl.dropboxusercontent.com/u/270150900/ha.html";
        wha.loadUrl(urlka);
        bka = (ImageButton) findViewById(R.id.bka);
        bka.setOnClickListener(this);
        wha.getSettings().setJavaScriptEnabled(true);
        wha.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://9bgg.tk/"));
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ha, menu);
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
            return true;
        }
        else if (id == R.id.action_aktu){
            new DownloadFileFromURL().execute(Login.file_heute_url);
            new DownloadFileFromURL().execute(Login.file_morgen_url);
            new DownloadFileFromURLVersion().execute(naviActivity.file_version_url);
            new DownloadFileFromURLS().execute(stundenActivity.file_stunden_url);
            Toast.makeText(getApplicationContext(), "Alles Aktualisiert", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(this, naviActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, bilderActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(this,mensaActivity.class));
        } else if (id == R.id.nav_view) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this,plan1Activity.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(this,plan2Activity.class));
        }else if (id == R.id.nav_k){
            startActivity(new Intent(this, termineActivity.class));
        }else if (id == R.id.stundenplan){
            startActivity(new Intent(this, stundenActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bka: {
                String urlka="https://dl.dropboxusercontent.com/u/270150900/ha.html";
                wha.loadUrl(urlka);
            }
        }
    }
}
