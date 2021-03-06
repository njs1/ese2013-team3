package ch.unibe.scg.team3.wordfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ch.unibe.scg.team3.localDatabase.UserHandler;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends Activity {

	protected UserHandler userHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	public void onCancel(View view) {
		Intent intent = new Intent(getApplicationContext(), PreferencesActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * Invoked when the user presses the OK button. It will try to login the
	 * user.
	 * 
	 * @param view
	 *            The OK button.
	 */
	public void onSignupOk(View view) {
		userHandler = new UserHandler(this);
		EditText username_edit = (EditText) findViewById(R.id.username_edit);
		EditText password_edit = (EditText) findViewById(R.id.password_edit);
		EditText email_edit = (EditText) findViewById(R.id.email_edit);
		final String username = username_edit.getText().toString();
		final String password = password_edit.getText().toString();
		final String email = email_edit.getText().toString();
		final ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		try {
			user.signUpInBackground(new SignUpCallback() {
				public void done(ParseException e) {
					if (e == null) {

						finish();
					} else {

						int code = e.getCode();
						String message = "someting is wrong";
						if (code == ParseException.CONNECTION_FAILED) {
							message = "You need internet connection to sign up";
						}
						if (code == ParseException.USERNAME_MISSING) {
							message = "You need to provide a username";
						}
						if (code == ParseException.USERNAME_TAKEN) {
							message = "This username is allready taken";
						}
						if (code == ParseException.EMAIL_MISSING
								|| code == ParseException.EMAIL_TAKEN
								|| code == ParseException.INVALID_EMAIL_ADDRESS) {
							message = "This email address is either allready assigned to an account or is not a valid address";
						}

						AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);

						alert.setTitle("Error");
						alert.setMessage(message);

						alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
							}
						});

						alert.show();

					}
				}
			});
		} catch (Exception e) {
			String message = "Username seems to be empty. Please fill in before.";
			AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);

			alert.setTitle("Error");
			alert.setMessage(message);

			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				}
			});

			alert.show();
			e.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		onCancel(null);
	}

}
