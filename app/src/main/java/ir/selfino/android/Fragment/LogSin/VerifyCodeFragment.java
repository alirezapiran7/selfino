package ir.selfino.android.Fragment.LogSin;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Map;

import ir.selfino.android.Dialog.DialogErrorConnection;
import ir.selfino.android.Dialog.DialogProgressBar;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlAPI;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyCodeFragment extends Fragment
{
    private static final String VERIFY_CODE = "verify_code";
    private static final String HEADER = "header";


    private int verifyCode;
    private String header;


    private TextView txtHeader,txtTime;
    private Button btnTry,btnDone;
    private EditText etVC;
    private ImageView imgBack;
    private UsernameShared usernameShared;

    private DialogProgressBar progressBar;
    private DialogErrorConnection dialogError;


    public VerifyCodeFragment()
    {
        // Required empty public constructor
    }
    public static VerifyCodeFragment newInstance(int verifyCode, String header)
    {
        VerifyCodeFragment fragment = new VerifyCodeFragment();
        Bundle args = new Bundle();
        args.putInt(VERIFY_CODE, verifyCode);
        args.putString(HEADER, header);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            verifyCode = getArguments().getInt(VERIFY_CODE);
            header = getArguments().getString(HEADER);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_code, container, false);
        setInflation(view);
        return view;
    }

    private void setInflation(View view)
    {
        usernameShared =new UsernameShared(getContext());
        dialogError=new DialogErrorConnection(getContext());
        progressBar=new DialogProgressBar(getContext());


        txtHeader=(TextView)view.findViewById(R.id.txt_forget_sign_check_fragment);
        txtHeader.setText(header);
        etVC=(EditText)view.findViewById(R.id.et_verify_check_phone);
        btnTry=(Button)view.findViewById(R.id.btn_again_code_check_phone);
        btnTry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkPhoneNumber();
                setTimer();
                btnTry.setEnabled(false);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(Integer.valueOf(etVC.getText().toString())==verifyCode)
                {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fragment_fade_in , R.anim.fragments_fade_out ,
                                    R.anim.fragment_fade_in , R.anim.fragments_fade_out)
                            .replace(R.id.fr_log_sin_activity , SignUpFragment.newInstance(header)).commit();
                }
                else
                {
                    Toast.makeText(getContext(), "کد وارد شده صحیح نیست", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgBack=(ImageView)view.findViewById(R.id.img_back_check_phone_fragment);
        imgBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!header.equals("ثبت نام"))
                {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fragment_fade_in , R.anim.fragments_fade_out ,
                                    R.anim.fragment_fade_in , R.anim.fragments_fade_out)
                            .replace(R.id.fr_log_sin_activity ,new LoginFragment()).commit();
                }
                else
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fragment_fade_in , R.anim.fragments_fade_out ,
                                    R.anim.fragment_fade_in , R.anim.fragments_fade_out)
                            .replace(R.id.fr_log_sin_activity , new PhoneFragment()).commit();
            }
        });
        setTimer();
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
                params.put("phone_number", "");
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
                JSONObject object=jsonObject.getJSONObject("result");
                verifyCode=object.getInt("verify_code");
                Toast.makeText(getContext(), object.getString("message"), Toast.LENGTH_SHORT).show();

            } else
            {
                btnTry.setEnabled(true);
                Toast.makeText(getContext(), "خطا دوباره امتحان کنید  .", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "خطا در سیستم با پشتیبانی تماس بگیرید  .", Toast.LENGTH_SHORT).show();
        }
    }
    public void setTimer(){

        new CountDownTimer(60000 , 1000){

            @Override
            public void onTick(long l) {
                txtTime.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                btnTry.setEnabled(true);
                txtTime.setText("0");
            }
        }.start();
    }
}
