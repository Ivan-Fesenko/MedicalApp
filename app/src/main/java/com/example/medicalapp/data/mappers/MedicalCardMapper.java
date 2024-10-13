package com.example.medicalapp.data.mappers;

import com.example.medicalapp.MedicalCard;
import com.example.medicalapp.data.room.MedicalCardEntity;

import java.util.ArrayList;
import java.util.List;

public class MedicalCardMapper {

    public static MedicalCardEntity mapToEntity(MedicalCard domainModel) {
        return new MedicalCardEntity(domainModel.getPatientName(), domainModel.getAge(), domainModel.getDiagnosis());
    }

    public static MedicalCard mapToDomainModel(MedicalCardEntity entity) {
        return new MedicalCard(entity.patientName, entity.age, entity.diagnosis);
    }

    public static List<MedicalCard> mapToDomainModelList(List<MedicalCardEntity> entityList) {
        List<MedicalCard> domainList = new ArrayList<>();
        for (MedicalCardEntity entity : entityList) {
            domainList.add(mapToDomainModel(entity));
        }
        return domainList;
    }
}
