package example.mmigmur.mmimgurgalleryexample.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import example.mmigmur.mmimgurgalleryexample.R;


/**
 * Created by migarcma on 26/1/17.
 */

public class DialogManager {


    /**
     * Current alert dialog
     */
    private static AlertDialog currentAlertDialog = null;

    /**
     * Current fragment dialog
     */
    private static DialogFragment currentDialogFragment = null;

    /**
     * Current dialog
     */
    private static Dialog currentDialog = null;

    /**
     * Animation time
     */
    private static final int DUR = 350;

    /**
     * Flag
     */
    private static boolean hideProgressDialog = false;


    /**
     * Default
     */
    public DialogManager(){
        super();
    }


    /**
     * Muestra un dialogo de alerta
     * @param context - contexto de la aplicacion
     * @param title - titulo del dialogo
     * @param mensaje - mensaje del dialogo
     * @param buttonTitle - titulo del boton. Si viene a null no se muestra boton
     * @param buttonListener - listenerBoton. Si viene a null no se meustra boton
     */
    public static void getAlertDialog(Context context, String title, String mensaje, String buttonTitle, DialogInterface.OnClickListener buttonListener) {

        // Ocultamos algun dialogo anterior
        DialogManager.hideCurrentDialog();

        // Configuramos el dialogo
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(mensaje);
        alertDialog.setCancelable(false);

        if (buttonTitle == null) {
            buttonTitle = context.getString(R.string.BTN_CLOSE);
        }

        if (buttonListener != null) {
            alertDialog.setPositiveButton(buttonTitle, buttonListener);
        } else {

            alertDialog.setPositiveButton(buttonTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hideCurrentDialog();
                }
            });
        }
        // Mostramos la alerta en el hilo principal
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                // Mostramos la alerta
                currentAlertDialog = alertDialog.show();
            }
        });
    }


    /**
     * Muestra un dialogo de alerta
     * @param context - contexto de la aplicacion
     * @param title - titulo del dialogo
     * @param mensaje - mensaje del dialogo
     * @param buttonAcceptTitle - titulo del boton positivo. Si viene a null no se muestra boton
     * @param buttonCancelTittle - titulo del boton negativo. Si viene a null no se muestra boton
     * @param buttonAcceptListener - listener del boton positivo. Si viene a null no se meustra boton
     * @param buttonCancelListener - listener del boton negativo. Si viene a null no se meustra boton
     */
    public static void getTwoButtonAlertDialog(Context context, String title, String mensaje, String buttonAcceptTitle, String buttonCancelTittle, DialogInterface.OnClickListener buttonAcceptListener, DialogInterface.OnClickListener buttonCancelListener) {

        // Configuramos el dialogo
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(mensaje);
        alertDialog.setCancelable(false);

        if (buttonAcceptTitle == null) {
            buttonAcceptTitle = context.getString(R.string.BTN_ACEPT);
        }

        if (buttonCancelTittle == null) {
            buttonAcceptTitle = context.getString(R.string.BTN_CLOSE);
        }

        if (buttonAcceptListener != null) {
            alertDialog.setPositiveButton(buttonAcceptTitle, buttonAcceptListener);
        } else {

            alertDialog.setPositiveButton(buttonAcceptTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        if (buttonCancelListener != null) {
            alertDialog.setNegativeButton(buttonCancelTittle, buttonCancelListener);
        } else {

            alertDialog.setNegativeButton(buttonCancelTittle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        // Mostramos la alerta en el hilo principal
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                // Mostramos la alerta
                currentAlertDialog = alertDialog.show();
            }
        });
    }


    /**
     * Oculta el dialogo actual en pantalla
     */
    public static void hideCurrentDialog() {

        try {

            hideProgressDialog = true;

            if (currentAlertDialog != null && currentAlertDialog.isShowing()) {
                currentAlertDialog.dismiss();
                currentAlertDialog.hide();
            }

            currentAlertDialog = null;


            if (currentDialog != null && currentDialog.isShowing()) {
                currentDialog.dismiss();
            }

            currentDialog = null;

            if (currentDialogFragment != null && currentDialogFragment.getShowsDialog()) {
                currentDialogFragment.dismiss();
            }

            currentDialogFragment = null;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            currentDialog = null;
            currentAlertDialog = null;
        }
    }


    /**
     * Open progress dialog
     * @param context - app context
     */
    public static void getProgressDialog(final Context context){
        hideProgressDialog = false;

        // Mostramos la alerta en el hilo principal
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                if (!hideProgressDialog) {
                    //inflate view
                    final LinearLayout llLoading = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.lay_full_screen_progress_dialog, null);

                    ProgressBar progBar = (ProgressBar) llLoading.findViewById(R.id.pgWZKbar);
                    if (progBar != null) {
                        progBar.setVisibility(View.VISIBLE);
                        progBar.setIndeterminate(true);
                        progBar.getIndeterminateDrawable()
                                .setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }

                    //inflate view
                    LayoutInflater inflater = LayoutInflater.from(context);
                    RelativeLayout rlDialogFullScreen = (RelativeLayout) inflater.inflate(R.layout.lay_dialog_fullscreen, null);

                    int w = context.getResources().getDisplayMetrics().widthPixels;
                    int h = context.getResources().getDisplayMetrics().heightPixels;

                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
                    params.addRule(RelativeLayout.CENTER_IN_PARENT);

                    rlDialogFullScreen.addView(llLoading, params);

                    // Ocultamos algun dialogo anterior
                    DialogManager.hideCurrentDialog();

                    // Configuramos el dialogo
                    final Dialog dialog = new Dialog(context, R.style.LoadingTheme);
                    dialog.setContentView(rlDialogFullScreen);
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);

                    // Mostramos la alerta
                    currentDialog = dialog;
                    currentDialog.show();
                }
            }
        });
    }


    /**
     *
     * @param context application context
     * @param year default year
     * @param month default month
     * @param dayOfMonth default day
     * @param maxDateMillis max date available to select in millis, null means no limit
     * @param onDateSetListener listener on date selection
     */
    public static void getDatePickerDialog(Context context, int year, int month, int dayOfMonth, Long maxDateMillis, Long minDateMillis, DatePickerDialog.OnDateSetListener onDateSetListener){

        // Ocultamos algun dialogo anterior
        DialogManager.hideCurrentDialog();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(context,onDateSetListener,year,month,dayOfMonth);
        if (maxDateMillis!=null) {
            datePickerDialog.getDatePicker().setMaxDate(maxDateMillis);
        }

        if(minDateMillis != null){
            datePickerDialog.getDatePicker().setMinDate(minDateMillis);
        }

        // Mostramos la alerta en el hilo principal
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                //Mostramos el dialog
                currentAlertDialog = datePickerDialog;
                currentAlertDialog.show();
            }
        });
    }

    public static void getTimePickerDialog(Context context, int hourOfDay, TimePickerDialog.OnTimeSetListener onTimeSetListener){

        // Ocultamos algun dialogo anterior
        DialogManager.hideCurrentDialog();

        final TimePickerDialog timePickerDialog = new TimePickerDialog(context,onTimeSetListener,hourOfDay, 0,true);

        // Mostramos la alerta en el hilo principal
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                //Mostramos el dialog
                currentAlertDialog = timePickerDialog;
                currentAlertDialog.show();
            }
        });
    }



    /**
     * Shows an snack bar with a message
     * @param view view where Snackbar has to be shown
     * @param message message to show
     * @param duration duration of the message visibility
     */
    public static void showSnackBarMessage(View view, String message, int duration){
        Snackbar
                .make(view, message, duration)
                .show();
    }

    /**
     *
     * @param context - app context
     */
    public static void getBottomOptionMenu(Context context,MenuOptionsAdapter adapter, AdapterView.OnItemClickListener menuClickListener){

        final Dialog dialog = new Dialog(context, R.style.DialogSlideAnim);
        dialog.setCanceledOnTouchOutside(true);

        LayoutInflater inflater = LayoutInflater.from(context);
        RelativeLayout rlBottomMenu = (RelativeLayout)inflater.inflate(R.layout.lay_dialog_bottom_menu, null);
        dialog.setContentView(rlBottomMenu);

        ListView lvMenuItems = (ListView) rlBottomMenu.findViewById(R.id.lvMenuItems);
        lvMenuItems.setOnItemClickListener(menuClickListener);
        lvMenuItems.setAdapter(adapter);

        dialog.getWindow().getAttributes().windowAnimations = R.style.animTopBottom;

        // Mostramos la alerta en el hilo principal
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                // Mostramos la alerta
                currentDialog = dialog;
                dialog.show();
            }
        });

    }

    /**
     * Listview Adapter
     */
    public static class MenuOptionsAdapter extends BaseAdapter {

        private CharSequence[] options;
        private int[] iconList;
        private Context context;

        public MenuOptionsAdapter(CharSequence[] options, int[] iconList, Context context){
            this.options = options;
            this.iconList = iconList;
            this.context = context;
        }


        @Override
        public int getCount() {
            return this.options != null ? this.options.length : 0;
        }

        @Override
        public Object getItem(int position) {
            return this.options[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //get view
            View rowView = convertView;

            //if convert is null, inflate new view
            if(rowView == null){
                rowView = LayoutInflater.from(this.context).inflate(R.layout.lay_bottom_menu_row, parent, false);
            }

            TextView tvOption = (TextView) rowView.findViewById(R.id.tvOption);
            tvOption.setText(this.options[position]);
            rowView.setTag(position);

            ImageView ivIcon = (ImageView) rowView.findViewById(R.id.ivIcon);
            ivIcon.setImageResource(this.iconList[position]);

            return rowView;
        }

    }


//    public static void showSuccessDialog(Context context, String title, String subtitle, String btnText, View.OnClickListener btnListener){
//
//        SuccessDialogFragment successDialogFragment = SuccessDialogFragment.newInstance(title, subtitle, btnText, btnListener);
//
//        currentDialogFragment = successDialogFragment;
//        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//        currentDialogFragment.show(fragmentManager,SuccessDialogFragment.TAG);
//
//    }

}
