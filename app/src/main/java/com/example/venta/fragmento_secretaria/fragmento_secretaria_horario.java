package com.example.venta.fragmento_secretaria;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.venta.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmento_secretaria_horario extends Fragment {
    EditText desayuno,almuerzo_en,almuerzo_post,almuerzo,almuerzo_be,cena_en,cena_post,cena,cena_be;
    Button btn_registrar;
    CalendarView calendarView;
    String fecha;


    public fragmento_secretaria_horario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_fragmento_secretaria_horario, container, false);

        btn_registrar=v.findViewById(R.id.btn_registrar);

        calendarView = v.findViewById(R.id.calendario);

        desayuno=v.findViewById(R.id.text_desayuno);
        almuerzo_en=v.findViewById(R.id.text_al_entrada);
        almuerzo_post=v.findViewById(R.id.text_al_postre);
        almuerzo=v.findViewById(R.id.text_al);
        almuerzo_be=v.findViewById(R.id.text_al_bebida);
        cena_en=v.findViewById(R.id.text_ce_entrada);
        cena_post=v.findViewById(R.id.text_ce_postre);
        cena=v.findViewById(R.id.text_ce);
        cena_be=v.findViewById(R.id.text_ce_bebida);



        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (desayuno.getText().toString().isEmpty() || almuerzo_en.getText().toString().isEmpty() || almuerzo_post.getText().toString().isEmpty()
                || almuerzo.getText().toString().isEmpty() || almuerzo_be.getText().toString().isEmpty() || cena_en.getText().toString().isEmpty() ||
                cena_post.getText().toString().isEmpty() || cena.getText().toString().isEmpty() || cena_be.getText().toString().isEmpty()){

                    Toast.makeText(getContext(),"Hay campos vacios!!",Toast.LENGTH_SHORT).show();
                }else{

                    Ejecutar_Horario_ticket("http://192.168.1.73/venta_ticket/Secretaria/registros/horario.php");
                }

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                 fecha= year+"-"+(month+1)+"-"+dayOfMonth;

            }
        });

        return  v;

    }


    private void Ejecutar_Horario_ticket(String url){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),"OPERACION EXITOSA",Toast.LENGTH_SHORT).show();
                //LIMPIAMOS LOS CAMPOS  DESPUES DE RECARGAR
                desayuno.setText("");
                almuerzo_en.setText("");
                almuerzo_post.setText("");
                almuerzo.setText("");
                almuerzo_be.setText("");
                cena_en.setText("");
                cena_post.setText("");
                cena.setText("");
                cena_be.setText("");
            }
        },new  Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                //PARAMETROS PARA ENVIAR POR EL METODO POST

                parametros.put("fecha",fecha);
                parametros.put("desayuno",desayuno.getText().toString());
                parametros.put("almuerzo_entrada",almuerzo_en.getText().toString());
                parametros.put("almuerzo_postre",almuerzo_post.getText().toString());
                parametros.put("almuerzo",almuerzo.getText().toString());
                parametros.put("almuerzo_bebida",almuerzo_be.getText().toString());
                parametros.put("cena_entrada",cena_en.getText().toString());
                parametros.put("cena_postre",cena_post.getText().toString());
                parametros.put("cena",cena.getText().toString());
                parametros.put("cena_bebida",cena_be.getText().toString());


                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}
