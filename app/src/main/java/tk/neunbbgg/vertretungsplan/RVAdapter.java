package tk.neunbbgg.vertretungsplan;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txt, txt2;
        ImageView photo;




        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            txt = (TextView)itemView.findViewById(R.id.txt);
            photo = (ImageView)itemView.findViewById(R.id.photo);
            txt2 = (TextView)itemView.findViewById(R.id.txt2);

        }
    }

    List<Person> persons;

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_row, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.txt.setText(persons.get(i).txt);
        personViewHolder.photo.setImageResource(persons.get(i).photoId);
        personViewHolder.photo.setVisibility(persons.get(i).visible);
        personViewHolder.txt2.setText(persons.get(i).txt2);
        personViewHolder.photo.setOnClickListener(persons.get(i).photoo);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}