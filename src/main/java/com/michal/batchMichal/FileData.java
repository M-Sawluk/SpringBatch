package com.michal.batchMichal;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FileData {
    @NotNull
    private MultipartFile file;

    public FileData() {
    }

    public FileData(MultipartFile file) {

        this.file = file;
    }
}