package com.ariful.ariful;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView data=findViewById(R.id.data);

        firebaseFirestore=FirebaseFirestore.getInstance();
        Button buttonPanel=findViewById(R.id.buttonPanel);
        buttonPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("uaer").document("abc")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        String coin_getting=task.getResult().getString("coin");
                                        data.setText(coin_getting);
                                        final int main_coin=Integer.parseInt(coin_getting);
                                        final int sub_coin=main_coin-20;
                                        firebaseFirestore.collection("uaer").document("abc")
                                                .update("coin",String.valueOf(sub_coin))
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                       if (task.isSuccessful()) {
                                                           firebaseFirestore.collection("uaer")
                                                                   .document("momin")
                                                                   .get()
                                                                   .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                           if (task.isSuccessful()) {
                                                                               String coin_getting1=task.getResult().getString("coin");
                                                                               int gievn_coin=main_coin-sub_coin;
                                                                               int sub_total1=Integer.parseInt(coin_getting1)+gievn_coin;
                                                                               firebaseFirestore.collection("uaer")
                                                                                       .document("momin")
                                                                                       .update("coin",String.valueOf(sub_total1))
                                                                                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                           @Override
                                                                                           public void onComplete(@NonNull Task<Void> task) {
                                                                                               if (task.isSuccessful()) {
                                                                                                   Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                                                                               }
                                                                                           }
                                                                                       });
                                                                           }
                                                                       }
                                                                   });

                                                       }
                                                    }
                                                });
                                        Toast.makeText(MainActivity.this, ""+coin_getting, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        firebaseFirestore.collection("uaer").document("abc")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                String coin_getting=task.getResult().getString("coin");
                                data.setText(coin_getting);
                                Toast.makeText(MainActivity.this, ""+coin_getting, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}