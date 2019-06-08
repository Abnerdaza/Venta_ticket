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
public class fragmento_secretaria_saldo extends Fragment {
    EditText edi_saldo,edi_alum_codigo;
    Button btn_recargar;

    public fragmento_secretaria_saldo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_fragmento_secretaria_saldo, container, false);
        edi_alum_codigo = v.findViewById(R.id.alum_codigo);
        edi_saldo = v.findViewById(R.id.alum_saldo);
        btn_recargar=v.findViewById(R.id.btn_recargar);

        btn_recargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edi_saldo.getText().toString().isEmpty() || edi_alum_codigo.getText().toString().isEmpty()){
                    Toast.makeText(getContext(),"Hay campos vacios!!",Toast.LENGTH_SHORT).show();

                }else{
                    ejecutarRecargar("http://192.168.1.73/venta_ticket/Secretaria/registros/generar_recarga.php");
                }



            }
        });



        return  v;
    }

    private void ejecutarRecargar(String url){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),"OPERACION EXITOSA",Toast.LENGTH_SHORT).show();
                //LIMPIAMOS LOS CAMPOS  DESPUES DE RECARGAR
                edi_alum_codigo.setText("");
                edi_saldo.setText("");
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
                parametros.put("alum_codigo",edi_alum_codigo.getText().toString());
                parametros.put("saldo",edi_saldo.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}
