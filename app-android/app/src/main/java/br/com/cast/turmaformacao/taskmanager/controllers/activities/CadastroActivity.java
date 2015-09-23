package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Cadastro;
import br.com.cast.turmaformacao.taskmanager.model.services.CadastroBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class CadastroActivity extends AppCompatActivity{
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonRegister;
    private Cadastro cadastro;
    public static final String PARAM_TASK = "PARAM_TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initCadastro();
        bindEditTextPassword();
        bindEditTextLogin();
        bindButtonRegister();
    }

    private void initCadastro() {
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.cadastro = (Cadastro) extras.getParcelable(PARAM_TASK);
        }

        this.cadastro = this.cadastro == null ? new Cadastro() : this.cadastro;
    }

    private void bindButtonRegister() {
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String requiredMessage = CadastroActivity.this.getString(R.string.msg_required);

                if (!FormHelper.validateRequired(requiredMessage, editTextLogin, editTextPassword)) {
                    bindCadastro();
                    CadastroBusinessServices.save(cadastro);
                    Toast.makeText(CadastroActivity.this, CadastroBusinessServices.findAll().toString(), Toast.LENGTH_LONG).show();
                    CadastroActivity.this.finish();
                }
            }
        });
    }

    private void bindCadastro() {
        cadastro.setLogin(editTextLogin.getText().toString());
        cadastro.setPassword(editTextPassword.getText().toString());
    }

    private void bindEditTextLogin() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextLogin.setText(cadastro.getLogin() == null ? "" : cadastro.getLogin());
    }

    private void bindEditTextPassword() {
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword.setText(cadastro.getPassword() == null ? "" : cadastro.getPassword());
    }

}
