package MenuLateral.ui.myWebInicioSesion;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareasprofcaei.R;
import com.example.tareasprofcaei.databinding.FragmentMywebiniciosesionBinding;
import com.example.tareasprofcaei.databinding.FragmentMywebiniciosesionBinding;

public class myWebInicioSesionFragment extends Fragment {

    private FragmentMywebiniciosesionBinding binding;
private WebView myWebInicioSesion;
    private ValueCallback<Uri[]> filePathCallback;

    private final ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (filePathCallback == null) return;

                Uri[] results = null;
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {
                        String dataString = result.getData().getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }

                filePathCallback.onReceiveValue(results);
                filePathCallback = null;
            }
    );
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myWebInicioSesionViewModel slideshowViewModel =
                new ViewModelProvider(this).get(myWebInicioSesionViewModel.class);

        binding = FragmentMywebiniciosesionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Inicializar WebView
        myWebInicioSesion = root.findViewById(R.id.webviewInicioSesion);

        // Configurar WebView
        WebSettings settingsHTML = myWebInicioSesion.getSettings();
        settingsHTML.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario
        settingsHTML.setDomStorageEnabled(true); // Habilitar DOM Storage
        settingsHTML.setDatabaseEnabled(true); // Habilitar base de datos
        myWebInicioSesion.setWebViewClient(new WebViewClient()); // Para que las páginas se carguen dentro del WebView

        // Configurar WebChromeClient para manejar la selección de archivos
        myWebInicioSesion.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                myWebInicioSesionFragment.this.filePathCallback = filePathCallback;
                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult.launch(intent);
                } catch (ActivityNotFoundException e) {
                    myWebInicioSesionFragment.this.filePathCallback = null;
                    return false;
                }
                return true;
            }
        });

        // Cargar una página web
        myWebInicioSesion.loadUrl("https://tareasprofcaei.antojitosjhony.site/login.php"); // Reemplaza con la URL que desees cargar



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}