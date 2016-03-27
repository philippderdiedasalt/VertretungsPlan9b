package tk.neunbbgg.vertretungsplan;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.RemoteViews;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Implementation of App Widget functionality.
 */
public class VplanWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.vplan_widget);

        String strURL = "http://wji0znhdkmk4m6wr.myfritz.net:8081/heute.png";
        try{
            URL urlURL = new URL(strURL);
            HttpURLConnection con = (HttpURLConnection)urlURL.openConnection();
            InputStream is = con.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);
            views.setImageViewBitmap(R.id.vwidget, bmp);
        } catch(Exception e) {
            e.printStackTrace();
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.vplan_widget);
        String strURL = "http://wji0znhdkmk4m6wr.myfritz.net:8081/heute.png";
        try{
            URL urlURL = new URL(strURL);
            HttpURLConnection con = (HttpURLConnection)urlURL.openConnection();
            InputStream is = con.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);
            views.setImageViewBitmap(R.id.vwidget, bmp);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.vplan_widget);
        String strURL = "http://wji0znhdkmk4m6wr.myfritz.net:8081/heute.png";
        try{
            URL urlURL = new URL(strURL);
            HttpURLConnection con = (HttpURLConnection)urlURL.openConnection();
            InputStream is = con.getInputStream();
            Bitmap bmp = BitmapFactory.decodeStream(is);
            views.setImageViewBitmap(R.id.vwidget, bmp);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

