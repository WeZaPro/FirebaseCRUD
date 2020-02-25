package com.ardian.firebasecruduas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBCreateAct extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private Button Submit;
    private EditText Nama,NIM,Semester,IPK;
    private Spinner Jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbcreate);

        Nama = findViewById(R.id.nama_mhs);
        NIM = findViewById(R.id.nim_mhs);
        Semester = findViewById(R.id.semester_mhs);
        IPK = findViewById(R.id.ipk_mhs);
        Submit = findViewById(R.id.bt_submit);
        Jurusan= findViewById(R.id.jurusan);

        databaseReference = FirebaseDatabase.getInstance().getReference();
//        final Mahasiswa mahasiswa=(Mahasiswa) getIntent().getSerializableExtra("data");
//        if(mahasiswa!=null)
//        {
//
//            NIM.setText("");
//            Nama.setText("");
//            Semester.setText("");
//            IPK.setText("");
//            Jurusan.getSelectedItem().toString();
//
//            Submit.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//
//                public void onClick(View view) {
//
//                    mahasiswa.setNim(NIM.getText().toString());
//                    mahasiswa.setNama(Nama.getText().toString());
//                    mahasiswa.setNama(Semester.getText().toString());
//                    mahasiswa.setNama(IPK.getText().toString());
//                    mahasiswa.setJurusan(Jurusan.getSelectedItem().toString());
//
//                    updateMahasiswa(mahasiswa);
//
//                }
//            });
//        }
//        else{
//
//        }

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty(NIM.getText().toString()) || !isEmpty(Nama.getText().toString()) || !isEmpty(Semester.getText().toString())|| !isEmpty(IPK.getText().toString())){
                    submitMahasiswa(new Mahasiswa(NIM.getText().toString(),Nama.getText().toString(),Semester.getText().toString()
                    ,IPK.getText().toString(),Jurusan.getSelectedItem().toString()));
                }
                else {
                    Toast.makeText(DBCreateAct.this,"Data Mahasiswa Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
//    private void updateMahasiswa(Mahasiswa mahasiswa){
//        String dapatkey = getIntent().getExtras().getString("getPrimaryKey");
//        databaseReference.child("Mahasiswa")
//                .child(mahasiswa.getKey())
//                .setValue(mahasiswa)
//                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
//
//                    @Override
//
//                    public void onSuccess(Void aVoid) {
//
//                        Snackbar.make(findViewById(R.id.bt_submit), "Data Berhasil di Update", Snackbar.LENGTH_LONG).setAction("Oke"
//                                , new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        finish();
//                                    }
//                                }).show();
//                    }
//
//                });
//    }
    private void submitMahasiswa(Mahasiswa mahasiswa){
        databaseReference.child("Mahasiswa").push().setValue(mahasiswa).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                NIM.setText("");
                Nama.setText("");
                Semester.setText("");
                IPK.setText("");
                Jurusan.getSelectedItem().toString();
                Toast.makeText(DBCreateAct.this,"Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DBCreateAct.class);
    }

}
