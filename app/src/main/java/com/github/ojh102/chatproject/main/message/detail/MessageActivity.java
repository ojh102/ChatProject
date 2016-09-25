package com.github.ojh102.chatproject.main.message.detail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ojh102.chatproject.R;
import com.github.ojh102.chatproject.api.MessageApi;
import com.github.ojh102.chatproject.data.MessageData;
import com.github.ojh102.chatproject.data.MessageResponse;
import com.github.ojh102.chatproject.data.ServerResponse;
import com.github.ojh102.chatproject.util.NetworkManager;
import com.github.ojh102.chatproject.util.PropertyManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

    public static String KEY_MESSAGE = "key_message";
    public static String FILTER_FCM = "filter_fcm";

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.editMessage)
    EditText editMessage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MessageAdapter cAdapter;
    String messageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        registerReceiver(mMessageReceiver, new IntentFilter(FILTER_FCM));

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("1:1 채팅");

        cAdapter = new MessageAdapter();
        recyclerView.setAdapter(cAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        messageId = getIntent().getStringExtra(KEY_MESSAGE);
        getData();

    }

    private void getData() {

        MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
        Call<MessageResponse> call = messageApi.getMessageList(messageId);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if(response.isSuccessful()) {
                    List<MessageData> items = response.body().getMessage();
                    cAdapter.clear();
                    for (MessageData item : items) {
                        cAdapter.add(item);
                        recyclerView.scrollToPosition(items.size() - 1);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnSend)
    public void onClickSend() {
        String fromId = PropertyManager.getInstance().getId();
        String message = editMessage.getText().toString();

        editMessage.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editMessage.getApplicationWindowToken(), 0);

        MessageApi messageApi = NetworkManager.getInstance().getApi(MessageApi.class);
        Call<ServerResponse> call = messageApi.sendMessage(messageId, fromId, message);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                getData();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MessageData data = (MessageData) intent.getSerializableExtra(MessageData.KEY_MESSAGE_RESPONSE);
            if (cAdapter != null) {
                cAdapter.add(data);
            }

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}