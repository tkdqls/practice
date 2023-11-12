package com.example.productexample.Service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileService {

    //저장할 경로,파일명,데이터 값
    public String uploadFile(String uploadPath, String originalFileName, byte[] filedata) throws Exception {

        UUID uuid = UUID.randomUUID();    //문자열 생성
        String extendsion = originalFileName.substring(originalFileName.lastIndexOf("."));    //문자열 분리

        String saveFileName = uuid.toString()+extendsion;    //새로운 파일명

        //실질적 저장
        String uploadFullurl = uploadPath+saveFileName;    //저장위치 및 파일명

        FileOutputStream fos = new FileOutputStream(uploadFullurl);
        fos.write(filedata);
        fos.close();

        return saveFileName;    //데이터베이스에 저장할 파일명
    }


    //파일 삭제 (상품을 수정시 기존 파일을 삭제하고 새로운 파일을 저장)
    public void deleteFile(String uploadPath, String fileName) throws Exception {
        String deleteFileName = uploadPath+fileName;

        File deleteFile = new File(deleteFileName);
        if(deleteFile.exists()) {    //파일이 존재하면
            deleteFile.delete();       //파일삭제
        }
    }

}
