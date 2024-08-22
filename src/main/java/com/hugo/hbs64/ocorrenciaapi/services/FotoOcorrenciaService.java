package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.FotoOcorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.Ocorrencia;
import com.hugo.hbs64.ocorrenciaapi.repositories.FotoOcorrenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class FotoOcorrenciaService {

    private final FotoOcorrenciaRepository fotoOcorrenciaRepository;

    public FotoOcorrenciaService(FotoOcorrenciaRepository fotoOcorrenciaRepository) {
        this.fotoOcorrenciaRepository = fotoOcorrenciaRepository;
    }

    public FotoOcorrencia createByOcorrenciaAndFile(Ocorrencia ocorrencia, MultipartFile file) {
        String bucketName = "fotos-ocorrencias";
        // Enviar o arquivo para o Min.io
        MinioService.uploadFile(bucketName, file);

        FotoOcorrencia fotoOcorrencia = new FotoOcorrencia(ocorrencia, bucketName, Objects.requireNonNull(file.getOriginalFilename()));
        return fotoOcorrenciaRepository.save(fotoOcorrencia);
    }

}
