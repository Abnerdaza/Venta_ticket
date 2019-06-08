package com.example.venta.fragmento_alumno;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import com.example.venta.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmento_alumno_inicio extends Fragment {

    ViewFlipper v_flipper;
    VideoView videoView;
    public fragmento_alumno_inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragmento_alumno_inicio, container, false);
        v_flipper = (ViewFlipper) v.findViewById(R.id.v_flipper);

        v_flipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
        v_flipper.setAutoStart(true);
        v_flipper.setFlipInterval(4000);
        v_flipper.startFlipping();

        videoView = (VideoView) v.findViewById(R.id.videoView);
        String videoPath = "android.resource://"+getActivity().getPackageName()+ "/" + R.raw.video ;
        Uri uri= Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController= new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);




        return v;
    }

}
