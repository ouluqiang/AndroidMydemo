package com.personage.myolq.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.widget.rippleclick.Click_Text;
import com.personage.myolq.R;
import com.personage.myolq.base.InitFragment;
import com.personage.myolq.bmob.bean.User;
import com.personage.myolq.bmob.model.UserModel;
import com.personage.myolq.mvp.view.activity.LoginActivity;
import com.personage.myolq.mvp.view.activity.NewFriendActivity;
import com.personage.myolq.mvp.view.activity.UserInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.newim.BmobIM;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public class SetFragment extends InitFragment {
    @Bind(R.id.click_exit)
    Click_Text clickExit;
    @Bind(R.id.tv_username)
    TextView tvUsername;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_set;
    }

    @Override
    public void getFragmentCreateView() {
        String username = UserModel.getInstance().getCurrentUser().getNickname();
        tvUsername.setText(username);
        clickExit.setOnClickListener(new Click_Text.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.getInstance().logout();
                //可断开连接
                BmobIM.getInstance().disConnect();
                getActivity().finish();
                startActivity(LoginActivity.class, null);
            }
        });
    }

    @OnClick(R.id.ll_set_add)
    public void onSetadd() {
        startActivity(NewFriendActivity.class, null);
    }

    @OnClick(R.id.ll_set_userinfo)
    public void onSetUserInfo() {
        Bundle bundle = new Bundle();
        User user = BmobUser.getCurrentUser(getActivity(),User.class);
        bundle.putSerializable("u", user);
        startActivity(UserInfoActivity.class, bundle);
    }


}
