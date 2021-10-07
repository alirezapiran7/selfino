package ir.selfino.android.Function;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.selfino.android.Function.Models.Day;
import ir.selfino.android.Function.Models.Food;
import ir.selfino.android.Function.Models.Meal;
import ir.selfino.android.Function.Models.SelfService;


public class JsonParser
{
    public JsonParser()
    {

    }

    public static List<Day> JsonDays(JSONArray jsonArray) throws JSONException
    {
        List<Day> week = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject object = jsonArray.getJSONObject(i);
            Day day = JsonDay(object);
            week.add(day);
        }
        return week;
    }
    public static Day JsonDay(JSONObject object) throws JSONException
    {
        Day day = new Day();
        day.setId(object.getString("id"));
        day.setName(object.getString("name"));
        day.setDate(object.getString("date"));
        day.setStatus(object.getString("status"));

        return day;
    }

    public static List<SelfService> JsonSelfServices(JSONArray jsonArray) throws JSONException
    {
        List<SelfService> selfServices = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject object = jsonArray.getJSONObject(i);
            SelfService self = JsonSelfService(object);
            selfServices.add(self);
        }
        return selfServices;
    }
    public static SelfService JsonSelfService(JSONObject object) throws JSONException
    {
        SelfService self = new SelfService();
        self.setId(object.getString("id"));
        self.setName(object.getString("name"));
        return self;
    }
    public static Meal JsonMeal(JSONObject object, char name) throws JSONException
    {
        Meal meal =new Meal();
        meal.setName(name);
        meal.setKind_status(object.getString("kind_status"));
        meal.setKind(object.getString("kind"));
        meal.setFood_name(object.getString("food_name"));
        meal.setSelf(object.getString("self"));
        meal.setCount(object.getString("count"));
        meal.setCount_status(object.getString("count_status"));
        meal.setCount_color(object.getString("count_color"));
        meal.setLink(object.getString("link"));
        return meal;
    }

    public static List<Food> JsonFoods(JSONArray jsonArray) throws JSONException
    {
        List<Food> foods = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject object = jsonArray.getJSONObject(i);
            Food food = JsonFood(object);
            foods.add(food);
        }
        return foods;
    }
    public static Food JsonFood(JSONObject object) throws JSONException
    {
        Food food = new Food();
        food.setKind(object.getString("kind"));
        food.setName(object.getString("name"));
        food.setPrice(Long.valueOf(object.getString("price")));
        return food;
    }

//    public static UsernameShared JsonUsernamePaser(JSONObject result, Context context)throws JSONException
//    {
//        UsernameShared usernameShared=new UsernameShared(context);
//        usernameShared.setProfileID( result.getInt("id"));
//        usernameShared.setFirst_name(result.getString("first_name"));
//        usernameShared.setLast_name(result.getString("last_name"));
//        usernameShared.setStore_id(result.getInt("store_id"));
//        usernameShared.setStore_name(result.getString("store_name"));
//        usernameShared.setLevel_id(result.getInt("level_id"));
//        usernameShared.setEmail(result.getString("email"));
//        //usernameShared.setAnif_code(result.getInt("anif_code"));
//        if(result.getString("user_name")!=null)
//            usernameShared.setUser_name(result.getString("user_name"));
//        usernameShared.setPhone_number(result.getString("phone_number"));
//        //usernameShared.setTM(result.getInt("TM"));
//        //usernameShared.setAll_TM(result.getInt("all_TM"));
//        usernameShared.setBirthday(result.getString("birthday"));
//        if(result.getString("image")!=null)
//            usernameShared.setImage(result.getString("image")+"?dummy="+new Random().nextInt());
//        usernameShared.setStore_icon(result.getString("store_icon"));
//        usernameShared.setApi_token(result.getString("api_token"));
//        JSONObject options=result.getJSONObject("option");
//        usernameShared.setShow_order_store(options.getString("show_order_store"));
//        usernameShared.setSwitch_store(options.getString("switch_store"));
//        usernameShared.setShow_new_order(options.getString("show_new_order"));
//        usernameShared.setShow_pending_order(options.getString("show_pending_order"));
//        usernameShared.setShow_delivery_order(options.getString("show_delivery_order"));
//        usernameShared.setShow_complete_order(options.getString("show_complete_order"));
//        usernameShared.setDelivery_status(options.getString("delivery_status"));
//        usernameShared.setShow_products(options.getString("show_products"));
//        usernameShared.setShow_comments(options.getString("show_comments"));
//        usernameShared.setShow_sell_report(options.getString("show_sell_report"));
//        usernameShared.setShow_edit_store(options.getString("show_edit_store"));
//        usernameShared.setShow_checkout(options.getString("show_checkout"));
//        usernameShared.setShow_crm(options.getString("show_crm"));
//        usernameShared.setActive_slider(options.getString("active_slider"));
//        usernameShared.setSocial_network(options.getString("social_network"));
//        usernameShared.setShow_phone_number(options.getString("phone_number"));
//       // usernameShared.setShow_stores(options.getString("show_store"));
//        usernameShared.setShow_create_store(options.getString("create_store"));
//        //
//        return usernameShared;
//    }

}
