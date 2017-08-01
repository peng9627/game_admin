package niuniu.interfaces;

import niuniu.core.upload.IFileUploadService;
import niuniu.core.upload.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by pengyi on 2016/4/11.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public UploadResult upload(@RequestParam MultipartFile[] file) throws IOException {
        return fileUploadService.upload(file);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public void deleteTemp(String roomNo) {
        fileUploadService.delete(roomNo);
    }

}
