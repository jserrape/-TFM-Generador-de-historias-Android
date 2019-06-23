package com.jcsp.historiasinteractivas.Fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcsp.historiasinteractivas.Actividades.NavigationDrawerActivity;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MisionFragment extends Fragment {

    private Mision mision = null;
    private NavigationDrawerActivity nd;

    //Elementos de gestion la actividad
    private View vista;
    private ImageView imagen;
    private TextView completado;
    private TextView descripcion;
    private Button btnAtras;


    public MisionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mision = (Mision) getArguments().getSerializable("mision");
            nd = (NavigationDrawerActivity) getArguments().getSerializable("nd");
            int dat[] = new int[1];
            dat[0] = 1;
            Log.d("tagmis", mision.toString());
            Log.d("tagmis", dat.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_mision, container, false);
        imagen = vista.findViewById(R.id.imageView3);
        completado = vista.findViewById(R.id.textView11);
        descripcion = vista.findViewById(R.id.textView12);
        btnAtras = vista.findViewById(R.id.button2);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nd.irALista();
            }
        });
        comprobarAtributosMision();
        return vista;
    }


    private void comprobarAtributosMision() {
        if (mision.getImagen_final() == null) {
            GetPostService mAPIService = ApiUtils.getAPIService();
            mAPIService.solicitud_mision(mision.getId()).enqueue(new Callback<Mision>() {
                @Override
                public void onResponse(Call<Mision> call, Response<Mision> response) {
                    mision.setTipo_localizacion(response.body().getTipo_localizacion());
                    mision.setCodigo_localizacion(response.body().getCodigo_localizacion());
                    mision.setTipo_prueba(response.body().getTipo_prueba());
                    mision.setCodigo_prueba(response.body().getCodigo_prueba());
                    mision.setDescripcion_inicial(response.body().getDescripcion_inicial());
                    mision.setImagen_inicial(response.body().getImagen_inicial());
                    mision.setDescripcion_final(response.body().getDescripcion_final());
                    mision.setImagen_final(response.body().getImagen_final());
                    aniadirCampos();
                }

                @Override
                public void onFailure(Call<Mision> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
                }

            });
        } else {
            aniadirCampos();
        }
    }

    public void aniadirCampos() {
        byte[] imageAsBytes;
        if (mision.getCompletado().equals("False")) {
            imageAsBytes = Base64.decode(mision.getImagen_inicial().getBytes(), Base64.DEFAULT);
            descripcion.setText(mision.getDescripcion_inicial().replaceAll("#", System.getProperty("line.separator") + System.getProperty("line.separator")));
        } else {
            imageAsBytes = Base64.decode(mision.getImagen_final().getBytes(), Base64.DEFAULT);
            descripcion.setText(mision.getDescripcion_final().replaceAll("#", System.getProperty("line.separator") + System.getProperty("line.separator")));
        }
        imagen.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        imagen.setAdjustViewBounds(true);

        if (!mision.getCompletado().equals("False")) {
            completado.setText(mision.getCompletado());
        } else {
            completado.setText("");
        }
        descripcion.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
