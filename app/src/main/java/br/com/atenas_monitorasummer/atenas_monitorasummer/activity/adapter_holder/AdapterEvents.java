package br.com.atenas_monitorasummer.atenas_monitorasummer.activity.adapter_holder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.atenas_monitorasummer.atenas_monitorasummer.R;
import br.com.atenas_monitorasummer.atenas_monitorasummer.activity.Event;

/**
 * Created by pegurin on 23/02/18.
 */

public class AdapterEvents extends RecyclerView.Adapter {
    private List<Event> listEvents;
    private Context context;

    public AdapterEvents(List<Event> listEvents, Context context){
        this.listEvents = listEvents;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_my_events, parent, false);
        return new ViewHolderEvents(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderEvents viewHolderEvents = (ViewHolderEvents) holder;
        Event event = listEvents.get(position);
        viewHolderEvents.name.setText(event.getName().toString());
        viewHolderEvents.date.setText(event.getDate().toString());
        Log.v("Log","bind holder"+event.getName());
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    private class ViewHolderEvents extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView date;
        final ImageView imageView;
        final CardView cardView;

        public ViewHolderEvents(View view) {
            super(view);
            name = view.findViewById(R.id.text_view_my_event_name);
            date = view.findViewById(R.id.text_view_my_event_data);
            imageView = view.findViewById(R.id.imageView_my_event_image);
            cardView = view.findViewById(R.id.cardview);
        }
    }
}
