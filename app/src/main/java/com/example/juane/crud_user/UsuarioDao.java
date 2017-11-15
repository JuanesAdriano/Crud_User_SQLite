package com.example.juane.crud_user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by juane on 15/11/2017.
 */

public class UsuarioDao {
    private UsuarioHelper usuarioHelper;

    public UsuarioDao(Context context){
        this.usuarioHelper = new UsuarioHelper(context);
    }
    public boolean insertUsuario(Usuario user){
        SQLiteDatabase db = this.usuarioHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(UsuarioContract.COLUNA_NOME, user.getNome());
        valores.put(UsuarioContract.COLUNA_TELEFONE, user.getTelefone());
        valores.put(UsuarioContract.COLUNA_EMAIL, user.getEmail());
        valores.put(UsuarioContract.COLUNA_CPF, user.getCpf());

        long linhas= db.insert(UsuarioContract.NOME_TABELA, null, valores);
        return linhas == -1 ? false : true;

    }

    public boolean deleteUsuario(Integer id){
        SQLiteDatabase db = this.usuarioHelper.getWritableDatabase();

        String condicao = UsuarioContract.COLUNA_ID+" =?";
        String[] argumentos = {id.toString()};


        long linhas= db.delete(UsuarioContract.NOME_TABELA, condicao, argumentos);
        return linhas == -1 ? false : true;

    }

    public boolean editarUsuario(Usuario user){
        SQLiteDatabase db = this.usuarioHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(UsuarioContract.COLUNA_NOME, user.getNome());
        valores.put(UsuarioContract.COLUNA_TELEFONE, user.getTelefone());
        valores.put(UsuarioContract.COLUNA_EMAIL, user.getEmail());
        valores.put(UsuarioContract.COLUNA_CPF, user.getCpf());

        String condicao = UsuarioContract.COLUNA_ID+" = ?";
        String[] argumentos ={user.getId().toString()};

        long linhas = db.update(UsuarioContract.NOME_TABELA, valores, condicao, argumentos);
        return linhas != 1;
    }

    public ArrayList<Usuario> retreaveUsuarios(){
        SQLiteDatabase db = this.usuarioHelper.getReadableDatabase();
        String[] colunas = {
                UsuarioContract.COLUNA_ID, 
                UsuarioContract.COLUNA_NOME,
                UsuarioContract.COLUNA_EMAIL,
                UsuarioContract.COLUNA_TELEFONE,
                UsuarioContract.COLUNA_CPF
        };

        Cursor cursor = db.query(UsuarioContract.NOME_TABELA, colunas, null, null, null, null, UsuarioContract.COLUNA_NOME+" ASC");
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario contato = new Usuario();
            contato.setId(cursor.getInt(cursor.getColumnIndex(UsuarioContract.COLUNA_ID)));
            contato.setNome(cursor.getString(cursor.getColumnIndex(UsuarioContract.COLUNA_NOME)));
            contato.setTelefone(cursor.getString(cursor.getColumnIndex(UsuarioContract.COLUNA_TELEFONE)));
            contato.setEmail(cursor.getString(cursor.getColumnIndex(UsuarioContract.COLUNA_EMAIL)));
            contato.setCpf(cursor.getString(cursor.getColumnIndex(UsuarioContract.COLUNA_CPF)));
            users.add(contato);
        }
        cursor.close();
        return users;
    }


}
