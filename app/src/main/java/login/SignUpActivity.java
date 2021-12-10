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

import java.util.ArrayList;

public class SignUpActivity extends Fragment {

    EditText etEmail;
    EditText etSenha;
    EditText etConfirmacaoSenha;

    public SignUpActivity () { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.signup_activity, container, false);

        etEmail = v.findViewById(R.id.signInEditTextEmail);
        etSenha = v.findViewById(R.id.signInEditTextSenha);
        etConfirmacaoSenha = v.findViewById(R.id.signUpEditTextConfirmacaoSenha);

        Button buttonRegistrar = v.findViewById(R.id.signUpButtonRegistrar);
        Button buttonVoltar = v.findViewById(R.id.buttonSignUpVoltar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CreateAccount()) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameSignUp,
                            new HomeScreenActivity()).commit();
                }
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return v;
    }

    private void Register() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameSignUp,
                new MainFragment()).commit();
    }

    private boolean CreateAccount () {
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String confirmacaoSenha = etConfirmacaoSenha.getText().toString();

        boolean validEmail = true;

        if (email.equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o email!", Toast.LENGTH_LONG).show();
        } else if (senha.equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a senha!", Toast.LENGTH_LONG).show();
        } else if (confirmacaoSenha.equals("")) {
            Toast.makeText(getActivity(), "Por favor, confirme sua senha!", Toast.LENGTH_LONG).show();
        } else {
            ArrayList<String> emails = new ArrayList<>();
            DataBaseManager dataBase = new DataBaseManager(getActivity());
            dataBase.readAllUsuarios(getContext(), emails, "email");

            for (String e: emails) {
                if (e.equals(email)) {
                    validEmail = false;
                    Toast.makeText(getActivity(), "Email já existente", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }

        return validEmail;
    }
}
