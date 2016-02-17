package tk.neunbbgg.vertretungsplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Login extends ActionBarActivity implements View.OnClickListener {
    public static final String DEFAULT="N/A";
    Button bLogin;
    EditText etUsername;
    EditText etPassword;
    ImageView pl;
    public static String file_heute_url = "http://gymglinde.de/typo40/fileadmin/vertretungsplan/VertretungAktuell/PH_heute.htm";
    public static String file_morgen_url = "http://gymglinde.de/typo40/fileadmin/vertretungsplan/VertretungAktuell/PH_morgen.htm";
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String autologin = sharedPreferences.getString("ischecked", DEFAULT);

        new DownloadFileFromURL().execute(file_heute_url);
        new DownloadFileFromURL2().execute(file_morgen_url);





        if (autologin.equals("1")){

            startActivity(new Intent(this,naviActivity.class));
        }








        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        pl = (ImageView) findViewById(R.id.pl);
        pl.setOnClickListener(this);
        bLogin.setOnClickListener(this);







}



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin:

            if (etUsername.getText().toString().equals("9b")){

                if (etPassword.getText().toString().equals("9berta")){
                    startActivity(new Intent(this, naviActivity.class));
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


    void downloadFromUrl(URL url, String localFilename) throws IOException {
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URLConnection urlConn = url.openConnection();//connect

            is = urlConn.getInputStream();               //get connection inputstream
            fos = new FileOutputStream(localFilename);   //open outputstream to local file

            byte[] buffer = new byte[4096];              //declare 4KB buffer
            int len;

            //while we have availble data, continue downloading and storing to local file
            while ((len = is.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } finally {
                if (fos != null) {
                    fos.close();
                }
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
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory()+"/heute.htm");

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
            OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory()+"/morgen.htm");

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
