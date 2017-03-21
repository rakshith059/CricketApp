package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.MemberItem;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rakshith on 3/11/17.
 */
public class AddNewTeamFragment extends BaseFragment {

    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private List<MemberItem> teamMembersList;
    private Spinner spinnerMem1, spinnerMem2, spinnerMem3, spinnerMem4, spinnerMem5, spinnerMem6;
    private Spinner spinnerMem7, spinnerMem8, spinnerMem9;

    EditText etTeamName;
    EditText etCaptainName, etPlayer2, etPlayer3, etPlayer4, etPlayer5, etPlayer6, etPlayer7, etPlayer8, etPlayer9;
    EditText etCityName, etContactNo, etEmailId;

    TextInputLayout tilTeamName;
    TextInputLayout tilCaptainName, tilPlayer2, tilPlayer3, tilPlayer4, tilPlayer5, tilPlayer6, tilPlayer7, tilPlayer8, tilPlayer9;
    TextInputLayout tilCityName, tilContactNo, tilEmailId;

    String captainRole = Constants.ROLE_BATSMEN,
            mem2Role = Constants.ROLE_BATSMEN,
            mem3Role = Constants.ROLE_BATSMEN,
            mem4Role = Constants.ROLE_BATSMEN,
            mem5Role = Constants.ROLE_BATSMEN,
            mem6Role = Constants.ROLE_KEEPER,
            mem7Role = Constants.ROLE_BOWLER,
            mem8Role = Constants.ROLE_BOWLER,
            mem9Role = Constants.ROLE_BOWLER;

    TextView tvSubmit;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private String teamName, captainName, player2, player3, player4, player5, player6, player7, player8, player9, cityName, contactNumber;
    private String userId;
    private CardView cvUploadImage;
    private ImageView ivUploadImage;
    private TextView tvUploadtext;
    private ProgressBar pbUploadImage;

    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private boolean isImageAdded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_team, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(Constants.DB_TEAM);

        teamMembersList = new ArrayList<>();

        etTeamName = (EditText) view.findViewById(R.id.add_members_et_team_name);
        etCaptainName = (EditText) view.findViewById(R.id.add_members_et_captain_name);
        etPlayer2 = (EditText) view.findViewById(R.id.add_members_et_player2);
        etPlayer3 = (EditText) view.findViewById(R.id.add_members_et_player3);
        etPlayer4 = (EditText) view.findViewById(R.id.add_members_et_player4);
        etPlayer5 = (EditText) view.findViewById(R.id.add_members_et_player5);
        etPlayer6 = (EditText) view.findViewById(R.id.add_members_et_player6);
        etPlayer7 = (EditText) view.findViewById(R.id.add_members_et_player7);
        etPlayer8 = (EditText) view.findViewById(R.id.add_members_et_player8);
        etPlayer9 = (EditText) view.findViewById(R.id.add_members_et_player9);
        etCityName = (EditText) view.findViewById(R.id.add_members_et_city_name);
        etContactNo = (EditText) view.findViewById(R.id.add_members_et_contact_no);
        etEmailId = (EditText) view.findViewById(R.id.add_members_et_email_id);

        cvUploadImage = (CardView) view.findViewById(R.id.add_memeber_cv_uploadImage);
        ivUploadImage = (ImageView) view.findViewById(R.id.add_memeber_iv_uploadImage);
        tvUploadtext = (TextView) view.findViewById(R.id.add_members_tv_upload);
        pbUploadImage = (ProgressBar) view.findViewById(R.id.add_members_pb_upload_image);
        tilTeamName = (TextInputLayout) view.findViewById(R.id.add_members_til_team_name);
        tilCaptainName = (TextInputLayout) view.findViewById(R.id.add_members_til_captain_name);
        tilPlayer2 = (TextInputLayout) view.findViewById(R.id.add_members_til_player2);
        tilPlayer3 = (TextInputLayout) view.findViewById(R.id.add_members_til_player3);
        tilPlayer4 = (TextInputLayout) view.findViewById(R.id.add_members_til_player4);
        tilPlayer5 = (TextInputLayout) view.findViewById(R.id.add_members_til_player5);
        tilPlayer6 = (TextInputLayout) view.findViewById(R.id.add_members_til_player6);
        tilPlayer7 = (TextInputLayout) view.findViewById(R.id.add_members_til_player7);
        tilPlayer8 = (TextInputLayout) view.findViewById(R.id.add_members_til_player8);
        tilPlayer9 = (TextInputLayout) view.findViewById(R.id.add_members_til_player9);
        tilCityName = (TextInputLayout) view.findViewById(R.id.add_members_til_city_name);
        tilContactNo = (TextInputLayout) view.findViewById(R.id.add_members_til_contact_no);
        tilEmailId = (TextInputLayout) view.findViewById(R.id.add_members_til_email_id);

        tvSubmit = (TextView) view.findViewById(R.id.add_members_tv_submit);

        spinnerMem1 = (Spinner) view.findViewById(R.id.add_members_spinner_captain_role);
        spinnerMem2 = (Spinner) view.findViewById(R.id.add_members_spinner_player2_role);
        spinnerMem3 = (Spinner) view.findViewById(R.id.add_members_spinner_player3_role);
        spinnerMem4 = (Spinner) view.findViewById(R.id.add_members_spinner_player4_role);
        spinnerMem5 = (Spinner) view.findViewById(R.id.add_members_spinner_player5_role);
        spinnerMem6 = (Spinner) view.findViewById(R.id.add_members_spinner_player6_role);
        spinnerMem7 = (Spinner) view.findViewById(R.id.add_members_spinner_player7_role);
        spinnerMem8 = (Spinner) view.findViewById(R.id.add_members_spinner_player8_role);
        spinnerMem9 = (Spinner) view.findViewById(R.id.add_members_spinner_player9_role);

        List<String> roles = new ArrayList<>();
        roles.add(Constants.ROLE_BATSMEN);
        roles.add(Constants.ROLE_BOWLER);
        roles.add(Constants.ROLE_KEEPER);
        roles.add(Constants.ROLE_ALL_ROUNDER);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, roles);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerMem1.setAdapter(dataAdapter);
        spinnerMem2.setAdapter(dataAdapter);
        spinnerMem3.setAdapter(dataAdapter);
        spinnerMem4.setAdapter(dataAdapter);
        spinnerMem5.setAdapter(dataAdapter);
        spinnerMem6.setAdapter(dataAdapter);
        spinnerMem7.setAdapter(dataAdapter);
        spinnerMem8.setAdapter(dataAdapter);
        spinnerMem9.setAdapter(dataAdapter);

        setOnItemSelectedForSpinner();

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDataToDB();
            }
        });

        cvUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        return view;
    }

    private void uploadImageToFireBaseStorage() {
        ivUploadImage.setDrawingCacheEnabled(true);
        ivUploadImage.buildDrawingCache(true);
        Bitmap bitmap = ivUploadImage.getDrawingCache();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        ivUploadImage.setDrawingCacheEnabled(false);
        byte[] data = outputStream.toByteArray();

        String path = "teams/" + teamName + ".png";
        StorageReference storageReference = firebaseStorage.getReference(path);

//        StorageMetadata storageMetadata = new StorageMetadata.Builder()

        UploadTask uploadTask = storageReference.putBytes(data);

        pbUploadImage.setVisibility(View.VISIBLE);
        tvUploadtext.setVisibility(View.GONE);
        uploadTask.addOnSuccessListener(mActivity, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pbUploadImage.setVisibility(View.GONE);

                Uri url = taskSnapshot.getDownloadUrl();
                Log.d("Rakshith", "download url " + url.toString());
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(mActivity.getContentResolver(), filePath);
                ivUploadImage.setImageBitmap(bitmap);
//
//                tvUploadtext.setVisibility(View.VISIBLE);
                isImageAdded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setOnItemSelectedForSpinner() {
        spinnerMem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                captainRole = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem2Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem3Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem4Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem5Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem6Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem7Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem8Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMem9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mem9Role = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void submitDataToDB() {
        teamName = etTeamName.getText().toString().trim();
        captainName = etCaptainName.getText().toString().trim();
        player2 = etPlayer2.getText().toString().trim();
        player3 = etPlayer3.getText().toString().trim();
        player4 = etPlayer4.getText().toString().trim();
        player5 = etPlayer5.getText().toString().trim();
        player6 = etPlayer6.getText().toString().trim();
        player7 = etPlayer7.getText().toString().trim();
        player8 = etPlayer8.getText().toString().trim();
        player9 = etPlayer9.getText().toString().trim();
        cityName = etCityName.getText().toString().trim();
        contactNumber = etContactNo.getText().toString().trim();

        if (validate()) {
            if (TextUtils.isEmpty(userId)) {
                createName();
                if (isImageAdded)
                    uploadImageToFireBaseStorage();
            }
//            else {
//                updateName(mainName, teamMembersList);
//            }
        }
    }

    private Boolean validate() {
        if (TextUtils.isEmpty(teamName)) {
            tilTeamName.setError(getResources().getString(R.string.hint_team_name));
            return false;
        }
        if (TextUtils.isEmpty(captainName) || TextUtils.isEmpty(captainRole)) {
            tilTeamName.setError(getResources().getString(R.string.hint_captain_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(captainName, captainRole));
        }
        if (TextUtils.isEmpty(player2) || TextUtils.isEmpty(mem2Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player2, mem2Role));
        }
        if (TextUtils.isEmpty(player3) || TextUtils.isEmpty(mem3Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player3, mem3Role));
        }
        if (TextUtils.isEmpty(player4) || TextUtils.isEmpty(mem4Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player4, mem4Role));
        }
        if (TextUtils.isEmpty(player5) || TextUtils.isEmpty(mem5Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player5, mem5Role));
        }
        if (TextUtils.isEmpty(player6) || TextUtils.isEmpty(mem6Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player6, mem6Role));
        }
        if (TextUtils.isEmpty(player7) || TextUtils.isEmpty(mem7Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player7, mem7Role));
        }
        if (TextUtils.isEmpty(player8) || TextUtils.isEmpty(mem8Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player8, mem8Role));
        }
        if (TextUtils.isEmpty(player9) || TextUtils.isEmpty(mem9Role)) {
            tilTeamName.setError(getResources().getString(R.string.hint_player_name));
            return false;
        } else {
            teamMembersList.add(new MemberItem(player9, mem9Role));
        }
        return true;
    }

    private void createName() {
        if (TextUtils.isEmpty(userId)) {
            userId = databaseReference.push().getKey();
//            databaseReference.child("teamName").child(teamName).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
        }

        final TeamList nameList = new TeamList(teamName, teamMembersList, cityName, contactNumber);//name1, name2, name3, name4, name5, name6, name7, name8, name9, name10);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (!dataSnapshot.child("teamName").hasChild(teamName)) {
                databaseReference.child(userId).setValue(nameList);
                ((HomeActivity) getActivity()).popCurrentFragment();
//                    Log.d("Rakshith", "dont have user " + teamName);
//                    addNameChangeListner();
//
//                } else {¬
//                    Log.d("Rakshith", " have user " + teamName);
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Rakshith", "database error " + databaseError.getMessage());
            }
        });
    }


}
