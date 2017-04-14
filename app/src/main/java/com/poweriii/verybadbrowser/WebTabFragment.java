package com.poweriii.verybadbrowser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Holds a webview that loads the member url.
 */
public class WebTabFragment extends Fragment {

    private static final String ARG_URL = "URL_PARAM";
    private String mURL;

    public WebTabFragment() {
        // Required empty public constructor
    }

    /**
     * @param url URL of tabs webview.
     * @return A new instance of fragment WebTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebTabFragment newInstance(String url) {
        WebTabFragment fragment = new WebTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mURL = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web_tab, container, false);
        WebView wv = (WebView)v.findViewById(R.id.viewWeb);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(mURL);
        return v;
    }

    public String getURL(){
        return mURL;
    }
}
