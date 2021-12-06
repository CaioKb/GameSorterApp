package application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import login.LoginActivity;
import login.SignUpActivity;

public class MainFragment extends Fragment {
    public MainFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.main_menu_activity, container, false);

        Button buttonEntrar = v.findViewById(R.id.buttonEntrar);
        Button buttonRegistrar = v.findViewById(R.id.buttonRegistrar);
        Button buttonContinuarComoConvidado = v.findViewById(R.id.buttonContinuarComoConvidado);

        buttonEntrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuFrame,
                        new LoginActivity()).addToBackStack(null).commit();
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuFrame,
                        new SignUpActivity()).commit();
            }
        });

        buttonContinuarComoConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuFrame,
                        new HomeScreenActivity()).commit();*/
            }
        });

        return v;
    }
}
