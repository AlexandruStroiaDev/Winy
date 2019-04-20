package co.alexdev.winy.feature.ui.login.uimodel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import co.alexdev.winy.core.model.UserCredential;
import co.alexdev.winy.core.util.Constants;
import co.alexdev.winy.core.util.Validator;

public class ActivityLoginViewModel extends ViewModel implements LifecycleObserver {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private Constants.FIREBASE_DATABASE.LOGIN_STATE loginState = Constants.FIREBASE_DATABASE.LOGIN_STATE.NOT_SET;
    public String loginMessage = "";
    public UserCredential userCredential;

    public MutableLiveData<Enum> loginStateEnumLiveData = new MutableLiveData<>();

    //TODO Integrate DAGGER
    public ActivityLoginViewModel() {
        userCredential.email = "";
        userCredential.password = "";
    }

    public void loginUser() {
        loginState = Constants.FIREBASE_DATABASE.LOGIN_STATE.LOADING;

        if (!Validator.isEmailValid(userCredential.email) || !Validator.isPasswordValid(userCredential.password)) {
            loginState = Constants.FIREBASE_DATABASE.LOGIN_STATE.FAILURE;
            loginMessage = Constants.FIREBASE_DATABASE.MESSAGES.ERROR;
            loginStateEnumLiveData.setValue(loginState);
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(userCredential.email, userCredential.password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                loginState = Constants.FIREBASE_DATABASE.LOGIN_STATE.SUCCES;
            } else {
                loginMessage = Objects.requireNonNull(task.getException()).getMessage();
                loginState = Constants.FIREBASE_DATABASE.LOGIN_STATE.FAILURE;
            }
            loginStateEnumLiveData.setValue(loginState);
        });
    }
}
