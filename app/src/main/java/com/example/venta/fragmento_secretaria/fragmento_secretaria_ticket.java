package com.example.venta.fragmento_secretaria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class fragmento_secretaria_ticket extends Fragment {


    EditText text_periodo,text_stock;
    Button btn_guardar;

    public fragmento_secretaria_ticket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragmento_secretaria_ticket, container, false);

         text_periodo = v.findViewById(R.id.text_perido_stock);
        text_stock  = v.findViewById(R.id.text_stock);

        btn_guardar = v.findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text_periodo.getText().toString().isEmpty() || text_stock.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Hay campos vacios!!",Toast.LENGTH_SHORT).show();

                }else{

                    Ejecutar_stock_becados("http://192.168.1.73/venta_ticket/Secretaria/registros/stock_becados.php");
                }


            }
        });



        return v;
    }




    private void Ejecutar_stock_becados(String url){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),"OPERACION EXITOSA",Toast.LENGTH_SHORT).show();
                //LIMPIAMOS LOS CAMPOS  DESPUES DE RECARGAR
                text_periodo.setText("");
                text_stock.setText("");

            }
        },new  Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                //PARAMETROS PARA ENVIAR POR EL METODO POST

               parametros.put("periodo",text_periodo.getText().toString());
               parametros.put("becados",text_stock.getText().toString());



                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}
