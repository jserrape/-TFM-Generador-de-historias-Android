/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 27/03/19 1:51
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 27/03/19 1:51
 *
 */

package com.jcsp.historiasinteractivas.Dialogos;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.jcsp.historiasinteractivas.Fragments.MapFragment;
import com.jcsp.historiasinteractivas.R;
import com.jcsp.historiasinteractivas.REST.ApiUtils;
import com.jcsp.historiasinteractivas.REST.GetPostService;
import com.jcsp.historiasinteractivas.REST.Respuesta;
import com.jcsp.historiasinteractivas.Objetos_gestion.Mision;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EscaneoQRDialogo {

    private Mision mision;
    private Context contexto;

    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;

    private Button btn_cancelar;
    private Dialog dialogo;

    private MapFragment map;

    private Detector.Processor prod = null;

    public EscaneoQRDialogo(Context contexto, Mision mis, MapFragment mmap){
        dialogo = new Dialog(contexto);
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogo.setCancelable(true);
        dialogo.setContentView(R.layout.dialogo_escanero_qr);

        this.contexto=contexto;
        this.mision = mis;
        this.map = mmap;


        cameraView = dialogo.findViewById(R.id.camera_view);
        btn_cancelar = dialogo.findViewById(R.id.button_cancelar_qr);
        btn_cancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                barcodeDetector.release();
                map.iniciarDialogo(4);
                dialogo.dismiss();
            }
        });

        initQR();
        dialogo.show();
    }

    private void initQR() {
        //Crea el lector de qr
        barcodeDetector = new BarcodeDetector.Builder(contexto).setBarcodeFormats(Barcode.QR_CODE).build();

        //Crea la camara
        cameraSource = new CameraSource.Builder(contexto, barcodeDetector).build();

        // Prepara el lector de qr
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                //Verifica si el usuario ha dado permiso para la camara
                if (ContextCompat.checkSelfPermission(contexto,  android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        //Establece la función al escanear un código
        prod = new Detector.Processor() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections detections) {
                final SparseArray barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {

                    String txt = "";
                    txt = ((Barcode) barcodes.valueAt(0)).displayValue;

                    if (txt.equals(mision.getCodigo_localizacion())) {
                        btn_cancelar.post(new Runnable() {
                            public void run() {
                                map.iniciarDialogo(4);
                                dialogo.dismiss();
                            }
                        });
                    }

                    GetPostService mAPIService = ApiUtils.getAPIService();
                    mAPIService.enviar_texto(txt).enqueue(new Callback<Respuesta>() {
                        @Override
                        public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        }

                        @Override
                        public void onFailure(Call<Respuesta> call, Throwable t) {
                        }
                    });
                    //Cierra el detector de códigos
                    //barcodeDetector.release();
                }
            }
        };
        barcodeDetector.setProcessor(prod);
    }
}
