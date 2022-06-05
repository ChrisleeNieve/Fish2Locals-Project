package com.app.fish2locals;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class WholesalerCategoryActivity extends AppCompatActivity {
    private ImageView fish;
    private ImageView messagesview;
    private Button LogoutBtn, CheckOrdersBtn, ManageOrdersBtn,maps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholesaler_category);

        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WholesalerCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WholesalerCategoryActivity.this, WholesalerNewOrdersActivity.class);
                startActivity(intent);
            }
        });



        ManageOrdersBtn = (Button) findViewById(R.id.manage_orders_btn);
        ManageOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WholesalerCategoryActivity.this, ManageProducts.class);
                startActivity(intent);
            }
        });


        messagesview = (ImageView) findViewById(R.id.msg_icon);
        messagesview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WholesalerCategoryActivity.this, com.app.fish2locals.ChatChannel.class);
                startActivity(intent);
            }
        });

        fish = (ImageView) findViewById(R.id.add_products_button);
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WholesalerCategoryActivity.this, WholesalerAddNewProductActivity.class);
                intent.putExtra("category", "Fish");
                startActivity(intent);
            }
        });
        maps = (Button) findViewById(R.id.mapsbutton);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlocationActivity();
            }
        });
    }
        public void openlocationActivity () {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);

        }

    }


