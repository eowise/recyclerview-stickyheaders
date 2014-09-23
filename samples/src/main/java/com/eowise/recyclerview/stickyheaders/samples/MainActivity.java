package com.eowise.recyclerview.stickyheaders.samples;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eowise.recyclerview.stickyheaders.HeaderPosition;
import com.eowise.recyclerview.stickyheaders.StickyHeadersItemDecoration;
import com.eowise.recyclerview.stickyheaders.samples.adapters.PersonAdapter;
import com.eowise.recyclerview.stickyheaders.samples.adapters.PersonInitialAdapter;

/**
 * Created by aurel on 22/09/14.
 */
public class MainActivity extends Activity {

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        list = (RecyclerView)findViewById(R.id.list);

        list.setAdapter(new PersonAdapter());
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.addItemDecoration(new StickyHeadersItemDecoration(new PersonInitialAdapter(), list, HeaderPosition.OVERLAY));
    }
}
