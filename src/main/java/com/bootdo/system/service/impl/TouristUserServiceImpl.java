package com.bootdo.system.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.ImageUtils;
import com.bootdo.system.dao.TouristUserDao;
import com.bootdo.system.domain.TouristUserDO;
import com.bootdo.system.service.TouristUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TouristUserServiceImpl implements TouristUserService {
    @Autowired
    private TouristUserDao touristUserDao;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private FileService sysFileService;

    @Override
    public TouristUserDO get(Integer id) {
        return touristUserDao.get(id);
    }

    @Override
    public List<TouristUserDO> list(Map<String, Object> map) {
        return touristUserDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return touristUserDao.count(map);
    }

    @Override
    public int save(TouristUserDO touristUser) {
        return touristUserDao.save(touristUser);
    }

    @Override
    public int update(TouristUserDO touristUser) {
        return touristUserDao.update(touristUser);
    }

    @Override
    public int remove(Integer id) {
        return touristUserDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return touristUserDao.batchRemove(ids);
    }


    @Override
    public TouristUserDO updatePersonalImg(MultipartFile file, String avatar_data, Integer id) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        //获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        String[] str = avatar_data.split(",");
        //获取截取的x坐标
        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
        //获取截取的y坐标
        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
        //获取截取的高度
        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
        //获取截取的宽度
        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
        //获取旋转的角度
        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        try {
            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(rotateImage, prefix, out);
            //转换后存入数据库
            byte[] b = out.toByteArray();
            FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            throw new Exception("图片裁剪错误！！");
        }
        if (sysFileService.save(sysFile) > 0) {
            TouristUserDO userDO = new TouristUserDO();
            userDO.setId(id);
            userDO.setHeadUrl(sysFile.getUrl());
            touristUserDao.update(userDO);
        }
        TouristUserDO touristUserDO = touristUserDao.get(id);
        return touristUserDO;

    }

}
