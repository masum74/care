package com.example.fazlay.hackathon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class ActivitySeven extends AppCompatActivity {
    Bitmap NewPhoto;
    public String name;


    private String UPLOAD_URL ="http://iatahmid.000webhostapp.com/schoolmonitor/imageUpload.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    EditText presentToday;

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(count==0)
        {
            super.onCreate(savedInstanceState);
            count++;
        }
        setContentView(R.layout.layout_seven);
    }

    public void takePhoto(View v)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        Intent myCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //hey I want to take a picture
        startActivityForResult(myCameraIntent, 10);
    }

    public void browsePhoto(View v)
    {
        /*if (Environment.getExternalStorageState().equals("mounted")) {

            Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 11);
        }*/


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 11);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        int present=0;

        //if user pressed OK after taking picture to save it
        if (requestCode == 10 &&resultCode == RESULT_OK  && data != null )
        {
            NewPhoto = (Bitmap) data.getExtras().get("data");

            setContentView(R.layout.layout_one);

            FaceOverlayView mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
            present = mFaceOverlayView.setBitmap(NewPhoto);

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx: "+present);

            presentToday=(EditText) findViewById(R.id.txtPresent);
            presentToday.setText("present Today: " + String.valueOf(present));

            System.out.println("*************************************************" + NewPhoto);
        }
        else if (requestCode == 11 && resultCode == RESULT_OK && data != null )
        {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                NewPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                setContentView(R.layout.layout_one);
                FaceOverlayView mFaceOverlayView = (FaceOverlayView) findViewById( R.id.face_overlay );
                present=mFaceOverlayView.setBitmap(NewPhoto);

                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx: "+present);

                presentToday=(EditText) findViewById(R.id.txtPresent);
                presentToday.setText("Present Today: "+String.valueOf(present));

            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        //uploadImage();

    }


    ///////////////////////////////////////////////////////////


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage(){
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(ActivitySeven.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(ActivitySeven.this, "Error:"+volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                        System.out.println("ERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR:  "+volleyError.getMessage().toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(NewPhoto);

                //Getting Image Name
                String name = getPictureName();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
   /* private void uploadImage()
    {
        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Showing the progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(NewPhoto);

                //Getting Image Name
                //String name = editTextName.getText().toString().trim();
                String name = getPictureName();
                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, name);


                //returning parameters
                return params;

            }
        };


        //Adding request to the queue
        requestQueue.add(stringRequest);
    }*/

    public void btnUploadClicked(View v)
    {

        uploadImage();
    }

    private String getPictureName()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = sdf.format(new Date());
        return "school_" + timeStamp + ".jpg";
    }


}
