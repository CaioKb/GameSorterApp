package application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import database.DataBaseManager;

import java.util.ArrayList;
import java.util.Collections;

public class HomeScreenActivity extends Fragment {

    public HomeScreenActivity() { }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.login_activity, container, false);

        //Ao sortear jogo, altera visibilidade do textview, altera o nome exibindo o resultado
        ImageView img = v.findViewById(R.id.imageRoleta);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView jogoSorteado = v.findViewById(R.id.jogoSorteado);
                jogoSorteado.setText(SortGame());
                jogoSorteado.setVisibility(View.VISIBLE);
            }
        });
        return v;
    }

    private String SortGame() {
        DataBaseManager dataBaseManager = new DataBaseManager(getContext());
        ArrayList<String> games = new ArrayList<>();
        dataBaseManager.readAllGame(getContext(), games, "nome"); //aplicar filtro depois no select
        Collections.sort(games);
        return games.get(0);

    }
}
