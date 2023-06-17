package com.example.myapplication;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.service.carrier.CarrierMessagingClientService;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ml.KoreanFood;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CameraActivity extends AppCompatActivity {

    Button camera, gallery, goodBtn;
    ImageView imageView;
    TextView result;
    int imageSize = 224;
    MyDatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        init();
        SettingListener();

        camera = findViewById(R.id.button);
        gallery = findViewById(R.id.button2);
        imageView = findViewById(R.id.imageView);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                }else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 3);

            }
        });
    }

    // result에 edit된 텍스트를 InputActivity로 전송
    private void init() {
        result = findViewById(R.id.result_wd);
        goodBtn = findViewById(R.id.good);
    }
    private void SettingListener() {
        goodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = result.getText().toString();
                Intent intent = new Intent(CameraActivity.this, InputActivity.class);
                intent.putExtra("text", input);
                startActivity(intent);
            }
        });
    }


    public void classifyImage(Bitmap image){
        try {
            KoreanFood model = KoreanFood.newInstance(getApplicationContext());

            // 이미지 출력
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(),0 ,0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for(int i = 0; i<imageSize; i++){
                for(int j =0; j<imageSize; j++){
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            KoreanFood.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"가지볶음", "간장게장", "갈비구이", "갈비찜", "갈비탕", "갈치구이", "갈치조림", "감자전", "감자조림", "감자채볶음", "감자탕", "갓김치", "건새우볶음", "경단", "계란국", "계란말이", "계란찜", "계란후라이", "고등어구이", "고등어조림", "고사리나물", "고추장진미채볶음", "고추튀김", "곰탕_설렁탕", "곱창구이", "곱창전골", "과메기", "김밥", "김치볶음밥", "김치전", "김치찌개", "김치찜", "깍두기", "깻잎장아찌", "꼬막찜", "꽁치조림", "꽈리고추무침", "꿀떡", "나박김치", "누룽지", "닭갈비", "닭계장", "닭볶음탕", "더덕구이", "도라지무침", "도토리묵", "동그랑땡", "동태찌개", "된장찌개", "두부김치", "두부조림", "땅콩조림", "떡갈비", "떡국_만두국", "떡꼬치", "떡볶이", "라면", "라볶이", "막국수", "만두", "매운탕", "멍게", "메추리알장조림", "멸치볶음", "무국", "무생채", "물냉면", "물회", "미역국", "미역줄기볶음", "배추김치", "백김치", "보쌈", "부추김치", "북엇국", "불고기", "비빔냉면", "비빔밥", "산낙지", "삼겹살", "삼계탕", "새우볶음밥", "새우튀김", "생선전", "소세지볶음", "송편", "수육", "수정과", "수제비", "숙주나물", "순대", "순두부찌개", "시금치나물", "시래기국", "식혜", "알밥", "애호박볶음", "약과", "약식", "양념게장", "양념치킨", "어묵볶음", "연근조림", "열무국수", "열무김치", "오이소박이", "오징어채볶음", "오징어튀김", "우엉조림", "유부초밥", "육개장", "육회", "잔치국수", "잡곡밥", "잡채", "장어구이", "장조림", "전복죽", "젓갈", "제육볶음", "조개구이", "조기구이", "족발", "주꾸미볶음", "주먹밥", "짜장면", "짬뽕", "쫄면", "찜닭", "총각김치", "추어탕", "칼국수", "코다리조림", "콩국수", "콩나물국", "콩나물무침", "콩자반", "파김치", "파전", "편육", "피자", "한과", "해물찜", "호박전", "호박죽", "홍어무침", "황태구이", "회무침", "후라이드치킨", "훈제오리"};
            result.setText(classes[maxPos]);

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

// 이미지 처리 requestCode가 3일경우 카메라에서 사진찍은것, 아니면 갤러리에서 가져온거임
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(resultCode==RESULT_OK){
            if(requestCode==3){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }else {
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    Bitmap imgae = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                }catch(IOException e){
                    e.printStackTrace();;
                }
                imageView.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}