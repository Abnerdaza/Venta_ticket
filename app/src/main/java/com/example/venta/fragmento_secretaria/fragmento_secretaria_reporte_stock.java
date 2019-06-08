package com.example.venta.fragmento_secretaria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.venta.R;
import com.example.venta.Stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragmento_secretaria_reporte_stock extends Fragment {
   ListView lista_stock;

    public fragmento_secretaria_reporte_stock() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragmento_secretaria_reporte_stock, container, false);
        lista_stock=v.findViewById(R.id.lista_stock);

        Ejecutar_Reporte_stock("http://192.168.1.73/venta_ticket/Secretaria/reportes/reporte_stock.php");



        return v;
    }


    public void Ejecutar_Reporte_stock(String url){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Stock> lista = new ArrayList<Stock>();
                JSONObject jsonObject = null;
                try {
                for (int i = 0; i < response.length(); i++) {

                        Stock s = new Stock();
                        jsonObject = response.getJSONObject(i);
                        s.setStock_periodo(jsonObject.getString("stoc_periodo"));
                        s.setStoc_becados(jsonObject.getInt("stoc_becados"));
                        lista.add(s);




                }
                ArrayAdapter <Stock> stockArrayAdapter = new ArrayAdapter<Stock>(getContext(),android.R.layout.simple_list_item_1,lista);
                lista_stock.setAdapter(stockArrayAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
