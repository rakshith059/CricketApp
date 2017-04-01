package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;

/**
 * Created by rakshith on 4/1/17.
 */

public class AdminLoginFragment extends BaseFragment implements View.OnClickListener {
    TextInputLayout tilUsername;
    TextInputLayout tilpassword;

    EditText etUsername;
    EditText etPassword;

    TextView tvSubmit;

    FirebaseAuth firebaseAuth;
    private String email;
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        tilUsername = (TextInputLayout) view.findViewById(R.id.fragment_login_til_username);
        tilpassword = (TextInputLayout) view.findViewById(R.id.fragment_login_til_password);

        etUsername = (EditText) view.findViewById(R.id.fragment_login_et_username);
        etPassword = (EditText) view.findViewById(R.id.fragment_login_et_password);

        tvSubmit = (TextView) view.findViewById(R.id.fragment_login_tv_submit);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_login_tv_submit:
                email = etUsername.getText().toString();
                password = etPassword.getText().toString();
                ((HomeActivity) getActivity()).hideSoftKeyboard();

                if (validate())
                    login();
                break;
            default:
                break;
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(email)) {
            tilUsername.setError(getString(R.string.hint_email_id));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            tilpassword.setError(getString(R.string.hint_password));
            return false;
        }
        return true;
    }

    private void login() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Constants.setSharedPrefrence(mActivity, Constants.IS_USER_LOGGED_IN, Constants.TRUE);
                            Intent intent = new Intent(mActivity, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        } else
                            tilpassword.setError(getString(R.string.hint_email_password_mismatch));
                    }
                });
    }
}
