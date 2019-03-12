package edu.cnm.deepdive.ironorimgtransform.service;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import edu.cnm.deepdive.ironorimgtransform.TransformApplication;

/*
This has been created to be a singleton.
 */
public class GoogleSignInService {

  private GoogleSignInClient client;
  private GoogleSignInAccount account;

  private GoogleSignInService() {// made the constructor private so no other code creates an instance of this class.
    GoogleSignInOptions options = new GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        .build();

    client= GoogleSignIn.getClient(TransformApplication.getInstance(),options);
  }


  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();
  }

  public GoogleSignInClient getClient() {
    return client;
  }

  public void setClient(
      GoogleSignInClient client) {
    this.client = client;
  }

  public GoogleSignInAccount getAccount() {
    return account;
  }

  public void setAccount(
      GoogleSignInAccount account) {
    this.account = account;
  }
}
