package br.com.atenas_monitorasummer.atenas_monitorasummer.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.atenas_monitorasummer.atenas_monitorasummer.R;

public class NewEventActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String authID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        final EditText nameEvent = findViewById(R.id.edit_text_event_name);
        final EditText localEvent = findViewById(R.id.edit_text_event_local);
        final EditText dataEvent = findViewById(R.id.edit_text_event_data);
        final EditText timeEvent = findViewById(R.id.edit_text_event_time);
        final EditText imageEvent = findViewById(R.id.edit_text_event_image);
        final EditText descEvento = findViewById(R.id.edit_text_event_desc);

        Button buttonAddEvent = findViewById(R.id.button_event_add);

        try {
            authID = db.getApp().getUid();
        } catch (FirebaseApiNotAvailableException e) {
            e.printStackTrace();
        }
        Log.v("Log", "id=" + authID);

        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final Map<String, Object> event = new HashMap<>();
                Event event = new Event(
                nameEvent.getText().toString(),
                imageEvent.getText().toString(),
                dataEvent.getText().toString(),
                descEvento.getText().toString(),
                localEvent.getText().toString(),
                timeEvent.getText().toString());

                db.collection("users").document(authID).collection("events")
                        .add(event)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.v("Log", "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(NewEventActivity.this,"Evento criado com sucesso!", Toast.LENGTH_LONG);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.v("Log", "Error adding document", e);
                            }
                        });
            }
        });


    }
}
