package com.javainuse.bootmysqlcrud.service.Impl;

import com.javainuse.bootmysqlcrud.dto.ExternalReniecDto;
import com.javainuse.bootmysqlcrud.service.ExternalApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalApiServiceImpl implements ExternalApiService {
    private final WebClient webClient;

    public ExternalApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public ExternalReniecDto consultarReniec(String dni) {
        String url = "https://api.decolecta.com/v1/reniec/dni?numero=" + dni;
        ExternalReniecDto externalReniecDto = (ExternalReniecDto)this.webClient.get().uri(url, new Object[0]).header("Content-Type", new String[] { "application/json" }).header("Authorization", new String[] { "Bearer sk_9344.ZETheeFkUTXUFM8xHI7dVBmwHvztm9fH" }).retrieve().bodyToMono(ExternalReniecDto.class).block();
        return externalReniecDto;
    }
}
