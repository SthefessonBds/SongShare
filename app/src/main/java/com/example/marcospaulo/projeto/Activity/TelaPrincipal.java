package com.example.marcospaulo.projeto.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.marcospaulo.projeto.R;

public class TelaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    HomeFragment homeFragment;
    ExplorarFragment explorarFragment;
    NotificacoesFragment notificacoesFragment;
    NovoProjetoFragment novoProjetoFragment;
    MeusProjetosFragment meusProjetosFragment;
    FirebaseAuth mAuth;
    DatabaseReference UsersRef;

    Toolbar toolbar, toolbarinicio;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mAuth = FirebaseAuth.getInstance();
        UsersRef = (FirebaseDatabase.getInstance().getReference().child("Usuario"));
        toolbar.setTitle("SongShare!");
        toolbar.setTitleMarginStart(260);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        homeFragment = new HomeFragment() ;
        explorarFragment = new ExplorarFragment();
        novoProjetoFragment = new NovoProjetoFragment();
        notificacoesFragment = new NotificacoesFragment();
        meusProjetosFragment = new MeusProjetosFragment();


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragmentSelected = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            fragmentSelected = homeFragment;
                            break;

                        case R.id.nav_explorar:
                            fragmentSelected = explorarFragment;
                            break;
                        case R.id.nav_newproject:
                            fragmentSelected = novoProjetoFragment;
                            break;

                        case R.id.nav_notificacoes:
                            fragmentSelected = notificacoesFragment;
                            break;

                        case R.id.nav_meusprojetos:
                            fragmentSelected = meusProjetosFragment;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelected).commit();

                    return true;
                }

            };

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            sendToLoginActivity();
        }
    }


    private void sendToLoginActivity() {
        Intent login = new Intent(getBaseContext(), TelaLogin.class);
        startActivity(login);
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbarprincipalmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragmentSelected = null;
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent inte = new Intent(getBaseContext(), TelaPerfil.class);
            startActivity(inte);
        } else if (id == R.id.nav_explorar) {
            fragmentSelected = new ExplorarFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelected).commit();
            bottomNav.setSelectedItemId(R.id.nav_explorar);
        } else if (id == R.id.nav_newproject) {
            fragmentSelected = new NovoProjetoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelected).commit();
            bottomNav.setSelectedItemId(R.id.nav_newproject);

        } else if (id == R.id.nav_meusprojetos) {
            fragmentSelected = new MeusProjetosFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSelected).commit();
            bottomNav.setSelectedItemId(R.id.nav_meusprojetos);

        } else if (id == R.id.drawer_sair) {
            mAuth.signOut();
            sendToLoginActivity();
        } else if (id == R.id.nav_facebook) {
            Intent inte = new Intent(Intent.ACTION_VIEW);
            inte.setData(Uri.parse("https://www.facebook.com/FacebookBrasil/"));
            startActivity(inte);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
