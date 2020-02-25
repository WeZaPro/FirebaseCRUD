package com.ardian.firebasecruduas;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList <Mahasiswa> daftarmahasiswa;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Mahasiswa> mahasiswas,Context ctx){
        daftarmahasiswa = mahasiswas;
        daftarmahasiswa = mahasiswas;
        context = ctx;
        listener = (DBReadAct)context;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TV_NIM,TV_NAMA,TV_SEMESTER,TV_IPK,TV_JURUSAN;
        LinearLayout list_item;
        ViewHolder(View view){
            super(view);
            TV_NIM = view.findViewById(R.id.nim);
            TV_NAMA = view.findViewById(R.id.nama);
            TV_SEMESTER = view.findViewById(R.id.semester);
            TV_IPK = view.findViewById(R.id.ipk);
            TV_JURUSAN = view.findViewById(R.id.jurusan);
            list_item = view.findViewById(R.id.list_item);
        }
    }
    public interface dataListener{
        void onDeleteData(Mahasiswa mahasiswa, int position);
    }
    dataListener listener;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(V);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final String NIM = daftarmahasiswa.get(position).getNim();
        final String NAMA = daftarmahasiswa.get(position).getNama();
        final String SEMESTER = daftarmahasiswa.get(position).getSemester();
        final String IPK = daftarmahasiswa.get(position).getIpk();
        final String JURUSAN = daftarmahasiswa.get(position).getJurusan();
        holder.TV_NIM.setText(NIM);
        holder.TV_NAMA.setText(NAMA);
        holder.TV_SEMESTER.setText(SEMESTER);
        holder.TV_IPK.setText(IPK);
        holder.TV_JURUSAN.setText(JURUSAN);

        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.list_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.dialog_view);
//                dialog.setTitle("Pilih Aksi");
//                dialog.show();
//                Button editButton = dialog.findViewById(R.id.bt_edit_data);
//                Button delButton = dialog.findViewById(R.id.bt_delete_data);

//                editButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                        context.startActivity(DBCreateAct.getActIntent((Activity) context).putExtra("data", daftarmahasiswa.get(position)));
//                    }
//                });
//                delButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        listener.onDeleteData(daftarmahasiswa.get(position), position);
//                    }
//                });
                final String[] action = {"Update", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Bundle bundle = new Bundle();
                                bundle.putString("dataNIM", daftarmahasiswa.get(position).getNim());
                                bundle.putString("dataNama", daftarmahasiswa.get(position).getNama());
                                bundle.putString("dataSemester", daftarmahasiswa.get(position).getSemester());
                                bundle.putString("dataIPK", daftarmahasiswa.get(position).getIpk());
                                bundle.putString("dataJurusan", daftarmahasiswa.get(position).getJurusan());
                                bundle.putString("getPrimaryKey", daftarmahasiswa.get(position).getKey());
//                                context.startActivity(DBCreateAct.getActIntent((Activity) context).putExtra("data", daftarmahasiswa.get(position)));
                                Intent intent = new Intent(view.getContext(), DBEditAct.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                listener.onDeleteData(daftarmahasiswa.get(position), position);
                                break;
//                            case 2:
//                                Bundle bundle1 = new Bundle();
//                                bundle1.putString("dataNIM", daftarmahasiswa.get(position).getNim());
//                                bundle1.putString("dataNama", listMahasiswa.get(position).getNama());
//                                bundle1.putString("dataSemester", listMahasiswa.get(position).getSemester());
//                                bundle1.putString("dataIPK", listMahasiswa.get(position).getIpk());
//                                bundle1.putString("getPrimaryKey", listMahasiswa.get(position).getKey());
//                                Intent pindah = new Intent(view.getContext(), ShowData.class);
//                                pindah.putExtras(bundle1);
//                                context.startActivity(pindah);
//                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return daftarmahasiswa.size();
    }
}
