package com.TLBTECh.proj_bff_agendador_tarefas.infrastructure.client;

import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.TLBTECh.proj_bff_agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                           @RequestHeader(value = "Authorization", required=false) String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(value = "Authorization", required=false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader(value = "Authorization", required=false) String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                        @RequestParam("id") Long id,
                                        @RequestHeader(value = "Authorization", required=false) String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                        @RequestParam("id") Long id,
                                        @RequestHeader(value = "Authorization", required=false) String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(value = "Authorization", required=false) String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(value = "Authorization", required=false) String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);
}
