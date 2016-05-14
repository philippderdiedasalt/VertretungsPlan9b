package tk.neunbbgg.vertretungsplan;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TelefonAdapter extends RecyclerView.Adapter<TelefonAdapter.TelefonViewHolder> {



    public static class TelefonViewHolder extends RecyclerView.ViewHolder {

        CardView cvt;
        TextView name, number;



        TelefonViewHolder(View itemView) {
            super(itemView);

            cvt = (CardView)itemView.findViewById(R.id.cvt);
            name = (TextView)itemView.findViewById(R.id.name);
            number = (TextView)itemView.findViewById(R.id.number);

        }


    }

    public TelefonAdapter(List<Telefon> telefons){
        this.telefons = telefons;
    }

    List<Telefon> telefons;

    @Override
    public TelefonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_list_row2, viewGroup, false);
        TelefonViewHolder pvh = new TelefonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TelefonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(telefons.get(i).name);
        personViewHolder.number.setText(telefons.get(i).number);
    }

    @Override
    public int getItemCount() {
        return telefons.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}