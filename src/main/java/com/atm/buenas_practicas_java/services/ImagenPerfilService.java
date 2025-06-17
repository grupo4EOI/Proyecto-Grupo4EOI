package com.atm.buenas_practicas_java.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class ImagenPerfilService {

    private final Path raiz;

    public ImagenPerfilService(@Value("${app.upload-dir}") String uploadDir) {
        this.raiz = Paths.get(uploadDir);
        log.info("CONFIGURACIÓN DE DIRECTORIO:");
        log.info("Ruta configurada: {}", uploadDir);
        log.info("Ruta absoluta: {}", raiz.toAbsolutePath());
        log.info("Existe?: {}", Files.exists(raiz));
        log.info("Es directorio?: {}", Files.isDirectory(raiz));
        log.info("Permisos de escritura?: {}", Files.isWritable(raiz));
    }

    public String guardarImagen(MultipartFile archivo) {
        try {
            log.info("Intentando guardar archivo: {}", archivo.getOriginalFilename());
            log.info("Tamaño del archivo: {} bytes", archivo.getSize());

            // Verificar y crear directorio si no existe
            if (!Files.exists(raiz)) {
                log.warn("El directorio no existe, creando: {}", raiz);
                Files.createDirectories(raiz);
            }

            String nuevoNombre = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
            Path destino = raiz.resolve(nuevoNombre);

            log.info("Guardando en: {}", destino);
            Files.copy(archivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            // Verificar que el archivo se creó
            if (Files.exists(destino)) {
                log.info("Archivo guardado correctamente. Tamaño: {} bytes", Files.size(destino));
            } else {
                log.error("Error: El archivo no se creó después de Files.copy()");
            }

            return nuevoNombre;
        } catch (IOException e) {
            log.error("ERROR al guardar imagen", e);
            throw new RuntimeException("Error al guardar la imagen: " + e.getMessage(), e);
        }
    }
}
