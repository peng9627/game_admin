package game.core.upload;

import game.core.util.CoreStringUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengyi on 2016/4/11.
 */
public class FileUploadService implements IFileUploadService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DOT = ".";

    private FileUploadConfig fileUploadConfig;

    public void setFileUploadConfig(FileUploadConfig fileUploadConfig) {
        this.fileUploadConfig = fileUploadConfig;
    }

    @Override
    public UploadResult upload(MultipartFile[] files) throws IOException {
        List<Object> resultFiles = new ArrayList<Object>();
        File folder = new File(fileUploadConfig.getPath(), fileUploadConfig.getFolder());//创建工作目录
        folder.mkdirs();

        String url = fileUploadConfig.getDomainName() + fileUploadConfig.getFolder();
        for (MultipartFile file : files) {
            String message = null;
            if (file.isEmpty()) {
                message = "文件未上传";
                logger.info("文件未上传");
            }

            //返回客户端的文件系统中的原始文件名
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            String type = (fileName.substring(fileName.lastIndexOf(DOT) + 1)).toLowerCase();
            //获取文件大小
            long fileSize = file.getSize();

            if (fileUploadConfig.getMaxSize() < fileSize) {
                message = "文件过大，无法上传！";
                logger.info("上传文件[{}]大小[{}]超过了[{}]", fileName, fileSize, fileUploadConfig.getMaxSize());
            }

            if (!fileUploadConfig.getType().contains(type)) {
                message = "文件类型错误！";
                logger.info("上传文件[{}]类型[{}]错误", fileName, type);
            }
            if (CoreStringUtils.isEmpty(message)) {
                String filename = file.getOriginalFilename();
                String roomNo = filename.substring(0, filename.indexOf("_"));

                File file1 = new File(folder, roomNo);
                file1.mkdirs();
                //原文件
                File saveFile = new File(file1, CoreStringUtils.join(DOT, filename.substring(0, filename.indexOf(".")), type));
                file.transferTo(saveFile);

                logger.info("上传文件[{}]成功", saveFile.getAbsolutePath());

                resultFiles.add(new UploadSuccess(saveFile.getName(), fileSize,
                        url + saveFile.getName(),
                        CoreStringUtils.join(null, "/upload/delete?roomNo=", roomNo)));
            } else {
                resultFiles.add(new UploadFailure(file.getOriginalFilename(), message));
            }
        }
        return new UploadResult(resultFiles.toArray());
    }

    /**
     * @param roomNo 全路径图片地址
     */
    @Override
    public void delete(String roomNo) {
        try {
            File folder = new File(fileUploadConfig.getPath(), fileUploadConfig.getFolder());
            File file = new File(folder, roomNo);
            this.deleteFile(file);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public boolean deleteFile(File file) {
        boolean flag = FileUtils.deleteQuietly(file);
        if (flag) {
            logger.info("删除文件[{}]成功", file.getAbsolutePath());
        } else {
            logger.error("删除文件[{}]失败", file.getAbsolutePath());
        }
        return flag;
    }
}
