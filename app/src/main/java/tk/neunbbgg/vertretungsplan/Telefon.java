package tk.neunbbgg.vertretungsplan;


import android.view.View;

class Telefon {
    String name, number;

    View.OnClickListener telo;


    Telefon(String name, String number, View.OnClickListener telo) {
        this.name = name;
        this.number = number;
        this.telo = telo;
    }
}