package br.com.cast.turmaformacao.taskmanager.controllers.activities;

import android.app.ProgressDialog;
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
import br.com.cast.turmaformacao.taskmanager.model.services.CadastroBusinessServices;
import br.com.cast.turmaformacao.taskmanager.util.FormHelper;

public class CadastroActivity extends AppCompatActivity{
    private EditText editTextLogin;
    private EditText editTextPassword;
    private EditText editTextZipCode;
    private EditText editTextType;
    private EditText editTextStreet;
    private EditText editTextNeighborhood;
    private EditText editTextCity;
    private EditText editTextState;
    private Button buttonSearch;
    private Button buttonRegister;
    private Cadastro cadastro;
    private ProgressDialog progressDialog;

    public static final String PARAM_TASK = "PARAM_TASK";

    private class GetAddressTask extends AsyncTask<String, Void, Address> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(CadastroActivity.this);
            progressDialog.setMessage("Carregando");
            progressDialog.show();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddressByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);

            editTextCity.setText(address.getCity());
            editTextNeighborhood.setText(address.getNeighborhood());
            editTextState.setText(address.getState());
            editTextStreet.setText(address.getStreet());
            editTextType.setText(address.getType());
            editTextZipCode.setText(address.getZipCode());

            progressDialog.dismiss();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        initCadastro();
        bindEditTextPassword();
        bindEditTextLogin();
        bindAddress();
        bindButtonSearch();
        bindButtonRegister();
    }


    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zipCode = editTextZipCode.getText().toString();
                new GetAddressTask().execute(zipCode);
            }
        });
    }

    private void bindAddress() {
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCode);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextStreet = (EditText) findViewById(R.id.editTextStreet);
        editTextNeighborhood = (EditText) findViewById(R.id.editTextNeighborhood);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextState = (EditText) findViewById(R.id.editTextState);
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
