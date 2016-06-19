package com.personage.myolq.mvp.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javabean.entity.Conversation;
import com.personage.myolq.R;
import com.personage.myolq.base.InitFragment;
import com.personage.myolq.mvp.view.adapter.RecyclerConversation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/18 0018.
 */
public class ConversationFragment extends InitFragment {
    @Bind(R.id.conversation_rv)
    RecyclerView conversationRv;
    @Bind(R.id.conversation_srefresh)
    SwipeRefreshLayout conversationSrefresh;
    List<Conversation> conversations;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_conversation;
    }

    @Override
    public void getFragmentCreateView() {
        RecyclerConversation conversation=new RecyclerConversation(getActivity(),conversations);
        conversationRv.setAdapter(conversation);
    }


}
