package com.lfg.xlapibackend.controller;

import cn.hutool.core.io.FileUtil;
import com.lfg.xlapibackend.common.BaseResponse;
import com.lfg.xlapibackend.common.ErrorCode;
import com.lfg.xlapibackend.common.ResultUtils;
import com.lfg.xlapibackend.model.enums.FileUploadBizEnum;
import com.lfg.xlapibackend.model.enums.ImageStatusEnum;
import com.lfg.xlapibackend.model.file.UploadFileRequest;
import com.lfg.xlapibackend.model.vo.ImageVo;
import com.lfg.xlapibackend.utils.MinioUtilS;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 上传文件
 */
@Tag(name = "上传文件", description = "上传文件")
@RestController
@RequestMapping("/file")
@Slf4j

public class MinioController {
    final long ONE_M = 1024 * 1024L;
    @Resource
    private MinioUtilS minioUtilS;
    @Value("${minio.host}")
    private String address;
    @Value("${minio.bucket}")
    private String bucketName;

    /**
     * 上传文件
     * @param multipartFile 文件类型
     * @param uploadFileRequest 文件信息
     * @param request 用户信息
     * @return
     */
    @Parameter(name = "multipartFile", description = "文件类型", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "上传文件", description = "上传文件")
    @PostMapping("/upload")
    public BaseResponse<ImageVo> uploadFile(@RequestPart("file") MultipartFile multipartFile, UploadFileRequest
            uploadFileRequest, HttpServletRequest request) {
        //获取biz
        String biz = uploadFileRequest.getBiz();
        //获取上传类型
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        ImageVo imageVo = new ImageVo();
        if (fileUploadBizEnum == null) {
            return uploadError(imageVo, multipartFile, "上传失败,情重试.");
        }
        String result = validFile(multipartFile, fileUploadBizEnum);
        //判断图片是否符合条件
        if (!"success".equals(result)) {
            return uploadError(imageVo, multipartFile, result);
        }
        // 文件重命名
        String uuid = RandomStringUtils.randomAlphanumeric(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        try {
            //上传到minio文件吴福气
            String upload = minioUtilS.upload(multipartFile,filename);
            if (upload != null){
                System.out.println("upload:" + upload);
                imageVo.setName(multipartFile.getOriginalFilename());
                imageVo.setUid(RandomStringUtils.randomAlphanumeric(8));
                imageVo.setStatus(ImageStatusEnum.SUCCESS.getValue());
                imageVo.setUrl(upload);
                // 返回可访问地址
                return ResultUtils.success(imageVo);
            }
            return uploadError(imageVo, multipartFile, "上传失败,情重试");
        } catch (Exception e) {
            log.error("file upload error, filepath = " + filename, e);
            return uploadError(imageVo, multipartFile, "上传失败,情重试");
        }

    }

    private BaseResponse<ImageVo> uploadError(ImageVo imageVo, MultipartFile multipartFile, String message) {
        imageVo.setName(multipartFile.getOriginalFilename());
        imageVo.setUid(RandomStringUtils.randomAlphanumeric(8));
        imageVo.setStatus(ImageStatusEnum.ERROR.getValue());
        return ResultUtils.error(imageVo, ErrorCode.OPERATION_ERROR, message);
    }

    /**
     * 有效文件
     * 校验文件
     *
     * @param fileUploadBizEnum 业务类型
     * @param multipartFile     多部分文件
     */
    private String validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                return "文件大小不能超过 1M";
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp", "jiff").contains(fileSuffix)) {
                return "文件类型错误";
            }
        }
        return "success";
    }
}

