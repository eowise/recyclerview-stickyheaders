package com.eowise.recyclerview.stickyheaders.samples;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eowise.recyclerview.stickyheaders.HeaderPosition;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.eowise.recyclerview.stickyheaders.samples.adapters.BigramHeaderAdapter;
import com.eowise.recyclerview.stickyheaders.samples.adapters.InitialHeaderAdapter;
import com.eowise.recyclerview.stickyheaders.samples.adapters.PersonAdapter;

/**
 * Created by aurel on 22/09/14.
 */
public class MainActivity extends Activity {

    private RecyclerView list;
    private StickyHeadersItemDecoration top;
    private StickyHeadersItemDecoration overlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (RecyclerView)findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

        top = new StickyHeadersItemDecoration(new BigramHeaderAdapter(), list, HeaderPosition.TOP);
        overlay = new StickyHeadersItemDecoration(new InitialHeaderAdapter(), list, HeaderPosition.OVERLAY);


        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);


        actionBar.addTab(actionBar.newTab()
                        .setText("Top")
                        .setTabListener(new TopHeaderTabListener()),
                true);

        actionBar.addTab(actionBar.newTab()
                        .setText("Overlay")
                        .setTabListener(new OverlayHeaderTabListener()),
                false);

    }

    public class TopHeaderTabListener implements ActionBar.TabListener {

        @Override
        public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
            list.setAdapter(new PersonAdapter());
            list.removeItemDecoration(overlay);
            list.addItemDecoration(top);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

        }
    }


    public class OverlayHeaderTabListener implements ActionBar.TabListener {

        @Override
        public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {
            list.setAdapter(new PersonAdapter());
            list.removeItemDecoration(top);
            list.addItemDecoration(overlay);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction fragmentTransaction) {

        }
    }

}
