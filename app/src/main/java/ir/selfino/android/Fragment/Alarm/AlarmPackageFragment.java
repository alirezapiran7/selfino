package ir.selfino.android.Fragment.Alarm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ir.selfino.android.Function.Adapter.OptionAdapter;
import ir.selfino.android.R;
import lib.kingja.switchbutton.SwitchMultiButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmPackageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmPackageFragment extends Fragment
{
    private ProgressBar pbrAlarm;
    private SwitchMultiButton smbPackage;
    private RecyclerView rcvOptions;
    private TextView txtDes;
    private Button btnEnable;
    private LinearLayoutManager layoutManager;
    private OptionAdapter optionAdapter;
    private String[]packageName;
    public AlarmPackageFragment()
    {
        // Required empty public constructor
    }


    public static AlarmPackageFragment newInstance(String param1, String param2)
    {
        AlarmPackageFragment fragment = new AlarmPackageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        View view= inflater.inflate(R.layout.fragment_package_alarm, container, false);
        setInflation(view);
        return view;
    }
    public void setInflation(View view)
    {
        smbPackage=(SwitchMultiButton)view.findViewById(R.id.smb_package_name);
        packageName= new String[]{"رایگان", "نقره ای", "طلایی"};
        smbPackage.setText("رایگان", "نقره ای", "طلایی");
        smbPackage.setSelectedTab(0);
        smbPackage.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener()
        {
            @Override
            public void onSwitch(int position, String tabText)
            {

            }
        });
        txtDes=(TextView)view.findViewById(R.id.txt_des_alarm);
        btnEnable=(Button) view.findViewById(R.id.btn_enable_alarm);
        rcvOptions=(RecyclerView)view.findViewById(R.id.rcv_option_packages_alarm);
        pbrAlarm=(ProgressBar)view.findViewById(R.id.pbr_alarm);
    }

}
