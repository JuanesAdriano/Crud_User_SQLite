package com.example.juane.crud_user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juane on 15/11/2017.
 */

public class UsuarioHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS"+UsuarioContract.NOME_TABELA+"("+
            UsuarioContract.COLUNA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            UsuarioContract.COLUNA_NOME+" TEXT,"+
            UsuarioContract.COLUNA_TELEFONE+" TEXT,"+
            UsuarioContract.COLUNA_EMAIL+" TEXT,"+
            UsuarioContract.COLUNA_CPF+" TEXT);";

    private static  final String SQL_DROP = "DROP TABLE IF NOT EXISTS"+UsuarioContract.NOME_TABELA+";";
    private static final Integer DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";



    public UsuarioHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int versionAntiga, int versionNova) {
    db.execSQL(SQL_DROP);
    onCreate(db);
    }
}
