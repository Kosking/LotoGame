package frontScreen.repository;

import android.support.annotation.NonNull;

import com.example.work.loto.FirstAction.Repository.ConnectRepository;
import com.example.work.loto.FirstAction.Repository.Retrofit.SettingsObjects.PlayObject;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

public class MockConnectingRepository implements ConnectRepository {

    private String[] stringsPreferences;

    @Override
    public void setPreferences(String preferences[]) {
        stringsPreferences = preferences;
    }
    //TODO TEST
    @Override
    public Observable<List<PlayObject>> startGame() {
        return null;
    }

    @NonNull
    @Override
    public Observable<String[]> getPreferences() {
        return Observable.just(stringsPreferences)
                .compose(RxUtils.async());
    }

}
