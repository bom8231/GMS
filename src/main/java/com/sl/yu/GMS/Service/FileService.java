package com.sl.yu.GMS.Service;

import com.sl.yu.GMS.model.attachtable;
import com.sl.yu.GMS.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Value("${file.dir}")
    private String fileDir;

    public Long saveFile(MultipartFile files,@ModelAttribute attachtable attach) throws IOException {
        if (files.isEmpty()) {
            return null;
        }

        // 원래 파일 이름 추출
        String originName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)

        assert originName != null;
        String extension = originName.substring(originName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;

        //정보들 저장
        attach.setOriginName(originName);
        attach.setSavedName(savedName);
        attach.setSavedPath(savedPath);

        // 실제로 로컬에 uuid를 파일명으로 저장
        files.transferTo(new File(savedPath));

        // 데이터베이스에 파일 정보 저장
        attachtable savedFile = fileRepository.save(attach);

        return savedFile.getId();
    }
}