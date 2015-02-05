package controllers;

import models.Bar;
import org.json.JSONObject;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.check;
import views.html.index;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import static play.data.Form.form;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static String getURL(String tag){

        String str = "https://api.instagram.com/v1/tags/" + tag + "/media/recent?client_id=263b705f62dd4d6681ef922630b40627&access_token=1673290394.263b705.f4471f2caf7b4fc39e1534c2789b687f";
        return str;
    }

    public static boolean fetchData(String s ) throws IOException {
        //  System.setProperty("java.net.useSystemProxies", "true");
        InputStream is = new URL(s).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            JSONObject jsonObj = new JSONObject(jsonText);
            System.out.println(jsonObj.get("pagination"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static Result check() throws IOException {
        Form<Bar> userForm = form(Bar.class).bindFromRequest();

        if (!userForm.hasErrors()) {

            Bar bar = userForm.get();
            bar.save();
            String url = getURL(bar.tag);
            System.out.println("The url is :"+url);

            fetchData(url);
        }
        return ok(check.render());
    }

}
