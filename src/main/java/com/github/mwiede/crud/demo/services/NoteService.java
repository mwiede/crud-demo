package com.github.mwiede.crud.demo.services;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.github.mwiede.crud.demo.model.Customer;
import com.github.mwiede.crud.demo.model.Note;

@ApplicationScoped
@Transactional
public class NoteService {

    @PersistenceContext
    EntityManager entityManager;

    public void delete(Long id) {
        Note note = entityManager.find(Note.class, id);
        if (note != null) {
            entityManager.remove(note);
        }
    }

    public Note createNew(Customer currentCustomer, String text) {
        Note note = new Note();
        note.setCustomer(currentCustomer);
        note.setNote(text);
        entityManager.persist(note);
        return note;
    }

    public Note update(Note currentNoteEntity) {
        return entityManager.merge(currentNoteEntity);
    }
}
