package login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import application.HomeScreenActivity;
import application.MainActivity;
import application.MainFragment;
import application.R;

public class LoginActivity extends Fragment {

    public LoginActivity () { }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_activity, container, false);

        Button buttonEntrar = v.findViewById(R.id.signInButtonEntrar);
        Button buttonVoltar = v.findViewById(R.id.buttonLoginVoltar);

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValidAccount();
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

    private void isValidAccount() {
        Intent intent = new Intent (this.getActivity(),  HomeScreenActivity.class);
        startActivity(intent);
    }
}
