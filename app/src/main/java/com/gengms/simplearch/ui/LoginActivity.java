package com.gengms.simplearch.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.gengms.simplearch.R;
import com.gengms.simplearch.app.BasicApp;
import com.gengms.simplearch.data.db.entity.UserEntity;
import com.gengms.simplearch.databinding.ActivityLoginBinding;

import java.util.Date;
import java.util.UUID;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{
    private UserLoginTask mAuthTask = null;
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        // Set up the login form.
        mBinding.password.setOnEditorActionListener((textView, id, keyEvent)->
        {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL)
            {
                attemptLogin();
                return true;
            }
            return false;
        });
        mBinding.phoneSignInButton.setOnClickListener((v)->attemptLogin());
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin()
    {
        if (mAuthTask != null)
        {
            return;
        }

        // Reset errors.
        mBinding.phone.setError(null);
        mBinding.password.setError(null);

        // Store values at the time of the login attempt.
        String phone = mBinding.phone.getText().toString();
        String password = mBinding.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mBinding.password.setError(getString(R.string.error_invalid_password));
            focusView = mBinding.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(phone))
        {
            mBinding.phone.setError(getString(R.string.error_field_required));
            focusView = mBinding.phone;
            cancel = true;
        }
        else if (!isPhoneValid(phone))
        {
            mBinding.phone.setError(getString(R.string.error_invalid_email));
            focusView = mBinding.phone;
            cancel = true;
        }

        if (cancel)
        {
            focusView.requestFocus();
        }
        else
        {
            showProgress(true);
            mAuthTask = new UserLoginTask(phone, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isPhoneValid(String phone)
    {
        return phone.matches("1[34578]\\d{9}");
    }

    private boolean isPasswordValid(String password)
    {
        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mBinding.loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            mBinding.loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mBinding.loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mBinding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mBinding.loginProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mBinding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mBinding.loginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mBinding.loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {

        private final String mPhone;
        private final String mPassword;

        UserLoginTask(String phone, String password)
        {
            mPhone = phone;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                // Simulate network access.
                UserEntity user = new UserEntity(UUID.randomUUID().toString(),"zhangquandan",mPhone,1,new Date(System.currentTimeMillis()));
                ((BasicApp)getApplication()).getRepository().insertOrUpdate(user);
                SharedPreferences sp = getSharedPreferences("pet", MODE_PRIVATE);
                sp.edit().putString("uid", user.getUid()).apply();
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                return false;
            }

            return "000000".equals(mPassword);
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            mAuthTask = null;
            showProgress(false);

            if (success)
            {
                startActivity(new Intent(LoginActivity.this, MyActivity.class));
                finish();
            }
            else
            {
                mBinding.password.setError(getString(R.string.error_incorrect_password));
                mBinding.password.requestFocus();
            }
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

