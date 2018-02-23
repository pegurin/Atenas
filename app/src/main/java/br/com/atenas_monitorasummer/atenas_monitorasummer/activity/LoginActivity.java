package br.com.atenas_monitorasummer.atenas_monitorasummer.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.com.atenas_monitorasummer.atenas_monitorasummer.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText userLogin = findViewById(R.id.edit_text_user_login);
        final EditText passLogin = findViewById(R.id.edit_text_pass_login);
        final EditText passConfirmLogin = findViewById(R.id.edit_text_pass_confirm_login);
        final TextView tv_passConfirmLogin = findViewById(R.id.text_view_pass_confirm_login);

        Button buttonLogin = findViewById(R.id.button_login);
        final Button buttonCadastreSe = findViewById(R.id.button_cadastre_se);

        buttonCadastreSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passConfirmLogin.setVisibility(View.VISIBLE);
                tv_passConfirmLogin.setVisibility(View.VISIBLE);
                buttonCadastreSe.setVisibility(View.GONE);
            }
        });


        mAuth = FirebaseAuth.getInstance();

        //Logar
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (passConfirmLogin.getVisibility()== View.VISIBLE) {
                    if (passLogin.getText().toString().equals(passConfirmLogin.getText().toString())) {
                        //Cria um novo usuário
                        cadastrarLogin(userLogin.getText().toString(), passLogin.getText().toString());
                    } else{
                        Toast.makeText(LoginActivity.this, "Senhas diferentes!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    //Loga com um usuário já existente
                    verificarLogin(userLogin.getText().toString(), passLogin.getText().toString());
                }

            }

        });

    }

    private void verificarLogin(String userLogin, String passLogin) {
        mAuth.signInWithEmailAndPassword(userLogin, passLogin)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Erro! "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void cadastrarLogin(final String userLogin, String passLogin) {
        mAuth.createUserWithEmailAndPassword(userLogin, passLogin)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.v("Log", "task");
                        if (task.isSuccessful()) {

                            Map<String, Object> user = new HashMap<>();
                            user.put("userEmail", userLogin);
                            user.put("userID", mAuth.getUid());

                            // Add a new document with a generated ID
                            db.collection("users").document(mAuth.getUid())
                                    .set(user);

                            Toast.makeText(LoginActivity.this, "User criado!)", Toast.LENGTH_SHORT).show();
                            Log.v("Log", "Login criado");

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Log.v("Log", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

}
