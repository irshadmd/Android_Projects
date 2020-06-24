package com.learning.skilclasses.activities;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.developer.filepicker.view.FilePickerDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learning.skilclasses.R;
import com.learning.skilclasses.preferences.UserSession;
import com.learning.skilclasses.Utilities.ApiUrl;
import com.roger.catloadinglibrary.CatLoadingView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AskDoubtActivity extends AppCompatActivity {

    CatLoadingView mView;
    public final static int RequestCode = 100;
    FilePickerDialog dialog;

    @BindView(R.id.image)
    ImageView imageView;

    String image;

    @OnClick(R.id.remove_photo)
    void removeImage() {
        imageView.setImageBitmap(null);
    }


    @BindView(R.id.edit_message)
    EditText editMessage;

    OkHttpClient okHttpClient;
    UserSession userSession;

    @BindView(R.id.send_doubt)
    FloatingActionButton fab;

    @OnClick(R.id.send_doubt)
    void sendDoubt() {

        try {
            sendMessage(ApiUrl.SEND_MESSAGE, editMessage.getText().toString().trim(), userSession.getUserDetails().get(UserSession.KEY_ID), userSession.getUserDetails().get(UserSession.KEY_COURSE), image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.upload_images)
    void openImagePicker() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_doubt);
        ButterKnife.bind(this);
        userSession = new UserSession(this);
        mView = new CatLoadingView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (dialog != null) {   //Show dialog if the read permission has been granted.
                        dialog.show();
                    }
                } else {
                    //Permission has not been granted. Notify the user.
                    Toast.makeText(AskDoubtActivity.this, "Permission is Required for getting list of files", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                Uri selectedImageUri = data.getData();
                final String path = getFilePath(selectedImageUri);
                File file = new File(path);
                imageView.setImageURI(selectedImageUri);
                Log.d("LOCATION", file.getAbsolutePath());
                image = file.getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//    private String sendMessage(String url, String message, String user_id, String course, String image) throws Exception {
//        if (okHttpClient == null) {
//            okHttpClient = new OkHttpClient();
//        }
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("message", message)
//                .addFormDataPart("user_id", user_id)
//                .addFormDataPart("course", course)
//                .addFormDataPart("image", image + "")
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .build();
//        try (Response response = okHttpClient.newCall(request).execute()) {
//            return response.body().string();
//        }
//    }

    public void sendMessage(String url, String message, String user_id, String course, String image) {
        if (TextUtils.isEmpty(editMessage.getText())) {
            fab.setEnabled(true);
            Toast.makeText(this, "Must add caption", Toast.LENGTH_SHORT).show();
            mView.dismiss();
            return;
        }
        fab.setEnabled(false);
        mView.show(getSupportFragmentManager(), "Uploading");
        mView.setCancelable(false);
        mView.setText("Uploading");

        if (!TextUtils.isEmpty(image)) {

            try {
                Toast.makeText(this, "Here....", Toast.LENGTH_SHORT).show();
                OkHttpClient okHttpClient = new OkHttpClient();
                final File file = new File(image);
                Uri uri = Uri.fromFile(file);
                String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
                String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
                String imageName = file.getAbsolutePath();
                //Log.e(TAG, imageFile.getName()+" "+mime+" "+uriToFilename(uri));
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image", imageName, RequestBody.create(MediaType.parse(mime), file))
                        .addFormDataPart("user_id", user_id)
                        .addFormDataPart("message", message)
                        .addFormDataPart("course", course)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .post(requestBody)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("RESPONSE", e.getMessage());
                        runOnUiThread(() -> {
                            fab.setEnabled(true);
                            mView.dismiss();
                            Toast.makeText(AskDoubtActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        });

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("RESPONSE", response.body().string());
                        runOnUiThread(() -> {
                            fab.setEnabled(true);
                            mView.dismiss();
                            onBackPressed();
                        });
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }


//            AndroidNetworking.upload(url)
//                    .addMultipartParameter("image", image)
//                    .addMultipartParameter("message", message)
//                    .addMultipartParameter("user_id", user_id)
//                    .addMultipartParameter("course", course)
//                    .setTag("uploadTest")
//                    .setPriority(Priority.HIGH)
//                    .build()
//                    .setUploadProgressListener(new UploadProgressListener() {
//                        @Override
//                        public void onProgress(long bytesUploaded, long totalBytes) {
//                            // do anything with progress
//                        }
//                    })
//                    .getAsJSONObject(new JSONObjectRequestListener() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            try {
//                                if (response.getBoolean("status")) {
//                                    fab.setEnabled(true);
//                                    mView.dismiss();
//                                    onBackPressed();
//                                } else {
//                                    fab.setEnabled(true);
//                                    mView.dismiss();
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                fab.setEnabled(true);
//                                mView.dismiss();
//
//                            }
//                        }
//
//                        @Override
//                        public void onError(ANError error) {
//                            // handle error
//                            Log.d("ERROR", error.getMessage() + "");
//                            fab.setEnabled(true);
//                            mView.dismiss();
//
//                        }
//                    });
        } else {
            AndroidNetworking.upload(url)
                    .addMultipartParameter("message", message)
                    .addMultipartParameter("user_id", user_id)
                    .addMultipartParameter("course", course)
                    .setTag("uploadTest")
                    .setPriority(Priority.HIGH)
                    .build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {
                            // do anything with progress
                        }
                    })
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("status")) {
                                    fab.setEnabled(true);
                                    mView.dismiss();
                                    onBackPressed();
                                } else {
                                    fab.setEnabled(true);
                                    mView.dismiss();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                fab.setEnabled(true);
                                mView.dismiss();

                            }
                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                            Log.d("ERROR", error.getMessage() + "");
                            fab.setEnabled(true);
                            mView.dismiss();

                        }
                    });
        }
    }


    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] imageBytes = bao.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public String getFilePath(Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(AskDoubtActivity.this, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {


            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}

