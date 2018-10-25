package com.example.risha.spamhealer;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private TensorFlowInferenceInterface inferenceInterface;

    private String path;
    private int[] intValues;
    private float[] floatValues;
    private float[] output;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      Button  picker=(Button) findViewById(R.id.select_path);

        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /**   Intent in = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                in.addCategory(Intent.CATEGORY_DEFAULT);
                startActivityForResult(Intent.createChooser(in, "Choose directory"), 9999);
**/
                inferenceInterface = new TensorFlowInferenceInterface(getAssets(), "opt_mnist_convnet.pb");

                AssetManager assetManager = getAssets();
                try {

                    InputStream istr = assetManager.open("1.jpeg");
                    Bitmap bitmap = BitmapFactory.decodeStream(istr);
                    ByteArrayOutputStream stream=new ByteArrayOutputStream();

                    intValues=new int[124*124];
                    floatValues=new float[124*124*3];
                    bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
                    for (int i = 0; i < intValues.length; ++i) {


                        floatValues[i * 3 + 0] = (((val >> 16) & 0xFF) - 20) / 12;
                        floatValues[i * 3 + 1] = (((val >> 8) & 0xFF) - 20) / 12;
                        floatValues[i * 3 + 2] = ((val & 0xFF) - 20) / 12;
                    }

                    inferenceInterface.feed("conv2d_1_input",floatValues,124,124,3);
                    inferenceInterface.run(new String[]{"activation_5/Sigmoid"});
                    inferenceInterface.fetch("activation_5/Sigmoid",output);
                    Toast.makeText(getApplicationContext(),output+"",Toast.LENGTH_SHORT).show();
                }catch (Exception e){}




            }
        });




    }

  /**  private float predict(Bitmap img){
        // model has only 1 output neuron
        float output[] = new float[1];

        // feed network with input of shape (1,input.length) = (1,2)
        inferenceInterface.feed("dense_1_input",img, 1);
        inferenceInterface.run(new String[]{"dense_2/Sigmoid"});
        inferenceInterface.fetch("dense_2/Sigmoid", output);

        // return prediction
        return output[0];
    }
**/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 9999:
                path=data.getData().toString();
                Toast.makeText(getApplicationContext(),""+data.getData(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
