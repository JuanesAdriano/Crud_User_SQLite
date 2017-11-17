package com.example.juane.crud_user;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {

    String nome[];
    String telefone[];
    String cpf[];
    String email[];
    String id[];

    Usuario user = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ListView userList = (ListView) findViewById(R.id.usuarioList);

        retreave();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(UserList.this, UsuarioMain.class);
                TextView tv = (TextView) view.findViewById(R.id.id);
                it.putExtra("id", tv.getText().toString());
                startActivity(it);
            }
        });

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            UsuarioDao usuarioDao = new UsuarioDao(getApplicationContext());
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.id);
                Log.d("IDTE",textView.getText().toString());
                if (usuarioDao.deleteUsuario(Integer.parseInt(textView.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Usuário Deletado Com Sucesso!", Toast.LENGTH_LONG).show();
                    retreave();
                }
                return true;
            }
        });
    }

    public void retreave() {
        UsuarioDao UsuarioDao = new UsuarioDao(getApplicationContext());
        ArrayList<Usuario> usuarios = UsuarioDao.retreaveUsuarios();
        id = new String[usuarios.size()];
        nome = new String[usuarios.size()];
        telefone = new String[usuarios.size()];
        int i = 0;
        for (Usuario c : usuarios) {
            id[i] = c.getId().toString();
            nome[i] = c.getNome();
            telefone[i] = c.getTelefone();
            i++;
        }
        ListView listView = (ListView) findViewById(R.id.usuarioList);
        Adapter adapter = new Adapter(getApplicationContext(),  id, nome, telefone);
        listView.setAdapter(adapter);
    }
    private class Adapter extends ArrayAdapter<String> {
        Context context;
        int[] img;
        String id[];
        String nome[];
        String telefone[];

        Adapter(Context c, String[] id, String[] nome, String[] telefone) {
            super(c, R.layout.row, R.id.id, nome);
            this.img = img;
            this.id = id;
            this.nome = nome;
            this.telefone = telefone;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);

            TextView textViewNome = row.findViewById(R.id.contatoNome);
            TextView textId = row.findViewById(R.id.id);
            TextView textViewTelefone = row.findViewById(R.id.contatoTelefone);
            textViewNome.setText(nome[position]);
            textId.setText(id[position]);
            textViewTelefone.setText(telefone[position]);
            return row;
        }


    }
}
