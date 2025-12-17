    package com.example.qrapp;

    import com.google.zxing.BarcodeFormat;
    import com.google.zxing.WriterException;
    import com.google.zxing.client.j2se.MatrixToImageWriter;
    import com.google.zxing.qrcode.QRCodeWriter;
    import org.springframework.stereotype.Service;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.FileSystems;
    import java.nio.file.Path;
    @Service
    public class QRCodeService {
        public String generateQRCode(String text,String fileName) throws WriterException,IOException{
            QRCodeWriter qrcodewrite=new QRCodeWriter();
            var bitMatrix=qrcodewrite.encode(text,BarcodeFormat.QR_CODE,300,300);
            String filePath = "src/main/resources/static/" + fileName;
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);
            return "/static" +fileName;
        }
    }
