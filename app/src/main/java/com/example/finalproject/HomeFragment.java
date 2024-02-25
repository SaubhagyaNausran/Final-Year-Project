package com.example.finalproject;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    private static final int PICK_PDF_REQUEST_CODE = 1;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the ImageView by ID
        ImageView uploadPdfImageView = v.findViewById(R.id.uploadPdf);
        progressBar = v.findViewById(R.id.progressBar);

        // Set a click listener on the ImageView
        uploadPdfImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(intent, PICK_PDF_REQUEST_CODE);

            }
        });

        return v;
    }

    //
    private String getRealPathFromURI(Uri contentUri) {
        String result = null;
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = getContext().getContentResolver().query(contentUri, proj, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                result = cursor.getString(column_index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedPdfUri = data.getData();
            String pdfname =  getPdfName(getActivity(),selectedPdfUri);
            Log.d("@@@@@@",pdfname);

            Intent intent = new Intent(getActivity(), PdfActivity.class);
            intent.putExtra("PDF_NAME", pdfname); // Pass the file name
            startActivity(intent);
        }
    }

    public static String getPdfName(Context context, Uri uri) {
        String displayName = "";

        ContentResolver contentResolver = context.getContentResolver();

        try (Cursor cursor = contentResolver.query(uri, null, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                displayName = cursor.getString(nameIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return displayName;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((Dashboard) getActivity()).setToolbarVisibility(true);
    }

}