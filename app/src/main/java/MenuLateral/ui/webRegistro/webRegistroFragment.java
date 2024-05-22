package MenuLateral.ui.webRegistro;

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
import com.example.tareasprofcaei.databinding.FragmentWebregistroBinding;

public class webRegistroFragment extends Fragment {

    private FragmentWebregistroBinding binding;
    private WebView myWebRegistro;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        webRegistroViewModel galleryViewModel =
                new ViewModelProvider(this).get(webRegistroViewModel.class);

        binding = FragmentWebregistroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar WebView
        myWebRegistro = root.findViewById(R.id.webviewRegistro);

        // Configurar WebView
        WebSettings settingsHTML = myWebRegistro.getSettings();
        settingsHTML.setJavaScriptEnabled(true); // Habilitar JavaScript si es necesario
        myWebRegistro.setWebViewClient(new WebViewClient()); // Para que las páginas se carguen dentro del WebView

        // Cargar una página web
        myWebRegistro.loadUrl("https://tareasprofcaei.antojitosjhony.site/register.php"); // Reemplaza con la URL que desees cargar



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}