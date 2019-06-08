package com.example.venta.fragmento_general;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.venta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmento_general_acerca_de extends Fragment {


    public fragmento_general_acerca_de() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_general_acerca_de, container, false);
    }

}
