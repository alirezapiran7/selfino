package ir.selfino.android.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.VolleyLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ir.selfino.android.Function.Adapter.FoodAdapter;
import ir.selfino.android.Function.Adapter.SelfSpinnerAdapter;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.Function.Models.Food;
import ir.selfino.android.Function.JsonParser;
import ir.selfino.android.Function.Models.Meal;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlSelf;
import ir.selfino.android.Interfaces.DaysListener;
import ir.selfino.android.Interfaces.FoodListener;
import ir.selfino.android.R;
import ir.selfino.android.Struct.MealStatus;

import static ir.selfino.android.Function.Controller.TAG;


public class DaysFragment extends Fragment implements FoodListener
{
    private static final String ARG_TODAY = "today";
    private String today = "0";
    private UsernameShared usernameShared;
    private TextView cTxtTime, cTxtCount, nTxtTime, nTxtCount, sTxtTime, sTxtCount;
    private ProgressBar cProgressBar, sProgressBar, nProgressBar;
    private Button cBtnError, nBtnError, sBtnError;
    private RecyclerView cRcvFood, nRcvFood, sRcvFood;
    private GridLayoutManager cManager, nManager, sManager;
    private FoodAdapter cAdapter, nAdapter, sAdapter;
    private ImageView cImgUp, cImgDown, nImgUp, nImgDown, sImgUp, sImgDown;
    private Spinner cSelf, nSelf, sSelf;

    private Meal cMeal, nMeal, sMeal;
    private LinearLayout cLlError, cLlCount, nLlError, nLlCount, sLlError, sLlCount;
    public DaysListener listener;
    private ScrollView slv;

    public DaysFragment()
    {
        // Required empty public constructor
    }

    public static DaysFragment newInstance(String tody,DaysListener listener)
    {
        DaysFragment fragment = new DaysFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TODAY, tody);
        fragment.setArguments(args);
        fragment.listener=listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            today = getArguments().getString(ARG_TODAY, "0");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_days, container, false);
        setInflation(view);
        getBreakFast();
        getLunch();
        getDinner();
        return view;
    }

    private void setInflation(View view)
    {
        usernameShared = new UsernameShared(getContext());
        slv=(ScrollView)view.findViewById(R.id.slv_meals_fragment);
        cMeal = new Meal();
        cMeal.setName('C');
        cProgressBar = (ProgressBar) view.findViewById(R.id.pbr_c_meal);
        cTxtTime = (TextView) view.findViewById(R.id.txt_c_time_meal);
        cTxtTime.setText(usernameShared.getTime_c_meal());

        cTxtCount = (TextView) view.findViewById(R.id.txt_c_count_meal);
        cLlError = (LinearLayout) view.findViewById(R.id.ll_c_error_meal);
        cLlCount = (LinearLayout) view.findViewById(R.id.ll_c_sub_count_foods_meal);
        cBtnError = (Button) view.findViewById(R.id.btn_c_try_again_meal);
        cBtnError.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getBreakFast();
            }
        });
        cRcvFood = (RecyclerView) view.findViewById(R.id.rcv_c_foods_meal);
        cManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        cImgUp = (ImageView) view.findViewById(R.id.img_c_add_count_meal);
        cImgUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int n = Integer.valueOf(cTxtCount.getText().toString());
                if (n <= 9)
                {
                    n = n + 1;
                    final int finalN = n;
                    Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtc_numGhaza" + today + "]').value = " + n + ";", new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String s)
                        {
                            cTxtCount.setText("" + finalN);
                        }
                    });
                }
            }
        });
        cImgDown = (ImageView) view.findViewById(R.id.img_c_min_count_meal);
        cImgDown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int n = Integer.valueOf(cTxtCount.getText().toString());
                if (n > 0)
                {
                    n = n - 1;
                    final int finalN = n;
                    Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtc_numGhaza" + today + "]').value = " + n + ";", new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String s)
                        {
                            cTxtCount.setText("" + finalN);
                        }
                    });
                }
            }
        });

        cSelf = (Spinner) view.findViewById(R.id.sp_c_self_meal);
        SelfSpinnerAdapter SelfAdapter = new SelfSpinnerAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, usernameShared.getAll_self_service());
        cSelf.setAdapter(SelfAdapter);
        cSelf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=EditC" + today + "]').value= " + (i+1) + ";", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });


        nMeal = new Meal();
        nMeal.setName('N');
        nProgressBar = (ProgressBar) view.findViewById(R.id.pbr_n_meal);
        nTxtTime = (TextView) view.findViewById(R.id.txt_n_time_meal);
        nTxtTime.setText(usernameShared.getTime_n_meal());
        nTxtCount = (TextView) view.findViewById(R.id.txt_n_count_meal);
        nLlError = (LinearLayout) view.findViewById(R.id.ll_n_error_meal);
        nLlCount = (LinearLayout) view.findViewById(R.id.ll_n_sub_count_foods_meal);
        nBtnError = (Button) view.findViewById(R.id.btn_n_try_again_meal);
        nBtnError.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getBreakFast();
            }
        });
        nManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        nRcvFood = (RecyclerView) view.findViewById(R.id.rcv_n_foods_meal);
        nImgUp = (ImageView) view.findViewById(R.id.img_n_add_count_meal);
        nImgUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int n = Integer.valueOf(nTxtCount.getText().toString());
                if (n <= 9)
                {
                    n = n + 1;
                    final int finalN = n;
                    Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtn_numGhaza" + today + "]').value = " + n + ";", new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String s)
                        {
                            nTxtCount.setText("" + finalN);
                        }
                    });
                }
            }
        });
        nImgDown = (ImageView) view.findViewById(R.id.img_n_min_count_meal);
        nImgDown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int n = Integer.valueOf(nTxtCount.getText().toString());
                if (n > 0)
                {
                    n = n - 1;
                    final int finalN = n;
                    Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtn_numGhaza" + today + "]').value = " + n + ";", new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String s)
                        {
                            nTxtCount.setText("" + finalN);
                        }
                    });
                }
            }
        });
        nSelf = (Spinner) view.findViewById(R.id.sp_n_self_meal);
        nSelf.setAdapter(SelfAdapter);
        nSelf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=EditN" + today + "]').value= " + (i+1) + ";", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        sMeal = new Meal();
        sMeal.setName('S');
        sProgressBar = (ProgressBar) view.findViewById(R.id.pbr_s_meal);
        sTxtTime = (TextView) view.findViewById(R.id.txt_s_time_meal);
        sTxtTime.setText(usernameShared.getTime_s_meal());
        sTxtCount = (TextView) view.findViewById(R.id.txt_s_count_meal);
        sLlError = (LinearLayout) view.findViewById(R.id.ll_s_error_meal);
        sLlCount = (LinearLayout) view.findViewById(R.id.ll_s_sub_count_foods_meal);
        sBtnError = (Button) view.findViewById(R.id.btn_s_try_again_meal);
        sBtnError.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getBreakFast();
            }
        });
        sManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        sRcvFood = (RecyclerView) view.findViewById(R.id.rcv_s_foods_meal);
        sImgUp = (ImageView) view.findViewById(R.id.img_s_add_count_meal);
        sImgUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int n = Integer.valueOf(sTxtCount.getText().toString());
                if (n <= 9)
                {
                    n = n + 1;
                    final int finalN = n;
                    Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txts_numGhaza" + today + "]').value = " + n + ";", new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String s)
                        {
                            sTxtCount.setText("" + finalN);
                        }
                    });
                }
            }
        });
        sImgDown = (ImageView) view.findViewById(R.id.img_s_min_count_meal);
        sImgDown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int n = Integer.valueOf(sTxtCount.getText().toString());
                if (n > 0)
                {
                    n = n - 1;
                    final int finalN = n;
                    Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txts_numGhaza" + today + "]').value = " + n + ";", new ValueCallback<String>()
                    {
                        @Override
                        public void onReceiveValue(String s)
                        {
                            sTxtCount.setText("" + finalN);
                        }
                    });
                }
            }
        });
        sSelf = (Spinner) view.findViewById(R.id.sp_s_self_meal);
        sSelf.setAdapter(SelfAdapter);
        sSelf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=EditS" + today + "]').value= " + (i+1) + ";", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    private void getDinner()
    {
        String lbl = getLabel();
        Controller.MyWeb.evaluateJavascript("(function () {" +
                "try {" +
                "var object = '{';" +
                "object = object + '\"ok\":true,';" +
                "object = object + '\"result\":{';" +
                "object = object + '\"kind_status\":\"' +document.querySelector('input#GhazaS" + today + "').className.toString()+ '\",';" +
                "if(document.querySelector('span#lbl" + lbl + "_ghazaS" + today + "')!=null)" +
                "{" +
                "object = object + '\"food_name\":\"'+document.querySelector('span#lbl" + getLabel() + "_ghazaS" + today + "').textContent.toString()+'\",';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"food_name\":\"'+document.querySelector('span#lbl" + lbl.toLowerCase() + "_ghazaS" + today + "').textContent.toString()+'\",';" +
                "}" +
                "object = object + '\"kind\":\"' +document.querySelector('input#GhazaS" + today + "').value.toString()+ '\",';" +
                "object = object + '\"self\":\"' +document.querySelector('input#EditS" + today + "').value.toString()+ '\",';" +
                "object = object + '\"count\":\"' +document.querySelector('input#txts_numGhaza" + today + "').value.toString()+ '\",';" +
                "object = object + '\"count_status\":\"' +document.querySelector('input#txts_numGhaza" + today + "').className.toString()+ '\",';" +
                "object = object + '\"count_color\":\"' +document.querySelector('input#txts_numGhaza" + today + "').style.color.toString()+ '\",';" +
                "if(document.querySelector('a#linkS" +today+ "')!=null)" +
                "{" +
                "object = object + '\"link\":\"'+document.querySelector('a#linkS"+today+ "').getAttribute('onclick').toString()+'\"';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"link\":\"null\"';" +
                "}" +

                "object = object + '}';" +
                "object = object + '}';" +
                "return object.toString();" +
                "}" +
                "catch(err){" +
                "var object='{';" +
                "object=object+'\"ok\":false,';" +
                "object=object+'\"message\":\"'+err.message+'\"';" +
                "object=object+'}';" +
                "return object.toString();" +
                "}" +
                "}" +
                "());", new ValueCallback<String>()
        {
            @Override
            public void onReceiveValue(String s)
            {

                try
                {
                    s = s.substring(1, s.length() - 1);
                    s = s.replace("\\", "");
                    Log.d(TAG, "onReceiveSMeal: "+today +" "+ s);
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getBoolean("ok"))
                    {
                        JSONObject object = jsonObject.getJSONObject("result");
                        sMeal = JsonParser.JsonMeal(object,'S');
                        Food food = new Food();
                        food.setKind(sMeal.getKind());
                        food.setName(sMeal.getFood_name());
                        List<Food> foods = new ArrayList<>();
                        foods.add(food);
                        sMeal.setFoods(foods);

                        Log.d(VolleyLog.TAG, "getStatus: "+ today+" s "+sMeal.getStatus());
                        if (sMeal.getStatus().equals(MealStatus.UnReserve_Changable))
                            getFoodDinner();
                        else
                            setDinner();
                    } else
                    {
                        sLlError.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onsMealError : " + jsonObject.getString("message"));
                    }

                } catch (JSONException e)
                {
                    sLlError.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onsMealErrorJsonError: " + e.getMessage());
                }
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void getFoodDinner()
    {

        Log.d(TAG, "getSaturday(): " + usernameShared.getSaturday());
        final char c = 'S';
        final String t = c + "" + today + "";
        final WebView web = new WebView(getContext());
        WebSettings CSettings = web.getSettings();
        CSettings.setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient()
        {
            boolean timeout1 = true;

            @Override
            public void onPageStarted(final WebView view, String url, Bitmap favicon)
            {
                timeout1 = true;
                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        if (timeout1)
                        {
                            getActivity().runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    web.loadUrl(UrlSelf.getBaseUrl() + sMeal.getLink());
                                }
                            });

                        } else
                            timer.cancel();
                    }
                }, 3500, 3500);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                //برای اینکه به طور خودکار رفرش شود اگر بعد از مدتی جواب نداد
                web.evaluateJavascript("(function() {" +

                        "try {" +
                        "var object = '{';" +
                        "object = object + '\"ok\":true,';" +
                        "object = object + '\"result\":{';" +
                        "object = object + '\"foods\":[';" +
                        "var l= document.querySelector('table#GhazaGrid').rows.length; " +
                        "for (var i = 1 ; i < l;i++)" +
                        "{ " +
                        "if(i!=1)" +
                        "{" +
                        "object = object + ',';" +
                        "}" +
                        "object = object + '{\"kind\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[0].textContent + '\",';" +
                        "object = object + '\"name\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[2].textContent + '\",';" +
                        "object = object + '\"price\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[3].textContent + '\"}';" +
                        "}" +
                        "object = object + ']';" +
                        "object = object + '}';" +
                        "object = object + '}';" +
                        "return object.toString();" +
                        "}" +
                        "catch(err){" +
                        "var object='{';" +
                        "v=document.querySelector('table#TABLE1').id;" +
                        "object=object+'\"ok\":false,';" +
                        "object=object+'\"message\":\"'+err.message+'\"';" +
                        "object=object+'}';" +
                        "return object.toString();" +
                        "}" +
                        "}" +
                        "());", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                        try
                        {
                            s = s.substring(1, s.length() - 1);
                            s = s.replace("\\", "");
                            Log.d(TAG, "onsReceiveFoods: " + s);
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getBoolean("ok"))
                            {
                                JSONObject object = jsonObject.getJSONObject("result");
                                sMeal.setFoods(JsonParser.JsonFoods(object.getJSONArray("foods")));
                                setDinner();
                                timeout1 = false;
                            } else
                            {
                                sMeal.setFoods(new ArrayList<Food>());
                                setDinner();
                                timeout1 = false;
                            }

                        } catch (JSONException e)
                        {
                            timeout1 = true;
                            Log.d(TAG, "FoodsError: " + s + "\n" + e.getMessage());
                        }
                    }
                });
            }

        });
        web.loadUrl(UrlSelf.getBaseUrl() + sMeal.getLink());
    }

    private void setDinner()
    {

        if (sMeal.getStatus().equals(MealStatus.Reserved_changeable) || sMeal.getStatus().equals(MealStatus.UnReserve_Changable))
        {
            sLlCount.setVisibility(View.VISIBLE);
            sSelf.setVisibility(View.VISIBLE);
            sSelf.setSelection(Integer.valueOf(sMeal.getSelf())-1);
        } else if (sMeal.getStatus().equals(MealStatus.Reserved_UnChangable))
        {
            sSelf.setVisibility(View.VISIBLE);
            sSelf.setEnabled(false);
            sSelf.setSelection(Integer.valueOf(sMeal.getSelf())-1);
        } else if (sMeal.getStatus().equals(MealStatus.UnReserve_UnChangable) || sMeal.getStatus().equals(MealStatus.Reserved_Use))
        {
            sLlCount.setVisibility(View.GONE);
            sSelf.setVisibility(View.GONE);
        }

        if (sMeal.getFoods().size() == 0)
        {
            sLlError.setVisibility(View.VISIBLE);
            sLlCount.setVisibility(View.GONE);
            sSelf.setVisibility(View.GONE);
            sRcvFood.setVisibility(View.GONE);
        } else
        {
            if (sLlError.isShown())
                sLlError.setVisibility(View.GONE);
            sRcvFood.setVisibility(View.VISIBLE);
            sAdapter = new FoodAdapter(getContext(), DaysFragment.this, sMeal);
            sRcvFood.setLayoutManager(sManager);
            sRcvFood.setAdapter(sAdapter);
        }
        sProgressBar.setVisibility(View.GONE);
    }

    private void getLunch()
    {
        String lbl = getLabel();
        Controller.MyWeb.evaluateJavascript("(function () {" +
                "try {" +
                "var object = '{';" +
                "object = object + '\"ok\":true,';" +
                "object = object + '\"result\":{';" +
                "object = object + '\"kind_status\":\"' +document.querySelector('input#GhazaN" + today + "').className.toString()+ '\",';" +
                "if(document.querySelector('span#lbl" + lbl + "_ghazaN" + today + "')!=null)" +
                "{" +
                "object = object + '\"food_name\":\"'+document.querySelector('span#lbl" + getLabel() + "_ghazaN" + today + "').textContent.toString()+'\",';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"food_name\":\"'+document.querySelector('span#lbl" + lbl.toLowerCase() + "_ghazaN" + today + "').textContent.toString()+'\",';" +
                "}" +
                "object = object + '\"kind\":\"' +document.querySelector('input#GhazaN" + today + "').value.toString()+ '\",';" +
                "object = object + '\"self\":\"' +document.querySelector('input#EditN" + today + "').value.toString()+ '\",';" +
                "object = object + '\"count\":\"' +document.querySelector('input#txtn_numGhaza" + today + "').value.toString()+ '\",';" +
                "object = object + '\"count_status\":\"' +document.querySelector('input#txtn_numGhaza" + today + "').className.toString()+ '\",';" +
                "object = object + '\"count_color\":\"' +document.querySelector('input#txtn_numGhaza" + today + "').style.color.toString()+ '\",';" +
                "if(document.querySelector('a#link" + today + "')!=null)" +
                "{" +
                "object = object + '\"link\":\"'+document.querySelector('a#link"+ today + "').getAttribute('onclick').toString()+'\"';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"link\":\"null\"';" +
                "}" +
                "object = object + '}';" +
                "object = object + '}';" +
                "return object.toString();" +
                "}" +
                "catch(err){" +
                "var object='{';" +
                "object=object+'\"ok\":false,';" +
                "object=object+'\"message\":\"'+err.message+'\"';" +
                "object=object+'}';" +
                "return object.toString();" +
                "}" +
                "}" +
                "());", new ValueCallback<String>()
        {
            @Override
            public void onReceiveValue(String s)
            {

                try
                {
                    s = s.substring(1, s.length() - 1);
                    s = s.replace("\\", "");
                    Log.d(TAG, "onReceiveNMeal: " + s);
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getBoolean("ok"))
                    {
                        JSONObject object = jsonObject.getJSONObject("result");
                        nMeal = JsonParser.JsonMeal(object,'N');
                        Food food = new Food();
                        food.setKind(nMeal.getKind());
                        food.setName(nMeal.getFood_name());
                        Log.d(VolleyLog.TAG, "getStatus: "+ today+" N "+nMeal.getStatus());
                        List<Food> foods = new ArrayList<>();
                        foods.add(food);
                        nMeal.setFoods(foods);
                        if (nMeal.getStatus().equals(MealStatus.UnReserve_Changable))
                            getFoodLunch();
                        else
                            setLunch();
                    } else
                    {
                        nLlError.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onNMealError : " + jsonObject.getString("message"));
                    }

                } catch (JSONException e)
                {
                    nLlError.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onnMealErrorJsonError: " + e.getMessage());
                }
            }
        });
    }

    private void setLunch()
    {

        if (nMeal.getStatus().equals(MealStatus.Reserved_changeable) || nMeal.getStatus().equals(MealStatus.UnReserve_Changable))
        {
            nLlCount.setVisibility(View.VISIBLE);
            nSelf.setVisibility(View.VISIBLE);
            nSelf.setEnabled(true);
            nSelf.setSelection(Integer.valueOf(nMeal.getSelf())-1);
        } else if (nMeal.getStatus().equals(MealStatus.Reserved_UnChangable))
        {
            nSelf.setVisibility(View.VISIBLE);
            nSelf.setEnabled(false);
            nSelf.setSelection(Integer.valueOf(nMeal.getSelf())-1);
        } else if (nMeal.getStatus().equals(MealStatus.UnReserve_UnChangable) || nMeal.getStatus().equals(MealStatus.Reserved_Use))
        {
            nLlCount.setVisibility(View.GONE);
            nSelf.setVisibility(View.GONE);
        }

        if (nMeal.getFoods().size() == 0)
        {
            nLlError.setVisibility(View.VISIBLE);
            nLlCount.setVisibility(View.GONE);
            nSelf.setVisibility(View.GONE);
            nRcvFood.setVisibility(View.GONE);
        } else
        {
            if (nLlError.isShown())
                nLlError.setVisibility(View.GONE);
            nRcvFood.setVisibility(View.VISIBLE);
            nAdapter = new FoodAdapter(getContext(), DaysFragment.this, nMeal);
            nRcvFood.setLayoutManager(nManager);
            nRcvFood.setAdapter(nAdapter);
        }
        nProgressBar.setVisibility(View.GONE);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void getFoodLunch()
    {

        Log.d(TAG, "getSaturday(): " + usernameShared.getSaturday());
        final char c = 'N';
        final String t = c + "" + today + "";
        final WebView web = new WebView(getContext());
        WebSettings CSettings = web.getSettings();
        CSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient()
        {
            boolean timeout1 = true;

            @Override
            public void onPageStarted(final WebView view, String url, Bitmap favicon)
            {
                timeout1 = true;
                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        if (timeout1)
                        {
                            getActivity().runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    web.loadUrl(UrlSelf.getBaseUrl()+nMeal.getLink());
                                }
                            });
                        } else
                            timer.cancel();
                    }
                }, 3500, 3500);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                //برای اینکه به طور خودکار رفرش شود اگر بعد از مدتی جواب نداد
                web.evaluateJavascript("(function() {" +
                        "try {" +
                        "var object = '{';" +
                        "object = object + '\"ok\":true,';" +
                        "object = object + '\"result\":{';" +
                        "object = object + '\"foods\":[';" +
                        "var l= document.querySelector('table#GhazaGrid').rows.length; " +
                        "for (var i = 1 ; i < l;i++)" +
                        "{ " +
                        "if(i!=1)" +
                        "{" +
                        "object = object + ',';" +
                        "}" +
                        "object = object + '{\"kind\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[0].textContent + '\",';" +
                        "object = object + '\"name\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[2].textContent + '\",';" +
                        "object = object + '\"price\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[3].textContent + '\"}';" +
                        "}" +
                        "object = object + ']';" +
                        "object = object + '}';" +
                        "object = object + '}';" +
                        "return object.toString();" +
                        "}" +
                        "catch(err){" +
                        "var object='{';" +
                        "v=document.querySelector('table#TABLE1').id;" +
                        "object=object+'\"ok\":false,';" +
                        "object=object+'\"message\":\"'+err.message+'\"';" +
                        "object=object+'}';" +
                        "return object.toString();" +
                        "}" +
                        "}" +
                        "());", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                        try
                        {
                            s = s.substring(1, s.length() - 1);
                            s = s.replace("\\", "");
                            Log.d(TAG, "onNReceiveFoods: " + s);
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getBoolean("ok"))
                            {
                                JSONObject object = jsonObject.getJSONObject("result");
                                nMeal.setFoods(JsonParser.JsonFoods(object.getJSONArray("foods")));
                                setLunch();
                                timeout1 = false;
                            } else
                            {
                                nMeal.setFoods(new ArrayList<Food>());
                                setLunch();
                                timeout1 = false;
                            }

                        } catch (JSONException e)
                        {
                            timeout1 = true;
                            Log.d(TAG, "FoodsError: " + s + "\n" + e.getMessage());
                        }
                    }
                });
            }

        });
        web.loadUrl(UrlSelf.getBaseUrl() + nMeal.getLink());
    }


    private void getBreakFast()
    {

        String lbl = getLabel();
        Controller.MyWeb.evaluateJavascript("(function () {" +
                "try {" +
                "var object = '{';" +
                "object = object + '\"ok\":true,';" +
                "object = object + '\"result\":{';" +
                "object = object + '\"kind_status\":\"' +document.querySelector('input#GhazaC" + today + "').className.toString()+ '\",';" +
                "if(document.querySelector('span#lbl" + lbl + "_ghazaC" + today + "')!=null)" +
                "{" +
                "object = object + '\"food_name\":\"'+document.querySelector('span#lbl" + getLabel() + "_ghazaC" + today + "').textContent.toString()+'\",';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"food_name\":\"'+document.querySelector('span#lbl" + lbl.toLowerCase() + "_ghazaC" + today + "').textContent.toString()+'\",';" +
                "}" +
                "object = object + '\"kind\":\"' +document.querySelector('input#GhazaC" + today + "').value.toString()+ '\",';" +
                "object = object + '\"self\":\"' +document.querySelector('input#EditC" + today + "').value.toString()+ '\",';" +
                "object = object + '\"count\":\"' +document.querySelector('input#txtc_numGhaza" + today + "').value.toString()+ '\",';" +
                "object = object + '\"count_status\":\"' +document.querySelector('input#txtc_numGhaza" + today + "').className.toString()+ '\",';" +
                "object = object + '\"count_color\":\"' +document.querySelector('input#txtc_numGhaza" + today + "').style.color.toString()+ '\",';" +
                "if(document.querySelector('a#linkC" + today + "')!=null)" +
                "{" +
                "object = object + '\"link\":\"'+document.querySelector('a#linkC"+ today + "').getAttribute('onclick').toString()+'\"';" +
                "}" +
                "else" +
                "{" +
                "object = object + '\"link\":\"null\"';" +
                "}" +
                "object = object + '}';" +
                "object = object + '}';" +
                "return object.toString();" +
                "}" +
                "catch(err){" +
                "var object='{';" +
                "object=object+'\"ok\":false,';" +
                "object=object+'\"message\":\"'+err.message+'\"';" +
                "object=object+'}';" +
                "return object.toString();" +
                "}" +
                "}" +
                "());", new ValueCallback<String>()
        {
            @Override
            public void onReceiveValue(String s)
            {

                try
                {
                    s = s.substring(1, s.length() - 1);
                    s = s.replace("\\", "");
                    Log.d(TAG, "onReceiveCMeal: " + s);
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getBoolean("ok"))
                    {
                        JSONObject object = jsonObject.getJSONObject("result");
                        cMeal = JsonParser.JsonMeal(object,'C');
                        Food food = new Food();
                        food.setKind(cMeal.getKind());
                        food.setName(cMeal.getFood_name());
                        List<Food> foods = new ArrayList<>();
                        foods.add(food);
                        cMeal.setFoods(foods);
                        Log.d(VolleyLog.TAG, "getStatus: "+ today+" C "+cMeal.getStatus());
                        if (cMeal.getStatus().equals(MealStatus.UnReserve_Changable))
                            getFoodBreakFast();
                        else
                            setBreakfast();
                    } else
                    {
                        cLlError.setVisibility(View.VISIBLE);
                        Log.d(TAG, "oncMealError : " + jsonObject.getString("message"));
                    }

                } catch (JSONException e)
                {
                    cLlError.setVisibility(View.VISIBLE);
                    Log.d(TAG, "oncMealErrorJsonError: " + e.getMessage());
                }
            }
        });
    }

    private void setBreakfast()
    {

        if (cMeal.getStatus().equals(MealStatus.Reserved_changeable) || cMeal.getStatus().equals(MealStatus.UnReserve_Changable))
        {
            cLlCount.setVisibility(View.VISIBLE);
            cSelf.setVisibility(View.VISIBLE);
            cSelf.setEnabled(true);
            cSelf.setSelection(Integer.valueOf(cMeal.getSelf())-1);
        } else if (cMeal.getStatus().equals(MealStatus.Reserved_UnChangable))
        {
            cSelf.setVisibility(View.VISIBLE);
            cSelf.setEnabled(false);
            cSelf.setSelection(Integer.valueOf(cMeal.getSelf())-1);
        } else if (cMeal.getStatus().equals(MealStatus.UnReserve_UnChangable) || cMeal.getStatus().equals(MealStatus.Reserved_Use))
        {
            cLlCount.setVisibility(View.GONE);
            cSelf.setVisibility(View.GONE);
        }

        if (cMeal.getFoods().size() == 0)
        {
            cLlError.setVisibility(View.VISIBLE);
            cLlCount.setVisibility(View.GONE);
            cSelf.setVisibility(View.GONE);
            cRcvFood.setVisibility(View.GONE);
        } else
        {
            Log.d(TAG, "setBreakfast: " + cMeal.getStatus());
            if (cLlError.isShown())
                cLlError.setVisibility(View.GONE);
            cRcvFood.setVisibility(View.VISIBLE);
            cAdapter = new FoodAdapter(getContext(), DaysFragment.this, cMeal);
            cRcvFood.setLayoutManager(cManager);
            cRcvFood.setAdapter(cAdapter);
        }
        cProgressBar.setVisibility(View.GONE);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void getFoodBreakFast()
    {

        Log.d(TAG, "getSaturday(): " + usernameShared.getSaturday());
        final char c = 'C';
        final String t = c + "" + today + "";
        final WebView web = new WebView(getContext());
        WebSettings CSettings = web.getSettings();
        CSettings.setJavaScriptEnabled(true);


        web.setWebViewClient(new WebViewClient()
        {
            boolean timeout1 = true;

            @Override
            public void onPageStarted(final WebView view, String url, Bitmap favicon)
            {
                timeout1 = true;
                final Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        if (timeout1)
                        {
                            getActivity().runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    web.loadUrl(UrlSelf.getBaseUrl() +cMeal.getLink());
                                }
                            });

                        } else
                            timer.cancel();
                    }
                }, 3500, 3500);
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                //برای اینکه به طور خودکار رفرش شود اگر بعد از مدتی جواب نداد
                web.evaluateJavascript("(function() {" +

                        "try {" +
                        "var object = '{';" +
                        "object = object + '\"ok\":true,';" +
                        "object = object + '\"result\":{';" +
                        "object = object + '\"foods\":[';" +
                        "var l= document.querySelector('table#GhazaGrid').rows.length; " +
                        "for (var i = 1 ; i < l;i++)" +
                        "{ " +
                        "if(i!=1)" +
                        "{" +
                        "object = object + ',';" +
                        "}" +
                        "object = object + '{\"kind\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[0].textContent + '\",';" +
                        "object = object + '\"name\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[2].textContent + '\",';" +
                        "object = object + '\"price\":\"' + document.querySelector('table#GhazaGrid').rows[i].cells[3].textContent + '\"}';" +
                        "}" +
                        "object = object + ']';" +
                        "object = object + '}';" +
                        "object = object + '}';" +
                        "return object.toString();" +
                        "}" +
                        "catch(err){" +
                        "var object='{';" +
                        "v=document.querySelector('table#TABLE1').id;" +
                        "object=object+'\"ok\":false,';" +
                        "object=object+'\"message\":\"'+err.message+'\"';" +
                        "object=object+'}';" +
                        "return object.toString();" +
                        "}" +
                        "}" +
                        "());", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                        try
                        {
                            s = s.substring(1, s.length() - 1);
                            s = s.replace("\\", "");
                            Log.d(TAG, "onReceiveFoods: " + s);
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.getBoolean("ok"))
                            {
                                JSONObject object = jsonObject.getJSONObject("result");
                                cMeal.setFoods(JsonParser.JsonFoods(object.getJSONArray("foods")));
                                setBreakfast();
                                timeout1 = false;
                            } else
                            {
                                cMeal.setFoods(new ArrayList<Food>());
                                setBreakfast();
                                timeout1 = false;
                            }

                        } catch (JSONException e)
                        {
                            timeout1 = true;
                            Log.d(TAG, "FoodsError: " + s + "\n" + e.getMessage());
                        }
                    }
                });
            }

        });
        web.loadUrl(UrlSelf.getBaseUrl() + cMeal.getLink());
    }


    private String getLabel()
    {
        switch (today)
        {
            case "1":
                return "sat";
            case "2":
                return "Sun";
            case "3":
                return "Mon";
            case "4":
                return "thr";
            case "5":
                return "Wed";
            case "6":
                return "Tur";
            case "7":
                return "Fri";
            default:
                return "sat";
        }
    }

    @Override
    public void cancelFood(Meal meal, Food food)
    {
        listener.ShowSave();
        if(meal.getName()=='C')
        {
            Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtc_numGhaza" + today + "]').value = 0;", new ValueCallback<String>()
            {
                @Override
                public void onReceiveValue(String s)
                {
                }
            });
        }
        else if(meal.getName()=='N')
        {
            Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtn_numGhaza" + today + "]').value = 0;", new ValueCallback<String>()
            {
                @Override
                public void onReceiveValue(String s)
                {
                }
            });
        }
        else if(meal.getName()=='S')
        {
            Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txts_numGhaza" + today + "]').value = 0;", new ValueCallback<String>()
            {
                @Override
                public void onReceiveValue(String s)
                {
                }
            });
        }

    }

    @Override
    public void selectFood(Meal meal, Food food)
    {
        listener.ShowSave();
        if(meal.getStatus().equals(MealStatus.UnReserve_Changable))
        {
            String m="";
            switch (meal.getName())
            {
                case 'C':
                    m = "0";
                    break;
                case 'N':
                    m="1";
                    break;
                case 'S':
                    m="2";
                    break;
                default:
                    break;
            }

                Controller.MyWeb.evaluateJavascript("javascript:SelectGh(\"" + food.getKind() + "\",\""+m+"\",\"" + food.getName() + "\",\"" + today + "\",\"" + food.getPrice() + "\");", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {

                    }
                });
        }else if(meal.getStatus().equals(MealStatus.Reserved_changeable))
        {
            if(meal.getName()=='C')
            {
                Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtc_numGhaza" + today + "]').value = "+cMeal.getCount()+";", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                    }
                });
            }
            else if(meal.getName()=='N')
            {
                Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txtn_numGhaza" + today + "]').value = "+nMeal.getCount()+";", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                    }
                });
            }
            else if(meal.getName()=='S')
            {
                Controller.MyWeb.evaluateJavascript("javascript:document.querySelector('input[name=txts_numGhaza" + today + "]').value = "+sMeal.getCount()+";", new ValueCallback<String>()
                {
                    @Override
                    public void onReceiveValue(String s)
                    {
                    }
                });
            }
        }
    }
}
