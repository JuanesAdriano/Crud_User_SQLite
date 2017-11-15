package com.example.juane.crud_user;

import android.provider.BaseColumns;

/**
 * Created by juane on 15/11/2017.
 */

public class UsuarioContract implements BaseColumns {
    private UsuarioContract(){};

    public static final String NOME_TABELA = "usuarios";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME= "nome";
    public static final String COLUNA_TELEFONE= "telefone";
    public static final String COLUNA_EMAIL= "email";
    public static final String COLUNA_CPF= "cpf";


}
