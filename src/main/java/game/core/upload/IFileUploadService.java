package game.core.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by pengyi on 2016/4/11.
 */
public interface IFileUploadService {
    UploadResult upload(MultipartFile[] files) throws IOException;

    boolean deleteFile(File file);

    void delete(String roomNo);
}
