package com.mooc.ppjock;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mooc.ppjock.utils.NavGraphBuilder;
import com.mooc.libcommon.utils.StatusBar;
import com.mooc.ppjock.view.AppBottomBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    public static final String TAG = "MainActivity";

    AppBottomBar navView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);

        //启用沉浸式布局，白底黑字
        StatusBar.fitSystemBar(this);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = NavHostFragment.findNavController(fragment);
        NavigationUI.setupWithNavController(navView,navController);
        NavGraphBuilder.build(this, navController, fragment.getId());
        navView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navController.navigate(item.getItemId());
        return !TextUtils.isEmpty(item.getTitle());
    }
}
