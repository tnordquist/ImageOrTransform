package edu.cnm.deepdive.ironorimgtransform.service;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import edu.cnm.deepdive.ironorimgtransform.TransformApplication;

/**
This has been created to be a singleton. The methods in the instance of this class provide for requesting email and id of the user of the app, setting the client and the account for google sign in.
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

    client = GoogleSignIn.getClient(TransformApplication.getInstance(), options);
  }


  /**
   * Returns the instance regarding the single sign criteria.
   *
   * @return instance of {@link InstanceHolder}
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();
  }

  /**
   * Returns the appropriate client.
   * @return the GoogleSignIn client.
   */
  public GoogleSignInClient getClient() {
    return client;
  }

  /**
   * Sets the appropriate {@link GoogleSignInClient}
   * @param client the set instance of GoogleSignInClient.
   */
  public void setClient(
      GoogleSignInClient client) {
    this.client = client;
  }

  /**
   * Returns the appropriate {@link GoogleSignInAccount}.
   * @return the GoogleSignInAccount client.
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Sets the appropriate {@link GoogleSignInAccount}
   * @param account the set instance of GoogleSignInAccount.
   */
  public void setAccount(
      GoogleSignInAccount account) {
    this.account = account;
  }
}
