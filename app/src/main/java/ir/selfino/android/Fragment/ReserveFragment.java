package ir.selfino.android.Fragment;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

import ir.selfino.android.Activity.MainActivity;
import ir.selfino.android.Function.Adapter.DaysPagerAdapter;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.Function.Models.Day;
import ir.selfino.android.Function.JsonParser;
import ir.selfino.android.Function.Models.SelfService;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlSelf;
import ir.selfino.android.Interfaces.DaysListener;
import ir.selfino.android.R;
import ir.selfino.android.Struct.DayStatus;

import static ir.selfino.android.Function.Controller.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReserveFragment extends Fragment implements DaysListener
{
    public static String RESERVE_FRAGMENT_UPDATE = "ir.selfinoo.android.Fragment.ReserveFragment";

    private UsernameShared usernameShared;
    private TabLayout tlDays;
    private ViewPager vpDays;
    //  public FloatingActionButton fabSave;
    private DaysPagerAdapter daysPagerAdapter;
    private String week = "0";
    private List<Day> days;
    private List<SelfService> selfServices;
    private ProgressBar progressBar;
    private RelativeLayout rlError;
    private Button btnError;
    private Button btnSave;
    private Button btnSelf;
    private CoordinatorLayout cdlReserve;
    private BroadcastReceiver broadcastReceiver;
    private Menu menuItems;
    public  static final int RESERVE_FRAGMENT = 11345;

    public ReserveFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reserve, container, false);
        setInflation(view);
       // getJsop();
       getDetailsAndDays();
        return view;
    }




    private void setInflation(View view)
    {

        usernameShared = new UsernameShared(getContext());
        menuItems = ((MainActivity) getActivity()).navigationView.getMenu();
        progressBar = (ProgressBar) view.findViewById(R.id.pbr_days_reserve);
        rlError = (RelativeLayout) view.findViewById(R.id.rl_error_days_reserve);
        btnError = (Button) view.findViewById(R.id.btn_error_days_reserve);
        btnError.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.MyWeb.loadUrl(Controller.MyWeb.getUrl());
            }
        });

        cdlReserve = (CoordinatorLayout) view.findViewById(R.id.cdl_days_reserve);
        tlDays = (TabLayout) view.findViewById(R.id.tl_days_reserve);
        vpDays = (ViewPager) view.findViewById(R.id.vp_days_reserve);
        btnSave = (Button) view.findViewById(R.id.btn_save_reserve);
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.MyWeb.evaluateJavascript("javascript: document.querySelector('input#btn_saveKharid').click();", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {

                    }
                });
            }
        });
//        btnSelf = (Button) view.findViewById(R.id.btn_select_self_reserve);
//        btnSelf.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//
//            }
//        });

        Controller.MyWeb.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                if (cdlReserve.isShown())
                    cdlReserve.setVisibility(View.GONE);
                if (rlError.isShown())
                    rlError.setVisibility(View.GONE);
                setDisableMenu();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                if (Controller.MyWeb.getUrl().contains(UrlSelf.getErrorPage()))
                    BackToLogin();
                else if (!Controller.MyWeb.getUrl().contains(UrlSelf.getReserveUrl()))
                    Controller.MyWeb.loadUrl(UrlSelf.getReserveUrl());
                else
                getDetailsAndDays();
            }
        });
    }

    private void getDetailsAndDays()
    {
        Controller.MyWeb.evaluateJavascript("(function() { try" +
                "{ " +
                "var object = '{';" +
                "object = object + '\"ok\":true,';" +
                "object = object + '\"result\":{';" +
                "object = object + '\"message\":\"' + document.querySelector('span#LbMsg').textContent.toString() + '\",';" +
                "object = object + '\"full_name\":\"' + document.querySelector('span#LbFName').textContent.toString() + '\",';" +
                "object = object + '\"user_name\":\"' + document.querySelector('span#lbnumber').textContent.toString() + '\",';" +
                "object = object + '\"balance\":\"' + document.querySelector('span#lbEtebar').textContent.toString() + '\",';" +
                "var l = document.querySelector('select#RD_Self').options.length;" +
                "object = object + '\"self_services\":[ ';" +
                "for (var i = 0 ; i < l;i++)" +
                "{ " +
                "if(i!=0)" +
                "{" +
                "object = object + ',';" +
                "}" +
                "object = object + '{\"id\":\"' + i + '\",';" +
                "object = object + '\"name\":\"' + document.querySelector('select#RD_Self').options[i].textContent + '\"}';" +
                "}" +
                "object = object + '],';" +
                "object = object + '\"self_selected\":\"' +document.querySelector('select#RD_Self').selectedIndex.toString() + '\",';" +
                "var pWeek = document.querySelector('a#btnPriWeek1').style.color.toString();" +
                "var cWeek = document.querySelector('a#btncurrweek1').style.color.toString();" +
                "var nWeek = document.querySelector('a#btnnextweek1').style.color.toString();" +
                "if(cWeek != null && (cWeek.toLowerCase() == 'rgb(0, 100, 0)' || cWeek.toLowerCase() == 'darkgreen')) " +
                "{" +
                "object = object + '\"week\":\"0\",';" +
                "}" +
                "else if(nWeek != null && (nWeek.toLowerCase() == 'rgb(0, 100, 0)' || nWeek.toLowerCase() == 'darkgreen')) " +
                "{" +
                "object = object + '\"week\":\"1\",';" +
                "}" +
                "else if(pWeek != null && (pWeek.toLowerCase() == 'rgb(139, 0, 0)' || pWeek.toLowerCase() == 'darkgreen')) " +
                "{" +
                "object = object + '\"week\":\"-1\",';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"week\":\"0\",';" +
                "}" +
                "object = object + '\"days\":[';" +
                "for (var i = 1; i <= 7;i++)" +
                "{" +
                "if(i!=1)" +
                "{" +
                "object = object + ',';" +
                "}" +
                "object = object + '{\"id\":\"' + i + '\",';" +
                "object = object + '\"name\":\"' + document.querySelector(\"span#Name\"+i.toString()).textContent.toString() + '\",';" +
                "object = object + '\"date\":\"' + document.querySelector(\"span#D\" + i.toString()).textContent.toString() + '\",';" +
                "var vColor=document.querySelector('span#Name' + i).style.color.toString();" +
                "if(vColor!=null && (vColor == 'rgb(139, 0, 0)' || vColor == 'darkred')) " +
                "{" +
                "object = object + '\"status\":\"-1\"}';" +
                "}" +
                "else if(vColor!=null && (vColor == 'rgb(0, 0, 139)' || vColor=='darkblue')) " +
                "{" +
                "object = object + '\"status\":\"1\"}';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"status\":\"0\"}';" +
                "}" +
                "}" +
                "object = object + ']';" +
                "object = object + '}';" +
                "object = object + '}';" +
                "return object.toString();" +
                "}catch(err)" +
                "{" +
                "var object='{';" +
                "object=object+'\"ok\":false,';" +
                "object=object+'\"message\":\"'+err+'\"';" +
                "object=object+'}';" +
                "return object.toString();" +
                "}" +
                "})();", new ValueCallback<String>()
        {
            @Override
            public void onReceiveValue(String s)
            {
                try
                {

                    progressBar.setVisibility(View.GONE);
                    s = s.substring(1, s.length() - 1);
                    s = s.replace("\\", "");
                    Log.d(TAG, "onReceiveDetails: " + s);
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getBoolean("ok"))
                    {
                        //todo reserve login
                        JSONObject object = jsonObject.getJSONObject("result");
                        usernameShared.setFull_name(object.getString("full_name"));
                        if(!object.getString("message").contains("غیر مجاز"))
                            Toast.makeText(getContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                        usernameShared.setBalance(object.getString("balance"));
                        usernameShared.setAll_self_service(object.getJSONArray("self_services").toString());
                        usernameShared.setSelf_index(object.getString("self_selected"));
                        days = JsonParser.JsonDays(object.getJSONArray("days"));
                        usernameShared.setSaturday(days.get(0).getDate());
                        selfServices = JsonParser.JsonSelfServices(object.getJSONArray("self_services"));
                        week = object.getString("week");

                        // btnSelf.setText(usernameShared.getSelf_selected());
                        setEnableMenu();
                        setDays();
                    } else
                    {
                        rlError.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onReserveError : " + jsonObject.getString("message"));
                    }

                } catch (JSONException e)
                {
                    rlError.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onReserveJsonError: " + e.getMessage());
                }
            }
        });
    }

    private void setDays()
    {
        daysPagerAdapter = new DaysPagerAdapter(getChildFragmentManager(), days, this);
        vpDays.setAdapter(daysPagerAdapter);
        tlDays.setupWithViewPager(vpDays);
        vpDays.setOffscreenPageLimit(7);
        if (week.equals("0"))
        {
            for (int i = 0; i < 7; i++)
            {
                if (days.get(i).getStatus().equals(DayStatus.Today))
                    vpDays.setCurrentItem(i);
            }
        }

        progressBar.setVisibility(View.GONE);
        cdlReserve.setVisibility(View.VISIBLE);

    }

    private void setTabText()
    {
        tlDays.getTabAt(6).setCustomView(myCustomTab("شنبه", days.get(0).getDate()));
        tlDays.getTabAt(5).setCustomView(myCustomTab("۱شنبه", days.get(1).getDate()));
        tlDays.getTabAt(4).setCustomView(myCustomTab("۲شنبه", days.get(2).getDate()));
        tlDays.getTabAt(3).setCustomView(myCustomTab("۳شنبه", days.get(3).getDate()));
        tlDays.getTabAt(2).setCustomView(myCustomTab("۴شنبه", days.get(4).getDate()));
        tlDays.getTabAt(1).setCustomView(myCustomTab("۵شنبه", days.get(5).getDate()));
        tlDays.getTabAt(0).setCustomView(myCustomTab("جمعه", days.get(6).getDate()));
    }

    private View myCustomTab(String name, String date)
    {
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.row_tab_day, null);
        //promptsView.setBackground(getContext().getDrawable(R.drawable.selector_btn_up));
        TextView txtname = (TextView) promptsView.findViewById(R.id.txt_name_tab_day_row);
        TextView txtdate = (TextView) promptsView.findViewById(R.id.txt_date_tab_day_row);
        txtname.setText(name);
        txtdate.setText(date);

        return promptsView;
    }

    private void setEnableMenu()
    {
        menuItems.findItem(R.id.account_main_menu).setVisible(true);
        menuItems.findItem(R.id.credit_main_menu).setVisible(true);
        menuItems.findItem(R.id.week_main_menu).setVisible(true);
        menuItems.findItem(R.id.again_enter_main_menu).setVisible(true);
        menuItems.findItem(R.id.credit_balance_main_menu).setTitle("" + usernameShared.getBalance());
        menuItems.findItem(R.id.full_name_main_menu).setTitle(usernameShared.getFull_name());
        menuItems.findItem(R.id.username_main_menu).setTitle(usernameShared.getStu_id());

        if (week.equals("0"))
        {
            menuItems.findItem(R.id.pre_week_main_menu).setEnabled(true);
            menuItems.findItem(R.id.stream_week_main_menu).setEnabled(false);
            menuItems.findItem(R.id.next_week_main_menu).setEnabled(true);
        } else if (week.equals("1"))
        {
            menuItems.findItem(R.id.pre_week_main_menu).setEnabled(true);
            menuItems.findItem(R.id.stream_week_main_menu).setEnabled(true);
            menuItems.findItem(R.id.next_week_main_menu).setEnabled(false);
        } else if (week.equals("-1"))
        {
            menuItems.findItem(R.id.pre_week_main_menu).setEnabled(false);
            menuItems.findItem(R.id.stream_week_main_menu).setEnabled(true);
            menuItems.findItem(R.id.next_week_main_menu).setEnabled(true);
        }
    }
    private void setDisableMenu()
    {
        menuItems.findItem(R.id.account_main_menu).setVisible(false);
        menuItems.findItem(R.id.credit_main_menu).setVisible(false);
        menuItems.findItem(R.id.week_main_menu).setVisible(false);
        menuItems.findItem(R.id.again_enter_main_menu).setVisible(false);
    }

    @Override
    public void ShowSave()
    {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESERVE_FRAGMENT && resultCode == Activity.RESULT_OK)
        {
            Controller.MyWeb.reload();
        }
    }

    private void BackToLogin()
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SelfLoginFragment reserveFragment=new SelfLoginFragment();
        transaction.replace(R.id.frm_holder_fragment,reserveFragment);
        transaction.commit();
    }
}
