package com.lewaos.recyclercradview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;

import com.lewaos.recyclercradview.TouchHepler.OnStartDragListener;
import com.lewaos.recyclercradview.TouchHepler.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {


    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recList = (RecyclerView) findViewById(R.id.card_list);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);
        ContactAdapter ca = new ContactAdapter(createList(30), this);
        recList.setAdapter(ca);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(ca);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recList);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private List<ContactInfo> createList(int size) {

        List<ContactInfo> result = new ArrayList<ContactInfo>();
        for (int i=1; i <= size; i++) {
            ContactInfo ci = new ContactInfo();
            ci.name = ContactInfo.NAME_PREFIX + i;
            ci.surname = ContactInfo.SURNAME_PREFIX + i;
            ci.email = ContactInfo.EMAIL_PREFIX + i + "@test.com";

            result.add(ci);

        }

        return result;
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
