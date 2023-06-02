package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.capstone.MessageAdapter;
import com.example.capstone.MyMessage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button logoutBtn;
    private FirebaseAuth auth;

    RecyclerView recycler_view;
    TextView tv_welcome;
    EditText et_msg;
    ImageButton btn_send;

    List<MyMessage> messageList;
    MessageAdapter messageAdapter;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String MY_SECRET_KEY = "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";

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.nav_timetable:
                        Intent intentTimeTable = new Intent(MainActivity.this, TimeTableActivity.class);
                        startActivity(intentTimeTable);
                        return true;
                    default:
                        return false;
                }
            }
        });


        recycler_view = findViewById(R.id.recycler_view);
        tv_welcome = findViewById(R.id.tv_welcome);
        et_msg = findViewById(R.id.et_msg);
        btn_send = findViewById(R.id.btn_send);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        recycler_view.setLayoutManager(manager);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recycler_view.setAdapter(messageAdapter);

        client = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = et_msg.getText().toString().trim();
                addToChat(question, MyMessage.SENT_BY_ME);
                et_msg.setText("");
                callAPI(question);
                tv_welcome.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    void addToChat(String message, String sentBy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new MyMessage(message, sentBy));
                messageAdapter.notifyDataSetChanged();
                recycler_view.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response) {
        messageList.remove(messageList.size() - 1);

        addToChat(response, MyMessage.SENT_BY_BOT);
    }

    void callAPI(String question) {
        //okhttp
        messageList.add(new MyMessage("...", MyMessage.SENT_BY_BOT));

        if (question.equals("내일 제주도 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주도 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주도 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주도 날씨알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주시 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }
        if (question.equals("내일 제주시 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주시 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주시 날씨알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 제주시 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 날씨알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 제주시 날씨알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 제주시 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 제주시 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 제주시 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 제주시 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주시 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주시 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주시 날씨알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주시 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주시 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주시 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주도 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주도 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주도 날씨 어떄?")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주도 날씨 어떄요?")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주도 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 제주도 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 제주도 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=제주도+날씨 " ;
            addResponse(websiteLink);
            return;
        }


        if (question.equals("내일 부산 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 부산 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 부산 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 부산 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 부산 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 부산 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 부산 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 부산 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 부산 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 부산 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 부산 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 부산 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 부산 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("부산 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("부산 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("부산 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("부산 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 부산 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=부산+날씨&oquery=중구+남포동&tqi=icAbWwprvxZsskYThfossssssw4-436704 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 서울 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 서울 날씨알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 서울 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("내일 서울 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "내일 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 서울 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 서울 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 서울 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("다음주 서울 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "다음주 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 서울 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 서울 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 서울 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("오늘 서울 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 날씨")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 날씨 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 날씨 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 날씨 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "오늘 서울 날씨 입니다!! \n * 네이버 날씨 * \n\n - 링크 확인 \n https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=서울+날씨&oquery=야놀자&tqi=icmXZdprvN8ss6RAGINssssstR0-493104 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 업체 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 업체 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 업체")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 업체 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌터카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌터카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌터카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌터카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌터카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌터카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌터카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 업체 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 업체 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 업체")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 업체 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌터카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌터카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌터카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌터카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌터카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌터카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌터카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 업체 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 업체 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 업체")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 업체 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 근처 렌트카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌트카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌트카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌트카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌트카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌트카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주공항 렌트카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 업체 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 업체 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 업체")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 업체 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 근처 렌트카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌트카 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌트카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌트카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌트카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌트카 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주 공항 렌트카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주공항 근처 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }
        if (question.equals("제주도 렌터카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 업체 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 업체")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }
        if (question.equals("제주도 렌터카 업체 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌터카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }
        if (question.equals("제주도 렌트카 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 업체 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 업체")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }
        if (question.equals("제주도 렌트카 업체 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주도 렌트카 업체 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주도 렌터카 업체 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 제주속으로 \n https://jejussok.com/?ACE_REF=adwords_g&ACE_KW=&gad=1&gclid=CjwKCAjwg-GjBhBnEiwAMUvNW6v_VSg0oGOY5YWw7tKGS-0Avs0XBo_-SAeDCqmZZOT2KWWZWHG-iRoCAL0QAvD_BwE \n\n - sk렌터카 \n https://homepage.skcarrental.com/?utm_source=google_pmax_pc&utm_medium=da&utm_campaign=dn_skr_jeju&utm_content=zero&BSCPN=SKR_JEJU&BSPRG=GDN&BSCCN1=SKR_JEJU&epe_vid=208&epe_tcd=312425642&gclid=CjwKCAjwg-GjBhBnEiwAMUvNWyrhLYOmVxQHEKj_nIrHsYLaG2zliZCtqRNkmlNsvzzu02epr5X_5hoCxY4QAvD_BwE " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 숙소 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 숙소 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 숙소 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 숙소")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 숙소 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 숙소 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 호텔 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 근처 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 호텔 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 호텔 알려줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 호텔")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 호텔 알려주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("제주시 추천 호텔 알려 주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "제주시 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/제주시?keyword=제주시&searchKeyword=제주시 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/제주시--제주특별자치도--대한민국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=제주시%2C%20제주특별자치도&place_id=ChIJ_XltioXgDDUR4pyR4WmImks&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageb " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 숙소 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 숙소 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        } if (question.equals("명동 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        } if (question.equals("명동 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000v/detailpagem " ;
            addResponse(websiteLink);
            return;
        } if (question.equals("명동 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagepㅍ " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 숙소 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 숙소 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 숙소 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 숙소 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 숙소 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 호텔 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("서울 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "서울 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/서울/keyword-all?advert=KEYWORD&keyword=서울&searchKeyword=서울&pathDivision=keyword-all \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000 " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 호텔 추천해")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        } if (question.equals("명동 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        } if (question.equals("명동 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        } if (question.equals("명동 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("명동 근처 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "명동 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/명동?keyword=명동&searchKeyword=명동 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/서울-명동/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=5&channel=EXPLORE&refinement_paths%5B%5D=%2Fhomes&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagem " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://1223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("해운대 근처 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "해운대 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20해운대?keyword=부산%20해운대&searchKeyword=부산%20해운대 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=해운대&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagep " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 근처 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("광안리 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "광안리 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/부산%20광안리?keyword=부산%20광안리&searchKeyword=부산%20광안리 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=광안리&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpageo " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 호텔 추천해줘")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 호텔 추천")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 호텔 추천해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen" ;
            addResponse(websiteLink);
            return;
        }

        if (question.equals("경복궁 근처 호텔 추천 해주세요")) {
            // 특정 질문에 대한 웹사이트 링크를 설정
            String websiteLink = "경복궁 근처 추천 호텔 정보입니다! \n * 아래 사이트 들을 참고 해주세요 * \n\n - 야놀자 \n https://www.yanolja.com/search/경복궁?keyword=경복궁&searchKeyword=경복궁 \n\n - 에어비앤비 \n https://www.airbnb.co.kr/s/한국/homes?tab_id=home_tab&flexible_trip_lengths%5B%5D=one_week&price_filter_input_type=0&price_filter_num_nights=1&channel=EXPLORE&date_picker_type=calendar&checkin=2023-06-09&checkout=2023-06-10&search_type=unknown&query=경복궁&refinement_paths%5B%5D=%2Fhomes \n\n- 4P 예약 \n  http://223.194.157.156:3000/detailpagen " ;
            addResponse(websiteLink);
            return;
        }



        //추가된 내용
        JSONArray arr = new JSONArray();
        JSONObject baseAi = new JSONObject();
        JSONObject userMsg = new JSONObject();
        try {
            //AI 속성설정
            baseAi.put("role", "user");
            baseAi.put("content", "You are a helpful and kind AI Assistant.");
            //유저 메세지
            userMsg.put("role", "user");
            userMsg.put("content", question);
            //array로 담아서 한번에 보낸다
            arr.put(baseAi);
            arr.put(userMsg);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JSONObject object = new JSONObject();
        try {
            //모델명 변경
            object.put("model", "gpt-3.5-turbo");
            object.put("messages", arr);
//            아래 put 내용은 삭제하면 된다
//            object.put("model", "text-davinci-003");
//            object.put("prompt", question);
//            object.put("max_tokens", 4000);
//            object.put("temperature", 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(object.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")  //url 경로 수정됨
                .header("Authorization", "Bearer " + MY_SECRET_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response due to " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        //아래 result 받아오는 경로가 좀 수정되었다.
                        String result = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    addResponse("Failed to load response due to " + response.body().string());
                }
            }
        });
    }

}