package com.example.qrapp;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")

public class Controller {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private QRCodeService QrCodeService;
    @PostMapping
    public Contact addContact(@RequestBody Contact contact) throws WriterException,IOException{
            // Create QR text from contact details
            String qrText = contact.getName() + "\n" + contact.getPhone() + "\n" + contact.getEmail();

            // Create a filename for the QR code image
            String fileName = contact.getName().replaceAll(" ", "_") + "_qr.png";

            // Generate the QR code and store the file path
            String qrPath = QrCodeService.generateQRCode(qrText, fileName);

            // Save QR code path into the contact object
            contact.setQrCodeUrl(qrPath);

            // Save contact in the database and return it
            return contactRepository.save(contact);
        }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }



}
