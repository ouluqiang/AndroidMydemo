package imbmob.test.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by olq on 2016/5/27.
 */
public abstract class BaseFragment extends Fragment{

    public View mView;

    public abstract int getFragmentLayout();

    public abstract void getFragmentCreateView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getFragmentLayout(), container,false);
        ButterKnife.bind(this, mView);
        getFragmentCreateView();
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public <T extends View> T getFindViewById(int id){
        return (T) mView.findViewById(id);
    }

}
