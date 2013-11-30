package ch.unibe.scg.team3.wordfinder;

import java.util.ArrayList;
import java.util.List;

import ch.unibe.scg.team3.parseQueryAdapter.FriendRequestsAdapter;
import ch.unibe.scg.team3.parseQueryAdapter.FriendsAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ReceivedRequestsActivity extends Activity {
	private ArrayList<ParseUser> requests;
	ListView requestListView;
	FriendRequestsAdapter requestAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_received_requests);
		requests = new ArrayList<ParseUser>();
	}
	@Override
	protected void onResume() {
		super.onResume();

		getFriendRequests();
		requestAdapter = new FriendRequestsAdapter(this, R.id.request_list, requests);
		
		requestListView = (ListView) findViewById(R.id.request_list);	
		requestListView.setAdapter(requestAdapter);
	}

	private void getFriendRequests() {
		ParseUser me = ParseUser.getCurrentUser();
		if (me != null) {
			String id = me.getObjectId();

			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"Request");
			query.whereEqualTo("subject_id", id);
			query.findInBackground(new FindCallback<ParseObject>(){

				@Override
				public void done(List<ParseObject> requestList, ParseException arg1) {
					requests.clear();
					for(ParseObject request : requestList){
						
						String initiatorId = request.getString("initiator_id");
						ParseQuery<ParseUser> query = ParseUser.getQuery();
						
						try {
							query.get(initiatorId);
							requests.add(query.getFirst());
							
							requestListView.setAdapter(requestAdapter);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
}
