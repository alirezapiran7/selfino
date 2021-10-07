package ir.selfino.android.Function.Models;

import android.util.Log;

import java.util.List;

import static ir.selfino.android.Function.Controller.TAG;

public class Meal
{

    private char name;
    private List<Food> foods;
    private String count ;
    private String status;
    private String self ;
    private String kind;
    private String kind_status;
    private String count_status;
    private String count_color;
    private String food_name;
    private String link;

    public String getKind()
    {
        return kind;
    }

    public void setKind(String kind)
    {
        this.kind = kind;
    }

    public char getName()
    {
        return name;
    }

    public void setName(char name)
    {
        this.name = name;
    }

    public List<Food> getFoods()
    {
        return foods;
    }

    public void setFoods(List<Food> foods)
    {
        this.foods = foods;
    }

    public String getCount()
    {
        return count;
    }

    public void setCount(String count)
    {
        this.count = count;
    }

    public String getStatus()
    {

        if (!kind.equals("0") )
        {
            if (count_status.contains("enable"))
            {
                status = "1";
            } else
            {
                if (count_color.contains("red"))
                    status = "5";
                else
                {
                    status = "2";
                }
            }
        } else
        {
            if (kind_status.contains("enable"))
            {
                status = "3";
            } else
            {
                status = "4";
            }
        }
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSelf()
    {
        return self;
    }

    public void setSelf(String self)
    {
        this.self = self;
    }

    public String getKind_status()
    {
        return kind_status;
    }

    public void setKind_status(String kind_status)
    {
        this.kind_status = kind_status;
    }

    public String getCount_status()
    {
        return count_status;
    }

    public void setCount_status(String count_status)
    {
        this.count_status = count_status;
    }

    public String getCount_color()
    {
        return count_color;
    }

    public void setCount_color(String count_color)
    {
        this.count_color = count_color;
    }

    public String getFood_name()
    {
        return food_name;
    }

    public void setFood_name(String food_name)
    {
        this.food_name = food_name;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        if(link!=null&&!link.equals("null"))
        {

            this.link = link.substring(12, link.length() - 3);
            Log.d(TAG, "setLink: "+this.link);
        }
        else
            this.link=link;
    }
}
