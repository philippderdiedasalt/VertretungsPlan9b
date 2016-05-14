package tk.neunbbgg.vertretungsplan;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class Login extends ActionBarActivity implements View.OnClickListener {
    public static final String DEFAULT = "N/A";
    Button bLogin, bvergessen;
    EditText etUsername, etPassword;
    CheckBox cbspeichern;
    ImageView pl;
    public static String serverip = "wji0znhdkmk4m6wr.myfritz.net";
    public static String file_heute_url = "http://wji0znhdkmk4m6wr.myfritz.net:8081/PH_heute.htm";
    public static String file_morgen_url = "http://wji0znhdkmk4m6wr.myfritz.net:8081/PH_morgen.htm";
    public static String file_mensa_url = "http://wji0znhdkmk4m6wr.myfritz.net:8081/mensa.png";
    ProgressDialog load;

    private static final int REQUEST_READ_PHONE_STATE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_PHONE_STATE
    };

    public static void verifyphonePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            final Activity activity1 = activity;
            AlertDialog ad = new AlertDialog.Builder(activity).create();
            ad.setCancelable(false); // This blocks the 'BACK' button
            ad.setMessage("Um dich einzuloggen braucht die App Zugriff auf \ndas Telefon um deine Device-ID zubekommen.\nDies ist nötig damit die App nicht missbraucht wird!");
            ad.setTitle("Wichtig!");
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    ActivityCompat.requestPermissions(
                            activity1,
                            PERMISSIONS_STORAGE,
                            REQUEST_READ_PHONE_STATE
                    );
                }
            });
            ad.show();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String registred = sharedPreferences.getString("isRegistred", "0");

        String username = sharedPreferences.getString("usernamelogin", null);

        String password = sharedPreferences.getString("passwordlogin", null);

        String autologin = sharedPreferences.getString("ischecked", DEFAULT);

        Boolean loginsave = sharedPreferences.getBoolean("logindata", false);





        verifyphonePermissions(this);




        cbspeichern = (CheckBox) findViewById(R.id.cbspeichern);


        cbspeichern.setChecked(loginsave);

        new DownloadFileFromURL().execute(file_heute_url, getFilesDir().getPath());
        new DownloadFileFromURL2().execute(file_morgen_url, getFilesDir().getPath());
        new DownloadFileFromURL3().execute(file_mensa_url, getFilesDir().getPath());
        new DownloadFileFromURLS().execute(stundenActivity.file_stunden_url, getFilesDir().getPath());

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        pl = (ImageView) findViewById(R.id.pl);
        pl.setOnClickListener(this);
        bLogin.setOnClickListener(this);
        bvergessen = (Button) findViewById(R.id.bvergessen);
        bvergessen.setOnClickListener(this);


        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        etUsername.setText(username);
        etPassword.setText(password);

        if (autologin.equals("1")) {
            if (mWifi.isConnected()) {
                if (username != null) {
                    auth();
                }
            } else {
                if (registred.equals("1")) {
                    startActivity(new Intent(this, naviActivity.class));
                } else {
                    AlertDialog ad = new AlertDialog.Builder(this).create();
                    ad.setCancelable(false); // This blocks the 'BACK' button
                    ad.setMessage("Du bist nicht Registriert!");
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            }
        }


    }
   /** public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    } **/
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cbspeichern:
                if (checked) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("usernamelogin", etUsername.getText().toString());
                    editor.putString("passwordlogin", etPassword.getText().toString());
                    editor.putBoolean("logindata", true);
                    editor.commit();

                    Toast.makeText(this, "Gespeichert!", Toast.LENGTH_LONG).show();
                }
                // Put some meat on the sandwich
                else {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usernamelogin", null);
                    editor.putString("passwordlogin", null);
                    editor.putBoolean("logindata", false);
                    editor.commit();

                    Toast.makeText(this, "Gespeichert!", Toast.LENGTH_LONG).show();
                }
                // Remove the meat
                break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bLogin:


                auth();
                break;
            case R.id.pl:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://9b-gg.jimdo.com"));
                startActivity(browserIntent);

                break;
            case R.id.bvergessen:
                startActivity(new Intent(this, Passreset.class));
                break;


        }
    }
    void auth(){
        load = new ProgressDialog(this);
        load.setIndeterminate(true);
        load.setCancelable(false);
        load.setMessage("logging in...");
        load.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        load.show();
        new Thread(new Runnable() {
            public void run() {
                mainauth();
            }
        }).start();


    }
    void loginsuccess(boolean succ){
        load.dismiss();
        if(succ){
            startActivity(new Intent(this, naviActivity.class));
        }

    }
    void mainauth() {



        if (etUsername.getText().toString().isEmpty()) {
            loginsuccess(false);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog ad = new AlertDialog.Builder(Login.this).create();
                    ad.setCancelable(false); // This blocks the 'BACK' button
                    ad.setMessage("Kein Benutzer eingetippt!");
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            });

        }
        if (etPassword.getText().toString().isEmpty()) {
            loginsuccess(false);
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    AlertDialog ad = new AlertDialog.Builder(Login.this).create();
                    ad.setCancelable(false); // This blocks the 'BACK' button
                    ad.setMessage("Kein Passwort eingetippt!");
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            });

        }
        if (!(etUsername.getText().toString().isEmpty() || (etPassword.getText().toString().isEmpty()))) {

            try {
                Socket lolsocket = new Socket(Inet4Address.getByName(serverip), 8099);
                JSONObject jauth = new JSONObject();
                JSONObject data = new JSONObject();
                try {
                    jauth.put("command", "auth");
                    data.put("username", etUsername.getText().toString());
                    data.put("password", etPassword.getText().toString());
                    data.put("device_id", Settings.Secure.getString(this.getContentResolver(),
                            Settings.Secure.ANDROID_ID));
                    TelephonyManager tMgr = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
                    String mPhoneNumber = tMgr.getLine1Number();
                    data.put("phone", mPhoneNumber);
                    jauth.put("data", data);
                    jauth.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(lolsocket.getOutputStream())));

                pw.println(jauth);
                pw.flush();

                BufferedReader br = new BufferedReader(new InputStreamReader(lolsocket.getInputStream()));
                String message = br.readLine();
                System.out.println(message);
                JSONObject jexit = new JSONObject();
                jexit.put("command", "exit");
                jexit.toString();
                pw.println(jexit);
                pw.flush();
                pw.close();
                lolsocket.close();
                if (message.equals("true")) {

                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("isRegistred", "1");
                    editor.commit();
                    System.out.println("AndroidId: " + Settings.Secure.getString(this.getContentResolver(),
                            Settings.Secure.ANDROID_ID));
                    loginsuccess(true);

                } else if (message.equals("false")) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog ad = new AlertDialog.Builder(Login.this).create();
                            ad.setCancelable(false); // This blocks the 'BACK' button
                            ad.setMessage("Benutzername oder Passwort falsch!");
                            ad.setButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            ad.show();

                        }

                    });
                    loginsuccess(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
                loginsuccess(false);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}


class DownloadFileFromURL extends AsyncTask<String, String, String> {


    /**
     * Before starting background thread
     * Show Progress Bar Dialog
     * */

    /**
     * Downloading file in background thread
     * */

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file

            OutputStream output = new FileOutputStream(f_url[1]+"/heute.htm");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress(""+(int)((total*100)/lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }



}
class DownloadFileFromURL2 extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream(f_url[1]+"/morgen.htm");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress(""+(int)((total*100)/lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }
}
class DownloadFileFromURL3 extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream( f_url[1] + "/mensa.png");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }
}
