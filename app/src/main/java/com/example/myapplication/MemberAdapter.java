package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MemberAdapter extends BaseAdapter {
    private List<Member> members;
    private LayoutInflater inflater;

    public MemberAdapter(Context context, List<Member> members) {
        this.members = members;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_member, parent, false);
        }

        Member member = (Member) getItem(position);

        TextView nameTextView = view.findViewById(R.id.nameTextView);

        nameTextView.setText(member.getName());

        return view;
    }
}
