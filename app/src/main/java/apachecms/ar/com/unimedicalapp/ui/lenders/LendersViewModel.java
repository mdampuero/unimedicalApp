package apachecms.ar.com.unimedicalapp.ui.lenders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LendersViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LendersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lenders fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}