package com.example.musta.simplyshare.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.musta.simplyshare.R;
import com.skyfishjy.library.RippleBackground;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveFragment extends Fragment {


    public static ReceiveFragment newInstance(){
        return new ReceiveFragment();
    }

    public ReceiveFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receive, container, false);
        final RippleBackground rippleBackground= (RippleBackground) view.findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return view;
    }

}
