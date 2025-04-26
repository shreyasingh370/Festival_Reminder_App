////package com.example.shreya_festival_project;
////
////import android.os.Bundle;
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.recyclerview.widget.LinearLayoutManager;
////import androidx.recyclerview.widget.RecyclerView;
////import java.util.ArrayList;
////import java.util.List;
////
////public class MainActivity extends AppCompatActivity {
////
////    private RecyclerView recyclerView;
////    private FestivalAdapter adapter;
////    private List<Festival> festivalList;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        recyclerView = findViewById(R.id.recyclerView);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////
////        festivalList = getSampleFestivals();
////        adapter = new FestivalAdapter(festivalList);
////        recyclerView.setAdapter(adapter);
////    }
////
////    private List<Festival> getSampleFestivals() {
////        List<Festival> list = new ArrayList<>();
////        list.add(new Festival("Diwali", "2025-10-20", "Festival of Lights celebrated by Hindus."));
////        list.add(new Festival("Eid", "2025-04-21", "Celebrated by Muslims marking the end of Ramadan."));
////        list.add(new Festival("Christmas", "2025-12-25", "Celebration of the birth of Jesus Christ."));
////        return list;
////    }
////}
////
////
//
//
//package com.example.shreya_festival_project;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.shreya_festival_project.Festival;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private FestivalAdapter adapter;
//    private List<Festival> festivalList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        festivalList = getSampleFestivals();
//        adapter = new FestivalAdapter(festivalList);
//        recyclerView.setAdapter(adapter);
//
//        FloatingActionButton fab = findViewById(R.id.addFestivalFab);
//        fab.setOnClickListener(v -> showAddFestivalDialog());
//    }
//
//    private List<Festival> getSampleFestivals() {
//        List<Festival> list = new ArrayList<>();
//        list.add(new Festival("Diwali", "2025-10-20", "Festival of Lights celebrated by Hindus."));
//        list.add(new Festival("Eid", "2025-04-21", "Celebrated by Muslims marking the end of Ramadan."));
//        list.add(new Festival("Christmas", "2025-12-25", "Celebration of the birth of Jesus Christ."));
//        return list;
//    }
//
//    private void showAddFestivalDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add New Festival");
//
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_festival, null);
//        EditText nameInput = view.findViewById(R.id.inputFestivalName);
//        EditText dateInput = view.findViewById(R.id.inputFestivalDate);
//        EditText infoInput = view.findViewById(R.id.inputFestivalInfo);
//
//        builder.setView(view);
//        builder.setPositiveButton("Add", (dialog, which) -> {
//            String name = nameInput.getText().toString();
//            String date = dateInput.getText().toString();
//            String info = infoInput.getText().toString();
//
//            if (!name.isEmpty() && !date.isEmpty() && !info.isEmpty()) {
//                Festival newFestival = new Festival(name, date, info);
//                festivalList.add(newFestival);
//                adapter.notifyItemInserted(festivalList.size() - 1);
//            }
//        });
//
//        builder.setNegativeButton("Cancel", null);
//        builder.show();
//    }
//}




package com.example.shreya_festival_project;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FestivalAdapter adapter;
    private List<Festival> festivalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1001);
            }
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        festivalList = getSampleFestivals();
        adapter = new FestivalAdapter(festivalList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.addFestivalFab);
        fab.setOnClickListener(v -> showAddFestivalDialog());
    }

    private List<Festival> getSampleFestivals() {
        List<Festival> list = new ArrayList<>();
        list.add(new Festival("Diwali", "2025-10-20", "Festival of Lights celebrated by Hindus."));
        list.add(new Festival("Eid", "2025-04-21", "Celebrated by Muslims marking the end of Ramadan."));
        list.add(new Festival("Christmas", "2025-12-25", "Celebration of the birth of Jesus Christ."));
        return list;
    }

    private void showAddFestivalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Festival");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_festival, null);
        EditText nameInput = view.findViewById(R.id.inputFestivalName);
        EditText dateInput = view.findViewById(R.id.inputFestivalDate);
        EditText infoInput = view.findViewById(R.id.inputFestivalInfo);

        builder.setView(view);
        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = nameInput.getText().toString();
            String date = dateInput.getText().toString();
            String info = infoInput.getText().toString();

            if (!name.isEmpty() && !date.isEmpty() && !info.isEmpty()) {
                Festival newFestival = new Festival(name, date, info);
                festivalList.add(newFestival);
                adapter.notifyItemInserted(festivalList.size() - 1);

                showFestivalNotification(newFestival);
                scheduleFestivalReminder(newFestival); // 🔔 Reminder setup
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "festival_channel", "Festival Reminder",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Channel for Festival Reminders");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void showFestivalNotification(Festival festival) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "festival_channel")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("New Festival Added")
                .setContentText(festival.getName() + " on " + festival.getDate())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat.from(this).notify((int) System.currentTimeMillis(), builder.build());
    }

    private void scheduleFestivalReminder(Festival festival) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(festival.getDate());

            if (date == null) return;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 8); // 8 AM reminder
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Intent intent = new Intent(this, FestivalReminderReceiver.class);
            intent.putExtra("festival_name", festival.getName());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1001) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, good to go
            } else {
                // Show message if needed
            }
        }
    }

}
