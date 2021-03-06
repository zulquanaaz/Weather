package com.example.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        setupNavigation();
    }

    public void setupNavigation(){
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);
        drawerLayout.closeDrawers();
        int id=menuItem.getItemId();
        Bundle b=new Bundle();
        switch (id){
            case R.id.c_montreal:
                b.putInt("Country",R.id.c_montreal);
                navController= Navigation.findNavController(this,R.id.nav_host_fragment);
                navController.navigate(R.id.currentFragment,b);
                break;
            case R.id.nav_logout:
               auth.signOut();
                Intent intent=new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
                break;
            case R.id.c_london:
                b.putInt("Country",R.id.c_london);
                navController= Navigation.findNavController(this,R.id.nav_host_fragment);
                navController.navigate(R.id.currentFragment,b);
                break;

            case R.id.c_vancouver:
                b.putInt("Country",R.id.c_vancouver);
                navController= Navigation.findNavController(this,R.id.nav_host_fragment);
                navController.navigate(R.id.currentFragment,b);
                break;
            case R.id.c_mumbai:r:
            b.putInt("Country",R.id.c_mumbai);
                navController= Navigation.findNavController(this,R.id.nav_host_fragment);
                navController.navigate(R.id.currentFragment,b);
                break;
            case R.id.c_toronto:r:
            b.putInt("Country",R.id.c_toronto);
                navController= Navigation.findNavController(this,R.id.nav_host_fragment);
                navController.navigate(R.id.currentFragment,b);
                break;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),drawerLayout);
    }
}