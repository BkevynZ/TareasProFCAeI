package MenuLateral.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tareasprofcaei.R;
import com.example.tareasprofcaei.databinding.FragmentHomeBinding;

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
        myWebHTML.setWebViewClient(new WebViewClient()); // Para que las páginas se carguen dentro del WebView

        // Cargar una página web
        myWebHTML.loadUrl("file:///android_asset/index.html"); // Reemplaza con la URL que desees cargar



        return root;



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}