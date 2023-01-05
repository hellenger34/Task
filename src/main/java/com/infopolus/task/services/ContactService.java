package com.infopolus.task.services;

import com.infopolus.task.dal.repositories.ContactRepository;
import com.infopolus.task.domain.api.Contact;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    @NonNull
    private final ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAllContacts();
    }
}
