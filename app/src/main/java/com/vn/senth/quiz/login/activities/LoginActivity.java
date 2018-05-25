package com.vn.senth.quiz.login.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.vn.core.base.BaseActivity;
import com.vn.senth.quiz.R;

import java.util.Arrays;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {
    private CallbackManager callbackFbManager;
    private GoogleApiClient mGoogleApiClient;
    private static final int GOOGLE_SIGN_IN = 9001;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
//        DeviceUtils.printHashKey(this);
    }

    @Override
    public void initData() {
        registerFacebookLogin();
        registerGoolgeLogin();
    }

    public void registerFacebookLogin() {
        callbackFbManager = CallbackManager.Factory.create();
        if (callbackFbManager == null) {
            return;
        }
        LoginManager.getInstance().registerCallback(callbackFbManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("facebook", loginResult.getAccessToken().getToken());
                    }

                    @Override
                    public void onCancel() {
                        // login cancelled
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // login error
                    }
                });

    }

    public void registerGoolgeLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
//                .requestIdToken(serverClientId)
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginActivity.this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signInGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    private void signOutGoogle() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                (Status status) -> {

                });
    }

    private void handleGoolgeSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI
            GoogleSignInAccount acct = result.getSignInAccount();
            if (acct != null) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGoolgeSignInResult(result);
        }
        // facebook callback
        callbackFbManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btnGoogle)
    public void loginByGoogle() {
        signInGoogle();
    }

    @OnClick(R.id.btnFacebook)
    public void loginByFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends", "email"));
    }
}
