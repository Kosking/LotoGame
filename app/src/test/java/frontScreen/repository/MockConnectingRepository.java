package frontScreen.repository;

import android.support.annotation.NonNull;

import com.example.work.loto.ConnectRepository;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

public class MockConnectingRepository implements ConnectRepository {

    private String[] stringsPreferences;

    @Override
    public void setPreferences(String preferences[]) {
        stringsPreferences = preferences;
    }

    @NonNull
    @Override
    public Observable<String[]> getPreferences() {
        return Observable.just(stringsPreferences)
                .compose(RxUtils.async());
    }

}
