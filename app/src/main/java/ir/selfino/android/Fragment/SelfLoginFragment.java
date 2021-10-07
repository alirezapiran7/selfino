package ir.selfino.android.Fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import ir.selfino.android.Activity.MainActivity;
import ir.selfino.android.Dialog.DialogErrorConnection;
import ir.selfino.android.Function.Controller;
import ir.selfino.android.Function.SharedPreferences.UsernameShared;
import ir.selfino.android.Function.Url.UrlSelf;
import ir.selfino.android.R;

import static ir.selfino.android.Function.Controller.TAG;

public class SelfLoginFragment extends Fragment
{

    private String captcha = "";
    private String message = "";
    private String captcha_type = "";
    private EditText edtUserCode, edtUserPass, edtCapthca;
    private Button btnEnter;
    private WebView imgCapthca2;
    private ImageView imgCapthca1;
    private UsernameShared usernameShared;
    private DialogErrorConnection dialogError;
    private ImageView imgStu,imgPass,imgCap;
    ////////
    private ProgressBar progressBar;
    private RelativeLayout rlError;
    private Button btnError;
    private ScrollView svLogin;


    public SelfLoginFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_self_login, container, false);
        setInflation(view);
        return view;
    }

    public void setInflation(View view)
    {
        usernameShared = new UsernameShared(getContext());
        Controller.clearCookies();
        progressBar = (ProgressBar) view.findViewById(R.id.pbr_self_login);
        rlError = (RelativeLayout) view.findViewById(R.id.rl_error_self_login);
        btnError = (Button) view.findViewById(R.id.btn_error_self_login);
        btnError.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Controller.MyWeb.loadUrl(Controller.MyWeb.getUrl());
            }
        });

        imgCap=(ImageView)view.findViewById(R.id.img_cap_delete_code_self_login);
        imgCap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                edtCapthca.setText("");
                imgCap.setVisibility(View.GONE);
                edtCapthca.requestFocus();
            }
        });
        imgCap.setVisibility(View.GONE);
        imgPass=(ImageView)view.findViewById(R.id.img_pass_delete_code_self_login);
        imgPass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                edtUserPass.setText("");
                imgPass.setVisibility(View.GONE);
                edtUserPass.requestFocus();
            }
        });
        imgPass.setVisibility(View.GONE);
        imgStu=(ImageView)view.findViewById(R.id.img_stu_delete_code_self_login);
        imgStu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                edtUserCode.setText("");
                imgStu.setVisibility(View.GONE);
                edtUserCode.requestFocus();
            }
        });
        imgStu.setVisibility(View.GONE);
        svLogin = (ScrollView) view.findViewById(R.id.sv_self_login);
        edtUserCode = (EditText) view.findViewById(R.id.et_stu_code_self_login);
        edtUserCode.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if(edtUserCode.length()>0)
                    imgStu.setVisibility(View.VISIBLE);
                else
                    imgStu.setVisibility(View.GONE);
            }
        });
        edtUserPass = (EditText) view.findViewById(R.id.et_stu_pass_self_login);
        edtUserPass.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if(edtUserPass.length()>0)
                    imgPass.setVisibility(View.VISIBLE);
                else
                    imgPass.setVisibility(View.GONE);
            }
        });
        edtCapthca = (EditText) view.findViewById(R.id.et_captcha_self_login);
        btnEnter = (Button) view.findViewById(R.id.btn_done_self_login);
        edtCapthca.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            if( ((MainActivity)getActivity()).bottomNavigation.isHidden())
                                ((MainActivity)getActivity()).bottomNavigation.restoreBottomNavigation();
                            loginSelf(edtUserCode.getText().toString(), edtUserPass.getText().toString(), edtCapthca.getText().toString());
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(edtCapthca.getWindowToken(), 0);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        edtCapthca.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

                if(edtCapthca.length()>2)
                {
                    if (!((MainActivity) getActivity()).bottomNavigation.isHidden())
                        ((MainActivity) getActivity()).bottomNavigation.hideBottomNavigation();

                }else if (((MainActivity) getActivity()).bottomNavigation.isHidden())
                    ((MainActivity) getActivity()).bottomNavigation.restoreBottomNavigation();
                if(edtCapthca.length()>0)
                    imgCap.setVisibility(View.VISIBLE);
                else
                    imgCap.setVisibility(View.GONE);
            }
        });
        btnEnter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                loginSelf(edtUserCode.getText().toString(), edtUserPass.getText().toString(), edtCapthca.getText().toString());
            }
        });
        imgCapthca1 = (ImageView) view.findViewById(R.id.img_captcha_self_login);
        imgCapthca2 = (WebView) view.findViewById(R.id.web_captcha_self_login);
        Controller.MyWeb.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                if (url.toLowerCase().equals(UrlSelf.getBaseUrl().toLowerCase()) || url.toLowerCase().equals(UrlSelf.getLoginUrl().toLowerCase()))
                {
                    Controller.MyWeb.evaluateJavascript("(function () {" +
                            "try {" +
                            "var object = '{';" +
                            "object = object + '\"ok\":true,';" +
                            "object = object + '\"result\":{';" +
                            "object = object + '\"message\":\"' + document.querySelector('span#lblmessage').textContent.toString() + '\",';" +
                            "if (document.querySelector('img[src^=CaptchaImage]') != null)" +
                            "{" +
                            "object = object + '\"captcha_image\":\"' + document.querySelector('img[src^=CaptchaImage]').src.toString() + '\",';" +
                            "object = object + '\"captcha_type\":\"1\"';" +
                            "}" +
                            "else " +
                            "{" +
                            "object = object + '\"captcha_image\":\"' + document.querySelector('img[src^=GenerateCaptcha]').src.toString() + '\",';" +
                            "object = object + '\"captcha_type\":\"2\"';" +
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

                                progressBar.setVisibility(View.GONE);
                                s = s.substring(1, s.length() - 1);
                                s = s.replace("\\", "");
                                Log.d(TAG, "onReceiveSelf: " + s);
                                JSONObject jsonObject = new JSONObject(s);
                                if (jsonObject.getBoolean("ok"))
                                {
                                    svLogin.setVisibility(View.VISIBLE);
                                    JSONObject object = jsonObject.getJSONObject("result");
                                    captcha = object.getString("captcha_image");
                                    message = object.getString("message");
                                    if(!message.contains("نام کاربری و کلمه عبور خود را وارد نمائید")&&!message.contains("غیر مجاز"))
                                        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
                                    captcha_type = object.getString("captcha_type");
                                    if (captcha_type.equals("1"))
                                    {
                                        Picasso.get().load(captcha).into(imgCapthca1);
                                        imgCapthca1.setVisibility(View.VISIBLE);
                                        imgCapthca2.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        imgCapthca2.loadUrl(captcha);
                                        imgCapthca2.setVisibility(View.VISIBLE);
                                        imgCapthca1.setVisibility(View.GONE);
                                    }
                                    if (rlError.isShown())
                                        rlError.setVisibility(View.GONE);
                                    setPerfernces();
                                } else
                                {
                                    rlError.setVisibility(View.VISIBLE);
                                    Log.d(TAG, "onLoginError : " + jsonObject.getString("message"));
                                }

                            } catch (JSONException e)
                            {
                                rlError.setVisibility(View.VISIBLE);
                                Log.d(TAG, "onLoginJsonError: " + e.getMessage());
                            }
                        }
                    });

                } else if (url.toLowerCase().equals(UrlSelf.getReserveUrl().toLowerCase()))
                {
                    usernameShared.setStu_id(edtUserCode.getText().toString());
                    usernameShared.setStu_pass(edtUserPass.getText().toString());
                    startFragment();
                }
                else if (url.toLowerCase().contains(UrlSelf.getNazarSanjiUrl().toLowerCase()))
                {
                    Controller.MyWeb.loadUrl(UrlSelf.getReserveUrl());
                }else
                {
                    svLogin.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    rlError.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error)
            {

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                progressBar.setVisibility(View.VISIBLE);
                rlError.setVisibility(View.GONE);
                svLogin.setVisibility(View.GONE);
            }
        });

        Controller.MyWeb.loadUrl(UrlSelf.getBaseUrl());
        edtCapthca.requestFocus();
    }

    private void startFragment()
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ReserveFragment reserveFragment=new ReserveFragment();
        transaction.replace(R.id.frm_holder_fragment,reserveFragment);
        transaction.commit();
    }

    public void loginSelf(String userCode, String pass, String Captecha)
    {
        String message = "";
        if (userCode.length() == 0)
        {
            message = "لطفا نام کاربری را وارد کنید";
        }
        if (pass.length() == 0)
        {
            if (!message.equals(""))
                message = "لطفا نام کاربری و رمز عبور را وارد کنید.";
            else
                message = "لطفا رمز عبور را وارد کنید";
        }
        if (captcha.length() == 0)
        {
            if (message.equals(""))
                message = "لطفا کد تصویر را وارد کنید.";
            else if (message.equals("لطفا نام کاربری را وارد کنید"))
                message = "لطفا نام کاربری و کد تصویر را وارد کنید.";
            else if (message.equals("لطفا رمز عبور را وارد کنید"))
                message = "لطفا رمز عبور و کد تصویر را وارد کنید.";
            else if (message.equals("لطفا نام کاربری و رمز عبور را وارد کنید."))
                message = "وارد کردن تمامی فیلدها الزمی است.";
        }
        if (message.equals(""))
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtCapthca.getWindowToken(), 0);
            if( ((MainActivity)getActivity()).bottomNavigation.isHidden())
                ((MainActivity)getActivity()).bottomNavigation.restoreBottomNavigation();
            Controller.MyWeb.evaluateJavascript("(function() { " +
                    "try{ " +
                    "document.querySelector('input[name=txtusername]').value = '" + userCode + "';" +
                    "document.querySelector('input[name=txtpassword]').value = '" + pass + "';" +
                    "if(document.querySelector('input[name=CaptchaControl1]')!=null)" +
                    "document.querySelector('input[name=CaptchaControl1]').value = '" + Captecha + "';" +
                    "if(document.querySelector('input[name=txtCaptchaText]')!=null)" +
                    "document.querySelector('input[name=txtCaptchaText]').value = '" + Captecha + "';" +
                    "document.querySelector('input[name=btnlogin]').click();" +
                    "var object = '{';" +
                    "object = object + '\"ok\":true';" +
                    "object = object + '}';" +
                    "return object.toString();" +
                    "}" +
                    "catch(err)" +
                    "{" +
                    "var object = '{';" +
                    "object = object + '\"ok\":false,';" +
                    "object=object+'\"message\":\"'+err.message+'\"';" +
                    "object = object + '}';" +
                    "return object.toString();" +
                    "}}());", new ValueCallback<String>()
            {
                @Override
                public void onReceiveValue(String s)
                {
                    try
                    {
                        s = s.substring(1, s.length() - 1);
                        s = s.replace("\\", "");
                        JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.getBoolean("ok"))
                        {

                        } else
                        {
                            Log.d(TAG, "onEnterLoginError : " + jsonObject.getString("message"));
                        }

                    } catch (JSONException e)
                    {

                        Log.d(TAG, "onEnterLoginJsonError: " + e.getMessage());
                    }
                }
            });
        } else
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setPerfernces()
    {
        edtCapthca.setText("");
        edtUserPass.setText(usernameShared.getStu_pass());
        edtUserCode.setText(usernameShared.getStu_id());
        if(edtUserCode.length()>0)
            imgStu.setVisibility(View.VISIBLE);
        if(edtUserPass.length()>0)
            imgPass.setVisibility(View.VISIBLE);
        if(edtUserCode.length()==0)
            edtUserCode.requestFocus();
        else if(edtUserPass.length()==0)
            edtUserPass.requestFocus();
        else
            edtCapthca.requestFocus();

    }
}
