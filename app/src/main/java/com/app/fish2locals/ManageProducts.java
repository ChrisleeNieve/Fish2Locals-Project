package com.app.fish2locals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.fish2locals.Model.Products;
import com.app.fish2locals.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class ManageProducts extends AppCompatActivity {

    private DatabaseReference ProductsRef;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_products);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        recyclerView = findViewById(R.id.manage_products_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

        @Override
        protected void onStart () {
            super.onStart();
            FirebaseRecyclerOptions<Products> options =
                    new FirebaseRecyclerOptions.Builder<Products>()
                            .setQuery(ProductsRef, Products.class)
                            .build();

            FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                    new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {

                        @Override
                        protected void onBindViewHolder(@NonNull ProductViewHolder holder, final int position, @NonNull final Products model) {
                            holder.txtProductName.setText(model.getPname());
                            holder.txtProductDescription.setText(model.getDescription());
                            holder.txtProductPrice.setText("Price = PHP" + model.getPrice());
                            Picasso.get().load(model.getImage()).into(holder.imageView);


                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    CharSequence options[] = new CharSequence[]
                                            {
                                                    "Update",
                                                    "Delete"
                                            };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ManageProducts.this);
                                    builder.setTitle("Manage Product: ");
                                    builder.setItems(options, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if (i==0){
                                                Intent intent = new Intent(ManageProducts.this, WholesalerAddNewProductActivity.class);
                                                intent.putExtra("pid", model.getPid());
                                                startActivity(intent);
                                            }
                                            if (i==1){
                                                String uID = getRef(position).getKey();
                                                RemoveProduct(uID);

                                            }
                                            else {
                                                finish();
                                            }

                                        }
                                    });
                                    builder.show();
                                }
                            });
                        }

                        @NonNull
                        @Override
                        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                            ProductViewHolder holder = new ProductViewHolder(view);
                            return holder;
                        }
                    };
            recyclerView.setAdapter(adapter);
            adapter.startListening();
        }
    private void RemoveProduct(String uID) {
        ProductsRef.child(uID).removeValue();
    }
    }





