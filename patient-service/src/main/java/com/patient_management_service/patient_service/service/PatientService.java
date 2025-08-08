package com.patient_management_service.patient_service.service;

import com.patient_management_service.patient_service.dto.PatientRequestDTO;
import com.patient_management_service.patient_service.dto.PatientResponseDTO;
import com.patient_management_service.patient_service.exception.EmailAlreadyExistsException;
import com.patient_management_service.patient_service.mapper.PatientMapper;
import com.patient_management_service.patient_service.model.Patient;
import com.patient_management_service.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient with this email already exists: " + patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
