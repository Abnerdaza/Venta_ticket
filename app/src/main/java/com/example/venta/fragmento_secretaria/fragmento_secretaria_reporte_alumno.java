package com.example.venta.fragmento_secretaria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.venta.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmento_secretaria_reporte_alumno extends Fragment {
    EditText buscar;
    ImageButton btn_buscar;
    TextView codigo,nombre,saldo,becado;


    public fragmento_secretaria_reporte_alumno() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v= inflater.inflate(R.layout.fragment_fragmento_secretaria_reporte_alumno, container, false);
        buscar = v.findViewById(R.id.codigo_alumno);
        codigo = v.findViewById(R.id.text_codigo_alumno);
        nombre = v.findViewById(R.id.text_nombre_alumno);
        saldo=v.findViewById(R.id.text_saldo_alumno);
        becado=v.findViewById(R.id.text_becado_alumno);
        btn_buscar=v.findViewById(R.id.btn_buscar);


        btn_buscar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Ejecutar_Reporte_alumno("http://192.168.1.73/venta_ticket/Secretaria/reportes/reporte_alumno.php?alum_codigo="+buscar.getText()+"");

            }
        });


        return   v;
    }


    public void Ejecutar_Reporte_alumno(String url){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombre.setText(jsonObject.getString("alum_nombres"));
                        codigo.setText(jsonObject.getString("alum_codigo"));
                        saldo.setText(jsonObject.getString("alum_saldo"));
                        becado.setText(jsonObject.getString("alum_beca"));
                        buscar.setText("");

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"ERROR EN LA BUSQUEDA",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);


    }

}
