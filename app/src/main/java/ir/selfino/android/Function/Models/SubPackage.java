package ir.selfino.android.Function.Models;

/**
 * Created by Alireza on 9/29/2017.
 */

public class SubPackage
{
    private int id;
    private int package_id;
    private int day_count;
    private int off;
    private int price;
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPackage_id()
    {
        return package_id;
    }

    public void setPackage_id(int package_id)
    {
        this.package_id = package_id;
    }

    public int getDay_count()
    {
        return day_count;
    }

    public void setDay_count(int day_count)
    {
        this.day_count = day_count;
    }

    public int getOff()
    {
        return off;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {

        this.price = price;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOff(int off)
    {
        this.off = off;
    }
}
