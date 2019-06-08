package com.example.venta.fragmento_alumno;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.venta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmento_alumno_menu extends Fragment {


    public fragmento_alumno_menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_alumno_menu, container, false);
    }

}
