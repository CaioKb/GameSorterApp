package login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import application.MainFragment;
import application.R;

public class SignUpActivity extends Fragment {
    public SignUpActivity () { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.signup_activity, container, false);

        Button buttonRegistrar = v.findViewById(R.id.signUpButtonRegistrar);
        Button buttonVoltar = v.findViewById(R.id.buttonSignUpVoltar);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return v;
    }

    private void Register() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameSignUp,
                new MainFragment()).commit();
    }
}
