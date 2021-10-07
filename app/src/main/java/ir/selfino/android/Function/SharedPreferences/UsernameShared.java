package ir.selfino.android.Function.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsernameShared
{

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String SHARED_REFFRENCE_NAME = "Username";


    private int profileID;
    private String first_name;
    private String last_name;
    private String password;
    private String nick_name;
    private String phone_number;
    private String api_token;
    //
    private String android_id = "";
    private String type;
    private int store_id;
    private String store_name;
    private String email;
    private String full_name;
    private int TM;
    private int all_TM;
    private String birthday;
    private String image;
    private String store_icon;
    private String address;
    private int anif_code;
    private int level_id;
    private String show_order_store;
    private String switch_store;
    private String show_new_order;
    private String show_pending_order;
    private String show_delivery_order;
    private String show_complete_order;
    private String delivery_status;
    private String show_products;
    private String show_comments;
    private String show_sell_report;
    private String show_edit_store;
    private String show_checkout;
    private String show_crm;
    private long balance;
    private String all_self_service;
    private String self_index;
    private String week;
    private String saturday;

    private String show_c_telnif;
    private String show_n_telnif;
    private String show_s_telnif;

    private String time_c_meal;
    private String time_n_meal;
    private String time_s_meal;

    private String stu_id;
    private String stu_pass;
    private String foods_page;

    public String getShow_c_telnif()
    {
        return sharedPreferences.getString("show_c_telnif", "0");
    }

    public void setShow_c_telnif(String show_c_telnif)
    {
        this.show_c_telnif = show_c_telnif;
        editor.putString("show_c_telnif", show_c_telnif);
        boolean b=editor.commit();
    }

    public String getShow_n_telnif()
    {
        return sharedPreferences.getString("show_n_telnif", "0");
    }

    public void setShow_n_telnif(String show_n_telnif)
    {
        this.show_n_telnif = show_n_telnif;
        editor.putString("show_n_telnif", show_n_telnif);
        editor.commit();
    }

    public String getShow_s_telnif()
    {
        return sharedPreferences.getString("show_s_telnif", "0");
    }

    public void setShow_s_telnif(String show_s_telnif)
    {
        this.show_s_telnif = show_s_telnif;
        editor.putString("show_s_telnif", show_s_telnif);
        editor.commit();
    }


    public String getTime_c_meal()
    {
        return sharedPreferences.getString("time_c_meal", "از ۰۶:۳۰ تا ۰۸:۳۰");
    }

    public void setTime_c_meal(String time_c_meal)
    {
        this.time_c_meal = time_c_meal;
        editor.putString("time_c_meal", time_c_meal);
        editor.commit();
    }

    public String getTime_n_meal()
    {
        return sharedPreferences.getString("time_n_meal", "از ۱۱:۳۰ تا ۱۴:۳۰");
    }

    public void setTime_n_meal(String time_n_meal)
    {
        this.time_n_meal = time_c_meal;
        editor.putString("time_n_meal", time_n_meal);
        editor.commit();
    }

    public String getTime_s_meal()
    {
        return sharedPreferences.getString("time_s_meal", "از ۱۸:۰۰ تا ۲۰:۳۰");
    }

    public void setTime_s_meal(String time_s_meal)
    {
        this.time_s_meal = time_s_meal;
        editor.putString("time_s_meal", time_s_meal);
        editor.commit();
    }

    public String getShow_order_store()
    {
        return sharedPreferences.getString("show_order_store", "0");
    }

    public void setShow_order_store(String show_order_store)
    {
        editor.putString("show_order_store", show_order_store);
        editor.commit();
        this.show_order_store = show_order_store;
    }

    public String getSwitch_store()
    {
        return sharedPreferences.getString("switch_store", "0");
    }

    public void setSwitch_store(String switch_store)
    {
        editor.putString("switch_store", switch_store);
        editor.commit();
        this.switch_store = switch_store;
    }

    public String getShow_new_order()
    {
        return sharedPreferences.getString("show_new_order", "0");
    }

    public void setShow_new_order(String show_new_order)
    {
        editor.putString("show_new_order", show_new_order);
        editor.commit();
        this.show_new_order = show_new_order;
    }

    public String getShow_pending_order()
    {
        return sharedPreferences.getString("show_pending_order", "0");
    }

    public void setShow_pending_order(String show_pending_order)
    {
        editor.putString("show_pending_order", show_pending_order);
        editor.commit();
        this.show_pending_order = show_pending_order;
    }

    public String getShow_delivery_order()
    {
        return sharedPreferences.getString("show_delivery_order", "0");
    }

    public void setShow_delivery_order(String show_delivery_order)
    {
        editor.putString("show_delivery_order", show_delivery_order);
        editor.commit();
        this.show_delivery_order = show_delivery_order;
    }

    public String getShow_complete_order()
    {
        return sharedPreferences.getString("show_complete_order", "0");
    }

    public void setShow_complete_order(String show_complete_order)
    {
        editor.putString("show_complete_order", show_complete_order);
        editor.commit();
        this.show_complete_order = show_complete_order;
    }

    public String getDelivery_status()
    {
        return sharedPreferences.getString("delivery_status", "0");
    }

    public void setDelivery_status(String delivery_status)
    {
        editor.putString("delivery_status", delivery_status);
        editor.commit();
        this.delivery_status = delivery_status;
    }

    public String getShow_products()
    {
        return sharedPreferences.getString("show_comments", "0");
    }

    public void setShow_products(String show_products)
    {
        editor.putString("show_products", show_products);
        editor.commit();
        this.show_products = show_products;
    }

    public String getShow_comments()
    {
        return sharedPreferences.getString("show_comments", "0");
    }

    public void setShow_comments(String show_comments)
    {
        editor.putString("show_comments", show_comments);
        editor.commit();
        this.show_comments = show_comments;
    }

    public String getShow_sell_report()
    {
        return sharedPreferences.getString("show_sell_report", "0");
    }

    public void setShow_sell_report(String show_sell_report)
    {
        editor.putString("show_sell_report", show_sell_report);
        editor.commit();
        this.show_sell_report = show_sell_report;
    }

    public String getShow_edit_store()
    {
        return sharedPreferences.getString("show_edit_store", "0");
    }

    public void setShow_edit_store(String show_edit_store)
    {
        editor.putString("show_edit_store", show_edit_store);
        editor.commit();
        this.show_edit_store = show_edit_store;
    }

    public String getShow_checkout()
    {
        return sharedPreferences.getString("show_checkout", "0");
    }

    public void setShow_checkout(String show_checkout)
    {
        editor.putString("show_checkout", show_checkout);
        editor.commit();
        this.show_checkout = show_checkout;
    }

    public String getShow_crm()
    {
        return sharedPreferences.getString("show_crm", "0");
    }

    public void setShow_crm(String show_crm)
    {
        this.show_crm = show_crm;
        editor.putString("show_crm", show_crm);
        editor.commit();

    }

    private boolean confirmCodeHasBeenSent;


    public UsernameShared(Context con)
    {
        this.context = con;
        sharedPreferences = context.getSharedPreferences(SHARED_REFFRENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        profileID = sharedPreferences.getInt("profileID", -1);
        api_token = sharedPreferences.getString("api_token", "0");
        confirmCodeHasBeenSent = sharedPreferences.getBoolean("confirmCodeHasBeenSent", false);
        //
        type = sharedPreferences.getString("type", "user");
    }

    public boolean checkExist()
    {
        return (!getApi_token().equals("0"));
    }

    public boolean checkConfirmCodeHasBeenSent()
    {
        return confirmCodeHasBeenSent;
    }

    public void delete()
    {
        editor.clear();
        editor.commit();
    }

    /* Getter and Setter */
    public int getProfileID()
    {
        return sharedPreferences.getInt("profileID", -1);
    }

    public void setProfileID(int profileID)
    {
        this.profileID = profileID;
        editor.putInt("profileID", profileID);
        editor.commit();
    }

    public String getFirst_name()
    {
        return sharedPreferences.getString("first_name", "0");
    }

    public void setFirst_name(String first_name)
    {
        editor.putString("first_name", first_name);
        editor.commit();
    }

    public String getLast_name()
    {
        return sharedPreferences.getString("last_name", "0");
    }

    public void setLast_name(String last_name)
    {
        editor.putString("last_name", last_name);
        editor.commit();
    }


    public String getPassword()
    {
        return sharedPreferences.getString("password", "0");
    }

    public void setPassword(String password)
    {
        editor.putString("password", password);
        editor.commit();
    }

    public String getNick_name()
    {
        return sharedPreferences.getString("nick_name", "0");
    }

    public void setNick_name(String nick_name)
    {
        editor.putString("nick_name", nick_name);
        editor.commit();
    }

    public String getPhone_number()
    {
        return sharedPreferences.getString("phone_number", "0");
    }

    public void setPhone_number(String phone_number)
    {
        editor.putString("phone_number", phone_number);
        editor.commit();
    }

    public String getApi_token()
    {
        return sharedPreferences.getString("api_token", "0");
        //return "j7cOaJJLyzF06linnTiZGW3NFBJ8QMzUuRypnhHjUeeu0pUuU2sEhwqFVt9RF17a6of0mrpK9CoSlHpegZjIglxCY4mhGFooGtBz";
    }

    public void setApi_token(String token)
    {
        this.api_token = token;
        editor.putString("api_token", token);
        editor.commit();
    }

    public boolean getConfirmCodeHasBeenSent()
    {
        return sharedPreferences.getBoolean("confirmCodeHasBeenSent", false);
    }

    public void setConfirmCodeHasBeenSent(boolean condition)
    {
        confirmCodeHasBeenSent = condition;
        editor.putBoolean("confirmCodeHasBeenSent", condition);
        editor.commit();
    }

    public String getType()
    {
        return sharedPreferences.getString("type", "user");
    }

    public void setType(String type)
    {
        this.type = type;
        editor.putString("type", type);
        editor.commit();
    }

    public String getAndroid_id()
    {
        android_id = sharedPreferences.getString("android_id", "");
        if (android_id.equals(""))
        {
            android_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            editor.putString("android_id", android_id);
            editor.commit();
        }
        return android_id;
    }


    public int getStore_id()
    {
        return sharedPreferences.getInt("store_id", -1);
    }

    public void setStore_id(int store_id)
    {
        this.store_id = store_id;
        editor.putInt("store_id", store_id);
        editor.commit();
    }

    public String getStore_name()
    {

        return sharedPreferences.getString("store_name", "");
    }

    public void setStore_name(String store_name)
    {
        this.store_name = store_name;
        editor.putString("store_name", store_name);
        editor.commit();
    }

    public String getEmail()
    {
        return sharedPreferences.getString("email", "");
    }

    public void setEmail(String email)
    {
        this.email = email;
        editor.putString("email", email);
        editor.commit();
    }

    public String getFull_name()
    {
        return sharedPreferences.getString("full_name", "");
    }

    public void setFull_name(String full_name)
    {
        this.full_name = full_name;
        editor.putString("full_name", full_name);
        editor.commit();
    }

    public String getTM()
    {
        return sharedPreferences.getString("TM", "");
    }

    public void setTM(int TM)
    {
        this.TM = TM;
        editor.putInt("TM", TM);
        editor.commit();

    }

    public String getAll_TM()
    {
        return sharedPreferences.getString("all_TM", "");
    }

    public void setAll_TM(int all_TM)
    {
        this.all_TM = all_TM;
        editor.putInt("all_TM", all_TM);
        editor.commit();
    }

    public String getBirthday()
    {
        return sharedPreferences.getString("birthday", "");
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
        editor.putString("birthday", birthday);
        editor.commit();
    }

    public String getImage()
    {
        return sharedPreferences.getString("image", "");
    }

    public void setImage(String image)
    {
        this.image = image;
        editor.putString("image", image);
        editor.commit();
    }

    public String getStore_icon()
    {
        return sharedPreferences.getString("store_icon", "");
    }

    public void setStore_icon(String store_icon)
    {
        this.store_icon = store_icon;
        editor.putString("store_icon", store_icon);
        editor.commit();
    }

    public String getAddress()
    {
        return sharedPreferences.getString("address", "");
    }

    public void setAddress(String address)
    {
        this.address = address;
        editor.putString("address", address);
        editor.commit();
    }

    public int getAnif_code()
    {
        return sharedPreferences.getInt("anif_code", -1);
    }

    public void setAnif_code(int anif_code)
    {
        this.anif_code = anif_code;
        editor.putInt("anif_code", anif_code);
        editor.commit();
    }

    public int getLevel_id()
    {
        return sharedPreferences.getInt("level_id", -1);
    }

    public void setLevel_id(int level_id)
    {
        this.level_id = level_id;
        editor.putInt("level_id", level_id);
        editor.commit();
    }

    public void setBalance(Long balance)
    {
        this.balance = balance;
        editor.putLong("balance", balance);
        editor.commit();
    }

    public void setBalance(String balance)
    {
        String[] temp = balance.split(",");
        String t = "";
        for (int i = 0; i < temp.length; i++)
        {
            t += temp[i];
        }
        this.balance = Long.valueOf(t);
        editor.putLong("balance", this.balance);
        editor.commit();
    }

    public long getBalance()
    {
        return sharedPreferences.getLong("balance", 0);
    }


    public List<String> getAll_self_service()
    {
        List<String> slefs = new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(sharedPreferences.getString("all_self_service", "[]"));
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                slefs.add(jsonObject.getString("name"));
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return slefs;
    }

    public void setAll_self_service(String all_self_service)
    {
        this.all_self_service = all_self_service;
        editor.putString("all_self_service", all_self_service);
        editor.commit();
    }

    public String getSelf_index()
    {
        return sharedPreferences.getString("self_index", "0");

    }

    public void setSelf_index(String self_index)
    {
        this.self_index = self_index;
        editor.putString("self_index", self_index);
        editor.commit();
    }

    public String getSelf_selected()
    {
        try
        {
            JSONArray jsonArray = new JSONArray(sharedPreferences.getString("all_self_service", "[]"));
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (sharedPreferences.getString("self_index", "0").equals("" + i))
                    return jsonObject.getString("name");
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return " سلف‌سرویس پیش‌فرض";

    }


    public String getWeek()
    {
        return sharedPreferences.getString("week", "0");
    }

    public void setWeek(String week)
    {
        this.week = week;
        editor.putString("week", week);
        editor.commit();
    }

    public String getSaturday()
    {
        return sharedPreferences.getString("saturday", "");

    }

    public void setSaturday(String saturday)
    {
        this.saturday = "";
        String[] temp = saturday.split("/");
        this.saturday = temp[2] + temp[1] + temp[0];
        editor.putString("saturday", this.saturday);
        editor.commit();
    }

    public String getStu_id()
    {
        return sharedPreferences.getString("stu_id", "");
    }

    public void setStu_id(String stu_id)
    {
        this.stu_id = stu_id;
        editor.putString("stu_id", stu_id);
        editor.commit();
    }

    public String getStu_pass()
    {
        return sharedPreferences.getString("stu_pass", "");
    }

    public void setStu_pass(String stu_pass)
    {
        editor.putString("stu_pass", stu_pass);
        editor.commit();
    }

    public String getFoods_page()
    {
        return sharedPreferences.getString("foods_page", "");
    }

    public void setFoods_page(String foods_page)
    {
        this.foods_page = foods_page;
        editor.putString("foods_page", foods_page);
        editor.commit();
    }
}
