package MenuLateral.ui.myWebInicioSesion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareasprofcaei.R;
import com.example.tareasprofcaei.databinding.FragmentMywebiniciosesionBinding;
import com.example.tareasprofcaei.databinding.FragmentMywebiniciosesionBinding;

public class myWebInicioSesionFragment extends Fragment {

    private FragmentMywebiniciosesionBinding binding;
private WebView myWebInicioSesion;
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
        myWebInicioSesion.setWebViewClient(new WebViewClient()); // Para que las páginas se carguen dentro del WebView

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