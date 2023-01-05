package com.infopolus.task.controllers;

import com.infopolus.task.domain.api.Contact;
import com.infopolus.task.services.ContactService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    @NonNull
    private final ContactService contactService;

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }
}
