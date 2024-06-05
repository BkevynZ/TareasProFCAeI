package MenuLateral.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareasprofcaei.NotificationReceiver;
import com.example.tareasprofcaei.R;
import com.example.tareasprofcaei.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WebView myWebHTML;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Inicializar WebView
        myWebHTML = root.findViewById(R.id.webviewhtml);

        // Configurar WebView
        WebSettings settingsHTML = myWebHTML.getSettings();
        settingsHTML.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario

// Habilitar almacenamiento local
        settingsHTML.setDomStorageEnabled(true); // Habilitar DOM Storage
        settingsHTML.setDatabaseEnabled(true); // Habilitar base de datos
          settingsHTML.setCacheMode(WebSettings.LOAD_DEFAULT); // Usar caché por defecto



        myWebHTML.setWebViewClient(new WebViewClient()); // Para que las páginas se carguen dentro del WebView

        // Cargar una página web
        myWebHTML.loadUrl("file:///android_asset/index.html"); // Reemplaza con la URL que desees cargar
        // Añadir interfaz de JavaScript
        myWebHTML.addJavascriptInterface(new WebAppInterface(getActivity()), "Android");


        return root;



    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void scheduleNotification(String message, int hour, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            Intent intent = new Intent(mContext, NotificationReceiver.class);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
    }




}