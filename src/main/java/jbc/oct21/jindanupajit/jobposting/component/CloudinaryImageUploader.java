package jbc.oct21.jindanupajit.jobposting.component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jbc.oct21.jindanupajit.jobposting.model.CloudinaryImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryImageUploader {
    private static Cloudinary cloudinaryInstance = null;

    private static String folder;
    @Autowired
    public CloudinaryImageUploader(
            @Value("${cloud.name}") String cloudName,
            @Value("${cloud.key}") String apiKey,
            @Value("${cloud.secret}") String apiSecret,
            @Value("${cloud.folder}") String folder
    ) {
        CloudinaryImageUploader.folder = folder;
        cloudinaryInstance = com.cloudinary.Singleton.getCloudinary();

        cloudinaryInstance.config.cloudName = cloudName;
        cloudinaryInstance.config.apiKey = apiKey;
        cloudinaryInstance.config.apiSecret = apiSecret;

    }

    public CloudinaryImage upload(String folder, String public_id, MultipartFile file) {
        try {
            Map result = cloudinaryInstance.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "use_filename", "false",
                            "folder", CloudinaryImageUploader.folder+"/"+folder,
                            "public_id", public_id,
                            "resource_type", "image"
                    ));
            return new CloudinaryImage(result);
        } catch (IOException e) {
            System.out.println("*********** Cloudinary ****************");
            e.printStackTrace();
            System.out.println("***************************************");
            return null;
        }

    }
}
