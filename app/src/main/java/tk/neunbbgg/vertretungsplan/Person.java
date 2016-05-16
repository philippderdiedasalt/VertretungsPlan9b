package tk.neunbbgg.vertretungsplan;

import android.view.View;

class Person {
    String txt, txt2;
    int photoId, visible;
    View.OnClickListener photoo;

    Person(String txt, int photoId, int visible, String txt2, View.OnClickListener photoo) {
        this.txt = txt;
        this.photoId = photoId;
        this.visible = visible;
        this.txt2 = txt2;
        this.photoo = photoo;


    }
}