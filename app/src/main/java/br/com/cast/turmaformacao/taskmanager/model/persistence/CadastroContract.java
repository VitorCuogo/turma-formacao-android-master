package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Cadastro;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public final class CadastroContract {
    public static final String TABLE    = "cadastro";
    public static final String ID       = "id";
    public static final String LOGIN    = "login";
    public static final String PASSWORD = "password";

    public static final String[] COLUNS = {ID, LOGIN, PASSWORD};

    private CadastroContract(){
        super();
    }

    public static String getCreateTableScript(){
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(LOGIN + " TEXT NOT NULL, ");
        create.append(PASSWORD + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Cadastro cadastro) {
        ContentValues values = new ContentValues();

        values.put(CadastroContract.ID, cadastro.getId());
        values.put(CadastroContract.LOGIN, cadastro.getLogin());
        values.put(CadastroContract.PASSWORD, cadastro.getPassword());

        return values;
    }

    public static Cadastro getCadastro (Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Cadastro cadastro = new Cadastro();
            cadastro.setId(cursor.getLong(cursor.getColumnIndex(CadastroContract.ID)));
            cadastro.setLogin(cursor.getString(cursor.getColumnIndex(CadastroContract.LOGIN)));
            cadastro.setPassword(cursor.getString(cursor.getColumnIndex(CadastroContract.PASSWORD)));


            return cadastro;
        }

        return null;
    }

    public static List<Cadastro> getCadastros(Cursor cursor) {

        Cadastro cadastro = new Cadastro();

        List<Cadastro> cadastros = new ArrayList<>();

        while (cursor.moveToNext()) {
            cadastros.add(getCadastro(cursor));
        }

        return cadastros;

    }
}
