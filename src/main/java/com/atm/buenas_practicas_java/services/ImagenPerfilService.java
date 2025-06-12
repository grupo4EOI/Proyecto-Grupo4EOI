package com.atm.buenas_practicas_java.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImagenPerfilService {

    private final Path raiz = Paths.get("uploads");

    public String guardarImagen(MultipartFile archivo) {
        try {
            Files.createDirectories(raiz);
            String nuevoNombre = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
            Files.copy(archivo.getInputStream(), raiz.resolve(nuevoNombre), StandardCopyOption.REPLACE_EXISTING);
            return nuevoNombre;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar el archivo", e);
        }
    }
}

