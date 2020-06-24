package com.example.techclub.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.techclub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ProgressDialog progressDialog;
    FirebaseUser user;
    TextView nav_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = FirebaseAuth.getInstance().getCurrentUser();

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);

        TextView nav_user = hView.findViewById(R.id.tv_email);
        nav_user.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        nav_status = hView.findViewById(R.id.tv_status);
        checkStatus();

        final CircleImageView iv = hView.findViewById(R.id.iv_dp);

        RequestOptions options = new RequestOptions()
                .centerInside()
                .placeholder(R.drawable.blank_dp)
                .error(R.drawable.blank_dp)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .priority(Priority.HIGH);
        if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() != null)
            Glide.with(this)
                    .load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                    .apply(options)
                    .into(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        iv.setImageBitmap(r.getBitmap());
                        uploadDp(r);
                    }
                }).show(MainActivity.this);
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_joinUs, R.id.nav_gallery, R.id.nav_contact, R.id.nav_aboutUs)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_joinUs) {
                    navController.navigate(R.id.nav_joinUs);
                } else if (item.getItemId() == R.id.nav_aboutUs) {
                    navController.navigate(R.id.nav_aboutUs);
                } else if (item.getItemId() == R.id.nav_gallery) {
                    navController.navigate(R.id.nav_gallery);
                } else if (item.getItemId() == R.id.nav_contact) {
                    navController.navigate(R.id.nav_contact);
                } else if (item.getItemId() == R.id.nav_logout) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this, Login_page.class));
                    finish();
                }
                drawer.closeDrawer(GravityCompat.START, true);
                return true;
            }
        });
    }

    void checkStatus() {
        if (user.isEmailVerified()) {
            nav_status.setText("Verified User");
            nav_status.setOnClickListener(null);
        } else {
            nav_status.setText("Click to verify your email");
            nav_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user.sendEmailVerification();
                    Toast.makeText(MainActivity.this, "Verification Email sent, Check your Inbox", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        user.reload().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                checkStatus();
            }
        });
    }

    private void uploadDp(PickResult r) {

        FirebaseStorage mStorage = FirebaseStorage.getInstance();
        final StorageReference ref = mStorage.getReference().child("DP").child(FirebaseAuth.getInstance().getCurrentUser().getUid() + "");
        Uri file = Uri.fromFile(new File(r.getPath()));

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Uploading DP");
        progressDialog.show();

        ref.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();

                        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                if (task.isSuccessful()) {
                                    updateUser(task.getResult());
                                } else
                                    Toast.makeText(MainActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                .getTotalByteCount());
                        progressDialog.setMessage("Uploaded " + (int) progress + "%");
                    }
                });

    }

    private void updateUser(Uri result) {

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(result)
                .build();

        FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
