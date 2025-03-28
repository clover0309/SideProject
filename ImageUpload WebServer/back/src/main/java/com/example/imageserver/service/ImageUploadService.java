package com.example.imageserver.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // 허용되는 이미지 확장자 목록
    private final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
        "jpg", "jpeg", "png", "gif", "bmp", "webp"
    );

    public String uploadImage(MultipartFile file) throws IOException {
        // 파일 확장자 검증
        String originalFilename = file.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        
        if (!isValidImageFile(extension)) {
            throw new IllegalArgumentException("지원되지 않는 이미지 형식입니다.");
        }

        // 고유한 파일명 생성
        String uniqueFileName = UUID.randomUUID().toString() + "." + extension;
        
        // 업로드 디렉토리 경로 설정
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        
        // 디렉토리가 없으면 생성
        Files.createDirectories(uploadPath);
        
        // 파일 저장 경로
        Path targetLocation = uploadPath.resolve(uniqueFileName);
        
        // 파일 복사
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        
        // 상대 경로 반환 (클라이언트에서 접근 가능한 URL)
        return "/images/" + uniqueFileName;
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1).toLowerCase();
    }

    private boolean isValidImageFile(String extension) {
        return ALLOWED_EXTENSIONS.contains(extension);
    }
}
