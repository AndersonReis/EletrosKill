package com.example.autentic.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autentic.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.graphics.Color.colorSpace;
import static android.graphics.Color.parseColor;

public class CadastroVeiculo extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;

    //qual estado da nossa autenticação no momento
    private FirebaseAuth.AuthStateListener authStateListener;

    private Spinner Marca;
    private Spinner Modelo;
    private Spinner Cor;
    private Spinner Pintura;

    String[] marcas = {"Fiat", "Chevrolet", "Citroen", "Subaru", "Volkswagen"};
    String[] modelos = {"Siena", "C4", "Uno", "Hilux", "Up"};
    String[] cores = {"Preto", "Branco", "Prata", "Azul", "Amarelo"};
    String[] pinturas = {"Sólida", "Metálica", "Perolizada"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        auth = FirebaseAuth.getInstance(); //acesso aos recursos na tela Firebase

        estadoAutenticacao();

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Marca = findViewById(R.id.spMarca);
        ArrayAdapter<String> adapterMarca = new ArrayAdapter<String>(this, R.layout.spinner_colors, marcas);
        Marca.setAdapter(adapterMarca);
        Marca.getBackground().setColorFilter(parseColor("#c0c0c0"), PorterDuff.Mode.SRC_ATOP);
        adapterMarca.setDropDownViewResource(R.layout.spinner_dropdown_colors);

        Modelo = findViewById(R.id.spModelo);
        ArrayAdapter<String> adapterModelo = new ArrayAdapter<String>(this, R.layout.spinner_colors, modelos);
        Modelo.setAdapter(adapterModelo);
        Modelo.getBackground().setColorFilter(parseColor("#c0c0c0"), PorterDuff.Mode.SRC_ATOP);
        adapterModelo.setDropDownViewResource(R.layout.spinner_dropdown_colors);

        Cor = findViewById(R.id.spCor);
        ArrayAdapter<String> adapterCor = new ArrayAdapter<String>(this, R.layout.spinner_colors, cores);
        Cor.setAdapter(adapterCor);
        Cor.getBackground().setColorFilter(parseColor("#c0c0c0"), PorterDuff.Mode.SRC_ATOP);
        adapterCor.setDropDownViewResource(R.layout.spinner_dropdown_colors);

        Pintura = findViewById(R.id.spPintura);
        ArrayAdapter<String> adapterPintura = new ArrayAdapter<String>(this, R.layout.spinner_colors, pinturas);
        Pintura.setAdapter(adapterPintura);
        Pintura.getBackground().setColorFilter(parseColor("#c0c0c0"), PorterDuff.Mode.SRC_ATOP);
        adapterPintura.setDropDownViewResource(R.layout.spinner_dropdown_colors);
    }


    //metodo que fiscaliza ou/ ouve o estado da autenticação
    private void estadoAutenticacao() {

        authStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    Toast.makeText(getBaseContext(), "Usuário " + (( FirebaseUser ) user).getEmail() + " esta logado", Toast.LENGTH_LONG).show();

                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        auth.removeAuthStateListener(authStateListener);
    }

}
