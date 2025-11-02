package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.dto.ExternalReniecDto;
import com.javainuse.bootmysqlcrud.dto.ExternalSunatDto;

public interface ExternalApiService {
    ExternalReniecDto consultarReniec(String paramString);
    ExternalSunatDto consultarSunat(String paramString);
}
