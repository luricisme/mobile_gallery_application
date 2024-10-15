package com.example.album_anh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private int typeLayout = 1;
    private int scrollPos = 0;
    ImageButton btnLayout;
    ImageButton btnHome;
    ImageButton btnAlbum;
    LinearLayout header1, header2;
    RecyclerView recyclerView;
    LayoutInflater inflater;
    View childLayoutHeader1, childLayoutHeader2;

    ImageButton btnFavorImg, btnEditImg, btnDeleteImg, btnShareImg, btnBackImg, btnFunctionImg;
    ImageView imgSoloPhoto;

    List<Integer> imageResIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Layout chứa RecyclerView
        createHome();
    }

    private void showSoloPhoto(int position){
        // Đổi sang layout hình chi tiết
        setContentView(R.layout.activity_solo_picture);

        btnFavorImg = findViewById(R.id.btnFavorImg);
        btnEditImg = findViewById(R.id.btnEditImg);
        btnDeleteImg = findViewById(R.id.btnDeleteImg);
        btnShareImg = findViewById(R.id.btnShareImg);
        btnBackImg = findViewById(R.id.btnBackImg);
        btnFunctionImg = findViewById(R.id.btnFunctionImg);
        imgSoloPhoto = findViewById(R.id.imgSoloPhoto);

        imgSoloPhoto.setImageResource(imageResIds.get(position));

        btnBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_home);
                createHome();
            }
        });
    }

    private void createHome(){
        btnHome = findViewById(R.id.btnHome);
        btnAlbum = findViewById(R.id.btnAlbum);

        header1 = findViewById(R.id.header_1);
        header2 = findViewById(R.id.header_2);

        inflater = LayoutInflater.from(this);
        childLayoutHeader1 = inflater.inflate(R.layout.activity_home_header_1, header1, false);
        header1.addView(childLayoutHeader1);

        childLayoutHeader2 = inflater.inflate(R.layout.activity_home_header_2, header2, false);
        header2.addView(childLayoutHeader2);

        btnLayout = childLayoutHeader2.findViewById(R.id.btnLayout);

        imageResIds.clear();
        imageResIds.add(R.drawable.test);
        imageResIds.add(R.drawable.test1);
        imageResIds.add(R.drawable.test2);
        imageResIds.add(R.drawable.test3);
        imageResIds.add(R.drawable.test4);
        imageResIds.add(R.drawable.test5);
        imageResIds.add(R.drawable.test6);
        imageResIds.add(R.drawable.test7);
        imageResIds.add(R.drawable.test8);
        imageResIds.add(R.drawable.test9);
        imageResIds.add(R.drawable.test10);
        imageResIds.add(R.drawable.test11);

        recyclerView = findViewById(R.id.layoutViewImage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapterLayout adapter = new MyAdapterLayout(imageResIds, this, typeLayout, new MyAdapterLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int index) {
                scrollPos = position;
                showSoloPhoto(position*typeLayout+index);
            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.scrollToPosition(scrollPos);
        switch (typeLayout) {
            case 1:
                btnLayout.setImageResource(R.drawable.imglayout_1);
                break;
            case 2:
                btnLayout.setImageResource(R.drawable.imglayout_2);
                break;
            case 3:
                btnLayout.setImageResource(R.drawable.imglayout_3);
                break;
            default:
                btnLayout.setImageResource(R.drawable.imglayout_1); // Hình ảnh mặc định nếu không có trường hợp nào khớp
                break;
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header1.removeAllViews();
                header2.removeAllViews();

                childLayoutHeader1 = inflater.inflate(R.layout.activity_home_header_1, header1, false);
                header1.addView(childLayoutHeader1);

                childLayoutHeader2 = inflater.inflate(R.layout.activity_home_header_2, header2, false);
                header2.addView(childLayoutHeader2);
            }
        });
        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header1.removeAllViews();
                header2.removeAllViews();
                recyclerView.removeAllViews();

                childLayoutHeader1 = inflater.inflate(R.layout.activity_album_header_1, header1, false);
                header1.addView(childLayoutHeader1);

                childLayoutHeader2 = inflater.inflate(R.layout.activity_album_header_2, header2, false);
                header2.addView(childLayoutHeader2);
            }
        });

        btnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeLayout < 3){
                    typeLayout++;
                }
                else if (typeLayout == 3){
                    typeLayout=1;
                }

                switch (typeLayout) {
                    case 1:
                        btnLayout.setImageResource(R.drawable.imglayout_1);
                        break;
                    case 2:
                        btnLayout.setImageResource(R.drawable.imglayout_2);
                        break;
                    case 3:
                        btnLayout.setImageResource(R.drawable.imglayout_3);
                        break;
                    default:
                        btnLayout.setImageResource(R.drawable.imglayout_1); // Hình ảnh mặc định nếu không có trường hợp nào khớp
                        break;
                }
                recyclerView.scrollToPosition(0);
                adapter.setLayoutType(typeLayout); // Cập nhật adapter
            }
        });
    }
}
