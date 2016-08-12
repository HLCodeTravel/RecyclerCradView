package com.lewaos.recyclercradview;


import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewaos.recyclercradview.TouchHepler.ItemTouchHelperAdapter;
import com.lewaos.recyclercradview.TouchHepler.ItemTouchHelperViewHolder;
import com.lewaos.recyclercradview.TouchHepler.OnStartDragListener;

import java.util.Collections;
import java.util.List;


/**
 * Created by lianghe on 16-8-11.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>  implements ItemTouchHelperAdapter{

    private List<ContactInfo> contactList;

    private OnStartDragListener mDragStartListener;


    public ContactAdapter(List<ContactInfo> contactList, OnStartDragListener dragStartListener) {
        this.contactList = contactList;
        mDragStartListener = dragStartListener;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);
        contactViewHolder.vEmail.setText(ci.email);
        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);


        // Start a drag whenever the handle view it touched

        contactViewHolder.vName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(contactViewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder{

        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        protected TextView tvOpen;

        public ContactViewHolder(View v) {
            super(v);
            tvOpen = (TextView)v.findViewById(R.id.tvOpen);
            vName = (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView) v.findViewById(R.id.txtSurname);
            vEmail = (TextView) v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);

        }

        @Override
        public void onItemSelected() {
            //itemView.setBackgroundColor(Color.LTGRAY);
            tvOpen.setVisibility(View.VISIBLE);
        }

        @Override
        public void onItemClear() {
            //itemView.setBackgroundResource(R.color.cardview_bg);
            tvOpen.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(contactList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        contactList.remove(position);
        notifyItemRemoved(position);
    }
}
