package br.com.cast.turmaformacao.taskmanager.controllers.adpters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public class LabelListAdapter extends BaseAdapter{
    private List<Label> labels;
    private Activity context;

    public LabelListAdapter(Activity context,List<Label> labels) {
        this.labels = labels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Label getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Label label = getItem(position);
        View view = context.getLayoutInflater().inflate(R.layout.list_item_label_task_form,parent,false);

        View color = view.findViewById(R.id.list_item_label_color);
        TextView name = (TextView) view.findViewById(R.id.list_item_label_name);
        String c = "#ffffff";

        if(label.getColor() != null){
            c = label.getColor().getHex();
        }

        int hexColor = android.graphics.Color.parseColor(c);

        color.getBackground().setColorFilter(hexColor, PorterDuff.Mode.SRC);
        name.setText(label.getName());

        return view;
    }
}
