package com.gengms.simplearch.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gengms.simplearch.R;
import com.gengms.simplearch.app.BasicApp;
import com.gengms.simplearch.databinding.FragmentMyBinding;

public class MyFragment extends Fragment implements LifecycleOwner
{

    private FragmentMyBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("pet", Activity.MODE_PRIVATE);
        ((BasicApp)getActivity().getApplication()).getRepository().getUserInfo(sp.getString("uid", ""))
        .observe(this,user->mBinding.setMy(user));
    }
}
