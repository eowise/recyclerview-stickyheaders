package com.eowise.recyclerview.stickyheaders;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aurel on 11/10/14.
 */
public class InvalidateAnimationRunnable implements Runnable {

    private RecyclerView recyclerView;

    public InvalidateAnimationRunnable(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        recyclerView.invalidate();

        if (recyclerView.getItemAnimator().isRunning()) {
            ViewCompat.postOnAnimation(recyclerView, this);
        }
    }
}
