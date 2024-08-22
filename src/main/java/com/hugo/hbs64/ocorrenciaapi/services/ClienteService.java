package com.hugo.hbs64.ocorrenciaapi.services;

import com.hugo.hbs64.ocorrenciaapi.entities.Cliente;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.ClienteDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.dtos.FormOcorrenciaDTO;
import com.hugo.hbs64.ocorrenciaapi.entities.mappers.ClienteMapper;
import com.hugo.hbs64.ocorrenciaapi.exceptions.CpfDuplicadoException;
import com.hugo.hbs64.ocorrenciaapi.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    public Cliente findByOcorrenciaDTO(FormOcorrenciaDTO formOcorrenciaDTO) {
        return clienteRepository.findByNmeClienteAndNroCpf(formOcorrenciaDTO.nmeCliente(), formOcorrenciaDTO.nroCpf()).orElseThrow();
    }

    public Cliente create(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByNroCpf(clienteDTO.nroCpf())) {
            throw new CpfDuplicadoException("CPF já cadastrado: " + clienteDTO.nroCpf());
        }
        Cliente cliente = ClienteMapper.INSTANCE.fromDto(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long codCliente, ClienteDTO clienteDTO) {
        if (clienteRepository.existsByNroCpf(clienteDTO.nroCpf())) {
            throw new CpfDuplicadoException("CPF já cadastrado: " + clienteDTO.nroCpf());
        }
        Cliente cliente = ClienteMapper.INSTANCE.fromDto(clienteDTO);
        cliente.setCodCliente(codCliente);
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
