package siam.ca.androidchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    ListView userListView;
    ArrayList<String> userList = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    String UserName;

    private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().getRoot();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userListView = (ListView) findViewById(R.id.userListView);
        arrayAdapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,userList);
        userListView.setAdapter(arrayAdapter);

        getUserName();
    }

    private void getUserName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText userName = new EditText(this);

        builder.setView(userName);
        builder.setPositiveButton("Must Enter Name", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (){
                    getUserName();
                }
                else {
                    UserName = userName.getText().toString();
                    addChildEventListener();
                    addUser(UserName);
                }
            }
        });
        builder.show();
    }

    private void addChildEventListener() {
        ChildEventListener childListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                arrayAdapter.add(
                        (String) dataSnapshot.child(UserName).getValue());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        dbr.addChildEventListener(childListener);
    }

    public void addUser(String Username) {

        dbr.child(Username);

        arrayAdapter.notifyDataSetChanged();
    }
}
