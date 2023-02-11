package me.kuroxi.Utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLChecker {
    public boolean check(String url) {
        try {
            URL u = new URL(url);
            u.toURI();
            return true;
        } catch (URISyntaxException | MalformedURLException e) {
            return false;
        }
    }
}
