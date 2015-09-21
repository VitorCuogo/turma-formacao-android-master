package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Cadastro;



public class CadastroRepository {

    private CadastroRepository(){
        super();
    }

    public static void save(Cadastro cadastro) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = CadastroContract.getContentValues(cadastro);

        if (cadastro.getId() == null) {

            db.insert(CadastroContract.TABLE, null, values);

        } else {

            String where = CadastroContract.ID + " = ? ";
            String[] params = {cadastro.getId().toString()};
            db.update(CadastroContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = CadastroContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(CadastroContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static Cadastro checkLogin(Cadastro cadastro){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = CadastroContract.LOGIN + " = ? and " + CadastroContract.PASSWORD + " = ?";
        String[] params = {cadastro.getLogin(),cadastro.getPassword()};

        Cursor cursor = db.query(CadastroContract.TABLE, CadastroContract.COLUNS, where, params, null, null, CadastroContract.ID);

        cadastro = CadastroContract.getCadastro(cursor);

        db.close();
        dataBaseHelper.close();

        return cadastro;
    }

    public static List<Cadastro> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(CadastroContract.TABLE, CadastroContract.COLUNS, null, null, null, null, CadastroContract.ID);
        List<Cadastro> values = CadastroContract.getCadastros(cursor);

        db.close();
        dataBaseHelper.close();

        return values;

    }
}
