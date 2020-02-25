package com.ardian.firebasecruduas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBEditAct extends AppCompatActivity {
    private EditText nimBaru, namaBaru, SemesterNew,IPKNew;
    private Spinner JurusanBaru;
    private Button update;
    private DatabaseReference database;
    private String cekNIM, cekNama, cekSemester,cekIPK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbedit);

        nimBaru = findViewById(R.id.nim_mhs_edit);
        namaBaru = findViewById(R.id.nama_mhs_edit);
        SemesterNew= findViewById(R.id.semester_mhs_edit);
        IPKNew= findViewById(R.id.ipk_mhs_edit);
        JurusanBaru= findViewById(R.id.jurusan_edit);
        update = findViewById(R.id.bt_submit);

        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cekNIM = nimBaru.getText().toString();
                cekNama = namaBaru.getText().toString();
                cekSemester = SemesterNew.getText().toString();
                cekIPK = IPKNew.getText().toString();


                if(isEmpty(cekNIM) || isEmpty(cekNama) || isEmpty(cekSemester)||isEmpty(cekIPK)){
                    Toast.makeText(DBEditAct.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {

                    Mahasiswa setMahasiswa = new Mahasiswa();
                    setMahasiswa.setNim(nimBaru.getText().toString());
                    setMahasiswa.setNama(namaBaru.getText().toString());
                    setMahasiswa.setSemester(SemesterNew.getText().toString());
                    setMahasiswa.setIpk(IPKNew.getText().toString());
                    setMahasiswa.setJurusan(JurusanBaru.getSelectedItem().toString());
                    updateMahasiswa(setMahasiswa);
                }
            }
        });
    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }
    private void getData(){

        final String getNIM = getIntent().getExtras().getString("dataNIM");
        final String getNama = getIntent().getExtras().getString("dataNama");
        final String getSemester = getIntent().getExtras().getString("dataSemester");
        final String getIPK = getIntent().getExtras().getString("dataIPK");
        final String getJurusan = getIntent().getExtras().getString("dataJurusan");
        nimBaru.setText(getNIM);
        namaBaru.setText(getNama);
        SemesterNew.setText(getSemester);
        IPKNew.setText(getIPK);
        JurusanBaru.getSelectedItem();
    }
    private void updateMahasiswa(Mahasiswa mahasiswa){
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Mahasiswa")
                .child(getKey)
                .setValue(mahasiswa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        nimBaru.setText("");
                        namaBaru.setText("");
                        SemesterNew.setText("");
                        IPKNew.setText("");
                        JurusanBaru.getSelectedItem().toString();
                        Toast.makeText(DBEditAct.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}
