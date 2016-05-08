package tk.neunbbgg.vertretungsplan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class changepwActivity extends Activity implements View.OnClickListener {

    EditText etchpwn, etchpwap, etchpwnp, etchpwnp2;
    Button bpwchange;
    public static String serverip = "wji0znhdkmk4m6wr.myfritz.net";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepw);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("usernamelogin", null);

        etchpwn = (EditText) findViewById(R.id.etchpwrn);
        etchpwap = (EditText) findViewById(R.id.etchpwap);
        etchpwnp = (EditText) findViewById(R.id.etchpwnp);
        etchpwnp2 = (EditText) findViewById(R.id.etchpwnp2);
        bpwchange = (Button) findViewById(R.id.bpwchange);
        bpwchange.setOnClickListener(this);
        if(!(isNumeric(username))){
            etchpwn.setText(username);
        }


    }
    public static boolean isNumeric(String str)
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
    }
    @Override
    public void onClick(View v) {
        if(etchpwap.getText().toString().equals("") || etchpwnp.getText().toString().equals("") || etchpwnp2.getText().toString().equals("") || etchpwn.getText().toString().equals("")){
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setCancelable(false); // This blocks the 'BACK' button
            ad.setMessage("Du hast nicht alle Daten eingegeben!");
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }
        if (etchpwnp.getText().toString().equals(etchpwnp2.getText().toString())){
            new Thread(new Runnable() {
                public void run() {
                    auth();
                }
            }).start();
        }
        if (!(etchpwnp.getText().toString().equals(etchpwnp2.getText().toString()))){
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setCancelable(false); // This blocks the 'BACK' button
            ad.setMessage("Das wiederderholte Passwort stimmt nicht überein!");
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
        }
    }
    private void auth(){
        try {
            Socket socket = new Socket(InetAddress.getByName(serverip), 8099);
            JSONObject jauth = new JSONObject();
            JSONObject data = new JSONObject();
            try {
                jauth.put("command", "changepw");
                data.put("username", etchpwn.getText().toString());
                data.put("password", etchpwap.getText().toString());
                data.put("newpw", etchpwnp.getText().toString());
                data.put("device_id", Settings.Secure.getString(this.getContentResolver(),
                        Settings.Secure.ANDROID_ID));
                jauth.put("data", data);
                jauth.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            pw.println(jauth);
            pw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = br.readLine();
            System.out.println(message);
            pw.close();
            socket.close();
            if (message.equals("true")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog ad = new AlertDialog.Builder(changepwActivity.this).create();
                        ad.setCancelable(false); // This blocks the 'BACK' button
                        ad.setMessage("Passwort erfolgreich geändert!");
                        ad.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        ad.show();

                    }

                });
            } else if (message.equals("false")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog ad = new AlertDialog.Builder(changepwActivity.this).create();
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
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

