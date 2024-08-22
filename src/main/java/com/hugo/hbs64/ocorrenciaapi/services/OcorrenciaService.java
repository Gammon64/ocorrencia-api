package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.Endereco;
import com.hugo.hbs64.ocorrenciaapi.entities.FotoOcorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.Ocorrencia;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FiltroOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FormOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.enums.StatusOcorrencia;
import com.hugo.hbs64.ocorrenciaapi.exceptions.OcorrenciaFinalizadaException;
import com.hugo.hbs64.ocorrenciaapi.repositories.OcorrenciaRepository;
import com.hugo.hbs64.ocorrenciaapi.repositories.specifications.OcorrenciaSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final ClienteService clienteService;
    private final EnderecoService enderecoService;
    private final FotoOcorrenciaService fotoOcorrenciaService;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository, ClienteService clienteService, EnderecoService enderecoService, FotoOcorrenciaService fotoOcorrenciaService) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.fotoOcorrenciaService = fotoOcorrenciaService;
    }

    public Page<Ocorrencia> findAllByFilter(FiltroOcorrenciaDTO filtroOcorrenciaDTO, Pageable pageable) {
        Page<Ocorrencia> ocorrenciaPage = ocorrenciaRepository.findAll(OcorrenciaSpecs.filtroOcorrenciaSpec(filtroOcorrenciaDTO), pageable);

        // Preenche a URL para download das fotos
        ocorrenciaPage.forEach(ocorrencia ->
                ocorrencia.getFotosOcorrencia().forEach(fotoOcorrencia ->
                        fotoOcorrencia.setUrl(MinioService.getFileUrl(fotoOcorrencia.getDscPathBucket(), fotoOcorrencia.getDscHash()))
                )
        );
        return ocorrenciaPage;
    }

    public Ocorrencia findById(Long id) {
        return ocorrenciaRepository.findById(id).orElseThrow();
    }

    private Ocorrencia buildOcorrenciaByDTO(FormOcorrenciaDTO formOcorrenciaDTO) {
        // Busca o cliente e o endereco
        Cliente cliente = clienteService.findByOcorrenciaDTO(formOcorrenciaDTO);
        Endereco endereco = enderecoService.findByOcorrenciaDTO(formOcorrenciaDTO);

        return new Ocorrencia(cliente, endereco);
    }

    private void saveFotoOcorrencia(Ocorrencia ocorrencia, List<MultipartFile> imagens) {
        if (!CollectionUtils.isEmpty(imagens)) {
            for (MultipartFile imagem : imagens) {
                FotoOcorrencia fotoOcorrencia = fotoOcorrenciaService.createByOcorrenciaAndFile(ocorrencia, imagem);
                ocorrencia.getFotosOcorrencia().add(fotoOcorrencia);
            }
        }
    }

    public Ocorrencia create(FormOcorrenciaDTO formOcorrenciaDTO) {
        Ocorrencia ocorrencia = buildOcorrenciaByDTO(formOcorrenciaDTO);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);

        saveFotoOcorrencia(ocorrencia, formOcorrenciaDTO.imagens());

        return ocorrencia;
    }

    public Ocorrencia update(Long codOcorrencia, FormOcorrenciaDTO formOcorrenciaDTO) {
        if (ocorrenciaRepository.existsByCodOcorrenciaAndStaOcorrencia(codOcorrencia, StatusOcorrencia.FINALIZADA)) {
            throw new OcorrenciaFinalizadaException("Ocorrência finalizada não pode ser alterada");
        }
        Ocorrencia ocorrencia = buildOcorrenciaByDTO(formOcorrenciaDTO);
        ocorrencia.setCodOcorrencia(codOcorrencia);
        ocorrencia = ocorrenciaRepository.save(ocorrencia);

        saveFotoOcorrencia(ocorrencia, formOcorrenciaDTO.imagens());
        return ocorrencia;
    }

    public Ocorrencia finalizarOcorrencia(Long codOcorrencia) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(codOcorrencia).orElseThrow();
        ocorrencia.setStaOcorrencia(StatusOcorrencia.FINALIZADA);

        return ocorrenciaRepository.save(ocorrencia);
    }

    public void delete(Long codOcorrencia) {
        ocorrenciaRepository.deleteById(codOcorrencia);
    }

}
