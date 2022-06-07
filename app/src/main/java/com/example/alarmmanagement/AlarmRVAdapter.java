package com.example.alarmmanagement;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmRVAdapter extends RecyclerView.Adapter<AlarmRVAdapter.ViewHolder> {

    private ArrayList<AlarmModal> AlarmModalArrayList;
    private Context context;
    private DBHandler dbHandler;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Toast message;



    public AlarmRVAdapter(ArrayList<AlarmModal> AlarmModalArrayList, Context context) {
        this.AlarmModalArrayList = AlarmModalArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_rv_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AlarmModal modal = AlarmModalArrayList.get(position);
        holder.date.setText(modal.getDate());
        holder.description.setText(modal.getDescription());
        holder.minutes.setText(modal.getMinutes());
        holder.hours.setText(modal.getHour());
        dbHandler = new DBHandler(this.context);

        holder.itemView.findViewById(R.id.bin1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = holder.description.getText().toString().substring(5,holder.description.getText().length());
                if (message != null) {
                    message.cancel();
                }

                message = Toast.makeText(context, "Alarm: "+holder.minutes.getText()+ " : "+
                        holder.hours.getText()+" has been cancelled",Toast.LENGTH_SHORT);
                message.show();

                dbHandler.deleteAlarm(String.valueOf(holder.hours.getText()),String.valueOf(holder.minutes.getText()));
                Intent i = new Intent(context, context.getClass());
                context.startActivity(i);

            }
        });
        holder.itemView.findViewById(R.id.replay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message != null) {
                    message.cancel();
                }

                message = Toast.makeText(context, "Alarm has been set at: "+holder.minutes.getText()+ " : "+
                        holder.hours.getText(),Toast.LENGTH_SHORT);
                message.show();

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(Calendar.HOUR_OF_DAY,Integer.parseInt(holder.minutes.getText()+""));
                calendar2.set(Calendar.MINUTE,Integer.parseInt(holder.hours.getText()+""));
                calendar2.set(Calendar.SECOND, 0);
                calendar2.set(Calendar.MILLISECOND, 0);
                if(calendar2.before(Calendar.getInstance())) {
                    calendar2.add(Calendar.DATE, 1);
                }
                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                String id = holder.description.getText().toString().substring(5,holder.description.getText().length());
                pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(id)+5, intent, 0);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), pendingIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return AlarmModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date, description, minutes, hours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            date = itemView.findViewById(R.id.idDate);
            description = itemView.findViewById(R.id.description);
            minutes = itemView.findViewById(R.id.minutes);
            hours = itemView.findViewById(R.id.hour);
        }
    }

}
