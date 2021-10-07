package ir.selfino.android.Fragment.LogSin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Map;

import ir.selfino.android.Dialog.DialogErrorConnection;
import ir.selfino.android.Dialog.DialogProgressBar;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlAPI;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.R;


public class LoginFragment extends Fragment
{

    private String pass1;

    private EditText etPass;
    private Button btnDone,btnForget;


    private DialogErrorConnection dialogError;
    private DialogProgressBar progressBar;
    private UsernameShared usernameShared;


    public LoginFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        setInflation(view);
        return view;
    }

    private void setInflation(View view)
    {
        usernameShared=new UsernameShared(getContext());
        dialogError=new DialogErrorConnection(getContext());
        progressBar=new DialogProgressBar(getContext());
        etPass=(EditText)view.findViewById(R.id.et_pass_login_fragment);
        btnDone=(Button)view.findViewById(R.id.btn_done_login_fragment);
        btnDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pass1 = etPass.getText().toString();
                //todo login
            }
        });
        btnForget=(Button)view.findViewById(R.id.btn_forget_pass_login_fragment);
        btnForget.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkPhoneNumber();
            }
        });
    }

    private void checkPhoneNumber()
    {
        progressBar.show();
        StringRequest putRequest = new StringRequest(Request.Method.POST, UrlAPI.getSendVerifyCode(),
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        // response
                        progressBar.dismiss();
                        checkResponse(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressBar.dismiss();
                        dialogError.show();
                        dialogError.btnTry.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                checkPhoneNumber();
                                dialogError.dismiss();
                            }
                        });
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
                params.put("phone_number","");
                return params;
            }
        };
        putRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 5, 1f));
        Controller.getPermission().addToRequestQueue(putRequest);
    }
    private void checkResponse(String response)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            boolean flag = jsonObject.getBoolean("ok");
            if (flag)
            {
                /* parse json */
                JSONObject object = jsonObject.getJSONObject("result");
                Toast.makeText(getContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragments_fade_out,
                                R.anim.fragment_fade_in, R.anim.fragments_fade_out)
                        .replace(R.id.fr_log_sin_activity, VerifyCodeFragment.newInstance(object.getInt("verify_code"), "فراموشی رمز عبور")).commit();

            } else
            {

                Toast.makeText(getContext(), "خطا در سیستم دوباره تلاش کنید  .", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "خطا در سیستم با پشتیبانی تماس بگیرید  .", Toast.LENGTH_SHORT).show();
        }
    }
}