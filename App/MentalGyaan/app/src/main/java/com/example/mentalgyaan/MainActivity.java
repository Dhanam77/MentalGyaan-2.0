package com.example.mentalgyaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
   // private NavigationView navigationView;
    private DatabaseReference Ref;

    public String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeFields();

        Ref = FirebaseDatabase.getInstance().getReference();


        drawerLayout = findViewById(R.id.drawer_layout);
        androidx.appcompat.app.ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawerLayout, mToolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

     //   SetNavigationView();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiaryFragment()).commit();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bot_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }

    private void SetupTherapies() {



    }

    //handle bottomnavigation buttons
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {


                case R.id.bot_chatbot: {
                    startActivity(new Intent(MainActivity.this, ChatBotActivity.class));
                    break;
                }

                case R.id.bot_diary: {
                    Fragment selectedFragment = new DiaryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    break;
                }

                case R.id.bot_therapy: {
                    Fragment selectedFragment = new TherapyFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    break;
                }

                case R.id.bot_quotes: {
                    Fragment selectedFragment = new QuotesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    break;
                }


                case R.id.bot_discover: {
                    Fragment selectedFragment = new DiscoverFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();


                    break;
                }


            }


            return true;

        }


    };


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

     //   Menu menu = navigationView.getMenu();


        switch (menuItem.getItemId()) {
            case R.id.side_help: {


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;


            }

            case R.id.side_noti: {


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(MainActivity.this, NoticeToCustomerActivity.class);
                startActivity(intent);
                break;


            }

            case R.id.side_quiz: {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                startActivity(new Intent(MainActivity.this, QuizActivity.class));

                break;
            }

            case R.id.side_face: {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            //    Intent intent = new Intent(MainActivity.this, FaceDetectionActivity.class);

              //  startActivity(intent);
                break;
            }


        }
        return true;
    }

/*
    //SET DATA IN NAVHEADER
    private void SetNavigationView() {

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final View header = navigationView.getHeaderView(0);

        TextView nameText = (TextView) header.findViewById(R.id.sidenav_header_name);
        nameText.setText("Dhanam Parekh");
    }
*/

    private void InitializeFields() {

        SetupToolbar();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bot_nav);


    }

    private void SetupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MentalGyaan");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
