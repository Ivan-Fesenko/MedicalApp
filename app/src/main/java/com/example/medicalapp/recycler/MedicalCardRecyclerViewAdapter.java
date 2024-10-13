package com.example.medicalapp.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalapp.R;
import com.example.medicalapp.data.room.MedicalCardEntity;

import java.util.List;

public class MedicalCardRecyclerViewAdapter extends RecyclerView.Adapter<MedicalCardRecyclerViewAdapter.ViewHolder> {

    private List<MedicalCardEntity> medicalCards;

    public MedicalCardRecyclerViewAdapter(List<MedicalCardEntity> medicalCards) {
        this.medicalCards = medicalCards;
    }

    public void updateList(List<MedicalCardEntity> newList) {
        medicalCards = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medical_card_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicalCardEntity medicalCard = medicalCards.get(position);
        holder.nameTextView.setText("Ім'я: " + medicalCard.patientName);  // Додаємо опис для наочності
        holder.ageTextView.setText("Вік: " + medicalCard.age);
        holder.diagnosisTextView.setText("Діагноз: " + medicalCard.diagnosis);
    }

    @Override
    public int getItemCount() {
        return medicalCards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView ageTextView;
        public TextView diagnosisTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            ageTextView = itemView.findViewById(R.id.textViewAge);
            diagnosisTextView = itemView.findViewById(R.id.textViewDiagnosis);
        }
    }
}
