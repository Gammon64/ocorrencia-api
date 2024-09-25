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

    /**
     * Busca todos os clientes com paginação.
     *
     * @param pageable Paginação.
     * @return Página de clientes.
     */
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente.
     * @return Cliente.
     */
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    /**
     * Busca um cliente pelos Dados da ocorrência.
     *
     * @param formOcorrenciaDTO Dados da ocorrência.
     * @return Cliente encontrado.
     * @throws Exception Cliente não encontrado.
     */
    public Cliente findByOcorrenciaDTO(FormOcorrenciaDTO formOcorrenciaDTO) {
        return clienteRepository.findByNmeClienteAndNroCpf(formOcorrenciaDTO.nmeCliente(), formOcorrenciaDTO.nroCpf()).orElseThrow();
    }

    /**
     * Cria um novo cliente.
     *
     * @param clienteDTO Dados do cliente.
     * @return Cliente criado.
     */
    public Cliente create(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByNroCpf(clienteDTO.nroCpf())) {
            throw new CpfDuplicadoException("CPF já cadastrado: " + clienteDTO.nroCpf());
        }
        Cliente cliente = ClienteMapper.INSTANCE.fromDto(clienteDTO);
        return clienteRepository.save(cliente);
    }

    /**
     * Atualiza um cliente.
     *
     * @param codCliente  ID do cliente.
     * @param clienteDTO Dados do cliente.
     * @return Cliente atualizado.
     */
    public Cliente update(Long codCliente, ClienteDTO clienteDTO) {
        if (clienteRepository.existsByNroCpf(clienteDTO.nroCpf())) {
            throw new CpfDuplicadoException("CPF já cadastrado: " + clienteDTO.nroCpf());
        }
        Cliente cliente = ClienteMapper.INSTANCE.fromDto(clienteDTO);
        cliente.setCodCliente(codCliente);
        return clienteRepository.save(cliente);
    }

    /**
     * Deleta um cliente pelo ID.
     *
     * @param id ID do cliente.
     */
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
