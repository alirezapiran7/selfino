package ir.selfino.android.Function.Models;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import ir.selfino.android.R;

/**
 * Created by Alireza on 9/29/2017.
 */

public class Day
{

    private String name, date, id, status;;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
//    public interface MealListener
//    {
//        void OnRecive(Meal meal);
//    }
//
//
//    public Day(int id, String name, String date, int status)
//    {
//        this.id = id;
//        this.name = name;
//        this.date = date;
//        this.status = status;
//    }
//    public void GetMeal(final MealListener CListener, final MealListener NListener, final MealListener SListener)
//    {
//        String lbl = "";
//        switch (id)
//        {
//            case 1:
//                lbl = "sat";
//                break;
//            case 2:
//                lbl = "Sun";
//                break;
//            case 3:
//                lbl = "Mon";
//                break;
//            case 4:
//                lbl = "thr";
//                break;
//            case 5:
//                lbl = "Wed";
//                break;
//            case 6:
//                lbl = "Tur";
//                break;
//            case 7:
//                lbl = "Fri";
//                break;
//        }
//        G.MyWeb.evaluateJavascript("(function() { var sum = \"\";" +
//                "sum =sum + document.querySelector('input#GhazaC" + id + "').className.toString()+\"@\";"+
//                "try { " +
//                "sum = sum + document.querySelector('span#lbl" + lbl + "_ghazaC" + id + "').textContent.toString()+\"@\";" +
//                "} catch(err) { " +
//                "sum =sum + document.querySelector('span#lbl" + (lbl.toLowerCase()) + "_ghazaC" + id + "').textContent.toString()+\"@\"; " +
//                "}" +
//                "sum = sum + document.querySelector('input#GhazaC" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#EditC" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "c" + "_numGhaza" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "c" + "_numGhaza" + id + "').className.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "c" + "_numGhaza" + id + "').style.color.toString()+\"#\";" +
//                "sum =sum + document.querySelector('input#GhazaN" + id + "').className.toString()+\"@\";"+
//                "try { " +
//                "sum = sum + document.querySelector('span#lbl" + lbl + "_ghazaN" + id + "').textContent.toString()+\"@\";" +
//                "} catch(err) { " +
//                "sum =sum + document.querySelector('span#lbl" + (lbl.toLowerCase()) + "_ghazaN" + id + "').textContent.toString()+\"@\"; " +
//                "}" +
//                "sum = sum + document.querySelector('input#GhazaN" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#EditN" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "n" + "_numGhaza" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "n" + "_numGhaza" + id + "').className.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "n" + "_numGhaza" + id + "').style.color.toString()+\"#\";" +
//                "sum =sum + document.querySelector('input#GhazaS" + id + "').className.toString()+\"@\";"+
//                "try { " +
//                "sum = sum + document.querySelector('span#lbl" + lbl + "_ghazaS" + id + "').textContent.toString()+\"@\";" +
//                "} catch(err) { " +
//                "sum =sum + document.querySelector('span#lbl" + (lbl.toLowerCase()) + "_ghazaS" + id + "').textContent.toString()+\"@\"; " +
//                "}" +
//                "sum = sum + document.querySelector('input#GhazaS" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#EditS" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "s" + "_numGhaza" + id + "').value.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "s" + "_numGhaza" + id + "').className.toString()+\"@\";" +
//                "sum = sum + document.querySelector('input#txt" + "s" + "_numGhaza" + id + "').style.color.toString();" +
//                "return sum ; })();", new ValueCallback<String>()
//        {
//            @Override
//            public void onReceiveValue(String s)
//            {
//               if(!s.equals("null"))
//               {
//                   try
//                   {
//                       s=s.substring(1,s.length()-1);
//                       String[]temp=s.split("#");
//                       ParsDataC(temp[0],CListener);
//                       ParsDataN(temp[1],NListener);
//                       ParsDataS(temp[2],SListener);
//
//                   }catch (Exception e)
//                   {
//
//                   }
//               }
//            }
//        });
//    }
//    public void ParsDataC(String s, final MealListener listener)
//    {
//        String numColor = "", kindStatus = "", numStatus = "", foodname = "";
//        int st = 0, kind=0, cnt=0, self=0;
//        String[] temp = s.split("@");
//        kindStatus = temp[0].toLowerCase();
//        foodname = temp[1];
//        kind = Integer.valueOf(temp[2]);
//        self = Integer.valueOf(temp[3]);
//        cnt = Integer.valueOf(temp[4]);
//        numStatus = temp[5].toLowerCase();
//        numColor = temp[6].toLowerCase();
//        if (kind != 0)
//        {
//            if (numStatus.contains("enable"))
//            {
//                st = 1;
//            } else
//            {
//                if (numColor.contains("red"))
//                    st = 5;
//                else
//                {
//                    st = 2;
//                }
//            }
//        } else
//        {
//            if (numStatus.contains("enable"))
//            {
//                st = 3;
//            } else
//            {
//                st = 4;
//            }
//        }
//        if (st == 3)
//        {
//            GetFoodsC(listener);
//        } else
//             listener.OnRecive(new Meal('C', st, cnt, self, new Food(kind, foodname)));
//    }
//    public void ParsDataN(String s, final MealListener listener)
//    {
//        String numColor = "", kindStatus = "", numStatus = "", foodname = "";
//        int st = 0, kind=0, cnt=0, self=0;
//        String[] temp = s.split("@");
//        kindStatus = temp[0].toLowerCase();
//        foodname = temp[1];
//        kind = Integer.valueOf(temp[2]);
//        self = Integer.valueOf(temp[3]);
//        cnt = Integer.valueOf(temp[4]);
//        numStatus = temp[5].toLowerCase();
//        numColor = temp[6].toLowerCase();
//        if (kind != 0)
//        {
//            if (numStatus.contains("enable"))
//            {
//                st = 1;
//            } else
//            {
//                if (numColor.contains("red"))
//                    st = 5;
//                else
//                {
//                    st = 2;
//                }
//            }
//        } else
//        {
//            if (numStatus.contains("enable"))
//            {
//                st = 3;
//            } else
//            {
//                st = 4;
//            }
//        }
//        if (st == 3)
//        {
//            GetFoodsN(listener);
//        } else
//            listener.OnRecive(new Meal('N', st, cnt, self, new Food(kind, foodname)));
//    }
//    public void ParsDataS(String s, final MealListener listener)
//    {
//        String numColor = "", kindStatus = "", numStatus = "", foodname = "";
//        int st = 0, kind=0, cnt=0, self=0;
//        String[] temp = s.split("@");
//        kindStatus = temp[0].toLowerCase();
//        foodname = temp[1];
//        kind = Integer.valueOf(temp[2]);
//        self = Integer.valueOf(temp[3]);
//        cnt = Integer.valueOf(temp[4]);
//        numStatus = temp[5].toLowerCase();
//        numColor = temp[6].toLowerCase();
//        if (kind != 0)
//        {
//            if (numStatus.contains("enable"))
//            {
//                st = 1;
//            } else
//            {
//                if (numColor.contains("red"))
//                    st = 5;
//                else
//                {
//                    st = 2;
//                }
//            }
//        } else
//        {
//            if (numStatus.contains("enable"))
//            {
//                st = 3;
//            } else
//            {
//                st = 4;
//            }
//        }
//        if (st == 3)
//        {
//            GetFoodsS(listener);
//        } else
//            listener.OnRecive(new Meal('S', st, cnt, self, new Food(kind, foodname)));
//    }
//    public void GetFoodsC( final MealListener foodListener)
//    {
//        final char c='C';
//        final String t = c + "" + id + "";
//       final WebView web = new WebView(G.context);
//        WebSettings CSettings = web.getSettings();
//        CSettings.setJavaScriptEnabled(true);
//        web.setWebViewClient(new WebViewClient()
//        {
//            boolean timeout1=true;
//            @Override
//            public void onPageStarted(final WebView view, String url, Bitmap favicon)
//            {
//                try
//                {
//                    timeout1 = true;
//                    Runnable run=new Runnable()
//                    {
//                        @Override
//                        public void run()
//                        {
//                            if (timeout1)
//                            {
//                                web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=0&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                            }
//                        }
//                    };
//                    Handler h = new Handler(Looper.myLooper());
//                    h.postDelayed(run,3000);
//                }catch (Exception e)
//                {
//                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=0&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                }
//
//                /*final Timer timer = new Timer();
//               final Handler h = new Handler();
//                timer.scheduleAtFixedRate(new TimerTask()
//                {
//                    @Override
//                    public void run()
//                    {
//                        Profile.Reservactivity.runOnUiThread(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                if (timeout1)
//                                {
//                                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=0&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                                } else
//                                {
//                                    timer.cancel();
//                                }
//                            }
//                        });
//
//                        h.post(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                if (timeout1)
//                                {
//                                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=0&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                                } else
//                                {
//                                    timer.cancel();
//                                }
//                            }
//                        });
//                    }
//                }, 5000, 5000);*/
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                web.evaluateJavascript("(function() {" +
//                        "var v=\"\";" +
//                        "v=document.querySelector('table#TABLE1').id;" +
//                        "try{ " +
//                        "var l= document.querySelector('table#GhazaGrid').rows.length; " +
//                        "v+=\"#\";" +
//                        "for(i=1 ;i < l; i++)" +
//                        "{" +
//                        "var v0=document.querySelector('table#GhazaGrid').rows[i].cells[0].textContent;" +
//                        "var v2=document.querySelector('table#GhazaGrid').rows[i].cells[2].textContent;" +
//                        "var v3=document.querySelector('table#GhazaGrid').rows[i].cells[3].textContent;" +
//                        "v+=v0+\"@\"+v2+\"@\"+v3;" +
//                        "if(i!=l-1)" +
//                        "v= v+ \"#\";" +
//                        "}" +
//                        "}catch(err){" +
//                        "return v; }" +
//                        "return v;})();", new ValueCallback<String>()
//                {
//                    @Override
//                    public void onReceiveValue(String s)
//                    {
//                        if (!s.equals("null"))
//                        {
//                            timeout1=false;
//                            if(!s.equals("\"TABLE1\""))
//                            {
//
//                                s = s.substring(1, s.length() - 1);
//                                foodListener.OnRecive(new Meal(c, SplitsFoods(s)));
//                            }
//                            else
//                            {
//                                foodListener.OnRecive(new Meal(c, Meal.UnReserve_UnChangable, 0, 0, new Food(0, "")));
//                            }
//                        } else
//                        {
//                            web.loadUrl(G.context.getString(R.string.SsUrl)+"SelectGhaza.aspx?date="+DateForUrl()+"&dow="+(getId()-1)+"&kind=0&sel=True&selg=False&week="+Profile.weekIndex+"&personeli=0000000");
//                        }
//                    }
//                });
//            }
//
//        });
//        web.loadUrl(G.context.getString(R.string.SsUrl)+"SelectGhaza.aspx?date="+DateForUrl()+"&dow="+(getId()-1)+"&kind=0&sel=True&selg=False&week="+Profile.weekIndex+"&personeli=0000000");
//        /*
//        G.MyWeb.evaluateJavascript("javascript: document.querySelector('a#link" + t + "').getAttribute('onclick');", new ValueCallback<String>()
//        {
//            @Override
//            public void onReceiveValue(String s)
//            {
//
//                if (!s.equals("null"))
//                {
//                    s = s.substring(13, s.length() - 4);
//                    web.loadUrl(G.context.getString(R.string.SsUrl) + s);
//                } else
//                {
//                    foodListener.OnRecive(new Meal(c,Meal.UnReserve_UnChangable,0,0,new Food(0,"")));
//                }
//            }
//        });*/
//    }
//    public void GetFoodsN( final MealListener foodListener)
//    {
//
//        final char c='N';
//        String t = String.valueOf(id);
//        final WebView web = new WebView(G.context);
//        WebSettings CSettings = web.getSettings();
//        CSettings.setJavaScriptEnabled(true);
//        web.setWebViewClient(new WebViewClient()
//        {
//            boolean timeout1=true;
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon)
//            {
//                try
//                {
//                    timeout1 = true;
//                    Runnable run = new Runnable()
//                    {
//                        @Override
//                        public void run()
//                        {
//                            if (timeout1)
//                            {
//                                web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=1&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                            }
//                        }
//                    };
//                    Handler h = new Handler(Looper.myLooper());
//                    h.postDelayed(run, 5000);
//                }catch (Exception e)
//                {
//                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=1&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                }
//                /*timeout1 = true;
//                final Timer timer = new Timer();
//                //final Handler h = new Handler();
//                timer.scheduleAtFixedRate(new TimerTask()
//                {
//                    @Override
//                    public void run()
//                    {
//                        Profile.Reservactivity.runOnUiThread(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                if (timeout1)
//                                {
//                                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=1&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                                } else
//                                {
//                                    timer.cancel();
//                                }
//                            }
//                        });
//                        h.post(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                if (timeout1)
//                                {
//                                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=1&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                                } else
//                                {
//                                    timer.cancel();
//                                }
//                            }
//                        });
//                    }
//                }, 5000, 5000);*/
//            }
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                web.evaluateJavascript("(function() {" +
//                        "var v=\"\";" +
//                        "v=document.querySelector('table#TABLE1').id;" +
//                        "try{ " +
//                        "var l= document.querySelector('table#GhazaGrid').rows.length; " +
//                        "v+=\"#\";" +
//                        "for(i=1 ;i < l; i++)" +
//                        "{" +
//                        "var v0=document.querySelector('table#GhazaGrid').rows[i].cells[0].textContent;" +
//                        "var v2=document.querySelector('table#GhazaGrid').rows[i].cells[2].textContent;" +
//                        "var v3=document.querySelector('table#GhazaGrid').rows[i].cells[3].textContent;" +
//                        "v+=v0+\"@\"+v2+\"@\"+v3;" +
//                        "if(i!=l-1)" +
//                        "v= v+ \"#\";" +
//                        "}" +
//                        "}catch(err){" +
//                        "return v; }" +
//                        "return v;})();", new ValueCallback<String>()
//                {
//                    @Override
//                    public void onReceiveValue(String s)
//                    {
//                        if (!s.equals("null"))
//                        {
//                            timeout1=false;
//                            if(!s.equals("\"TABLE1\""))
//                            {
//
//                                s = s.substring(1, s.length() - 1);
//                                foodListener.OnRecive(new Meal(c, SplitsFoods(s)));
//                            }
//                            else
//                            {
//                                foodListener.OnRecive(new Meal(c, Meal.UnReserve_UnChangable, 0, 0, new Food(0, "")));
//                            }
//                        } else
//                        {
//                            web.loadUrl(G.context.getString(R.string.SsUrl)+"SelectGhaza.aspx?date="+DateForUrl()+"&dow="+(getId()-1)+"&kind=0&sel=True&selg=False&week="+Profile.weekIndex+"&personeli=0000000");
//                        }
//                    }
//                });
//            }
//
//        });
//        web.loadUrl(G.context.getString(R.string.SsUrl)+"SelectGhaza.aspx?date="+DateForUrl()+"&dow="+(getId()-1)+"&kind=1&sel=True&selg=False&week="+Profile.weekIndex+"&personeli=0000000");
//       /* G.MyWeb.evaluateJavascript("javascript: document.querySelector('a#link" + t + "').getAttribute('onclick');", new ValueCallback<String>()
//        {
//            @Override
//            public void onReceiveValue(String s)
//            {
//
//                if (!s.equals("null"))
//                {
//                    s = s.substring(13, s.length() - 4);
//                    web.loadUrl(G.context.getString(R.string.SsUrl) + s);
//                } else
//                {
//                    foodListener.OnRecive(new Meal(c,Meal.UnReserve_UnChangable,0,0,new Food(0,"")));
//                }
//            }
//        });*/
//    }
//    public void GetFoodsS( final MealListener foodListener)
//    {
//        final char c='S';
//        String t = c + "" + id + "";
//        final WebView web = new WebView(G.context);
//        WebSettings CSettings = web.getSettings();
//        CSettings.setJavaScriptEnabled(true);
//        web.setWebViewClient(new WebViewClient()
//        {boolean timeout1=true;
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon)
//            {
//                try{
//                timeout1 = true;
//                Runnable run = new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                        if (timeout1)
//                        {
//                            web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=2&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                        }
//                    }
//                };
//                Handler h = new Handler(Looper.myLooper());
//                h.postDelayed(run, 3000);
//            }catch(Exception e)
//                {
//                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=2&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                }
//                /*timeout1 = true;
//                final Timer timer = new Timer();
//                //final Handler h = new Handler();
//                timer.scheduleAtFixedRate(new TimerTask()
//                {
//                    @Override
//                    public void run()
//                    {
//                        Profile.Reservactivity.runOnUiThread(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                if (timeout1)
//                                {
//                                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=2&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                                } else
//                                {
//                                    timer.cancel();
//                                }
//                            }
//                        });
//                        /*h.post(new Runnable()
//                        {
//                            @Override
//                            public void run()
//                            {
//                                if (timeout1)
//                                {
//                                    web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=2&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                                } else
//                                {
//                                    timer.cancel();
//                                }
//                            }
//                        });
//                    }
//                }, 5000, 5000);*/
//            }
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                web.evaluateJavascript("(function() {" +
//                        "var v=\"\";" +
//                        "v=document.querySelector('table#TABLE1').id;" +
//                        "try{ " +
//                        "var l= document.querySelector('table#GhazaGrid').rows.length; " +
//                        "v+=\"#\";" +
//                        "for(i=1 ;i < l; i++)" +
//                        "{" +
//                        "var v0=document.querySelector('table#GhazaGrid').rows[i].cells[0].textContent;" +
//                        "var v2=document.querySelector('table#GhazaGrid').rows[i].cells[2].textContent;" +
//                        "var v3=document.querySelector('table#GhazaGrid').rows[i].cells[3].textContent;" +
//                        "v+=v0+\"@\"+v2+\"@\"+v3;" +
//                        "if(i!=l-1)" +
//                        "v= v+ \"#\";" +
//                        "}" +
//                        "}catch(err){" +
//                        "return v; }" +
//                        "return v;})();", new ValueCallback<String>()
//                {
//                    @Override
//                    public void onReceiveValue(String s)
//                    {
//                        if (!s.equals("null"))
//                        {
//                            timeout1 = false;
//                            if (!s.equals("\"TABLE1\""))
//                            {
//
//                                s = s.substring(1, s.length() - 1);
//                                foodListener.OnRecive(new Meal(c, SplitsFoods(s)));
//                            } else
//                            {
//                                foodListener.OnRecive(new Meal(c, Meal.UnReserve_UnChangable, 0, 0, new Food(0, "")));
//                            }
//                        } else
//                        {
//                            web.loadUrl(G.context.getString(R.string.SsUrl) + "SelectGhaza.aspx?date=" + DateForUrl() + "&dow=" + (getId() - 1) + "&kind=0&sel=True&selg=False&week=" + Profile.weekIndex + "&personeli=0000000");
//                        }
//                    }
//                });
//            }
//
//        });
//        web.loadUrl(G.context.getString(R.string.SsUrl)+"SelectGhaza.aspx?date="+DateForUrl()+"&dow="+(getId()-1)+"&kind=2&sel=True&selg=False&week="+Profile.weekIndex+"&personeli=0000000");
//    }
//    public List<Food> SplitsFoods(String s)
//    {
//        String[] temp = s.split("#");
//        List<Food> foods=new ArrayList<>();
//        for (int i = 1; i < temp.length; i++)
//        {
//            String[]t = temp[i].split("@");
//            foods.add(new Food(Integer.valueOf(t[0]), t[1], Integer.valueOf(t[2])));
//        }
//        return foods;
//    }
//
//    public String DateForUrl()
//    {
//        String[] t;
//        if (Profile.weekIndex == 1)
//            t = Profile.NextWeek.get(0).getDate().split("/");
//        else
//            t = Profile.StreamWeek.get(0).getDate().split("/");
//        return t[2] + "" + t[1] + "" + t[0];
//    }
//    public int getId()
//    {
//        return id;
//    }
//
//    public void setId(int id)
//    {
//        this.id = id;
//    }
//
//    public int getStatus()
//    {
//        return status;
//    }
//
//    public void setStatus(int status)
//    {
//        this.status = status;
//    }
//
//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//
//    public String getDate()
//    {
//        return date;
//    }
//
//    public void setDate(String date)
//    {
//        this.date = date;
//    }

}
