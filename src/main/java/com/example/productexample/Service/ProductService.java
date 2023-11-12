package com.example.productexample.Service;

import com.example.productexample.DTO.ProductDTO;
import com.example.productexample.Entity.ProductEntity;
import com.example.productexample.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    @Value("${imgUploadLocation")
    private String imgUploadLocation;

    private final ProductRepository productRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper = new ModelMapper();

    public void remove(Integer id) throws Exception{
        ProductEntity productEntity = productRepository
                .findById(id).orElseThrow();
        fileService.deleteFile(imgUploadLocation, productEntity.getProduct_img());

        productRepository.deleteById(id);
    }

    public ProductDTO detail(Integer id) throws Exception {
        ProductEntity productEntity = productRepository
                .findById(id)
                .orElseThrow();
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);

        return productDTO;
    }

    public List<ProductDTO> list() throws Exception{
        List<ProductEntity> productEntities = productRepository.findAll();

        List<ProductDTO> productDTOS = Arrays.asList(
                modelMapper.map(productEntities, ProductDTO[].class)
        );

        return productDTOS;
    }

    public void register(ProductDTO productDTO, MultipartFile imgFile) throws Exception {
        String originalFileName = imgFile.getOriginalFilename();    //저장할 파일명
        String newFileName = "";    //새로 만든 파일명

        if(originalFileName != null) {
            newFileName = fileService.uploadFile(
                    imgUploadLocation,
                    originalFileName,
                    imgFile.getBytes()
            );
        }
        productDTO.setProduct_img(newFileName); //새로운 파일명을 재등록
        //변환
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);

        productRepository.save(productEntity);
    }

    public void modify(ProductDTO productDTO, MultipartFile imgFile) throws Exception {
        //기존파일 삭제
        ProductEntity productEntity = productRepository
                .findById(productDTO.getProduct_id())
                .orElseThrow();
        String deleteFile = productEntity.getProduct_img();

        String originalFileName = imgFile.getOriginalFilename();    //저장할 파일명
        String newFileName = "";    //새로 만든 파일명

        if(originalFileName.length() != 0) { //파일이 존재하면
            //바꿀 파일이 있으면
            if (deleteFile.length() != 0) { //기존파일이 있으면
                fileService.deleteFile(imgUploadLocation, deleteFile);
            }

            newFileName = fileService.uploadFile(
                    imgUploadLocation,
                    originalFileName,
                    imgFile.getBytes()
            );
            productDTO.setProduct_img(newFileName); //새로운 파일명을 재등록
        }
        productDTO.setProduct_img(productEntity.getProduct_img());

        //변환
        ProductEntity data = modelMapper.map(productDTO, ProductEntity.class);

        productRepository.save(data);
    }
}
