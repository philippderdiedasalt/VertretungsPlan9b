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

public class Passreset extends Activity implements View.OnClickListener {

    EditText etpwrn, etpwre, etpwrk;
    Button bpwreset;
    public static String serverip = "wji0znhdkmk4m6wr.myfritz.net";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passreset);

        etpwrn = (EditText) findViewById(R.id.etpwrn);
        etpwre = (EditText) findViewById(R.id.etpwre);
        etpwrk = (EditText) findViewById(R.id.etpwrk);

        bpwreset = (Button) findViewById(R.id.bpwreset);
        bpwreset.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("usernamelogin", null);
        if(isNumeric(username)){
            etpwrk.setText(username);
        }else {
            etpwrn.setText(username);
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
        final String andoid = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        new Thread(new Runnable() {
            public void run() {
                authwithemail(etpwrn.getText().toString(), etpwre.getText().toString(), etpwrk.getText().toString(), andoid);
            }
        }).start();
    }

    private void authwithemail(String name, String email, String klassbookid, String androidid)
    {
        String message = null;

        try {
            Socket socket = new Socket(InetAddress.getByName(serverip), 8099);
            JSONObject jauth = new JSONObject();
            JSONObject data = new JSONObject();
            try {
                jauth.put("command", "passwordreset");
                data.put("username", name);
                data.put("email", email);
                data.put("klassbookid", klassbookid);
                data.put("device_id", androidid);
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
            pw.close();
            socket.close();

            if(message.equals("true")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog ad = new AlertDialog.Builder(Passreset.this).create();
                        ad.setCancelable(false); // This blocks the 'BACK' button
                        ad.setMessage("Das Passwort wird an deine Email gesendet!");
                        ad.setButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        ad.show();

                    }

                });
            } else if(message.equals("false")){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog ad = new AlertDialog.Builder(Passreset.this).create();
                        ad.setCancelable(false); // This blocks the 'BACK' button
                        ad.setMessage("Die Daten stimmen nicht überein!");
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
