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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.developer.filepicker.view.FilePickerDialog;
import com.learning.skilclasses.R;
import com.learning.skilclasses.Utilities.ApiUrl;
import com.learning.skilclasses.preferences.UserSession;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProfileActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.image)
    CircleImageView imageView;
    @BindView(R.id.upload_image)
    LinearLayout uploadImage;
    OkHttpClient okHttpClient;
    FilePickerDialog dialog;

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    String selectedCourse;


    @OnClick(R.id.upload_image)
    void uploadImagePhp() {
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

    public final static int RequestCode = 100;
    UserSession userSession;

    int count = 0;

    @BindView(R.id.name)
    EditText editName;
    @BindView(R.id.username)
    EditText editUsername;

    List<String> categories;

    @BindView(R.id.progress_update)
    ProgressBar progress;

    @BindView(R.id.update)
    Button update;

    @OnClick(R.id.update)
    void updateUser() {
        try {
            update.setEnabled(false);
            progress.setVisibility(View.VISIBLE);
            String updateResponse = updateuser(userSession.getUserDetails().get(UserSession.KEY_ID), ApiUrl.UPDATE_USER_PROFILE, editName.getText().toString().trim(), selectedCourse);
            JSONObject jsonObject = new JSONObject(updateResponse);
            if (jsonObject.getBoolean("status")) {
                editName.setText(jsonObject.getString("name"));
                selectedCourse = jsonObject.getString("course");
                userSession.setUserCourse(selectedCourse);
                userSession.setName(editName.getText().toString().trim());
                Toast.makeText(this, "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                update.setEnabled(true);
            } else {
                update.setEnabled(true);
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
            progress.setVisibility(View.GONE);
        } catch (Exception e) {
            update.setEnabled(true);
            progress.setVisibility(View.GONE);
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userSession = new UserSession(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        selectedCourse = userSession.getUserDetails().get(UserSession.KEY_COURSE);
        getSupportActionBar().setTitle(userSession.getUserDetails().get(UserSession.KEY_NAME));
        ButterKnife.bind(this);
        editName.setText(userSession.getUserDetails().get(UserSession.KEY_NAME));
        editUsername.setEnabled(false);
        editUsername.setText(userSession.getUserDetails().get(UserSession.KEY_EMAIL));
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (editName.getText().toString().equals(s)) {
                    count = 0;
                } else {
                    count = 1;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editName.getText().toString().equals(s)) {
                    count = 0;
                } else {
                    count = 1;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editName.getText().toString().equals(s)) {
                    count = 0;
                } else {
                    count = 1;
                }
            }
        });
        try {
            String picture_url = getUserProfilePicture(ApiUrl.GET_USER_PROFILE_PIC, userSession.getUserDetails().get(UserSession.KEY_ID));
            JSONObject jsonObject = new JSONObject(picture_url);
            Glide.get(this).clearMemory();
            if (jsonObject.getBoolean("status")) {
                Glide.with(ProfileActivity.this).load(jsonObject.getString("image")).into((imageView));
            } else {
                Glide.with(ProfileActivity.this).load(R.drawable.user).into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        spinner.setOnItemSelectedListener(this);
        categories = new ArrayList<String>();
        categories.add("9");
        categories.add("10");
        categories.add("Class 11th PCM");
        categories.add("Class 11th PCB");
        categories.add("Class 11th PCMB");
        categories.add("Class 12th PCM");
        categories.add("Class 12th PCB");
        categories.add("Class 12th PCMB");
        String selectedCourse = userSession.getUserDetails().get(UserSession.KEY_COURSE);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        for (int i = 0; i < categories.size(); i++) {
            if (selectedCourse.equals(categories.get(i))) {
                spinner.setSelection(i);
            }
        }


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
                    Toast.makeText(ProfileActivity.this, "Permission is Required for getting list of files", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public String uploadPic(String url, String imgString, String email) throws IOException {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", email)
                .addFormDataPart("image", imgString)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] imageBytes = bao.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedCourse = categories.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getUserProfilePicture(String url, String id) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private String updateuser(String id, String url, String name, String course) throws Exception {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", id)
                .addFormDataPart("name", name)
                .addFormDataPart("course", course)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
                uploadFile(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK && requestCode == RequestCode) {
//            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
//            try {
//                if (returnValue.size() != 0) {
//                    InputStream stream = getContentResolver().openInputStream(Uri.fromFile(new File(returnValue.get(0))));
//                    Bitmap bitmap = BitmapFactory.decodeStream(stream);
//                    stream.close();
//                    String uploadResponse = uploadPic(ApiUrl.UPLOAD_USER_PROFILE_PIC, imageToString(bitmap), new UserSession(ProfileActivity.this).getUserDetails().get(UserSession.KEY_ID));
//                    Log.d("updateresponse", uploadResponse);
//                    Log.d("email", new UserSession(ProfileActivity.this).getUserDetails().get(UserSession.KEY_ID));
//                    JSONObject jsonObject1 = new JSONObject(uploadResponse);
//                    boolean status = false;
//                    status = jsonObject1.getBoolean("status");
//                    if (status) {
//                        Toast.makeText(ProfileActivity.this, "Uploaded Image", Toast.LENGTH_SHORT).show();
//                        Log.d("image_path", jsonObject1.getString("image"));
//                        String imgUrl = jsonObject1.getString("image");
//                        progressBar.setVisibility(View.VISIBLE);
//                        String picture_url = getUserProfilePicture(ApiUrl.GET_USER_PROFILE_PIC, userSession.getUserDetails().get(UserSession.KEY_ID));
//                        JSONObject jsonObject = new JSONObject(picture_url);
//                        if (jsonObject.getBoolean("status")) {
//                            Glide.with(ProfileActivity.this).load(jsonObject.getString("image"))
//                                    .apply(RequestOptions.skipMemoryCacheOf(true))
//                                    .apply(RequestOptions.signatureOf(new ObjectKey(String.valueOf(System.currentTimeMillis()))))
//                                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
//                                    .into((imageView));
//                        } else {
//                            Glide.with(ProfileActivity.this).load(R.drawable.user).into(imageView);
//                        }
//                        new Handler().postDelayed(() -> {
//                            Glide.with(ProfileActivity.this).load(imgUrl)
//                                    .listener(new RequestListener<Drawable>() {
//                                        @Override
//                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                                            progressBar.setVisibility(View.GONE);
//                                            return false;
//                                        }
//
//                                        @Override
//                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                                            progressBar.setVisibility(View.GONE);
//                                            return false;
//                                        }
//                                    })
//                                    .into(imageView);
//                        }, 3000);
//                    } else {
//
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void uploadFile(String sourceFileUri) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final File file = new File(sourceFileUri);
            Uri uri = Uri.fromFile(file);
            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
            String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.toLowerCase());
            String imageName = file.getAbsolutePath();
            //Log.e(TAG, imageFile.getName()+" "+mime+" "+uriToFilename(uri));
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", imageName, RequestBody.create(MediaType.parse(mime), file))
                    .addFormDataPart("id", new UserSession(ProfileActivity.this).getUserDetails().get(UserSession.KEY_ID))
                    .build();

            Request request = new Request.Builder()
                    .url(ApiUrl.UPLOAD_USER_PROFILE_PIC)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("RESPONSE", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("RESPONSE", response.body().string());
                    runOnUiThread(() -> {
                        Toast.makeText(ProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    });
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(ProfileActivity.this, uri)) {
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
