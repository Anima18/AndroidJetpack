package com.chris.androidjetpack.notifications;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.chris.androidjetpack.R;
import com.chris.androidjetpack.piging.PagerActivity;

import static android.app.Notification.CATEGORY_CALL;
import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static androidx.core.app.NotificationCompat.PRIORITY_MAX;

/**
 * Created by jianjianhong on 19-1-9
 */
public class NotificationActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "ANIMA_CHANNEL_ID";
    private static final String CHANNEL_NAME = "ANIMA_CHANNEL_NAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }


    public void showSimpleNotification(View view) {
        Intent resultIntent = new Intent(this, NotificationResultActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,  resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent snoozeIntent = new Intent(this, NotificationResultActivity.class);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getActivity(this, 0, snoozeIntent, 0);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                        .setAutoCancel(true)      //设置允许消失
                        .setCategory(CATEGORY_CALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)//设置最高权限
                        .setDefaults(Notification.DEFAULT_ALL)//设置声音和震动
                        .setContentIntent(resultPendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                        .addAction(android.R.drawable.ic_menu_call, "Call me", snoozePendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* 使用Heads Up Notification也有必要前提条件。
            （1）Android API >=21，即5.0以上
            （2）需要在设置中开启横幅通知权限（在设置通知管理中，不同机型可能存在差异）
            （3）需要设置震动或声音。（必须！）
            （4）需要设置Notification为最高权限。（必须！）*/

            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setFullScreenIntent(pendingIntent1, false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        // mId allows you to update the notification later on.
        notificationManager.notify(110, mBuilder.build());
    }

    public void showSimpleNotification2(View view) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_MAX)//设置最高权限
                        .setDefaults(Notification.DEFAULT_ALL)//设置声音和震动
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotificationResultActivity2.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack
        stackBuilder.addParentStack(NotificationResultActivity2.class);
        // Adds the Intent to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        // Gets a PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* 使用Heads Up Notification也有必要前提条件。
            （1）Android API >=21，即5.0以上
            （2）需要在设置中开启横幅通知权限（在设置通知管理中，不同机型可能存在差异）
            （3）需要设置震动或声音。（必须！）
            （4）需要设置Notification为最高权限。（必须！）*/

            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setFullScreenIntent(pendingIntent1, false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
        }
        // mId allows you to update the notification later on.
        mNotificationManager.notify(111, mBuilder.build());
    }

    public void showProgressNotification(View view) {
        final NotificationManager mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotifyManager.createNotificationChannel(channel);
        }
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setAutoCancel(false)
                .setSmallIcon(R.mipmap.ic_launcher);
        // Start a lengthy operation in a background thread

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            /* 使用Heads Up Notification也有必要前提条件。
            （1）Android API >=21，即5.0以上
            （2）需要在设置中开启横幅通知权限（在设置通知管理中，不同机型可能存在差异）
            （3）需要设置震动或声音。（必须！）
            （4）需要设置Notification为最高权限。（必须！）*/

            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 1, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setFullScreenIntent(pendingIntent1, false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotifyManager.createNotificationChannel(channel);
        }
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr+=5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            mBuilder.setProgress(100, incr, false);
                            // Displays the progress bar for the first time.
                            mNotifyManager.notify(0, mBuilder.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(1*1000);
                            } catch (InterruptedException e) {

                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0,0,false);
                        mNotifyManager.notify(112, mBuilder.build());
                    }
                }
        // Starts the thread by calling the run() method in its Runnable
        ).start();
    }
}
