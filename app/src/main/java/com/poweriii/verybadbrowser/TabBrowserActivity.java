package com.poweriii.verybadbrowser;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class TabBrowserActivity extends Activity {

    private ArrayList<WebTabFragment> frags;
    private FragmentManager fm;

    private EditText mEditURL;

    private int currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_browser);

        frags = new ArrayList<>();
        fm = getFragmentManager();
        mEditURL = (EditText)findViewById(R.id.editURL);
        currentTab = -1; // We start with no fragments.

        // Read URL, make new fragment, update the fragment list, then replace the fragment into view
        findViewById(R.id.buttonGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frags.add( WebTabFragment.newInstance( mEditURL.getText().toString() ));
                currentTab = frags.size()-1;
                fm.beginTransaction()
                        .replace( R.id.frameWeb, frags.get( currentTab ) )
                        .commit();
            }
        });

        // Update the currentTab.  Retreive fragment.  Set the url field in the
        // edit text box.
        findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( currentTab > 0 ){
                    currentTab--;
                    mEditURL.setText(frags.get(currentTab).getURL());
                    fm.beginTransaction()
                            .replace( R.id.frameWeb, frags.get( currentTab ) )
                            .commit();
                }

            }
        });


        findViewById(R.id.buttonForward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( currentTab < frags.size()-1 ){
                    currentTab++;
                    mEditURL.setText(frags.get(currentTab).getURL());
                    fm.beginTransaction()
                            .replace( R.id.frameWeb, frags.get( currentTab ) )
                            .commit();
                }
            }
        });
    }
}
