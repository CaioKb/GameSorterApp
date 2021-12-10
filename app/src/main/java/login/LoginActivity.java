package login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import application.HomeScreenActivity;
import application.MainActivity;
import application.MainFragment;
import application.R;
import database.DataBaseManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginActivity extends Fragment {

    EditText etEmail;
    EditText etSenha;

    public LoginActivity () { }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_activity, container, false);

        etEmail = v.findViewById(R.id.signInEditTextEmail);
        etSenha = v.findViewById(R.id.signInEditTextSenha);

        Button buttonEntrar = v.findViewById(R.id.signInButtonEntrar);
        Button buttonVoltar = v.findViewById(R.id.buttonLoginVoltar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidAccount()) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLogin,
                            new HomeScreenActivity()).commit();
                }
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLogin,
                        new MainFragment()).commit();
            }
        });

        return v;
    }
    // tratamento básico de login
    private boolean isValidAccount() {
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        boolean validEmail = false;
        boolean validPassword = false;

        if (email.equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o email!", Toast.LENGTH_LONG).show();
        } else if (senha.equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a senha!", Toast.LENGTH_LONG).show();
        } else {
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<String> senhas = new ArrayList<>();
            DataBaseManager dataBase = new DataBaseManager(getActivity());
            dataBase.readAllUsuarios(getContext(), emails, "email");
            dataBase.readAllUsuarios(getContext(), senhas, "senha");


            for (String e: emails) {
                if (e.equals(email)) {
                    validEmail = true;
                    break;
                }
            }

            if(!validEmail) {
                Toast.makeText(getActivity(), "Email invalido!", Toast.LENGTH_LONG).show();
            }

            for(String s : senhas) {
                if(s.equals(senha)) {
                    validPassword = true;
                    break;
                }
            }

            if(!validPassword){
                Toast.makeText(getActivity(), "Senha invalida!", Toast.LENGTH_LONG).show();
            }
        }

        return validEmail && validPassword;
    }
}
