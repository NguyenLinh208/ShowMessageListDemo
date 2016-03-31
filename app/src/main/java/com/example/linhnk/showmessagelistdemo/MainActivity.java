package com.example.linhnk.showmessagelistdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.show_chat_fragment)
    Button showChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.show_chat_fragment)
    void showChafFragment() {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        ChatFragment chatFragment = new ChatFragment();
        fragmentTransaction.replace(R.id.fr_content, chatFragment, ChatFragment.TAG);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        showChatButton.setVisibility(View.GONE);
    }
}
