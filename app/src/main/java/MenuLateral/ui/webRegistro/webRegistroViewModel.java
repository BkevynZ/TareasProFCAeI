package MenuLateral.ui.webRegistro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class webRegistroViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public webRegistroViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}