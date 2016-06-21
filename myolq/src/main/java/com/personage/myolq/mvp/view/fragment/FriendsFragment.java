package com.personage.myolq.mvp.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.example.widget.sortlist.CharacterParser;
import com.example.widget.sortlist.ClearEditText;
import com.example.widget.sortlist.SideBar;
import com.personage.myolq.R;
import com.personage.myolq.base.InitFragment;
import com.personage.myolq.bmob.bean.Friend;
import com.personage.myolq.bmob.bean.PinyinComparator;
import com.personage.myolq.bmob.event.RefreshEvent;
import com.personage.myolq.bmob.model.UserModel;
import com.personage.myolq.mvp.view.adapter.FriendsAdapter;


import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/6/18 0018.
 */
public class FriendsFragment extends InitFragment {

    @Bind(R.id.friends_swipe)
    SwipeRefreshLayout friends_swipe;
    @Bind(R.id.filter_edit)
    ClearEditText mClearEditText;
    @Bind(R.id.country_lvcountry)
    ListView sortListView;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.sidrbar)
    SideBar sideBar;
    private FriendsAdapter adapter;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<Friend> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_friends;
    }

    @Override
    public void getFragmentCreateView() {
        initViews();
    }

    private void initViews() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();

        sideBar.setTextView(dialog);

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(getActivity(), ((Friend) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        SourceDateList = filledData(getResources().getStringArray(R.array.date));

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new FriendsAdapter(getActivity(), SourceDateList);
        sortListView.setAdapter(adapter);



        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        friends_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
    }


    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<Friend> filledData(String[] date) {
        List<Friend> mSortList = new ArrayList<Friend>();

        for (int i = 0; i < date.length; i++) {
            Friend sortModel = new Friend();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<Friend> filterDateList = new ArrayList<Friend>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (Friend sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }


    /**注册自定义消息接收事件
     * @param event
     */
    @Subscribe
    public void onEventMainThread(RefreshEvent event){
        //重新刷新列表
        log("---联系人界面接收到自定义消息---");
        adapter.notifyDataSetChanged();
    }

    /**
     查询本地会话
     */
    public void query(){
        UserModel.getInstance().queryFriends(new FindListener<Friend>() {
            @Override
            public void onSuccess(List<Friend> list) {
                adapter.addlist(list);
//                adapter.notifyDataSetChanged();
                friends_swipe.setRefreshing(false);
            }

            @Override
            public void onError(int i, String s) {
                adapter.addlist(null);
//                adapter.notifyDataSetChanged();
                friends_swipe.setRefreshing(false);
            }
        });
    }

}
