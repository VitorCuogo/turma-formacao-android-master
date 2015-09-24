package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Address;
import br.com.cast.turmaformacao.taskmanager.model.entities.Cadastro;
import br.com.cast.turmaformacao.taskmanager.model.http.AddressService;
import br.com.cast.turmaformacao.taskmanager.model.persistence.CadastroRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignUp;
    private Cadastro cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindEditTextLogin();
        bindEditTextPassword();
        bindButtonLogin();
        bindButtonSignUp();
    }



    private void bindButtonLogin() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkLogin()) {
                    Intent redirectToTaskList = new Intent(LoginActivity.this, TaskListActivity.class);
                    startActivity(redirectToTaskList);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, R.string.msg_user_pass_incorrect, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean checkLogin(){

        Cadastro checkLogin = new Cadastro();

        checkLogin.setLogin(editTextLogin.getText().toString());
        checkLogin.setPassword(editTextPassword.getText().toString());

        cadastro = CadastroRepository.checkLogin(checkLogin);

        if(cadastro != null)
            return true;
        else
            return false;
    }

    private void bindButtonSignUp() {
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent redirectToCadastro = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(redirectToCadastro);
            }
        });
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
    }

}
