package io.github.jan.iftemplate.ignorieren;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.jan.iftemplate.Homework;
import io.github.jan.iftemplate.R;

public class HomeworkList extends ListView {
    public HomeworkList(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAdapter(new Adapter(context));
    }

    public void addHomework(Homework homework) {
        Adapter adapter = (Adapter) getAdapter();
        adapter.list.add(homework);
        adapter.notifyDataSetChanged();
    }

    public void setHomework(ArrayList<Homework> homework) {
        Adapter adapter = (Adapter) getAdapter();
        adapter.list.clear();
        adapter.list.addAll(homework);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Homework> getHomework() {
        return ((Adapter) getAdapter()).list;
    }

    public static class Adapter extends BaseAdapter {

        private final Context context;
        private final ArrayList<Homework> list = new ArrayList<>();

        public Adapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Activity activity = (Activity) context;
            View v = (convertView != null) ? convertView : activity.getLayoutInflater().inflate(R.layout.homework_item, parent, false);
            CheckBox isDone = v.findViewById(R.id.is_done);
            isDone.setChecked(list.get(position).isDone());
            isDone.setOnCheckedChangeListener((buttonView, isChecked) -> list.get(position).setDone(isChecked));
            Button delete = v.findViewById(R.id.delete_button);
            delete.setOnClickListener((view) -> {
                list.remove(position);
                notifyDataSetChanged();
            });
            TextView name = v.findViewById(R.id.content);
            name.setText(list.get(position).getContent());
            TextView dueTime = v.findViewById(R.id.due_time_time);
            dueTime.setText(list.get(position).getDueTime());
            return v;
        }

    }

}
