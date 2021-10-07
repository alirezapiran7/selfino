package ir.selfino.android.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.selfino.android.Dialog.DialogErrorConnection;
import ir.selfino.android.Dialog.DialogProgressBar;
import ir.selfino.android.Function.Adapter.UniversityAdapter;
import ir.selfino.android.Function.Models.University;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlAPI;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.Interfaces.UniversityListener;
import ir.selfino.android.R;

public class UniversityActivity extends AppCompatActivity implements UniversityListener
{
    private Button btnLogin,btnUni,btnState,btnEnter;
    private TextView txtSelfino;
    private ImageView imgMain;


    private DialogProgressBar progressBar;
    private UsernameShared usernameShared;

    private List<University> universities;
    private AlertDialog dial;
    private View pbr;
    private University university;
    private UniversityAdapter universityAdapter;
    private RecyclerView rcvUniversity;
    private LinearLayoutManager layoutManager;
    private ImageButton imgSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        setInflation();
    }
    private void setInflation()
    {
        btnUni=(Button)findViewById(R.id.btn_select_university);
        btnUni.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        btnLogin=(Button)findViewById(R.id.btn_sin_log_university);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(university!=null)
                {
                    startActivity(new Intent());
                }else
                {
                    Toast.makeText(UniversityActivity.this, "ابتدا دانشگاه خود را انتخاب کنید .", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnEnter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(university!=null)
                {
                    startActivity(new Intent());
                }else
                {
                    Toast.makeText(UniversityActivity.this, "ابتدا دانشگاه خود را انتخاب کنید .", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgMain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(university!=null)
                {
                    startActivity(new Intent());
                }else
                {
                    Toast.makeText(UniversityActivity.this, "ابتدا دانشگاه خود را انتخاب کنید .", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void CreateDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(UniversityActivity.this);
        View menu = LayoutInflater.from(UniversityActivity.this).inflate(R.layout.dialog_sellect_university, null);
        builder.setView(menu);
        dial = builder.create();
        rcvUniversity = (RecyclerView) menu.findViewById(R.id.rcv_list_university_dialog);
        pbr = (View) menu.findViewById(R.id.pbr_university_dialog);
        imgSearch = (ImageButton) menu.findViewById(R.id.img_search_university_dialog);
        imgSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        if(universities!=null)
        {
            universityAdapter = new UniversityAdapter(UniversityActivity.this, universities, UniversityActivity.this);
            rcvUniversity.setAdapter(universityAdapter);
            rcvUniversity.setLayoutManager(new LinearLayoutManager(UniversityActivity.this));
        }else {
            getUniversitiesApi();
        }

        dial.show();
    }

    private void getUniversitiesApi()
        {
            rcvUniversity.setVisibility(View.GONE);
            pbr.setVisibility(View.VISIBLE);
            StringRequest putRequest = new StringRequest(Request.Method.POST, UrlAPI.getSignUp(),
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            try
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean flag = jsonObject.getBoolean("ok");
                                if (flag)
                                {
                                    rcvUniversity.setVisibility(View.VISIBLE);
                                    //universities = JsonParser.JsonStores(jsonObject.getJSONArray("result"));
                                    universityAdapter = new UniversityAdapter(UniversityActivity.this, universities, UniversityActivity.this);
                                    rcvUniversity.setAdapter(universityAdapter);
                                    rcvUniversity.setLayoutManager(new LinearLayoutManager(UniversityActivity.this));
                                    pbr.setVisibility(View.GONE);
                                } else
                                    dial.dismiss();
                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            dial.dismiss();

                        }
                    }
            )
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    //params.put("api_token", usernameShared.getApi_token());
                    return params;
                }
            };
            putRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 5, 1f));
            Controller.getPermission().addToRequestQueue(putRequest);

    }

    @Override
    public void onSelectedUniversity(University university)
    {
        btnUni.setText("");
        this.university=university;
        //todo save to prefrences
    }
}
