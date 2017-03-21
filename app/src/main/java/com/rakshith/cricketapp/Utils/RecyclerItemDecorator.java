package com.rakshith.cricketapp.Utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Rakshith
 */
public class RecyclerItemDecorator extends RecyclerView.ItemDecoration {
    int     padding;
    public RecyclerItemDecorator(int padding)
    {
        this.padding = padding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left    = padding;
        outRect.right   = padding;
        outRect.bottom  = padding;

        if(parent.getChildLayoutPosition(view) == 0)
        {
            outRect.top     = padding;
        }
    }
}
