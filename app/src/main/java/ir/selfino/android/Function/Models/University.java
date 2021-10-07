package ir.selfino.android.Function.Models;

public class University
{
    private int id;
    private String fa_name;
    private String en_name;
    private String domain;
    private String search_key;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFa_name()
    {
        return fa_name;
    }

    public void setFa_name(String fa_name)
    {
        this.fa_name = fa_name;
    }

    public String getEn_name()
    {
        return en_name;
    }

    public void setEn_name(String en_name)
    {
        this.en_name = en_name;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public String getSearch_key()
    {
        return search_key;
    }

    public void setSearch_key(String search_key)
    {
        this.search_key = search_key;
    }
}
