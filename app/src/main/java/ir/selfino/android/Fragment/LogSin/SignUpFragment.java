package ir.selfino.android.Fragment.LogSin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import ir.selfino.android.Activity.MainActivity;
import ir.selfino.android.Dialog.DialogErrorConnection;
import ir.selfino.android.Dialog.DialogProgressBar;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlAPI;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.R;


public class SignUpFragment extends Fragment
{
    private static final String HEADER = "header";

    // TODO: Rename and change types of parameters
    private String header;
    private String pass1,pass2;

    private TextView txtHeader;
    private EditText etPass1,etPass2;
    private Button btnDone;

    private DialogErrorConnection dialogError;
    private DialogProgressBar progressBar;
    private UsernameShared usernameShared;


    public static SignUpFragment newInstance(String header)
    {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
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
            header = getArguments().getString(HEADER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        setInflation(view);
        return view;
    }

    private void setInflation(View view)
    {
        usernameShared =new UsernameShared(getContext());
        dialogError=new DialogErrorConnection(getContext());
        progressBar=new DialogProgressBar(getContext());
        txtHeader=(TextView)view.findViewById(R.id.txt_forget_sign_up_fragment);
        txtHeader.setText(header);
        etPass1=(EditText)view.findViewById(R.id.et_pass1_sign_up_fragment);
        etPass2=(EditText)view.findViewById(R.id.et_pass2_sign_up_fragment);
        btnDone=(Button)view.findViewById(R.id.btn_done_sign_up_fragment);
        btnDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pass1 = etPass1.getText().toString();
                pass2 = etPass2.getText().toString();
                if (pass1.length() < 6 || pass2.length() < 6)
                {
                    Toast.makeText(getContext(), "رمز عبور حداقل باید ۶ رقم باشد!", Toast.LENGTH_SHORT).show();
                    etPass1.setText("");
                    etPass2.setText("");
                    return;
                }
                if (pass1.equals(pass2)&&header.equals("ثبت نام"))
                {
                    signUpApi();
                }if (pass1.equals(pass2)&&header.equals("فراموشی رمز عبور"))
            {
                //todo forget pass
            }
                else
                {
                    Toast.makeText(getContext(), "رمز‌های عبور وارد شده یکسان نیست!", Toast.LENGTH_SHORT).show();
                    etPass2.setText("");
                    etPass1.setText("");
                }
            }
        });
    }

    private void signUpApi()
    {
        progressBar.show();
        StringRequest putRequest = new StringRequest(Request.Method.POST, UrlAPI.getSignUp(),
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
                                signUpApi();
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
                params.put("password", pass1);
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
               usernameShared.setPassword(pass1);
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            } else
            {
                //todo if not sign up?
            }

        } catch (JSONException e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "خطا در سیستم با پشتیبانی تماس بگیرید  .", Toast.LENGTH_SHORT).show();
        }
    }
}
