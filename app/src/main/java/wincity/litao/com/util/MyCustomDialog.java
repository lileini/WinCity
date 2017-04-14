package wincity.litao.com.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by shang guangneng on 2016/8/4 0004.
 */
public class MyCustomDialog {
    private final String WARNING = "Warning";
    private final String PLEASE_WAIT = "Please, wait...";

    private static MyCustomDialog instance;

    private Dialog dialog;
    private Context context;

    /**
     * Private construct, do nothing
     */
    private MyCustomDialog() {
    }

    /**
     * Singleton which gets the unique instance of DialogFactory and manage contexts automatically
     *
     * @param context Sets the current context of the current Activity
     * @return The unique instance of DialogFactory
     */
    public static synchronized MyCustomDialog getInstance(Context context) {
        if (instance == null) {
            instance = new MyCustomDialog();
        }
        instance.context = context;
        return instance;
    }

    /**
     * Dismiss the dialog
     */
    public void dismiss() {
        if (this.dialog != null) {
            this.dialog.dismiss();
            this.dialog = null;
            return;
        }
    }

    /**
     * Shows a Alert Dialog with a custom title, the message and the button listener
     *
     * @param title    Title of your dialog
     * @param message  Message of your dialog
     * @param listener Listener which be execute after click on button
     */
    public void makeAlert(String title, String message, OnClickListener listener) {
        this.dismiss();
        this.dialog = new AlertDialog.Builder(this.context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(this.context.getString(android.R.string.ok), listener)
                .show();
    }


    /**
     * Shows a Alert Dialog with a default title, the message and the button listener
     *
     * @param message  Message of your dialog
     * @param listener Listener which be execute after click on button
     */
    public void makeAlert(String message, OnClickListener listener) {
        this.makeAlert(this.WARNING, message, listener);
    }


    /**
     * Shows a Alert Dialog with a default title, the message and without button listener
     *
     * @param message Message of your dialog
     */
    public void makeAlert(String message) {
        this.makeAlert(message, null);
    }


    /**
     * Shows a Confirm Dialog which has a custom title, the message and yes button listener
     *
     * @param title       Title of your dialog
     * @param message     Message of your dialog
     * @param yesListener Listener which be execute after click on yes button
     */
    public void makeConfirm(String title, String message, OnClickListener yesListener) {
        this.dismiss();
        this.dialog = new AlertDialog.Builder(this.context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(this.context.getString(android.R.string.yes), yesListener)
                .setNegativeButton(this.context.getString(android.R.string.no), null)
                .show();
    }


    /**
     * Shows a Confirm Dialog which has a default title, the message and yes button listener
     *
     * @param message     Message of your dialog
     * @param yesListener Listener which be execute after click on yes button
     */
    public void makeConfirm(String message, OnClickListener yesListener) {
        this.makeConfirm(this.WARNING, message, yesListener);
    }


    /**
     * Shows a bottomView Dialog which has a custom title, the message by view
     *
     * @param title      can be null
     * @param bottomView
     * @param themeResId
     */
    public void showBottomContent(@Nullable String title, @NonNull View bottomView, @StyleRes int themeResId) {
        dialog = new Dialog(context, themeResId);
        dialog.setContentView(bottomView);
        if (null != title) {
            dialog.setTitle(title);
        }
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        bottomView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        this.setOnDismissListener(bottomView);
    }

    /**
     * Shows a custom View with has a custom title and can be cancelable
     *
     * @param title      Title of your dialog
     * @param view       View of your dialog
     * @param cancelable If your dialog can be cancelable
     */
    public void showContent(String title, View view, boolean cancelable) {
        this.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context)
                .setTitle(title)
                .setView(view)
                .setCancelable(cancelable);
        if (title != null) {
            builder.setTitle(title);
        }
        this.dialog = builder.show();

        this.setOnDismissListener(view);
    }


    /**
     * Remove all views when dialog is dismissing. This is important because of "the specified child already has a parent" exception
     *
     * @param view View of your dialog
     */
    private void setOnDismissListener(final View view) {
        this.dialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ViewGroup parent = (ViewGroup) view.getParent();
                parent.removeAllViews();
            }
        });
    }

    /**
     * Shows a custom View with has a custom title and can be cancelable
     *
     * @param title Title id of your dialog
     * @param view  View of your dialog
     */
    public void showContent(String title, View view) {
        this.showContent(title, view, true);
    }


    /**
     * Shows a custom View with has a default title and can be cancelable
     *
     * @param view       View of your dialog
     * @param cancelable If your dialog can be cancelable
     */
    public void showContent(View view, boolean cancelable) {
        this.showContent(null, view, cancelable);
    }

    /**
     * Shows a Wait Screen which has a custom message and can be cancelable
     *
     * @param message    The message of this dialog
     * @param cancelable If your dialog can be close by back button
     */
    public void showWaiting(String message, boolean cancelable) {
        this.dismiss();
        this.dialog = ProgressDialog.show(this.context, "", message, true, cancelable);
        this.dialog.show();
    }

    /**
     * Verifies if your dialog is showing or not
     *
     * @return If your dialog is showing
     */
    public boolean isShowing() {
        if (this.dialog != null)
            return this.dialog.isShowing();
        return false;
    }
}
