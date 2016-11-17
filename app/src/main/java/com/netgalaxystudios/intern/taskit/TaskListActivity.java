package com.netgalaxystudios.intern.taskit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Task[] items = new Task[3];
        items[0] = new Task();
        items[0].setName("Task 1");
        items[0].setDueDate(new Date());
        items[1] = new Task();
        items[1].setName("Task 2");
        items[1].setDone(true);
        items[2] = new Task();
        items[2].setName("Task 3");

        ListView listView = (ListView)findViewById(R.id.task_list);
        listView.setAdapter(new TaskAdapter(items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TaskListActivity.this, TaskActivity.class);
                Task task = (Task)parent.getAdapter().getItem(position);
                i.putExtra(TaskActivity.EXTRA, task);
                startActivity(i);
            }
        });
    }

    private class TaskAdapter extends ArrayAdapter<Task>{
        TaskAdapter(Task[] tasks){
            super(TaskListActivity.this, R.layout.task_list_row, R.id.task_item_name, tasks);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = super.getView(position, convertView, parent);
            Task task = getItem(position);
            TextView taskName = (TextView)convertView.findViewById(R.id.task_item_name);
            taskName.setText(task.getName());

            CheckBox doneBox = (CheckBox)convertView.findViewById(R.id.task_item_done);
            doneBox.setChecked(task.isDone());

            return convertView;
        }
    }
}
