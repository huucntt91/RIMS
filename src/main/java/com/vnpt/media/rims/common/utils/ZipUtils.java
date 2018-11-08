/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Cyano
 */
public class ZipUtils {

    private static Logger logger = LogManager.getLogger(ZipUtils.class);

    public ZipUtils() {

    }

    /**
     * Unzip it and delete
     *
     * @param zipFile input zip file
     * @param outputFolder
     * @param output zip file output folder
     */
    public static void unZipIt(String zipFile, String outputFolder) {

        byte[] buffer = new byte[1024];
        File inputFile = new File(zipFile);
        try {

            //create output directory is not exists
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            //get the zipped file list entry
            try ( //get the zip file content
                    ZipInputStream zis = new ZipInputStream(new FileInputStream(inputFile))) {
                //get the zipped file list entry
                ZipEntry ze = zis.getNextEntry();

                while (ze != null) {
                    if (ze.isDirectory()) {
                        String folderName = ze.getName();
                        File newFolder = new File(outputFolder + File.separator + folderName);
                        newFolder.mkdirs();
                    } else {
                        String fileName = ze.getName();
                        File newFile = new File(outputFolder + File.separator + fileName);
                        logger.info("file unzip : " + newFile.getAbsoluteFile());
                        //create all non exists folders
                        //else you will hit FileNotFoundException for compressed folder
                        new File(newFile.getParent()).mkdirs();
                        FileOutputStream fos = new FileOutputStream(newFile);
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }

                        fos.close();

                    }
                    ze = zis.getNextEntry();
                }

                zis.closeEntry();
            } finally {
                inputFile.delete();
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    /**
     * GunZip it
     */
    public static void gunzipIt(String fileGzip, String output_file) {

        byte[] buffer = new byte[1024];
        GZIPInputStream gzis = null;
        FileOutputStream out = null;
        try {

            gzis = new GZIPInputStream(new FileInputStream(fileGzip));

            out = new FileOutputStream(output_file);

            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            gzis.close();
            out.close();

//            logger.info("Decompress file done!");
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            try {
                if (gzis != null) {
                    gzis.close();
                }
                if (out != null) {
                    out.close();
                }
                File file = new File(fileGzip);
                if (file.exists()) {
                    file.delete();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    // Uses java.util.zip to create zip file
    void zipFolder(final Path sourceFolderPath, Path zipPath) throws Exception {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
            Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    public static void zipMultipleFiles(List<String> srcFiles, String zipFile) {
        try {
            // create byte buffer
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);

            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.size(); i++) {
                File srcFile = new File(srcFiles.get(i));
                if (srcFile.exists() && srcFile.isFile()) {
                    FileInputStream fis = new FileInputStream(srcFile);
                    // begin writing a new ZIP entry, positions the stream to the start of the entry data
                    zos.putNextEntry(new ZipEntry(srcFile.getName()));
                    int length;

                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                    // close the InputStream
                    fis.close();
                }
            }

            // close the ZipOutputStream
            zos.close();

        } catch (IOException ioe) {
            logger.error("Error creating zip file: " + ioe);
        }

    }
}
