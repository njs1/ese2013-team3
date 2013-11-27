package ch.unibe.scg.team3.parseQueryAdapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.unibe.scg.team3.wordfinder.R;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class FriendRequestsAdapter extends ArrayAdapter<ParseUser> {

	public FriendRequestsAdapter(Context context, int resource, List<ParseUser> objects) {
		super(context, resource, objects);
	}

	// public FriendRequestsAdapter(Context context,
	// com.parse.ParseQueryAdapter.QueryFactory<ParseUser> queryFactory) {
	// super(context, queryFactory);
	// }



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = View.inflate(getContext(), R.layout.friend_list_item, null);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.friend_username);
		ParseUser friend = getItem(position);
		name.setText(friend.getUsername());
		return convertView;
	}

	// @Override
	// public View getItemView(ParseUser request, View v, ViewGroup parent) {
	// if (v == null) {
	// v = View.inflate(getContext(), R.layout.friend_list_item, null);
	// }
	// super.getItemView(request, v, parent);
	//
	// TextView name = (TextView) v.findViewById(R.id.friend_username);
	//
	// name.setText(request.getUsername());
	//
	// return v;
	// }

}
