/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.ojh102.chatproject.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.data.MessageData;
import com.github.ojh102.chatproject.intro.SplashActivity;
import com.github.ojh102.chatproject.util.PropertyManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static com.github.ojh102.chatproject.main.message.detail.MessageActivity.FILTER_FCM;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        String fromId = remoteMessage.getData().get("fromId");
        String name = remoteMessage.getData().get("name");
        String message = remoteMessage.getData().get("message");
        String date = remoteMessage.getData().get("date");
        String time = remoteMessage.getData().get("time");

        MessageData data = new MessageData();
        data.setFromId(fromId);
        data.setName(name);
        data.setMessage(message);
        data.setDate(date);
        data.setTime(time);

        Intent intent = new Intent(FILTER_FCM);
        intent.putExtra(MessageData.KEY_MESSAGE_RESPONSE, data);
        sendBroadcast(intent);

        sendNotification(data);
    }
    // [END receive_message]
    /**
     * Create and show a simple notification containing the received FCM message.
     * @param data FCM Data received.
     */
    private void sendNotification(MessageData data) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = null;
        if(PropertyManager.getInstance().getAlram()) {
            defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(data.getName())
                .setContentText(data.getMessage())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}