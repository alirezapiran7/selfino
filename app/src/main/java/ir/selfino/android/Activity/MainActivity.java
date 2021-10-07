package ir.selfino.android.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.ImageView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import ir.selfino.android.Fragment.Alarm.AlarmFragment;
import ir.selfino.android.Fragment.Alarm.AlarmPackageFragment;
import ir.selfino.android.Fragment.Alarm.MyAlarmFragment;
import ir.selfino.android.Fragment.ErrorFragment;
import ir.selfino.android.Fragment.ReserveFragment;
import ir.selfino.android.Fragment.SelfLoginFragment;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.R;

public class MainActivity extends AppCompatActivity
{
    private UsernameShared usernameShared;
    public AHBottomNavigation bottomNavigation;
    private SelfLoginFragment loginFragment;
    private AlarmPackageFragment alarmFragment;
    private ImageView imgMenu;
    private Menu menuItems;
    public NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private int lastPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInflation();
        setBottomNavigationItems();
    }

    private void setBottomNavigationItems()
    {
        final List<AHBottomNavigationItem> items = new ArrayList<>();
        items.add(new AHBottomNavigationItem("رزرو غذا", R.drawable.soup));
        ///if (usernameShared.getShow_products().equals("1"))
        items.add(new AHBottomNavigationItem("هشدار", R.drawable.stopwatch));
        //if (usernameShared.getShow_comments().equals("1"))
        //items.add(new AHBottomNavigationItem("تلنیف", R.drawable.telnif));
        // if (usernameShared.getShow_sell_report().equals("1"))


        bottomNavigation.addItems(items);
        bottomNavigation.setForceTint(false);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setColored(false);
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#ffb130"));
        bottomNavigation.setAccentColor(Color.parseColor("#ed3258"));
        //bottomNavigation.setInactiveColor(Color.parseColor("#ed3258"));
        //bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#ffffff"));
        bottomNavigation.setSelectedBackgroundVisible(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener()
        {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected)
            {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (items.get(position).getTitle(MainActivity.this))
                {
                    case "رزرو غذا":
                        fragmentTransaction.replace(R.id.frm_holder_fragment, loginFragment,"login");
                        fragmentTransaction.commit();
                        return true;
                    case "هشدار":
                        fragmentTransaction.replace(R.id.frm_holder_fragment, alarmFragment,"alarm");
                        fragmentTransaction.commit();
                        GoneSelfMenu();
                        return true;

                }
                return false;
            }
        });
        bottomNavigation.setCurrentItem(0);
    }

    private void setInflation()
    {

        usernameShared = new UsernameShared(this);
        alarmFragment = new AlarmPackageFragment();
        loginFragment = new SelfLoginFragment();
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation_menu);
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_menu_main);
        navigationView = (NavigationView) findViewById(R.id.navegation_menu_main);

        navigationView.inflateMenu(R.menu.main_menu);
        if (usernameShared.checkExist())
        {
            navigationView.getMenu().findItem(R.id.phone_number_main_menu).setTitle(usernameShared.getPhone_number());
            navigationView.getMenu().findItem(R.id.phone_number_main_menu).setVisible(true);
            navigationView.getMenu().findItem(R.id.login_main_menu).setVisible(false);
        } else
        {
            navigationView.getMenu().findItem(R.id.phone_number_main_menu).setVisible(false);
            navigationView.getMenu().findItem(R.id.login_main_menu).setVisible(true);
        }
        navigationView.getMenu().findItem(R.id.account_main_menu).setVisible(false);
        navigationView.getMenu().findItem(R.id.credit_main_menu).setVisible(false);
        navigationView.getMenu().findItem(R.id.week_main_menu).setVisible(false);
        navigationView.getMenu().findItem(R.id.again_enter_main_menu).setVisible(false);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.login_main_menu:
                        startActivity(new Intent(MainActivity.this, LogSinActivity.class));
                        break;
                    case R.id.increase_credit_main_menu:
                        startActivityForResult(new Intent(MainActivity.this, CreditActivity.class), ReserveFragment.RESERVE_FRAGMENT);
                        break;
                    case R.id.pre_week_main_menu:
                        Controller.MyWeb.evaluateJavascript("javascript: document.querySelector('a#btnPriWeek1').click();", new ValueCallback<String>()
                        {
                            @Override
                            public void onReceiveValue(String s)
                            {

                            }
                        });
                        break;
                    case R.id.stream_week_main_menu:
                        Controller.MyWeb.evaluateJavascript("javascript: document.querySelector('a#btncurrweek1').click();", new ValueCallback<String>()
                        {
                            @Override
                            public void onReceiveValue(String s)
                            {

                            }
                        });
                        break;
                    case R.id.next_week_main_menu:
                        Controller.MyWeb.evaluateJavascript("javascript: document.querySelector('a#btnnextweek1').click();", new ValueCallback<String>()
                        {
                            @Override
                            public void onReceiveValue(String s)
                            {

                            }
                        });
                        break;
                    case R.id.again_enter_main_menu:
                        bottomNavigation.setCurrentItem(0);
                        GoneSelfMenu();
                        break;
                    case R.id.connect_main_menu:
                        startActivity(new Intent(MainActivity.this, LogSinActivity.class));
                        break;

                    case R.id.about_us_main_menu:
                        startActivity(new Intent(MainActivity.this, LogSinActivity.class));
                        break;
                    case R.id.guide_main_menu:
                        startActivity(new Intent(MainActivity.this, LogSinActivity.class));
                        break;
                    case R.id.exit_main_menu:
                        startActivity(new Intent(MainActivity.this, LogSinActivity.class));
                        break;
                }
                drawerLayout.closeDrawer(Gravity.RIGHT);
                return false;
            }
        });
        imgMenu = (ImageView) findViewById(R.id.img_menu_icon_tb);
        imgMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!drawerLayout.isDrawerOpen(Gravity.RIGHT))
                    drawerLayout.openDrawer(Gravity.RIGHT);
                else
                    drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
    }
    public void GoneSelfMenu()
    {
        navigationView.getMenu().findItem(R.id.account_main_menu).setVisible(false);
        navigationView.getMenu().findItem(R.id.credit_main_menu).setVisible(false);
        navigationView.getMenu().findItem(R.id.week_main_menu).setVisible(false);
        navigationView.getMenu().findItem(R.id.again_enter_main_menu).setVisible(false);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ReserveFragment.RESERVE_FRAGMENT && resultCode == Activity.RESULT_OK) ;
        {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (int i = 0; i < fragments.size(); i++)
            {
                if (fragments.get(i).getClass().getName().equals("ir.selfino.android.Fragment.ReserveFragment"))
                    fragments.get(i).onActivityResult(ReserveFragment.RESERVE_FRAGMENT, Activity.RESULT_OK, null);
            }
        }
    }
}
