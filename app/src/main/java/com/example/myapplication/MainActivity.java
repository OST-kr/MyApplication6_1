package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button myButton;
    Button myButton1;
    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DB에 내용 추가
        dbHelper = new MyDatabaseHelper(this);
        dbHelper.addFood("갈비구이", "구이", "3");
        dbHelper.addFood("갈치구이", "구이", "3");
        dbHelper.addFood("감자전", "구이", "2");
        dbHelper.addFood("계란말이", "구이", "2");
        dbHelper.addFood("계란후라이", "구이", "2");
        dbHelper.addFood("고등어구이", "구이", "3");
        dbHelper.addFood("곱창구이", "구이", "2");
        dbHelper.addFood("과메기", "구이", "3");
        dbHelper.addFood("김치전", "구이", "5");
        dbHelper.addFood("닭갈비", "구이", "5");
        dbHelper.addFood("갈치구이", "구이", "3");
        dbHelper.addFood("더덕구이", "구이", "5");
        dbHelper.addFood("떡갈비", "구이", "5");
        dbHelper.addFood("불고기", "구이", "5");
        dbHelper.addFood("삼겹살", "구이", "3");
        dbHelper.addFood("생선전", "구이", "5");
        dbHelper.addFood("장어구이", "구이", "3");
        dbHelper.addFood("조개구이", "구이", "3");
        dbHelper.addFood("조기구이", "구이", "3");
        dbHelper.addFood("파전", "구이", "5");
        dbHelper.addFood("피자", "구이", "7");
        dbHelper.addFood("호박전", "구이", "5");
        dbHelper.addFood("황태구이", "구이", "3");
        dbHelper.addFood("훈제오리", "구이", "7");
        dbHelper.addFood("갈비탕", "국", "180");
        dbHelper.addFood("감자탕", "국", "180");
        dbHelper.addFood("계란국", "국", "180");
        dbHelper.addFood("곰탕", "국", "180");
        dbHelper.addFood("곱창전골", "국", "180");
        dbHelper.addFood("김치찌개", "국", "180");
        dbHelper.addFood("닭계장", "국", "180");
        dbHelper.addFood("닭볶음탕", "국", "180");
        dbHelper.addFood("동태찌개", "국", "180");
        dbHelper.addFood("된장찌개", "국", "180");
        dbHelper.addFood("떡국", "국", "180");
        dbHelper.addFood("매운탕", "국", "180");
        dbHelper.addFood("무국", "국", "180");
        dbHelper.addFood("미역국", "국", "180");
        dbHelper.addFood("북엇국", "국", "180");
        dbHelper.addFood("삼계탕", "국", "180");
        dbHelper.addFood("수제비", "국", "180");
        dbHelper.addFood("순두부찌개", "국", "180");
        dbHelper.addFood("시래기국", "국", "180");
        dbHelper.addFood("육개장", "국", "180");
        dbHelper.addFood("추어탕", "국", "180");
        dbHelper.addFood("콩나물국", "국", "180");
        dbHelper.addFood("갓김치", "김치", "30");
        dbHelper.addFood("깍두기", "김치", "30");
        dbHelper.addFood("깻잎장아찌", "김치", "365");
        dbHelper.addFood("나박김치", "김치", "30");
        dbHelper.addFood("두부김치", "김치", "23");
        dbHelper.addFood("배추김치", "김치", "30");
        dbHelper.addFood("백김치", "김치", "30");
        dbHelper.addFood("부추김치", "김치", "30");
        dbHelper.addFood("열무김치", "김치", "30");
        dbHelper.addFood("오이소박이", "김치", "30");
        dbHelper.addFood("총각김치", "김치", "30");
        dbHelper.addFood("파김치", "김치", "30");
        dbHelper.addFood("고사리나물", "나물", "3");
        dbHelper.addFood("무생채", "나물", "6");
        dbHelper.addFood("숙주나물", "나물", "5");
        dbHelper.addFood("시금치나물", "나물", "6");
        dbHelper.addFood("경단", "떡", "2");
        dbHelper.addFood("꿀떡", "떡", "1");
        dbHelper.addFood("떡꼬치", "떡", "3");
        dbHelper.addFood("떡볶이", "떡", "5");
        dbHelper.addFood("송편", "떡", "1");
        dbHelper.addFood("라면", "면", "1");
        dbHelper.addFood("라볶이", "면", "3");
        dbHelper.addFood("막국수", "면", "3");
        dbHelper.addFood("물냉면", "면", "3");
        dbHelper.addFood("비빔냉면", "면", "3");
        dbHelper.addFood("짜장면", "면", "1");
        dbHelper.addFood("짬뽕", "면", "1");
        dbHelper.addFood("쫄면", "면", "3");
        dbHelper.addFood("칼국수", "면", "2");
        dbHelper.addFood("콩국수", "면", "3");
        dbHelper.addFood("열무국수", "면", "3");
        dbHelper.addFood("잔치국수", "면", "3");
        dbHelper.addFood("잡채", "면", "3");
        dbHelper.addFood("꽈리고추무침", "무침", "6");
        dbHelper.addFood("도라지무침", "무침", "5");
        dbHelper.addFood("도토리묵", "무침", "7");
        dbHelper.addFood("콩나물무침", "무침", "6");
        dbHelper.addFood("홍어무침", "무침", "6");
        dbHelper.addFood("회무침", "무침", "5");
        dbHelper.addFood("김밥", "밥", "1");
        dbHelper.addFood("김치볶음밥", "밥", "3");
        dbHelper.addFood("누룽지", "밥", "7");
        dbHelper.addFood("비빔밥", "밥", "2");
        dbHelper.addFood("새우볶음밥", "밥", "2");
        dbHelper.addFood("새우볶음밥", "밥", "2");
        dbHelper.addFood("알밥", "밥", "2");
        dbHelper.addFood("유부초밥", "밥", "2");
        dbHelper.addFood("잡곡밥", "밥", "2");
        dbHelper.addFood("주먹밥", "밥", "2");
        dbHelper.addFood("가지볶음", "볶음", "5");
        dbHelper.addFood("감자채볶음", "볶음", "6");
        dbHelper.addFood("건새우볶음", "볶음", "6");
        dbHelper.addFood("고추장진미채볶음", "볶음", "10");
        dbHelper.addFood("멸치볶음", "볶음", "6");
        dbHelper.addFood("미역줄기볶음", "볶음", "6");
        dbHelper.addFood("소세지볶음", "볶음", "4");
        dbHelper.addFood("애호박볶음", "볶음", "5");
        dbHelper.addFood("어묵볶음", "볶음", "5");
        dbHelper.addFood("오징어채볶음", "볶음", "14");
        dbHelper.addFood("제육볶음", "볶음", "5");
        dbHelper.addFood("주꾸미볶음", "볶음", "5");
        dbHelper.addFood("보쌈", "쌈", "14");
        dbHelper.addFood("수육", "쌈", "3");
        dbHelper.addFood("편육", "쌈", "2");
        dbHelper.addFood("수정과", "음청", "60");
        dbHelper.addFood("식혜", "음청", "5");
        dbHelper.addFood("약과", "음청", "150");
        dbHelper.addFood("약식", "음청", "2");
        dbHelper.addFood("한과", "음청", "180");
        dbHelper.addFood("간장게장", "조림", "7");
        dbHelper.addFood("갈치조림", "조림", "3");
        dbHelper.addFood("감자조림", "조림", "3");
        dbHelper.addFood("고등어조림", "조림", "7");
        dbHelper.addFood("꽁치조림", "조림", "7");
        dbHelper.addFood("두부조림", "조림", "6");
        dbHelper.addFood("땅콩조림", "조림", "5");
        dbHelper.addFood("메추리알장조림", "조림", "5");
        dbHelper.addFood("양념게장", "조림", "15");
        dbHelper.addFood("연근조림", "조림", "7");
        dbHelper.addFood("우엉조림", "조림", "5");
        dbHelper.addFood("장조림", "조림", "5");
        dbHelper.addFood("코다리조림", "조림", "7");
        dbHelper.addFood("콩자반", "조림", "5");
        dbHelper.addFood("전복죽", "죽", "2");
        dbHelper.addFood("호박죽", "죽", "2");
        dbHelper.addFood("갈비찜", "찜", "4");
        dbHelper.addFood("계란찜", "찜", "2");
        dbHelper.addFood("김치찜", "찜", "4");
        dbHelper.addFood("꼬막찜", "찜", "3");
        dbHelper.addFood("순데", "찜", "2");
        dbHelper.addFood("족발", "찜", "3");
        dbHelper.addFood("찜닭", "찜", "5");
        dbHelper.addFood("해물찜", "찜", "4");
        dbHelper.addFood("고추튀김", "튀김", "4");
        dbHelper.addFood("동그랑땡", "튀김", "4");
        dbHelper.addFood("새우튀김", "튀김", "4");
        dbHelper.addFood("양념치킨", "튀김", "3");
        dbHelper.addFood("오징어튀김", "튀김", "4");
        dbHelper.addFood("후라이드치킨", "튀김", "3");
        dbHelper.addFood("멍게", "회", "8");
        dbHelper.addFood("물회", "회", "1");
        dbHelper.addFood("산낙지", "회", "1");
        dbHelper.addFood("육회", "회", "1");



        //음식추가 버튼
        myButton = findViewById(R.id.addfood);
        //냉장고 이동 버튼
        myButton1 = findViewById(R.id.myrefrigerator);
        // 음식추가 버튼 동작
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        // 냉장고 이동 버튼 동작
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyRefrigerator.class);
                startActivity(intent);
            }
        });
    }
}
