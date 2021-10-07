package ir.selfino.android.Fragment.LogSin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import ir.selfino.android.Function.SharedPreferences.LoginShared;
import ir.selfino.android.Function.Url.UrlAPI;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.R;

public class PhoneFragment extends Fragment
{
    private EditText etPhoneNumber;
    private String phoneNumber;
    private Button btnAccept;
    private ImageView imgBack, prev;
    private LoginShared loginShared;
    private DialogProgressBar progressBar;
    private DialogErrorConnection dialogError;


    public PhoneFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        setInflation(view);
        return view;
    }

    private void setInflation(View view)
    {
        loginShared = new LoginShared(getContext());
        progressBar = new DialogProgressBar(getContext());
        dialogError = new DialogErrorConnection(getContext());
        etPhoneNumber = (EditText) view.findViewById(R.id.et_phone_number_fragment);
        imgBack = (ImageView) view.findViewById(R.id.img_back_phone_fragment);
        btnAccept = (Button) view.findViewById(R.id.btn_phone_number_fragment);
        btnAccept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (etPhoneNumber.getText().length() == 11)
                {
                    phoneNumber = etPhoneNumber.getText().toString();
                    loginShared.setPhoneNumber(phoneNumber);
                    checkPhoneNumber();
                } else
                {
                    Toast.makeText(getContext(), "شماره موبایل نامعتبر", Toast.LENGTH_SHORT).show();
                }

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getActivity().onBackPressed();
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
                params.put("phone_number", phoneNumber);
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
                if(object.getBoolean("is_register"))
                {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragments_fade_out,
                                    R.anim.fragment_fade_in, R.anim.fragments_fade_out)
                            .replace(R.id.fr_log_sin_activity,new LoginFragment()).commit();
                }else
                {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragments_fade_out,
                                    R.anim.fragment_fade_in, R.anim.fragments_fade_out)
                            .replace(R.id.fr_log_sin_activity, VerifyCodeFragment.newInstance(object.getInt("verify_code"), "ثبت نام")).commit();
                }

            } else if (jsonObject.getInt("error_code") == 200)
            {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragments_fade_out).replace(R.id.fr_log_sin_activity, new PhoneFragment()).commit();
            } else
            {
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragments_fade_out).replace(R.id.fr_log_sin_activity, new PhoneFragment()).commit();
            }

        } catch (JSONException e)
        {
            Toast.makeText(getContext(), "خطا در سیستم با پشتیبانی تماس بگیرید  .", Toast.LENGTH_SHORT).show();
        }
    }
}
